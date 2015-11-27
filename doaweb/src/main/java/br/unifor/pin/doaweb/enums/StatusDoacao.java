package br.unifor.pin.doaweb.enums;

public enum StatusDoacao {

	PENDENTE("PENDENTE"),
	VISUALIZADA("VISUALIZADA"),
	RECEBIDA("RECEBIDA");
	
	  private final String label;

	  private StatusDoacao(String label) {
	    this.label = label;
	  }

	  public String getLabel() {
	    return this.label;
	  }
	
}
