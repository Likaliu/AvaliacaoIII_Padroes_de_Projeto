package br.edu.ifba.inf011.model.encoder;

import br.edu.ifba.inf011.model.renderer.Renderer;

public class ProResEncoder implements Encoder {

	private Renderer target;
    private final EncoderProfile profile = EncoderProfile.CINEMA_PRORES;
    private String outputPath;

    @Override
    public void initialize(Renderer target) {
    	
    	this.target = target;
    }

    @Override
    public void setupContainer(String outputPath) {
    	
        this.outputPath = outputPath;
    }

}