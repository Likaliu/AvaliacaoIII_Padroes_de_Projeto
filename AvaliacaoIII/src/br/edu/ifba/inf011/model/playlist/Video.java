package br.edu.ifba.inf011.model.playlist;

import br.edu.ifba.inf011.avaliacao3.visitor.PlaylistVisitor;


public class Video implements PlaylistItem {
	
    private final String nome;
    private final double tamanhoMegaBytes;
    private final String link;

    public Video(String nome, double tamanho, String link) { 
    	
		if (nome == null || nome.trim().isEmpty())
			throw new IllegalArgumentException("Nome do video e obrigatorio");
		if (tamanho < 0)
			throw new IllegalArgumentException("Tamanho do video nao pode ser negativo");
		if (link == null || link.trim().isEmpty())
			throw new IllegalArgumentException("Link do video e obrigatorio");
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
