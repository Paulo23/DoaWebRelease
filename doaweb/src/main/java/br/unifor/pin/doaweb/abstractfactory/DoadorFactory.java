package br.unifor.pin.doaweb.abstractfactory;

import br.unifor.pin.doaweb.entity.Doadores;
import br.unifor.pin.doaweb.entity.Usuarios;

public class DoadorFactory extends UsuarioFactory {

	@Override
	public Usuarios criaUsuario() {
		return new Doadores();
	}

}
