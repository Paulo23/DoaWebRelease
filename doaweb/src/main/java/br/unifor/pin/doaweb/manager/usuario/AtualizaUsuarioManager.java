package br.unifor.pin.doaweb.manager.usuario;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.unifor.pin.doaweb.bussines.UsuarioBO;
import br.unifor.pin.doaweb.dao.UsuariosDAO;
import br.unifor.pin.doaweb.entity.Instituicoes;
import br.unifor.pin.doaweb.entity.Usuarios;
import br.unifor.pin.doaweb.exceptions.BOException;
import br.unifor.pin.doaweb.to.SegurancaTO;
import br.unifor.pin.doaweb.utils.MessagesUtils;
import br.unifor.pin.doaweb.utils.Navigation;
/**
 * @author patrick.cunha
 * 
 */
@RequestScoped
@ManagedBean(name = "atualizaUsuario")
@Component(value = "atualizaUsuario")
public class AtualizaUsuarioManager {

	@Autowired
	private UsuarioBO usuariosBO;
	
	@Autowired
	private SegurancaTO segurancaTO;
	private Usuarios usuarioSelecionado;
	
	
	public String preparaAtualizar() {
		usuarioSelecionado = this.segurancaTO.getUsuario();

		return Navigation.ATUALIZA;
	}
	
	public String preparaListar(){
		this.limparDados();
		return Navigation.SUCESSO;
	}
	
	public String excluir(){
		try {
			usuariosBO.excluirUsuario(this.segurancaTO.getUsuario());
			MessagesUtils.info("Conta excluída com sucesso.");
		} catch (BOException e) {
			MessagesUtils.error(e.getMessage());
			e.printStackTrace();
			return Navigation.FRACASSO;
		}
		
		return Navigation.EXCLUIR;
	}

	
	public void limparDados(){

	}


	
	
}
