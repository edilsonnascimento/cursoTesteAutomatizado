package br.edu.enascimento.negocio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GerenciadoraClientesTest {

    @Test
    public void testPesquisaClientes(){
        //cenario
        Cliente cliente01 = new Cliente(1, "Budogan", 31, "budogan@gmail.com", 1, true);
        Cliente cliente02 = new Cliente(2, "Silvio", 38, "Silvio@gmail.com", 1, true);
        List<Cliente> clientesBanco = new ArrayList<>();
        clientesBanco.add(cliente01);
        clientesBanco.add(cliente02);
        GerenciadoraClientes servico = new GerenciadoraClientes(clientesBanco);
        //acao
        Cliente cliente = servico.pesquisaCliente(1);
        //verificaco
        assertEquals(cliente01.getId(), cliente.getId());
        assertEquals(cliente01.getEmail(), cliente.getEmail());
    }
    @Test
    public void testRemoveCliente(){
        //cenario
        Cliente cliente01 = new Cliente(1, "Budogan", 31, "budogan@gmail.com", 1, true);
        Cliente cliente02 = new Cliente(2, "Silvio", 38, "Silvio@gmail.com", 1, true);
        List<Cliente> clientesBanco = new ArrayList<>();
        clientesBanco.add(cliente01);
        clientesBanco.add(cliente02);
        GerenciadoraClientes servico = new GerenciadoraClientes(clientesBanco);
        //acao
        boolean clienteRemovido = servico.removeCliente(2);
        //validacao
        assertTrue(clienteRemovido);
        assertEquals(1, clientesBanco.size());
        assertNull(servico.pesquisaCliente(2));
    }



}
