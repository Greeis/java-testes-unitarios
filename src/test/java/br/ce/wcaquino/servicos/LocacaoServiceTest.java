package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.is;
import java.util.Date;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.Assert;
import org.junit.Rule;

public class LocacaoServiceTest {
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	
	@Test
	public void testeLocacao() throws Exception {
		//cenario = preparacao 
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Grazi");
		Filme filme = new Filme("Filme 1", 1, 5.0);
		//ação
		Locacao locacao = service.alugarFilme(usuario, filme);
		//verificação
		
		
		
		
		//Assert.assertEquals(5.0, locacao.getValor(), 0.01);
		//assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		//assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
		
		
		//usando o assertThat
		
		//Assert.assertThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.equalTo(5.0)));
		//Assert.assertThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.not(6.0)));
		//Assert.assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
		//Assert.assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));
		
		
		
		
		//Usando o Rule 
		
		error.checkThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.equalTo(5.0)));
		error.checkThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.not(6.0)));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));
		 
	}
	
	//tratamento de exceção
	@Test(expected=Exception.class)  //informando que existe uma exceção esperada
	public void testLocacao_filmeSemEstoque() throws Exception{
		//cenario = preparacao 
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Grazi");
		Filme filme = new Filme("Filme 1", 0, 5.0);
		//ação
		service.alugarFilme(usuario, filme);
		//verificação
				
	}
	
}
