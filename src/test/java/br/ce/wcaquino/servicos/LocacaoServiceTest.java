package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.is;
import java.util.Date;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.Assert;
import org.junit.Rule;

public class LocacaoServiceTest {
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testeLocacao() throws Exception {
		//cenario = preparacao 
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Grazi");
		Filme filme = new Filme("Filme 1", 1, 5.0);
		
		//acao
		Locacao locacao = service.alugarFilme(usuario, filme);
		
		//verificacao
		
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
	
	//tratamento de excecao
	
	@Test(expected=FilmeSemEstoqueException.class)  //informando que existe uma excecao esperada
	public void testLocacao_filmeSemEstoque() throws Exception{
		//cenario = preparacao 
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Grazi");
		Filme filme = new Filme("Filme 1", 0, 5.0);
		//acao
		service.alugarFilme(usuario, filme);
		//verificacao
	}
//	
//	@Test  //informando que existe uma excecao esperada
//	public void testLocacao_filmeSemEstoque2(){
//		//cenario = preparacao 
//		LocacaoService service = new LocacaoService();
//		Usuario usuario = new Usuario("Grazi");
//		Filme filme = new Filme("Filme 1",0, 5.0);
//		//acao
//		try {
//			service.alugarFilme(usuario, filme);
//			Assert.fail("Deveria ter lancado uma excecao");
//		} catch (Exception e) {
//			Assert.assertThat(e.getMessage(), is("Filme sem estoque"));
//		} 
//		//verificacao
//	}
//	
//	@Test  //informando que existe uma excecao esperada
//	public void testLocacao_filmeSemEstoque3() throws Exception{
//		//cenario = preparacao 
//		LocacaoService service = new LocacaoService();
//		Usuario usuario = new Usuario("Grazi");
//		Filme filme = new Filme("Filme 1",0, 5.0);
//		exception.expect(Exception.class); //esta esperando uma classe exception
//		exception.expectMessage("Filme sem estoque");
//		//acao
//		
//		service.alugarFilme(usuario, filme);
//		 
//		//verificacao
//		
//	}
	
	//formA robusta
	@Test
	public void testLocacao_UsuarioVazio() throws FilmeSemEstoqueException {
		//preparacao
		LocacaoService service = new LocacaoService();
		Filme filme = new Filme("Filme 1",0, 5.0);
		//ACAO
		try {
			service.alugarFilme(null, filme);
			Assert.fail();
		} catch (LocadoraException e) {
			// VERIFICACAO
			//checando a msg
			Assert.assertThat(e.getMessage(), is("Usuario vazio!"));
		}
	}
	
	//forma nova
	@Test
	public void testLocacao_FilmeVazio() throws FilmeSemEstoqueException, LocadoraException {
		//preparacao
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Grazi");
		
		//Espero...
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio!");
		
		//acao
		service.alugarFilme(usuario, null);
	}
}

