package br.edu.ifba.inf011.avaliacao1.timeline.builder;

import br.edu.ifba.inf011.model.canva.Canva;
import br.edu.ifba.inf011.model.encoder.Encoder;
import br.edu.ifba.inf011.model.renderer.Renderer;

public interface TimelineBuilder {
	
	public TimelineBuilder reset();
	public TimelineBuilder setCanva(Canva canva);
	public TimelineBuilder setEncoder(Encoder encoder);
	public TimelineBuilder setRenderer(Renderer renderer);
	public TimelineBuilder addObjectAdapterVideo(String name);
	public TimelineBuilder addClassAdapterVideo(String name);
	public TimelineBuilder addAudio(String name);
	public TimelineBuilder addSubtitle(String name, String idioma);
	public Timeline build();
}