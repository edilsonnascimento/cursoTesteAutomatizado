package br.edu.enascimento.servico;

import br.edu.enascimento.exception.IdadeNaoPermitidaException;
import br.edu.enascimento.model.Cliente;
import br.edu.enascimento.repository.ClienteRepository;

import java.util.List;

public class ClienteService {

	private ClienteRepository repository;

	public ClienteService() {
		this.repository = new ClienteRepository();
	}
	public List<Cliente> buscaTodosClientes() {
		return repository.buscarTodos();
	}
	public Cliente pesquisaCliente (int idCliente) {
		return repository.buscarPor(idCliente);
	}
	public void adicionaCliente (Cliente novoCliente) {
		repository.adicionar(novoCliente);
	}
	public boolean removeCliente (int idCliente) {
		return repository.removerPor(idCliente);
	}
	public boolean clienteAtivo (int idCliente) {
		return repository.estaAtivoPor(idCliente);
	}
	public void limpaBase() {
		repository.limpar();
	}
	public boolean validaIdade(int idade) throws IdadeNaoPermitidaException {
		if(idade < 18 || idade > 65)
			throw new IdadeNaoPermitidaException(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA);
		return true;
	}
}
