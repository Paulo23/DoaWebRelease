package br.unifor.pin.doaweb.manager.usuario;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.unifor.pin.doaweb.bussines.UsuarioBO;
import br.unifor.pin.doaweb.entity.Doadores;
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

	// DADOS INSTITUIÇÃO
	private String end;
	private String telefone;
	private String email;
	private String desc;

	public String preparaListar() {
		this.limparDados();
		return Navigation.SUCESSO;
	}

	public String preparaAtualizarInstituicao() {
		setUsuarioSelecionado(this.segurancaTO.getUsuario());

		return Navigation.ATUALIZAINST;
	}

	public String preparaAtualizarDoador() {
		setUsuarioSelecionado(this.segurancaTO.getUsuario());

		return Navigation.ATUALIZADOA;
	}

	public String atualizarInst() {
		Instituicoes instituicoes = (Instituicoes) usuarioSelecionado;

		if (getEnd().length() == 0) {
			instituicoes.setEndereco(usuarioSelecionado.getEndereco());
		} else {
			instituicoes.setEndereco(getEnd());
			limparDados();
		}

		if (getTelefone().length() == 0) {
			instituicoes.setTelefone(((Instituicoes) usuarioSelecionado)
					.getTelefone());
		} else {
			instituicoes.setTelefone(getTelefone());
			limparDados();
		}
		if (getDesc().length() == 0) {
			instituicoes.setDesc(((Instituicoes) usuarioSelecionado).getDesc());
		} else {
			instituicoes.setDesc(getDesc());
			limparDados();
		}
		usuariosBO.atualizar(instituicoes);

		return Navigation.SUCESSO;
	}

	public String atualizarDoador() {
		Doadores doadores = (Doadores) usuarioSelecionado;

		if (getEnd().length() == 0) {
			doadores.setEndereco(usuarioSelecionado.getEndereco());
		} else {
			doadores.setEndereco(getEnd());
			limparDados();
		}

		if (getTelefone().length() == 0) {
			doadores.setTelDoador(((Doadores) usuarioSelecionado)
					.getTelDoador());
		} else {
			doadores.setTelDoador(getTelefone());
			limparDados();
		}
		usuariosBO.atualizar(doadores);

		return Navigation.SUCESSO;
	}

	public void limparDados() {
		this.end = "";
		this.desc = "";
		this.email = "";
		this.telefone = "";
	}

	public String voltar() {
		limparDados();
		return Navigation.VOLTAR;
	}

	public String excluir() {
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

	/*
	 * GETTS AND SETTS
	 */

	public Usuarios getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuarios usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
