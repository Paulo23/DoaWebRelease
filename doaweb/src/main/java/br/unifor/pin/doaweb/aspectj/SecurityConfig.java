/**
 * 
 */
package br.unifor.pin.doaweb.aspectj;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.unifor.pin.doaweb.entity.Doadores;
import br.unifor.pin.doaweb.entity.Instituicoes;
import br.unifor.pin.doaweb.entity.Usuarios;
import br.unifor.pin.doaweb.enums.TipoUsuario;
import br.unifor.pin.doaweb.to.SegurancaTO;

/**
 * @author patrick.cunha
 * @since 07/05/2015
 */
@Aspect
@Component
public class SecurityConfig {

	@Autowired
	private SegurancaTO segurancaTO;
	private static final Logger LOG = LoggerFactory
			.getLogger(SecurityConfig.class);
	private static final String SECURITY_TAG = "[SEGURANCA] ";

	@Before("@within(RolesAllowed) || @within(PermitAll) || @annotation(RolesAllowed) || @annotation(PermitAll)")
	public void checkSecurity(JoinPoint joinPoint) {

		Method metodo = ((MethodSignature) joinPoint.getSignature())
				.getMethod();

		// Verifica se o acesso ao metodo esta liberado.
		if (metodo.getAnnotation(PermitAll.class) != null) {
			return;
		}

		// Contexto de segurança do usuário logado
		Usuarios usuario = this.segurancaTO.getUsuario();
		if (usuario == null || !this.segurancaTO.isAutenticado()) {
			this.dispararAcessoNegado();
		}

		LOG.debug(SECURITY_TAG + "Usuário: " + usuario);
		LOG.debug(SECURITY_TAG + "Funcionalidade acessada: "
				+ joinPoint.getSignature());

		RolesAllowed permissoesDoMetodo = metodo
				.getAnnotation(RolesAllowed.class) != null ? metodo
				.getAnnotation(RolesAllowed.class) : metodo.getDeclaringClass()
				.getAnnotation(RolesAllowed.class);
				
		if(permissoesDoMetodo != null){
			final List<TipoUsuario> permissoesRequeridas = new ArrayList<TipoUsuario>(Arrays.asList(permissoesDoMetodo.value()));
			final TipoUsuario permissaoDoUsuario = this.retornaPermissaoDoUsuario(usuario);

			if(permissoesRequeridas.contains(permissaoDoUsuario)){
				return;
			}
		} else {
			this.dispararAcessoNegado();
		}

	}

	/**
	 * @param usuario
	 * @return A permissao do usuario
	 */
	private TipoUsuario retornaPermissaoDoUsuario(Usuarios usuario) {
		if(usuario instanceof Doadores){
			return TipoUsuario.DOADOR;
		} else if (usuario instanceof Instituicoes) {
			return TipoUsuario.INSTITUICAO;
		}
		return null;
	}

	/**
	 * 
	 */
	private void dispararAcessoNegado() {
		SecurityException se = new SecurityException(SECURITY_TAG + "Capturada uma tentativa de acesso indevido. Tentativa abortada.");
		LOG.error(SECURITY_TAG + "Capturada uma tentativa de acesso indevido. Tentativa abortada.", se);

		throw se;
	}
	
}
