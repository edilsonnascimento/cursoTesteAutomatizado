package br.edu.enascimento.servico;

import br.edu.enascimento.builder.ContaCorrenteBuilder;
import br.edu.enascimento.model.ContaCorrente;
import br.edu.enascimento.repository.ContaCorrenteRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static br.edu.enascimento.builder.ContaCorrenteBuilder.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ContaCorrenteServiceTest {

    @Mock
    private ContaCorrenteRepository repository;

    @InjectMocks
    private ContaCorrenteService service;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveRetornarUmaListaDeContasCorrente(){
        //cenario
        //acao
        List<ContaCorrente> resultado = service.getContasDoBanco();
        //validacao
        assertNotNull(resultado);
    }

    @Test
    public void deveRetornarUmaContaCorrentePorIdConta(){
        //cenario
        when(repository.buscarID(anyInt())).thenReturn(umaContaCorrente().constroi());
        //acao
        ContaCorrente resultado = service.pesquisarPor(anyInt());
        //validacao
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
    }
    @Test
    public void deveAdicionarUmaNovaContaCorrente(){
        //cenario
        //acao
        service.adicionar(any());
        //validacao
        verify(repository, times(1)).adicionar(any());
    }
    @Test
    public void deveApagarUmaContaPeloIdContaCorrente(){
        //cenario
        when(repository.remover(anyInt())).thenReturn(true);
        //acao
        boolean resultado = service.apagarPor(anyInt());
        //validacao
        assertTrue(resultado);
    }

    @Test
    public void deveVerificarEstadoContaCorrenteEhAtivo(){
        //cenario
        when(repository.isAtiva(anyInt())).thenReturn(true);
        //acao
        boolean resultado = service.estaAtivaPor(anyInt());
        //validacao
        assertTrue(resultado);
    }
    @Test
    public void deveVerificarEstadoContaCorrenteEhInativo(){
        //cenario
        when(repository.isAtiva(anyInt())).thenReturn(false);
        //acao
        boolean resultado = service.estaAtivaPor(anyInt());
        //validacao
        assertFalse(resultado);
    }

    @Test
    public void deveTransferirContasPeloIdDasContasCorrentes(){
        //cenario
        ContaCorrente conta1 = umaContaCorrente().constroi();
        ContaCorrente conta2 = umaContaCorrente().comId(2).constroi();
        when(repository.buscarID(1)).thenReturn(conta1);
        when(repository.buscarID(2)).thenReturn(conta2);
        //acao
        boolean resultado = service.transfereValor(1, 100.0, 2);
        //validacao
        verify(repository,times(2)).buscarID(anyInt());
        verify(repository, times(1)).buscarID(1);
        verify(repository, times(1)).buscarID(2);
        assertTrue(resultado);
        assertEquals(200.0, conta2.getSaldo());
        assertEquals(0.0, conta1.getSaldo());
    }
    @DisplayName("nao deve transferir valores entre contas saldo da conta origem menor que o valor a transferir.")
    @Test
    public void naoDeveTransferirContasPeloIdDasContasCorrente(){
        //cenario
        ContaCorrente conta1 = umaContaCorrente().constroi();
        ContaCorrente conta2 = umaContaCorrente().comId(2).constroi();
        when(repository.buscarID(1)).thenReturn(conta1);
        when(repository.buscarID(2)).thenReturn(conta2);
        //acao
        boolean resultado = service.transfereValor(1, 200.0, 2);
        //validacao
        verify(repository,times(2)).buscarID(anyInt());
        verify(repository, times(1)).buscarID(1);
        verify(repository, times(1)).buscarID(2);
        assertFalse(resultado);
        assertEquals(100.0, conta2.getSaldo());
        assertEquals(100.0, conta1.getSaldo());
    }
    @DisplayName("deve transferir valores entre contas considerando saldo mais o limite, pelo ID das contas.")
    @Test
    public void deveTransferirContasPeloIdDasContasCorrenteConsiderandLimite(){
        //cenario
        ContaCorrente contaOrigem = umaContaCorrente().comLimite(200.0).comSaldo(0.0).constroi();
        ContaCorrente contaDestino = umaContaCorrente().comLimite(200.0).comId(2).constroi();
        when(repository.buscarID(1)).thenReturn(contaOrigem);
        when(repository.buscarID(2)).thenReturn(contaDestino);
        //acao
        boolean resultado = service.transfereValor(contaOrigem.getId(), 200.0, contaDestino.getId());
        //validacao
        verify(repository,times(2)).buscarID(anyInt());
        verify(repository, times(1)).buscarID(contaOrigem.getId());
        verify(repository, times(1)).buscarID(contaDestino.getId());
        assertTrue(resultado);
        assertEquals(300.0, contaDestino.getSaldo());
        assertEquals(-200.0, contaOrigem.getSaldo());
        assertEquals(0.0, contaOrigem.getLimite());
        assertEquals(200, contaDestino.getLimite());
    }
}

















