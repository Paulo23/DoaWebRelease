package br.unifor.pin.saa.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.unifor.pin.doaweb.dao.DoadoresDAO;
import br.unifor.pin.doaweb.entity.Usuarios;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class UsuarioDAOTest {
	
	@Autowired
	private DoadoresDAO usuarioDAO;

	@Test
	public void testSalvar() throws Exception {
		
		Usuarios doador = new Usuarios();
//		doador.setNome("adriano");
		doador.setSenha("123456");
		doador.setEmail("adriano");
		doador.setAtivo(false);
		//usuarioDAO.salvar(doador);
		
		
		Assert.assertNotNull(doador.getId());
		System.out.println(doador.getId());
		
	}
	
//	@Test
//	public void testListaPorNome(){
//		List<Usuarios> usuarios = usuarioDAO.listarPorNome("adri");
//		Assert.assertEquals(1, usuarios.size());
//	}

}
