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
}
