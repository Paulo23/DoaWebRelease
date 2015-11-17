package br.unifor.pin.doaweb.abstractfactory;

import br.unifor.pin.doaweb.entity.Usuarios;
import br.unifor.pin.doaweb.enums.TipoUsuario;

public abstract class UsuarioFactory {

	public static UsuarioFactory obtemFactory(TipoUsuario tipoUsuario){
		if(tipoUsuario.equals(TipoUsuario.DOADOR)){
			return new DoadorFactory();
		} else {
			return new InstituicaoFactory();
		}
	}
	
	public abstract Usuarios criaUsuario();
}
