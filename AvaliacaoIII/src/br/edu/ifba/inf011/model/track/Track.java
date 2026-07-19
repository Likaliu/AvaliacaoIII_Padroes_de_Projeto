package br.edu.ifba.inf011.model.track;

public interface Track {
	
	public String getStreamName();
	public Integer getDurationInSeconds();
	public void render(Integer init, Integer duration);
}