package br.unifor.pin.saa.dao;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.unifor.pin.doaweb.bussines.CampanhaBO;
import br.unifor.pin.doaweb.dao.CampanhasDAO;
import br.unifor.pin.doaweb.dao.UsuariosDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
@org.springframework.transaction.annotation.Transactional
public class CampanhaBOTest {
	
	@Autowired
	private CampanhaBO bo;
	
	@Autowired
	private CampanhasDAO campanhasDAO;
	
	@Autowired
	private UsuariosDAO dao;
	
	//Checa se o sistema atualiza o status da campanha de acordo com a data atual no sistema
	@Test
	public void testMudaStatus(){
		bo.setStatusCampanhas();
	}
	
	@Test
	public void testValidaFimCampanha(){
		Date date = new Date();
		//Checa se a data de termino da campanha é posterior a data atual
		//data iguais sistema retorna false (Data inválida)
		Assert.assertFalse(bo.validaDataDeTerminoDeCampanha(date));
		
	}

}
