package br.edu.ifba.inf011.model.renderer;

import br.edu.ifba.inf011.model.canva.Canva;

public interface Renderer {
	
    public void initialize(Canva target);
    public void render();
}