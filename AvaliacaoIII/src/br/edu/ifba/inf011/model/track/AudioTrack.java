package br.edu.ifba.inf011.model.track;

import br.edu.ifba.inf011.avaliacao2.adapter.ClassAdapterTrack;

public class AudioTrack extends ClassAdapterTrack implements Track {
	
    private String streamName;

    public AudioTrack(String streamName) {
    	
        super(streamName);
    }

    public AudioTrack(AudioTrack at) {
    	
    	super(at.streamName);
    }
    
    public AudioTrack fork() {
    	
    	return new AudioTrack(this);
    }    


}
