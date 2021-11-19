package br.edu.enascimento.negocio;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GerenciadoraClientesTest {

    private Cliente cliente01;
    private Cliente cliente02;
    private List<Cliente> clientesBanco;
    private GerenciadoraClientes servico;

    @BeforeEach
    public void setup(){
        cliente01 = new Cliente(1, "Budogan", 31, "budogan@gmail.com", 1, true);
        cliente02 = new Cliente(2, "Silvio", 38, "Silvio@gmail.com", 1, true);
        clientesBanco = new ArrayList<>();
        clientesBanco.add(cliente01);
        clientesBanco.add(cliente02);
        servico = new GerenciadoraClientes(clientesBanco);
    }
    @AfterEach
    public void tearDown(){
        servico.limpa();
    }
    @Test
    public void devePesquisarClientePeloIDCliente(){
        //acao
        Cliente cliente = servico.pesquisaCliente(1);
        //verificaco
        assertEquals(cliente01.getId(), cliente.getId());
        assertEquals(cliente01.getEmail(), cliente.getEmail());
    }
    @Test
    public void deveRemoverClientePeloIdCliente(){
        //acao
        boolean clienteRemovido = servico.removeCliente(cliente02.getId());
        //validacao
        assertTrue(clienteRemovido);
        assertEquals(cliente01.getId(), clientesBanco.size());
        assertNull(servico.pesquisaCliente(cliente02.getId()));
    }
}
