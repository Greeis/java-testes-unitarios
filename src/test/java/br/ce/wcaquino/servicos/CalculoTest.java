package br.ce.wcaquino.servicos;
import junit.framework.TestCase;
import org.junit.Assert;

public class CalculoTest extends TestCase {
	
	public void testExecutaCalculo() {
		//define os valores a serem calculados e testados
		float PassaValor1 = 10;
		float PassaValor2 = 5;
		float RetornoEsperado = 15;
		//dispara o método "executaCalculo" da classe "calculo" e armazena
		//o resultado em uma variável
		
		float RetornoFeito = Calculo.executaCalculo (PassaValor1, PassaValor2);
		
		assertEquals(RetornoEsperado, RetornoFeito, 0);
	}
}
