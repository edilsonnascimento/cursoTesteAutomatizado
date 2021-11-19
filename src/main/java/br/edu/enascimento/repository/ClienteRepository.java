package br.edu.enascimento.repository;

import br.edu.enascimento.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {

    private List<Cliente> clientesDoBanco;

    public ClienteRepository() {
        this.clientesDoBanco = new ArrayList<>();
    }

    public List<Cliente> buscarTodos() {
        return this.clientesDoBanco;
    }

    public Cliente buscarPor(int idCliente) {
        for (Cliente cliente : clientesDoBanco) {
            if(cliente.getId() == idCliente)
                return cliente;
        }
        return null;
    }

    public void adicionar(Cliente novoCliente) {
        clientesDoBanco.add(novoCliente);
    }

    public boolean removerPor(int idCliente) {
        boolean clienteRemovido = false;
        for (int i = 0; i < clientesDoBanco.size(); i++) {
            Cliente cliente = clientesDoBanco.get(i);
            if(cliente.getId() == idCliente){
                clientesDoBanco.remove(i);
                clienteRemovido = true;
                break;
            }
        }
        return clienteRemovido;
    }
    public boolean estaAtivoPor(int idCliente) {
        boolean clienteAtivo = false;
        for (int i = 0; i < clientesDoBanco.size(); i++) {
            Cliente cliente = clientesDoBanco.get(i);
            if(cliente.getId() == idCliente)
                if(cliente.isAtivo()){
                    clienteAtivo = true;
                    break;
                }
        }
        return clienteAtivo;
    }

    public void limpar() {
        this.clientesDoBanco.clear();
    }
}
