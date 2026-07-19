package br.edu.ifba.inf011.avaliacao3.visitor;

import br.edu.ifba.inf011.model.comercial.Episodio;
import br.edu.ifba.inf011.model.comercial.Filme;
import br.edu.ifba.inf011.model.comercial.Pacote;
import br.edu.ifba.inf011.model.comercial.Serie;
import br.edu.ifba.inf011.model.playlist.MP3;
import br.edu.ifba.inf011.model.playlist.Video;

public class LarguraDeBandaVisitor implements PlaylistVisitor {

    private static final double MEGABYTES_POR_SEGUNDO = 1.5;

    private double totalMegaBytes = 0;

    public Double getTotalMegaBytes() {
    	
        return totalMegaBytes;
    }

    @Override public void visit(Filme filme) {
    	
        totalMegaBytes += filme.getDuracao() * MEGABYTES_POR_SEGUNDO;
    }

    @Override public void visit(Episodio episodio) {
    	
        totalMegaBytes += episodio.getDuracao() * MEGABYTES_POR_SEGUNDO;
    }

    @Override public void visit(Serie serie) {
    	
        totalMegaBytes += serie.getDuracao() * MEGABYTES_POR_SEGUNDO;
    }

    @Override public void visit(Pacote pacote) {
    	
        totalMegaBytes += pacote.getDuracao() * MEGABYTES_POR_SEGUNDO;
    }

    @Override public void visit(MP3 mp3) {
    	
        totalMegaBytes += mp3.getTamanhoMegaBytes();
    }

    @Override public void visit(Video video) {
    	
        totalMegaBytes += video.getTamanhoMegaBytes();
    }
}
