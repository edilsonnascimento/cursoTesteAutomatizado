package br.edu.enascimento.negocio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GerenciadoraContasTest {

    @Test
    public void testTransfereValor(){
        //cenario
        ContaCorrente conta1 = new ContaCorrente(1, 200.00, true);
        ContaCorrente conta2 = new ContaCorrente(2, 0, true);
        List<ContaCorrente> contasDoBanco = new ArrayList<>();
        contasDoBanco.add(conta1);
        contasDoBanco.add(conta2);
        GerenciadoraContas servico = new GerenciadoraContas(contasDoBanco);
        //acao
        servico.transfereValor(1,100.00, 2);
        //validacao
        assertEquals(100.00, servico.pesquisaConta(2).getSaldo());
        assertEquals(100.00, servico.pesquisaConta(1).getSaldo());
    }
    @Test
    public void naoDevePermitirTransferenciaSemSaldoNaConta(){
        //cenario
        int idConta1 = 1;
        int idConta2 = 2;
        ContaCorrente conta1 = new ContaCorrente(idConta1, 100.00, true);
        ContaCorrente conta2 = new ContaCorrente(idConta2, 0, true);
        List<ContaCorrente> contasDoBanco = new ArrayList<>();
        contasDoBanco.add(conta1);
        contasDoBanco.add(conta2);
        GerenciadoraContas servico = new GerenciadoraContas(contasDoBanco);
        //acao
        boolean resultado = servico.transfereValor(idConta1, 200, idConta2);
        //verificacao
        assertFalse(resultado);
        assertEquals(conta1.getSaldo(), 100.00);
        assertEquals(conta2.getSaldo(), 0.00);
    }
}
