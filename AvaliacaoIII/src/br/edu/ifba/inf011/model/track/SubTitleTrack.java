package br.edu.ifba.inf011.model.track;

import br.edu.ifba.inf011.avaliacao1.prototype.Forkable;
import br.edu.ifba.inf011.avaliacao2.adapter.ClassAdapterTrack;

public class SubTitleTrack extends ClassAdapterTrack implements Forkable {
	
    private String idioma;

    public SubTitleTrack(String streamName, String idioma) {
    	
    	super(streamName);
        this.idioma = idioma;
    }
    
    public SubTitleTrack(SubTitleTrack st) {
    	
    	super(st.streamName);
        this.idioma = st.idioma;
    }    

    public String getIdioma() {
    	
        return this.idioma;
    }

    public void setIdioma(String idioma) {
    	
        this.idioma = idioma;
    }

    public SubTitleTrack fork() {
    	
    	return new SubTitleTrack(this);
    }
}