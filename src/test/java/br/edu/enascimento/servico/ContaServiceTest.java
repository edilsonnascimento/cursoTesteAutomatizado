package br.edu.enascimento.servico;

import br.edu.enascimento.model.ContaCorrente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ContaServiceTest {

	@Mock
	private List<ContaCorrente> contasDoBanco;

	@InjectMocks
	private ContaService servico;

	@BeforeEach
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}

	@AfterEach
	public void tearDown(){
		contasDoBanco.clear();
	}

	@Test
	public void deveRetornarUmaListaDeContas(){
		//cenario
		//acao
		List<ContaCorrente> resultado = servico.getContasDoBanco();
		//validacao
		assertNotNull(resultado);
	}

	public void deveRetornarUmaContaCorrentePeloIdConta(){
		//cenario

		//acao
		//validacao
	}
}
