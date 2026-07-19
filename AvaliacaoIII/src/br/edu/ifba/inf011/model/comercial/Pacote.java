package br.edu.ifba.inf011.model.comercial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.edu.ifba.inf011.avaliacao3.composite.ItemVendaComponent;
import br.edu.ifba.inf011.avaliacao3.visitor.PlaylistVisitor;

public class Pacote implements ItemVendaComponent {

  private static final double FATOR_DESCONTO_BLACK_FRIDAY = 0.9;

	protected String titulo;
    protected List<ItemVendaComponent> itens;
    
    public Pacote(String titulo) {
    	
		if (titulo == null || titulo.trim().isEmpty())
			throw new IllegalArgumentException("Titulo do pacote e obrigatorio");
    	this.titulo = titulo;
    	this.itens = new ArrayList<ItemVendaComponent>();
    }
    
    public Pacote(String titulo, List<ItemVendaComponent> itens) {
    	
		if (titulo == null || titulo.trim().isEmpty())
			throw new IllegalArgumentException("Titulo do pacote e obrigatorio");
		if (itens == null)
			throw new IllegalArgumentException("Lista de itens do pacote nao pode ser nula");
    	this.titulo = titulo;
	    this.itens = new ArrayList<ItemVendaComponent>(itens);
    }

    public void addItem(ItemVendaComponent item) {
    	
		if (item == null)
			throw new IllegalArgumentException("Item do pacote nao pode ser nulo");
    	this.itens.add(item);
    }

    @Override
    public String getTitulo() {
    	
    	return this.titulo;
    }
        
    @Override
    public Double getPreco() {
    	
        double soma = this.itens.stream().mapToDouble(ItemVendaComponent::getPreco).sum();
        return soma * FATOR_DESCONTO_BLACK_FRIDAY;
    }
        
    @Override
    public Integer getDuracao() {
    	
        return this.itens.stream().mapToInt(ItemVendaComponent::getDuracao).sum();
    }

	@Override
	public void accept(PlaylistVisitor visitor) {
		
		visitor.visit(this);
	}

	public List<ItemVendaComponent> getItens() {
		
    return Collections.unmodifiableList(itens);
	}
}
