package br.edu.enascimento.servico;

import br.edu.enascimento.model.ContaCorrente;
import br.edu.enascimento.repository.ContaCorrenteRepository;

import java.util.List;

public class ContaService {

	private ContaCorrenteRepository repository;

	public ContaService() {
		this.repository = new ContaCorrenteRepository();
	}
	public List<ContaCorrente> getContasDoBanco() {
		return repository.contaCorrentes();
	}
	public ContaCorrente pesquisarPor(int idConta) {
		return repository.buscarID(idConta);
	}
	public void adicionar(ContaCorrente novaConta) {
		repository.adicionar(novaConta);
	}
	public boolean apagarPor(int idConta) {
		return repository.remover(idConta);
	}
	public boolean estaAtivaPor(int idConta) {
		return repository.isAtiva(idConta);
	}
	
	public boolean transfereValor (int idContaOrigem, double valor, int idContaDestino) {
		boolean sucesso = false;
		ContaCorrente contaDestino = repository.buscarID(idContaDestino);
		ContaCorrente contaOrigem = repository.buscarID(idContaOrigem);
		if(contaOrigem.getSaldo() >= valor){
			contaDestino.setSaldo(contaDestino.getSaldo() + valor);
			contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
			sucesso = true;
		}
		return sucesso;
	}
}
