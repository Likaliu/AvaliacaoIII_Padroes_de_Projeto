package br.edu.ifba.inf011.model.playlist;

import br.edu.ifba.inf011.avaliacao3.visitor.PlaylistVisitor;

public interface PlaylistItem {
	
    void accept(PlaylistVisitor visitor);
}
