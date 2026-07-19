package br.edu.ifba.inf011.avaliacao3.visitor;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf011.model.comercial.Episodio;
import br.edu.ifba.inf011.model.comercial.Filme;
import br.edu.ifba.inf011.model.comercial.Pacote;
import br.edu.ifba.inf011.model.comercial.Serie;
import br.edu.ifba.inf011.model.playlist.MP3;
import br.edu.ifba.inf011.model.playlist.Video;

public class RelatorioNomesVisitor implements PlaylistVisitor {

    private final List<String> nomes = new ArrayList<>();

    public List<String> getNomes() {
        return nomes;
    }

    @Override
    public void visit(Filme filme) {
        nomes.add(filme.getTitulo());
    }

    @Override
    public void visit(Episodio episodio) {
        nomes.add(episodio.getTitulo());
    }

    @Override
    public void visit(Serie serie) {
        nomes.add(serie.getTitulo());
    }

    @Override
    public void visit(Pacote pacote) {
        nomes.add(pacote.getTitulo());
    }

    @Override
    public void visit(MP3 mp3) {
        nomes.add(mp3.getNome());
    }

    @Override
    public void visit(Video video) {
        nomes.add(video.getNome());
    }
}