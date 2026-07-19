package br.edu.ifba.inf011.model.comercial;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf011.avaliacao3.composite.ItemVendaComponent;
import br.edu.ifba.inf011.avaliacao3.visitor.PlaylistVisitor;

public class Pacote implements ItemVendaComponent {

    protected String titulo;
    protected List<ItemVendaComponent> itens;
    
    public Pacote(String titulo) {
        
        this.titulo = titulo;
        this.itens = new ArrayList<ItemVendaComponent>();
    }
    
    public Pacote(String titulo, List<ItemVendaComponent> itens) {
        
        this.titulo = titulo;
        this.itens = itens;
    }

    public void addItem(ItemVendaComponent item) {
        
        this.itens.add(item);
    }

    @Override
    public String getTitulo() {
        
        return this.titulo;
    }
        
    @Override
    public Double getPreco() {
        
        double soma = this.itens.stream().mapToDouble(ItemVendaComponent::getPreco).sum();
        return soma * 0.9;
    }
        
    @Override
    public Integer getDuracao() {
        
        return this.itens.stream().mapToInt(ItemVendaComponent::getDuracao).sum();
    }

    @Override
    public void accept(PlaylistVisitor visitor) {
        
        visitor.visit(this);
        // Cascata do padrão Composite: repassa o visitor para os elementos internos
        for (ItemVendaComponent item : this.itens) {
            item.accept(visitor);
        }
    }

    public List<ItemVendaComponent> getItens() {
        
        return itens;
    }
}
