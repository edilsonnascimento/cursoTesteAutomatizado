package br.edu.enascimento.builder;

import br.edu.enascimento.model.ContaCorrente;

public class ContaCorrenteBuilder {

    private ContaCorrente contaCorrente;

    public static ContaCorrenteBuilder umaContaCorrente() {
        ContaCorrenteBuilder contaCorrenteBuilder = new ContaCorrenteBuilder();
        contaCorrenteBuilder.contaCorrente = new ContaCorrente(1, 100.0, true);
        return contaCorrenteBuilder;
    }

    public ContaCorrenteBuilder comId(int novoId){
        contaCorrente.setId(novoId);
        return this;
    }

    public ContaCorrenteBuilder comLimite(double valor){
        contaCorrente.setLimite(valor);
        return this;
    }

    public ContaCorrenteBuilder comSaldo(double valor){
        contaCorrente.setSaldo(valor);
        return this;
    }
    public ContaCorrente constroi(){
        return this.contaCorrente;
    }
}
