package br.edu.ifba.inf011.model.comercial;

import java.util.ArrayList;
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

	protected String titulo;
	protected Integer temporada;
    protected List<Episodio> episodios;
    
    public Serie(String titulo, Integer temporada) {
    	
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
    	
    	this.episodios.add(episodio);
    }
    
    @Override
    public String getTitulo() {
    	
    	return this.titulo;
    }
        
    @Override
    public Double getPreco() {
    	
        double soma = this.episodios.stream().mapToDouble(Episodio::getPreco).sum();
        return soma * 0.9;
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
		
		return episodios;
	}
}
