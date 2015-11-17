package br.unifor.pin.doaweb.enums;

public enum TipoDoacao {

	ALIMENTOS("ALIMENTOS"),
	DINHEIRO("DINHEIRO"),
	ROUPAS("ROUPAS");
	
	/*
	 * retornar labels em um metodo no
	 *  cadCampanhaManage para  setar valores no selectOneMenu em cadCampanhaxhtml
	 */
	
	  private final String label;

	  private TipoDoacao(String label) {
	    this.label = label;
	  }

	  public String getLabel() {
	    return this.label;
	  }
}
