package br.edu.ifba.inf011.model.playlist;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.inf011.avaliacao3.visitor.ExportadorXmlVisitor;
import br.edu.ifba.inf011.avaliacao3.visitor.LarguraDeBandaVisitor;
import br.edu.ifba.inf011.avaliacao3.visitor.PlaylistVisitor;
import br.edu.ifba.inf011.avaliacao3.visitor.RelatorioNomesVisitor;

public class Playlist {
	
	private List<PlaylistItem> items;
	
	public Playlist() {
		
		this.items = new ArrayList<PlaylistItem>();
	}
	
	public void addItem(PlaylistItem item) {
		
		this.items.add(item);
	}

	public void aplicar(PlaylistVisitor visitor) {
		
		for (PlaylistItem item : this.items) {
			item.accept(visitor);
		}
	}
	
	public String toXML() {
		
		ExportadorXmlVisitor visitor = new ExportadorXmlVisitor();
		this.aplicar(visitor);
		return "<playlist>\n" + visitor.getXml() + "</playlist>\n";
	}

	public Double getBandaTotal() {
		
		LarguraDeBandaVisitor visitor = new LarguraDeBandaVisitor();
		this.aplicar(visitor);
		return visitor.getTotalMegaBytes();
	}

	public List<String> getRelatorioNomes() {
		
		RelatorioNomesVisitor visitor = new RelatorioNomesVisitor();
		this.aplicar(visitor);
		return visitor.getNomes();
	}
}
