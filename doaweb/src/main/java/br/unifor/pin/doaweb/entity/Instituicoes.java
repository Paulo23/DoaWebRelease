package br.unifor.pin.doaweb.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 * @author equipe.doaweb
 *
 */
@Entity 
@DiscriminatorValue("1")
public class Instituicoes extends Usuarios {
	
	@Column(name="cnpj_instituicao", nullable = false, length = 18, unique = true)
	private String cnpj;
	
	@Column(name="razao_social", nullable = false)
	private String razaoSocial;
	
	@Column(nullable = false)
	private String telefone;

	@Column(name = "descricao_instituicao", nullable = false)
	private String desc;
	
	@OneToMany(mappedBy = "instituicao", fetch=FetchType.EAGER)
	private List<Campanhas> campanhas;
	
	/*
	 * Dados Bancarios
	 */
	
	@Column(name = "nomeBanco_dadosBancarios", nullable = false)
	private String nomeBanco;

	@Column(name = "operacao_dadosBancarios", nullable = true, length = 10)
	private String operacao;

	@Column(name = "agencia_dadosBancarios", nullable = false, length = 50)
	private String agencia;

	@Column(name = "conta_dadosBancarios", nullable = false, length = 50)
	private String conta;

	/*
	 * GETS/SETS DADOS DA INSTITUIÇÃO
	 */
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	/*
	 * GETS/SETS DADOS BANCARIOS
	 */

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	
	
}
