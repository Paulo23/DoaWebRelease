package br.unifor.pin.doaweb.manager.usuario;


import br.unifor.pin.doaweb.entity.Instituicoes;

public class InstituicaoBuilder extends UsuarioBuilder {

	public InstituicaoBuilder(CadUsuarioManager cadUsuarioManager) {
		super(cadUsuarioManager);
		usuario = new Instituicoes();
	}

	@Override
	public void buildInstituicao() {
		buildUsuario();
		
	}

	@Override
	public void buildDoador() {
		// TODO Auto-generated method stub
		
	}
	

	
}
