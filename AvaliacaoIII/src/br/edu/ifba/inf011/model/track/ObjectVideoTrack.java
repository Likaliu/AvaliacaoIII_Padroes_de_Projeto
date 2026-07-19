package br.edu.ifba.inf011.model.track;

import br.edu.ifba.inf011.avaliacao2.adapter.ObjectAdapterTrack;

public class ObjectVideoTrack extends ObjectAdapterTrack implements Track {
	
    private String streamName;

    public ObjectVideoTrack(String streamName) {
    	
        super(streamName);
    }

    public ObjectVideoTrack(ObjectVideoTrack vt) {
    	
    	super(vt.streamName);
    }
    
    public ObjectVideoTrack fork() {
    	
    	return new ObjectVideoTrack(this);
    }    

}
