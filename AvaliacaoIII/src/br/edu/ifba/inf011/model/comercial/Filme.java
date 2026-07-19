package br.edu.ifba.inf011.model.comercial;

import br.edu.ifba.inf011.avaliacao1.timeline.builder.Timeline;
import br.edu.ifba.inf011.avaliacao3.composite.ItemVendaComponent;
import br.edu.ifba.inf011.avaliacao3.visitor.PlaylistVisitor;

/**
 * PADRÃO: COMPOSITE — papel: LEAF (Avaliação III, Questão I).
 * PADRÃO: VISITOR — papel: CONCRETE ELEMENT (Avaliação III, Questão II).
 */
public class Filme implements ItemVendaComponent {
    
    private String titulo;
    private Double preco;
    private Timeline timeline;

    public Filme(String titulo, Double preco, Timeline timeline) {
        
        this.titulo = titulo;
        this.preco = preco;
        this.timeline = timeline;
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

    @Override
    public void accept(PlaylistVisitor visitor) {
        
        visitor.visit(this);
    }
}
