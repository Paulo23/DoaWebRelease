package br.unifor.pin.doaweb.manager.usuario;

import br.unifor.pin.doaweb.entity.Usuarios;

public abstract class UsuarioBuilder {

	protected Usuarios usuario;
	protected CadUsuarioManager cadUsuarioManager;

	public UsuarioBuilder(CadUsuarioManager cadUsuarioManager) {
		this.cadUsuarioManager = cadUsuarioManager;
		usuario = new Usuarios();
	}
	
	public void buildUsuario() {
		getUsuario().setEmail(cadUsuarioManager.getEmail());
		getUsuario().setSenha(cadUsuarioManager.getSenha());
	}
	public abstract void buildInstituicao();
	public abstract void buildDoador();

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	} 
	
}
