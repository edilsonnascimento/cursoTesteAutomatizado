package br.edu.enascimento.servico;

import br.edu.enascimento.exception.IdadeNaoPermitidaException;
import br.edu.enascimento.model.Cliente;
import br.edu.enascimento.repository.ClienteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static br.edu.enascimento.builder.ClienteBuilder.umCliente;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveRetornarUmaListaDeClientes(){
        //cenario
        List<Cliente> listaClientes = Arrays.asList(umCliente().constroi());
        when(repository.buscarTodos()).thenReturn(listaClientes);
        //acao
        List<Cliente> resultado = service.buscarTodosClientes();
        //validacao
        verify(repository, times(1)).buscarTodos();
        assertNotNull(resultado);
    }
    @Test
    public void deveRetonarUmClientePesquisadoPorIdCliente(){
        //cenario
        Cliente cliente = umCliente().constroi();
        when(repository.buscarPor(anyInt())).thenReturn(cliente);
        //acao
        Cliente resultado = service.pesquisarCliente(anyInt());
        //validacao
        verify(repository, times(1)).buscarPor(anyInt());
        assertEquals(cliente, resultado);
    }
    @Test
    public void deveAdicionarUmNovoCliente(){
        //cenario
        Cliente cliente = umCliente().constroi();
        ArgumentCaptor<Cliente> clienteArgumentCaptor = ArgumentCaptor.forClass(Cliente.class);
        //acao
        service.adicionarCliente(cliente);
        //validacao
        verify(repository).adicionar(clienteArgumentCaptor.capture());
        Cliente novoCliente = clienteArgumentCaptor.getValue();
        assertEquals(novoCliente, cliente);
    }
    @Test
    public void deveRemoverClientePeloIdCliente(){
        //cenario
        when(repository.removerPor(anyInt())).thenReturn(true);
        //acao
        boolean resultado = service.removerCliente(anyInt());
        //validacao
        assertTrue(resultado);
        verify(repository, times(1)).removerPor(anyInt());
    }
    @Test
    public void deveVerficarClienteAtivo(){
        //cenario
        when(repository.estaAtivoPor(anyInt())).thenReturn(true);
        //acao
        boolean resultado = service.clienteAtivo(anyInt());
        //validacao
        verify(repository, times(1)).estaAtivoPor(anyInt());
        assertTrue(resultado);
    }
    @Test
    public void deveValidarIdadePermitida() throws IdadeNaoPermitidaException {
        //cenario
        //acao
        boolean resultado = service.validarIdade(25);
        //validacao
        assertTrue(resultado);
    }
    @Test
    public void deveLancarExcecaoParaIdadeMenorIntervalo(){
        //cenario
        String mensagemEsperada = IdadeNaoPermitidaException.MSG_IDADE_INVALIDA;
        //acao
        Exception exception = assertThrows(IdadeNaoPermitidaException.class, ()->
                service.validarIdade(17));
        //validacao
        assertEquals(mensagemEsperada, exception.getMessage());
    }
    @Test
    public void deveLancarExcecaoParaIdadeMaiorIntervalo(){
        //cenario
        String mensagemEsperada = IdadeNaoPermitidaException.MSG_IDADE_INVALIDA;
        //acao
        Exception exception = assertThrows(IdadeNaoPermitidaException.class, ()->
                service.validarIdade(66));
        //validacao
        assertEquals(mensagemEsperada, exception.getMessage());
    }
}
