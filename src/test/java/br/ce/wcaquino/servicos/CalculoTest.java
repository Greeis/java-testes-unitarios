package br.ce.wcaquino.servicos;

import junit.framework.TestCase;
import org.junit.Assert;

import br.ce.wcaquino.servicos.Calculo;

public class CalculoTest extends TestCase {
	
	public void testExecutaCalculo() {
		//define os valores a serem calculados e testados
		float PassaValor1 = 10;
		float PassaValor2 = 5;
		float RetornoEsperado = 15;
		//dispara o m�todo "executaCalculo" da classe "calculo" e armazena
		//o resultado em uma vari�vel
		
		float RetornoFeito = Calculo.executaCalculo (PassaValor1, PassaValor2);
		
		assertEquals(RetornoEsperado, RetornoFeito, 0);
	}
}
