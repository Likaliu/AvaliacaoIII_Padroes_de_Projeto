package br.edu.ifba.inf011.avaliacao3.composite;

import br.edu.ifba.inf011.model.playlist.PlaylistItem;

public interface ItemVendaComponent extends PlaylistItem {
	
    String getTitulo();
    Double getPreco();
    Integer getDuracao();
}
