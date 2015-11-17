package br.unifor.pin.doaweb.enums;

public enum TipoUsuario {
	
	DOADOR("Doador"), INSTITUICAO("Instituição");
	
	private String descricao;
	
	private TipoUsuario(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
