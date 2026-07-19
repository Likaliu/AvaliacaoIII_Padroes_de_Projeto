package br.edu.ifba.inf011.avaliacao3.composite;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoDeCompras {

    private final List<ItemVendaComponent> itens = new ArrayList<>();

    public void adicionar(ItemVendaComponent item) {
    	
        itens.add(item);
    }

    public Double getPrecoTotal() {
    	
        return itens.stream().mapToDouble(ItemVendaComponent::getPreco).sum();
    }

    public Integer getDuracaoTotalEmSegundos() {
    	
        return itens.stream().mapToInt(ItemVendaComponent::getDuracao).sum();
    }
}
