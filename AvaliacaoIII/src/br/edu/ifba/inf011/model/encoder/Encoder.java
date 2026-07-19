package br.edu.ifba.inf011.model.encoder;

import br.edu.ifba.inf011.model.renderer.Renderer;

public interface Encoder{
	
    void initialize(Renderer target);
    void setupContainer(String outputPath);
}