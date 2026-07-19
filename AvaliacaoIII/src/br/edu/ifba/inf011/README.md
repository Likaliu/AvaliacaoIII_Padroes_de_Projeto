# Avaliação III — INF011 Padrões de Projeto

Refatoração do framework de streaming para conectar o domínio técnico
(`Timeline`, reaproveitado das avaliações I e II) ao domínio comercial
(`Filme`, `Serie`, `Episodio`, `Pacote`), endereçando os dois cenários do
enunciado com os padrões descritos abaixo.

**Integrantes:** Jaqueline Gabriela, Liane Barbosa e Wheston Ramos.

---

## Questão I

### Padrão: Composite

**Justificativa:** como carrinho de compras precisa calcular preço e duração
total tratando um filme avulso e um pacote complexo (aninhados) **exatamente da mesma forma**.
O padrão Composite resolve isso definindo um contrato único (`ItemVendaComponent`)
que tanto folhas (`Filme`, `Serie`) quanto o próprio composto (`Pacote`)
implementam, permitindo somar preço/duração recursivamente sem o cliente
precisar diferenciar os casos.

Classe | Papel (Participante):

`ItemVendaComponent` | **Component** — contrato único (`getTitulo`, `getPreco`, `getDuracao`).

`Filme` | **Leaf** — item de venda sem filhos.

`Serie` | **Leaf** — uma série inteira empacotável como um único item.

`Pacote` | **Composite** — contém `List<ItemVendaComponent>` (outros `Pacote`, `Filme` ou `Serie`); soma preço/duração recursivamente e aplica os 10% de desconto da Black Friday sobre o total.

`CarrinhoDeCompras` | **Client** — opera somente via `ItemVendaComponent`, sem saber se está lidando com um item avulso ou um pacote aninhado |

### Padrão: Builder

**Justificativa:** como criação manual de pacotes aninhados gerava construtores
gigantescos e ilegíveis (`new Pacote("SciFi", new Pacote("Matrix", ...), new
Serie(...), ...)`). O Builder resolve isso com uma montagem fluente, passo
a passo, inclusive permitindo aninhar outros pacotes já construídos.

Classe | Papel (Participante):

`PacoteBuilder` | **Builder** — monta a lista de itens via `comFilme()`, `comSerie()`, `comPacote()`, `comItem()`, e produz o `Pacote` final em `build()` .

`Pacote` | **Product** — objeto complexo produzido pelo builder.

---

## Questão II

### Padrão: Visitor

**Justificativa:** toda nova operação analítica pedida pela equipe de dados
(Largura de Banda, Relatório de Nomes, Exportador XML) exigia alterar
diretamente as classes de conteúdo (`MP3`, `Video`, `Episodio`, `Filme`),
violando o Princípio Aberto/Fechado (cada mudança arriscava quebrar código
já funcionando) e o Princípio da Responsabilidade Única (cada classe
acumulava lógica de domínio + lógica analítica). O Visitor resolve isso
extraindo cada operação para uma classe própria: as classes de conteúdo só
sabem "aceitar um visitante" (`accept`), sem precisam ser
alteradas quando uma nova operação analítica surge.

Classe | Papel (Participante):

`PlaylistItem` | **Element** — contrato único (`accept(PlaylistVisitor)`) implementado por todo item de playlist.

`PlaylistVisitor` | **Visitor** — interface com um `visit()` por tipo concreto de elemento.

`LarguraDeBandaVisitor` | **Concrete Visitor** — soma o consumo de banda da playlist.

`RelatorioNomesVisitor` | **Concrete Visitor** — extrai o nome de cada elemento da playlist.

`ExportadorXmlVisitor` | **Concrete Visitor** — monta a representação XML da playlist. 

`Filme`, `Episodio`, `Serie`, `Pacote`, `MP3`, `Video` | **Concrete Element** — cada um implementa `accept()` chamando `visitor.visit(this)`.
- itens do catálogo (`Filme`/`Episodio`/`Serie`/`Pacote`) e itens "sem relação" enviados pelo próprio cliente (`MP3`/`Video`) são tratados de forma uniforme.

`Playlist` | **Object Structure** — coleção heterogênea que percorre os itens aplicando o visitor recebido.
- `getBandaTotal()`, `getRelatorioNomes()` e `toXML()` são atalhos de conveniência que instanciam o visitor correspondente.

---

## Observações

- `ItemVendaComponent` (Composite, Questão I) **estende** `PlaylistItem`
  (Element, Questão II): no domínio, tudo que é vendável também deve poder
  entrar numa playlist e ser exportado/analisado. Isso permite que
  `Pacote` percorra seus próprios itens chamando `accept()` diretamente ao
  recursar para exportação XML, sem casts.
- `canva`, `renderer`, `encoder`, `track`,
  `avaliacao1.*` e `avaliacao2.*` são o motor técnico reaproveitado das
  avaliações I e II (Builder de `Timeline`, Prototype/`Forkable`, Decorator
  de trailers/créditos, Adapter de `HDDBinaryReader`) — apenas dão suporte às classes comerciais.

---

## Qualidade Arquitetural e SOLID

- **Open/Closed Principle:** novas operações sobre playlist são adicionadas por
  novos `PlaylistVisitor`, sem alterar `Filme`, `Serie`, `Pacote`, `MP3` ou
  `Video`. Para venda, novos itens podem implementar `ItemVendaComponent` sem
  alterar `CarrinhoDeCompras`.
- **Single Responsibility Principle:** regras de composição ficam em `Pacote`,
  construção fluente em `PacoteBuilder`, agregação de compra em
  `CarrinhoDeCompras` e operações analíticas/exportadoras nos Visitors.
- **Liskov Substitution Principle:** `CarrinhoDeCompras` e `Pacote` dependem do
  contrato `ItemVendaComponent`, tratando folhas e compostos de forma uniforme.
- **Interface Segregation Principle:** `PlaylistItem` contém apenas `accept`, e
  `ItemVendaComponent` adiciona somente as operações necessárias para venda
  (`getTitulo`, `getPreco`, `getDuracao`).
- **Dependency Inversion Principle:** clientes trabalham principalmente com
  abstrações (`ItemVendaComponent`, `PlaylistItem`, `PlaylistVisitor`) em vez de
  tipos concretos.

### Cuidados de Clean Code adotados

- Coleções internas de `Pacote` e `Serie` não são expostas para alteração direta.
- `PacoteBuilder` cria pacotes usando cópia defensiva dos itens adicionados.
- Entradas inválidas (`null`, títulos vazios, preços negativos e tamanhos
  negativos) são rejeitadas cedo por exceções claras.
- Números mágicos relevantes foram nomeados como constantes, como o fator de
  desconto de pacotes/séries.
- `ExportadorXmlVisitor` escapa caracteres especiais ao gerar XML.

### Trade-offs conhecidos

- O `Visitor` facilita adicionar novas operações, mas exige alterar a interface
  `PlaylistVisitor` e os visitors concretos se um novo tipo de item de playlist
  for criado. Essa escolha é adequada porque o enunciado enfatiza novas operações
  analíticas sobre um conjunto conhecido de tipos.
- O desconto está mantido nas classes comerciais porque é uma regra fixa do
  cenário. Se existissem campanhas ou políticas variáveis, o próximo padrão GoF
  recomendado seria `Strategy` para isolar a política de precificação.

