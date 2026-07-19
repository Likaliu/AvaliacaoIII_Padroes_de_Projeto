package br.edu.ifba.inf011.avaliacao2.decorator;

public interface RenderableContent {
	
	public Integer getDurationInSeconds();
	public void render(Integer init, Integer duration);
}