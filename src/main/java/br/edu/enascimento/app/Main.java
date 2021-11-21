package br.edu.enascimento.app;

import br.edu.enascimento.model.Cliente;
import br.edu.enascimento.model.ContaCorrente;
import br.edu.enascimento.servico.ClienteService;
import br.edu.enascimento.servico.ContaCorrenteService;

import java.util.Scanner;

public class Main {

	private static ClienteService clienteService;
	private static ContaCorrenteService contaService;

	public static void main(String[] args) {
		
		inicializaSistemaBancario(); // criando algumas contas e clientes fictícios
		
		Scanner sc = new Scanner(System.in);
		boolean continua = true;
		
		while (continua){
			
			printMenu();
			
			int opcao = sc.nextInt();
			
			switch (opcao) {
			// Consultar por um cliente
			case 1:
				System.out.print("Digite o ID do cliente: ");
				int idCliente = sc.nextInt();
				Cliente cliente = clienteService.pesquisarCliente(idCliente);
				
				if(cliente != null)
					System.out.println(cliente.toString());
				else
					System.out.println("Cliente não encontrado!");
				
				pulalinha();
				break;

			// Consultar por uma conta corrente
			case 2:
				System.out.print("Digite o ID da conta: ");
				int idConta = sc.nextInt();
				ContaCorrente conta = contaService.pesquisarPor(idConta);
				
				if(conta != null)
					System.out.println(conta.toString());
				else
					System.out.println("Conta não encontrado!");
				
				pulalinha();
				break;

			// Ativar um cliente
			case 3:
				
				System.out.print("Digite o ID do cliente: ");
				int idCliente2 = sc.nextInt();
				Cliente cliente2 = clienteService.pesquisarCliente(idCliente2);
				
				if(cliente2 != null){
					cliente2.setAtivo(true);
					System.out.println("Cliente ativado com sucesso!");
				}
				else
					System.out.println("Cliente n" +
							"Não encontrado!");
			
				pulalinha();
				break;
				
			// Desativar um cliente
			case 4:
				
				System.out.print("Digite o ID do cliente: ");
				int idCliente3 = sc.nextInt();
				Cliente cliente3 = clienteService.pesquisarCliente(idCliente3);
				
				if(cliente3 != null){
					cliente3.setAtivo(false);
					System.out.println("Cliente desativado com sucesso!");
				}
				else
					System.out.println("Cliente não encontrado!");
				
				pulalinha();
				break;
			
			// Sair
			case 5:
				continua = false;
				System.out.println("################# Sistema encerrado #################");
				break;
				
			default:
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				break;
			}
		}
	}

	private static void pulalinha() {
		System.out.println("\n");
	}

	/**
	 * Imprime menu de opcoes do nosso sistema bancario
	 */
	private static void printMenu() {
		
		System.out.println("O que você deseja fazer? \n");
		System.out.println("1) Consultar por um cliente");
		System.out.println("2) Consultar por uma conta corrente");
		System.out.println("3) Ativar um cliente");
		System.out.println("4) Desativar um cliente");
		System.out.println("5) Sair");
		System.out.println();
		
	}

	/**
	 * Metodo que cria e insere algumas contas e clientes no sistema do banco,
	 * apenas para realizacao de testes manuais atravs do metodo main acima.
	 */
	private static void inicializaSistemaBancario() {
		contaService = new ContaCorrenteService();
		ContaCorrente conta01 = new ContaCorrente(1, 0, true);
		ContaCorrente conta02 = new ContaCorrente(2, 0, true);
		contaService.adicionar(conta01);
		contaService.adicionar(conta02);

		clienteService = new ClienteService();
		Cliente cliente01 = new Cliente(1, "Gustavo Farias", 31, "gugafarias@gmail.com", conta01.getId(), true);
		Cliente cliente02 = new Cliente(2, "Felipe Augusto", 34, "felipeaugusto@gmail.com", conta02.getId(), true);
		clienteService.adicionarCliente(cliente01);
		clienteService.adicionarCliente(cliente02);
	}
	
}

