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
	public List<Cliente> buscarTodosClientes() {
		return repository.buscarTodos();
	}
	public Cliente pesquisarCliente (int idCliente) {
		return repository.buscarPor(idCliente);
	}
	public void adicionarCliente (Cliente novoCliente) {
		repository.adicionar(novoCliente);
	}
	public boolean removerCliente (int idCliente) {
		return repository.removerPor(idCliente);
	}
	public boolean clienteAtivo (int idCliente) {
		return repository.estaAtivoPor(idCliente);
	}
	public boolean validarIdade(int idade) throws IdadeNaoPermitidaException {
		if(idade < 18 || idade > 65)
			throw new IdadeNaoPermitidaException(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA);
		return true;
	}
}
