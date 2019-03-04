package br.ce.wcaquino.suites;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.wcaquino.servicos.CalculadoraTest;
import br.ce.wcaquino.servicos.CalculoTest;
import br.ce.wcaquino.servicos.CalculoValorLocacaoTest;
import br.ce.wcaquino.servicos.LocacaoServiceTest;

@RunWith(Suite.class)
//aqui eu defino todos as classes de testes que eu quero que execute
@SuiteClasses({
	CalculoTest.class,
	CalculadoraTest.class,
	CalculoValorLocacaoTest.class,
	LocacaoServiceTest.class
}) 

public class SuitesExecucao {
	
	@BeforeClass
	public static void before() {
		System.out.println("Before");
	}
	
	@AfterClass
	public static void after() {
		System.out.println("After");
	}
}
