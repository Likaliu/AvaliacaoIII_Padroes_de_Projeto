package br.edu.ifba.inf011.avaliacao3.visitor;

import br.edu.ifba.inf011.avaliacao3.composite.ItemVendaComponent;
import br.edu.ifba.inf011.model.comercial.Episodio;
import br.edu.ifba.inf011.model.comercial.Filme;
import br.edu.ifba.inf011.model.comercial.Pacote;
import br.edu.ifba.inf011.model.comercial.Serie;
import br.edu.ifba.inf011.model.playlist.MP3;
import br.edu.ifba.inf011.model.playlist.Video;

public class ExportadorXmlVisitor implements PlaylistVisitor {

    private final StringBuilder xml = new StringBuilder();

    public String getXml() {
    	
        return xml.toString();
    }

    @Override
    public void visit(Filme filme) {
    	
        xml.append("<filme titulo=\"").append(escapeXml(filme.getTitulo())).append("\"/>\n");
    }

    @Override
    public void visit(Episodio episodio) {
    	
        xml.append("<episodio titulo=\"").append(escapeXml(episodio.getTitulo()))
           .append("\" numero=\"").append(episodio.getNumero()).append("\"/>\n");
    }

    @Override
    public void visit(Serie serie) {
    	
        xml.append("\t<serie titulo=\"").append(escapeXml(serie.getTitulo()))
           .append("\" temporada=\"").append(serie.getTemporada()).append("\">\n");
        for (Episodio episodio : serie.getEpisodios()) {
            episodio.accept(this);
        }
        xml.append("\t</serie>\n");
    }

    @Override
    public void visit(Pacote pacote) {
    	
        xml.append("<pacote titulo=\"").append(escapeXml(pacote.getTitulo())).append("\">\n");
        for (ItemVendaComponent item : pacote.getItens()) {
            item.accept(this);
        }
        xml.append("</pacote>\n");
    }

    @Override
    public void visit(MP3 mp3) {
    	
        xml.append("<mp3 nome=\"").append(escapeXml(mp3.getNome())).append("\"/>\n");
    }

    @Override
    public void visit(Video video) {
    	
        xml.append("  <video nome=\"").append(escapeXml(video.getNome()))
           .append("\" link=\"").append(escapeXml(video.getLink())).append("\"/>\n");
    }

  private String escapeXml(String valor) {
		
    return valor.replace("&", "&amp;")
        .replace("\"", "&quot;")
        .replace("'", "&apos;")
        .replace("<", "&lt;")
        .replace(">", "&gt;");
  }
}
