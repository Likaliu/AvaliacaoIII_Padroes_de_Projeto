package br.edu.ifba.inf011.model.playlist;

import br.edu.ifba.inf011.avaliacao3.visitor.PlaylistVisitor;


public class Video implements PlaylistItem {
	
    public String nome;
    public double tamanhoMegaBytes;
    public String link;

    public Video(String nome, double tamanho, String link) { 
    	
        this.nome = nome; 
        this.tamanhoMegaBytes = tamanho; 
        this.link = link;
    }
    
    public double getTamanhoMegaBytes() {
    	
    	return this.tamanhoMegaBytes;
    }
    
    public String getNome() {
    	
    	return this.nome;
    }

    public String getLink() {
    	
    	return this.link;
    }

	@Override
	public void accept(PlaylistVisitor visitor) {
		
		visitor.visit(this);
	}
}
