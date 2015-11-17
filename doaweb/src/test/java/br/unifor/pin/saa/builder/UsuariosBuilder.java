package br.unifor.pin.saa.builder;

import br.unifor.pin.doaweb.entity.Usuarios;

public class UsuariosBuilder {
	
	private Usuarios usuario;
	
	public UsuariosBuilder() {
		usuario = new Usuarios();
	}
	
	public UsuariosBuilder comNome(){
		return this;
	}
	
	public UsuariosBuilder comSenha(){
		usuario.setSenha("123456");
		return this;
	}
	
	public UsuariosBuilder comEmail(){
		return this;
	}
	
	public UsuariosBuilder sendoAtivo(){
//		usuario.setAtivo(false);
		return this;
	}
	
	public Usuarios build(){
		return usuario;
	}
	
}
