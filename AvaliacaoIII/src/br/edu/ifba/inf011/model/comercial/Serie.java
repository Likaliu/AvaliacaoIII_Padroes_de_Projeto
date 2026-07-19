package br.edu.ifba.inf011.model.comercial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ifba.inf011.avaliacao3.composite.ItemVendaComponent;
import br.edu.ifba.inf011.avaliacao3.visitor.PlaylistVisitor;

/**
 * PADRÃO: COMPOSITE — papel: LEAF (uma Série inteira pode ser empacotada
 * como um único item de venda, ex: "a série Black Mirror" no enunciado).
 * PADRÃO: VISITOR — papel: CONCRETE ELEMENT (Avaliação III, Questão II).
 *
 * OBS: corrigido um bug do código-base — o construtor recebia "temporada"
 * mas nunca atribuía o campo (this.temporada = temporada estava faltando).
 */
public class Serie implements ItemVendaComponent {

  private static final double FATOR_DESCONTO_SERIE = 0.9;

	protected String titulo;
	protected Integer temporada;
    protected List<Episodio> episodios;
    
    public Serie(String titulo, Integer temporada) {
    	
		if (titulo == null || titulo.trim().isEmpty())
			throw new IllegalArgumentException("Titulo da serie e obrigatorio");
		if (temporada == null || temporada <= 0)
			throw new IllegalArgumentException("Temporada deve ser positiva");
    	this.titulo = titulo;
    	this.temporada = temporada;
    	this.episodios = new ArrayList<Episodio>();
    }

    public Serie(String titulo, Integer temporada, Episodio... episodios) {
    	
    	this(titulo, temporada);
    	for (Episodio episodio : episodios)
    		this.episodios.add(episodio);
    }

    public void addEpisodio(Episodio episodio) {
    	
		if (episodio == null)
			throw new IllegalArgumentException("Episodio nao pode ser nulo");
    	this.episodios.add(episodio);
    }
    
    @Override
    public String getTitulo() {
    	
    	return this.titulo;
    }
        
    @Override
    public Double getPreco() {
    	
        double soma = this.episodios.stream().mapToDouble(Episodio::getPreco).sum();
        return soma * FATOR_DESCONTO_SERIE;
    }
        
    @Override
    public Integer getDuracao() {
    	
        return this.episodios.stream().mapToInt(Episodio::getDuracao).sum();
    }    
    
    public Integer getTemporada() {
    	
    	return this.temporada;
    }

	@Override
	public void accept(PlaylistVisitor visitor) {
		
		visitor.visit(this);
	}

	public List<Episodio> getEpisodios() {
		
    return Collections.unmodifiableList(episodios);
	}
}
