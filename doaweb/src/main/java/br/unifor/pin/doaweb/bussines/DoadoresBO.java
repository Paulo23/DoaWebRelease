package br.unifor.pin.doaweb.bussines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifor.pin.doaweb.aspectj.Loggable;
import br.unifor.pin.doaweb.dao.DoadoresDAO;


@Loggable
@Service
public class DoadoresBO {
	
	@Autowired
	private DoadoresDAO doadoresDAO;

}
