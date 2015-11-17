package br.unifor.pin.doaweb.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.unifor.pin.doaweb.bussines.DoacaoBO;
import br.unifor.pin.doaweb.entity.Doacao;

@RestController
@RequestMapping("doacoes")
public class DoacoesRest {
	
	@Autowired
	private DoacaoBO doacaoBO;

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String getGreeting(@PathVariable String name) {
		String result = "Hello " + name;
		return result;
	}
	
	@RequestMapping(value="minhasdoacoes/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<Doacao>> getDoacoes(@PathVariable Integer id){
		List<Doacao> doacoes = doacaoBO.buscarDoacPorIdDoador(id);
		return new ResponseEntity<List<Doacao>>(doacoes, HttpStatus.OK);
	}

}
