package br.ce.wcaquino.servicos;

import org.junit.Test;

import br.ce.wcaquino.entidades.Usuario;
import org.junit.Assert;

public class AssertTest {
	
	@Test
	public void test() {
		
		Assert.assertTrue(true);
		Assert.assertFalse(false);
		
		//assertEqual verica se um é igual a outro
		Assert.assertEquals("Erro de comparação: ", 1,1);
		Assert.assertEquals(0.51234, 0.512 , 0.001);
		Assert.assertEquals(Math.PI, 3.14, 0.01);
		
		
		//formas de conversão
		int i = 5;
		Integer i2 = 5;
		
		Assert.assertEquals(Integer.valueOf(i), i2);
		Assert.assertEquals(i, i2.intValue());
		
		
		//Comparação de string
		
		Assert.assertEquals("bola", "bola");
		Assert.assertTrue("bola".equalsIgnoreCase("Bola")); //Não considera o B
		Assert.assertTrue("bola".startsWith("bo")); 
		
		
		Usuario u1 = new Usuario ("Grazi"); 
		Usuario u2 = new Usuario ("Grazi"); 
		
		//depois de gerado o metodo equals
		
		Assert.assertEquals(u1, u2);
		
		Assert.assertSame(u2, u2);
		
		Usuario u3 = u2;
		Assert.assertSame(u3, u2);
		Assert.assertNotSame(u1, u2);
		
		//verificar se o obj está null
		
		Usuario u4 = null;
		
		Assert.assertNull(u4);
		
		//verificar que não está vazio
		
		Assert.assertNotNull(u1);
		
		
		
		
		
		
	}
	

}
