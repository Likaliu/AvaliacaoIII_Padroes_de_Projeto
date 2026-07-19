package br.edu.ifba.inf011.model.track;

import br.edu.ifba.inf011.avaliacao2.adapter.ClassAdapterTrack;

public class ClassVideoTrack extends ClassAdapterTrack implements Track {
	
    private String streamName;

    public ClassVideoTrack(String streamName) {
    	
        super(streamName);
    }

    public ClassVideoTrack(ClassVideoTrack vt) {
    	
    	super(vt.streamName);
    }
    
    public ClassVideoTrack fork() {
    	
    	return new ClassVideoTrack(this);
    }    

}
