

import org.junit.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrdemTest {

	public static int contador = 0;
//	
//	public void inicia() {
//		contador = 1;
//	}
//	
//	public void verifica() {
//		Assert.assertEquals(1, contador);
//	}
//	
//	@Test
//	public void testGeral() {
//		inicia();
//		verifica();
//	}

//Executar os testes em ordem alfabetica
	@Test
	public void inicia() {
	contador = 1;
	}

	@Test
	public void verifica() {
	Assert.assertEquals(1, contador);
	}
}
