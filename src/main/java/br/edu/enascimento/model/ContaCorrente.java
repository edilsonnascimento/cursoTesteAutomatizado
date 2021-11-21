package br.edu.enascimento.model;

public class ContaCorrente {
	
	private int id;
	private double saldo;
	private boolean ativa;
	private double limite;

	public ContaCorrente(int id, double saldo, boolean ativa) {
		this.id = id;
		this.saldo = saldo;
		this.ativa = ativa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

	public double getLimite() {
		return saldo < 0 ? limite + saldo : limite;
	}

	public ContaCorrente setLimite(double limite) {
		this.limite = limite;
		return this;
	}

	@Override
	public String toString() {
		String str = "========================="
					+ "Id: " + this.id + "\n"
					+ "Saldo: " + this.saldo + "\n"
					+ "Status: " + (ativa?"Ativa":"Inativa") + "\n"
					+ "=========================";
		return str;
	}
}
