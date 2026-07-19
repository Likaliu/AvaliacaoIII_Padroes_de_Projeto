package br.edu.ifba.inf011.avaliacao3.builder;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf011.avaliacao3.composite.ItemVendaComponent;
import br.edu.ifba.inf011.model.comercial.Filme;
import br.edu.ifba.inf011.model.comercial.Pacote;
import br.edu.ifba.inf011.model.comercial.Serie;

/**
 * PADRÃO: BUILDER — papel: BUILDER (Avaliação III, Questão I).
 * Substitui o "new Pacote("SciFi", new Pacote(...), new Serie(...), ...)"
 * gigante e ilegível do enunciado por uma montagem fluente, passo a passo —
 * inclusive permitindo aninhar outros Pacotes (Super Pacotes) e Séries
 * inteiras junto com filmes avulsos, todos tratados como ItemVendaComponent.
 */
public class PacoteBuilder {

    private String titulo;
    private final List<ItemVendaComponent> itens = new ArrayList<>();

    public static PacoteBuilder criar(String titulo) {
    	
        PacoteBuilder builder = new PacoteBuilder();
        builder.titulo = titulo;
        return builder;
    }

    public PacoteBuilder comFilme(Filme filme) {
    	
        itens.add(filme);
        return this;
    }

    public PacoteBuilder comSerie(Serie serie) {
    	
        itens.add(serie);
        return this;
    }

    public PacoteBuilder comPacote(Pacote pacote) {
    	
        itens.add(pacote);
        return this;
    }

    public PacoteBuilder comItem(ItemVendaComponent item) {
    	
        itens.add(item);
        return this;
    }

    public Pacote build() {
    	
        return new Pacote(titulo, itens);
    }
}
