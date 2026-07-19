package br.edu.ifba.inf011.model.playlist;

import br.edu.ifba.inf011.avaliacao3.visitor.PlaylistVisitor;

public class MP3 implements PlaylistItem {
	
    private final String nome;
    private final double tamanhoMegaBytes;

    public MP3(String nome, double tamanho) { 
    	
		if (nome == null || nome.trim().isEmpty())
			throw new IllegalArgumentException("Nome do MP3 e obrigatorio");
		if (tamanho < 0)
			throw new IllegalArgumentException("Tamanho do MP3 nao pode ser negativo");
        this.nome = nome; 
        this.tamanhoMegaBytes = tamanho; 
    }
    
    public double getTamanhoMegaBytes() {
    	
    	return this.tamanhoMegaBytes;
    }

    public String getNome() {
    	
    	return this.nome;
    }

	@Override
	public void accept(PlaylistVisitor visitor) {
		
		visitor.visit(this);
	}
}
