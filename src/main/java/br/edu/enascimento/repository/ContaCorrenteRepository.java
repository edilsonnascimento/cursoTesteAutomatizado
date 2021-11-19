package br.edu.enascimento.repository;

import br.edu.enascimento.model.ContaCorrente;

import java.util.ArrayList;
import java.util.List;

public class ContaCorrenteRepository {

    private List<ContaCorrente> contasDoBanco;

    public ContaCorrenteRepository() {
        this.contasDoBanco = new ArrayList<>();
    }

    public ContaCorrente buscarID(int idConta){
        for (ContaCorrente contaCorrente : contasDoBanco) {
            if(contaCorrente.getId() == idConta)
                return contaCorrente;
        }
        return null;
    }

    public List<ContaCorrente> contaCorrentes(){
        return contasDoBanco;
    }

    public void adicionar(ContaCorrente contaCorrente){
        contasDoBanco.add(contaCorrente);
    }

    public boolean remover(int idConta){
        boolean contaRemovida = false;

        for (int i = 0; i < contasDoBanco.size(); i++) {
            ContaCorrente conta = contasDoBanco.get(i);
            if(conta.getId() == idConta){
                contasDoBanco.remove(i);
                break;
            }
        }
        return contaRemovida;
    }

    public boolean isAtiva(int idConta){
        boolean contaAtiva = false;
        for (int i = 0; i < contasDoBanco.size(); i++) {
            ContaCorrente conta = contasDoBanco.get(i);
            if(conta.getId() == idConta)
                if(conta.isAtiva()){
                    contaAtiva = true;
                    break;
                }
        }
        return contaAtiva;
    }
}
