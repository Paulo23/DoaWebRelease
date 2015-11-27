package br.unifor.pin.doaweb.bussines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unifor.pin.doaweb.aspectj.Loggable;
import br.unifor.pin.doaweb.aspectj.PermitAll;
import br.unifor.pin.doaweb.dao.DoadoresDAO;
import br.unifor.pin.doaweb.dao.UsuariosDAO;
import br.unifor.pin.doaweb.entity.Doadores;
import br.unifor.pin.doaweb.entity.Usuarios;
import br.unifor.pin.doaweb.exceptions.BOException;
import br.unifor.pin.doaweb.exceptions.DAOException;

@Loggable
@Component
@Transactional(propagation=Propagation.REQUIRED)
public class UsuarioBO {

	@Autowired
	private UsuariosDAO usuarioDAO;
		
	@Autowired
	private DoadoresDAO doadoresDAO;
	
	public void salvar(Usuarios usuario) throws BOException {
		usuario.setAtivo(true);
		
		if (usuario instanceof Doadores) {
			Usuarios usuarioComMesmoCPFJaCadastrado = doadoresDAO.buscarPorCPF(((Doadores)usuario).getCpf());
			if(usuarioComMesmoCPFJaCadastrado != null){
				throw new BOException("CPF já cadastrado. Volte a tela de cadastro e tente novamente.");
			}
		}
		
		usuarioDAO.salvar(usuario);
	}

	public void atualizar(Usuarios usuario) {
		usuarioDAO.atualizar(usuario);
	}

	@Loggable(enable = false)
	public Usuarios loggar(String email, String senha) throws BOException {
		Usuarios usuario = usuarioDAO.buscarPorEmailSenha(email, senha);
		if(usuario != null){
			if(!usuario.isAtivo()){
				throw new BOException("Sua conta está inativa, entre em contato para reativá-la.");
			}
		}
		return usuario;
	}

	@Loggable(enable = false)
	public Usuarios buscarUsuarioPorEmail(String email) {
		return usuarioDAO.buscarPorEmail(email);
	}

	@PermitAll
	@Loggable(enable = false)
	public Usuarios buscarPorId(Integer id) {
		try {
			return usuarioDAO.buscaPorId(id);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void excluirUsuario(Usuarios usuario) throws BOException {
		try {
			usuario.setAtivo(false);
			usuarioDAO.atualizar(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BOException("Não foi possível excluir sua conta.");
		}
	}
	

}
