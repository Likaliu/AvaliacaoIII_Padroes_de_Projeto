package br.edu.ifba.inf011.model;

import java.util.List;

import br.edu.ifba.inf011.avaliacao1.timeline.builder.CinemaTimelineBuilder;
import br.edu.ifba.inf011.avaliacao1.timeline.builder.Timeline;
import br.edu.ifba.inf011.avaliacao1.timeline.builder.TimelineBuilder;
import br.edu.ifba.inf011.avaliacao3.builder.PacoteBuilder;
import br.edu.ifba.inf011.model.comercial.Episodio;
import br.edu.ifba.inf011.model.comercial.Filme;
import br.edu.ifba.inf011.model.comercial.Pacote;
import br.edu.ifba.inf011.model.comercial.Serie;
import br.edu.ifba.inf011.model.playlist.MP3;
import br.edu.ifba.inf011.model.playlist.Playlist;
import br.edu.ifba.inf011.model.playlist.Video;
import br.edu.ifba.inf011.avaliacao3.composite.CarrinhoDeCompras;

public class Cliente {

	public void run() {

		TimelineBuilder builder = new CinemaTimelineBuilder();
		Timeline cinemaTimeline = builder.reset().addClassAdapterVideo("MainShot_4K.mov").build();

		// Questão I: Composite + Builder
		// "Trilogia Matrix": pacote fechado com 3 filmes
		Pacote trilogiaMatrix = PacoteBuilder.criar("Trilogia Matrix")
				.comFilme(new Filme("Matrix", 20.0, cinemaTimeline))
				.comFilme(new Filme("Matrix Reloaded", 25.0, cinemaTimeline))
				.comFilme(new Filme("Matrix Revolutions", 15.0, cinemaTimeline)).build();

		// "Star Wars": outro pacote fechado
		Pacote starWars = PacoteBuilder.criar("Star Wars")
				.comFilme(new Filme("Uma Nova Esperança", 18.0, cinemaTimeline))
				.comFilme(new Filme("O Império Contra-Ataca", 18.0, cinemaTimeline)).build();

		// "Black Mirror": série empacotável
		Serie blackMirror = new Serie("Black Mirror", 1, new Episodio("The National Anthem", 5.0, 1, cinemaTimeline),
				new Episodio("Fifteen Million Merits", 5.0, 2, cinemaTimeline),
				new Episodio("The Entire History of You", 5.0, 3, cinemaTimeline));

		// Filme avulso
		Filme bladeRunner = new Filme("Blade Runner", 12.0, cinemaTimeline);

		// "Coleção Sci-Fi": Super Pacote que aninha os dois pacotes acima,
		// a série e o filme avulso
		Pacote colecaoSciFi = PacoteBuilder.criar("Coleção Sci-Fi").comPacote(trilogiaMatrix).comPacote(starWars)
				.comSerie(blackMirror).comFilme(bladeRunner).build();

		System.out.println("=== Questão I: Composite + Builder ===");
		System.out.println("Preço da Trilogia Matrix (sozinha): " + trilogiaMatrix.getPreco());
		System.out.println("Preço da Coleção Sci-Fi (aninhada): " + colecaoSciFi.getPreco());
		System.out.println("Duração da Coleção Sci-Fi (aninhada): " + colecaoSciFi.getDuracao() + "s");

		CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
		carrinho.adicionar(bladeRunner); // filme avulso
		carrinho.adicionar(colecaoSciFi); // pacote complexo aninhado
		System.out.println("Carrinho (filme avulso + pacote aninhado, tratados igual):");
		System.out.println("  Preço total: " + carrinho.getPrecoTotal());
		System.out.println("  Duração total: " + carrinho.getDuracaoTotalEmSegundos() + "s");

		// === Questão II: Visitor ===
		Playlist playlist = new Playlist();
		playlist.addItem(colecaoSciFi); // catálogo (Pacote aninhado)
		playlist.addItem(new MP3("Son Of A Gun", 1000)); // upload do cliente
		playlist.addItem(new Video("Trailer caseiro", 250, "http://exemplo.com/trailer")); // upload do cliente

		System.out.println("\nQuestão II: Visitor");
		System.out.println("Consumo de largura de banda: " + playlist.getBandaTotal());

		List<String> nomes = playlist.getRelatorioNomes();
		System.out.println("Relatório com o nome dos elementos: " + nomes);

		String xml = playlist.toXML();
		System.out.println("Exportação XML:\n" + xml);
	}

	public static void main(String[] args) {
		
		new Cliente().run();
	}

}
