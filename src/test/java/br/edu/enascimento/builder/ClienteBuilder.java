package br.edu.enascimento.builder;

import br.edu.enascimento.model.Cliente;

public class ClienteBuilder {

    private Cliente cliente;

    public static ClienteBuilder umCliente() {
        ClienteBuilder builder = new ClienteBuilder();
        builder.cliente = new Cliente(1, "Cliente", 35, "cliente@gmail.com", 1, true);
        return builder;
    }

    public Cliente constroi(){
        return cliente;
    }

}
