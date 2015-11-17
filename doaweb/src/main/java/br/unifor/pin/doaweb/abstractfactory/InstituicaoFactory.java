package br.unifor.pin.doaweb.abstractfactory;

import br.unifor.pin.doaweb.entity.Instituicoes;
import br.unifor.pin.doaweb.entity.Usuarios;

public class InstituicaoFactory extends UsuarioFactory {

	@Override
	public Usuarios criaUsuario() {
		return new Instituicoes();
	}
	
	

}
