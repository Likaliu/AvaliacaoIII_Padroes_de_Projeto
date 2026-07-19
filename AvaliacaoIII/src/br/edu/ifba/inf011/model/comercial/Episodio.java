package br.edu.ifba.inf011.model.comercial;

import br.edu.ifba.inf011.avaliacao1.timeline.builder.Timeline;
import br.edu.ifba.inf011.avaliacao3.composite.ItemVendaComponent;
import br.edu.ifba.inf011.avaliacao3.visitor.PlaylistVisitor;

/**
 * PADRÃO: COMPOSITE — papel: LEAF (caso seja vendido/empacotado avulso).
 * PADRÃO: VISITOR — papel: CONCRETE ELEMENT (Avaliação III, Questão II).
 */
public class Episodio implements ItemVendaComponent {

	private String titulo;
	private Double preco;
	private Timeline timeline;
	private Integer numero;

	public Episodio(String titulo, Double preco, Integer numero, Timeline timeline) {
		
		if (titulo == null || titulo.trim().isEmpty())
			throw new IllegalArgumentException("Titulo do episodio e obrigatorio");
		if (preco == null || preco < 0)
			throw new IllegalArgumentException("Preco do episodio nao pode ser negativo");
		if (numero == null || numero <= 0)
			throw new IllegalArgumentException("Numero do episodio deve ser positivo");
		if (timeline == null)
			throw new IllegalArgumentException("Timeline do episodio nao pode ser nula");
		this.titulo = titulo;
		this.preco = preco;
		this.timeline = timeline;
		this.numero = numero;
	}

	@Override
	public Double getPreco() {
		
		return this.preco;
	}

	@Override
	public Integer getDuracao() {
		
		return this.timeline.getDurationInSeconds();
	}

	@Override
	public String getTitulo() {
		
		return this.titulo;
	}

	public Integer getNumero() {
		
		return this.numero;
	}

	@Override
	public void accept(PlaylistVisitor visitor) {
		
		visitor.visit(this);
	}
}
