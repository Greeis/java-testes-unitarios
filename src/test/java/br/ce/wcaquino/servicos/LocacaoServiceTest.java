package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import br.ce.wcaquino.builders.FilmeBuilder;
import br.ce.wcaquino.builders.LocacaoBuilder;
import br.ce.wcaquino.builders.UsuarioBuilder;
import br.ce.wcaquino.daos.LocacaoDAO;
import br.ce.wcaquino.daos.LocacaoDAOFake;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.matchers.MatchersProprios;
import br.ce.wcaquino.utils.DataUtils;
import buildermaster.BuilderMaster;
//ORGANIZAR O IMPORT CTRL + SHIFT + O
public class LocacaoServiceTest {
	private LocacaoService service;
	private LocacaoDAOFake dao;
	private SPCService spc;
	private EmailService email;
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	
	@Before
	public void setup() {
		service = new LocacaoService();
		//LocacaoDAO dao = new LocacaoDAOFake();
		dao = Mockito.mock(LocacaoDAOFake.class);
		service.setLocacaoDAO(dao);
		spc = Mockito.mock(SPCService.class);
		service.setSPCService(spc);
		email = Mockito.mock(EmailService.class);
		service.setEmailService(email);
	}
	
//	@After
//	public void tearDown() {
//		System.out.println("Depois");
//	}
//	
//	@BeforeClass
//	public static void setupClass() {
//		System.out.println("Antes de instanciar uma classe");
//	}
//	
//	@AfterClass
//	public static void tearDownClass() {
//		System.out.println("Depois de instanciar uma classe");
//	}

	@Test
	public void deveAlugarFilme() throws Exception {
		//so executa quando nao for sabado
		Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		//cenario = preparacao 
		Usuario usuario = UsuarioBuilder.umUsuario().agora();
		List<Filme> filmes = Arrays.asList(FilmeBuilder.umFilme().comValor(5.0).agora());
		
		//acao
		Locacao locacao = service.alugarFilme(usuario, filmes);
		
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
		error.checkThat(locacao.getDataLocacao(), MatchersProprios.ehHoje());
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));
		error.checkThat(locacao.getDataRetorno(), MatchersProprios.ehHojeComDiferencaDias(1));
		 
	}
	
	//tratamento de excecao
	
	@Test(expected=FilmeSemEstoqueException.class)  //informando que existe uma excecao esperada
	public void naoDeveAlugarFilmeSemEstoque() throws Exception{
		//cenario = preparacao 
		//Usuario usuario = new Usuario("Grazi");
		Usuario usuario = UsuarioBuilder.umUsuario().agora();
		List<Filme> filmes = Arrays.asList(FilmeBuilder.umFilme().semEstoque().agora());
		//acao
		service.alugarFilme(usuario, filmes);
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
	
	//forma robusta
	@Test
	public void naoDeveAlugarFilmeSemUsuario() throws FilmeSemEstoqueException {
		//preparacao
		List<Filme> filmes = Arrays.asList(FilmeBuilder.umFilme().agora());
		//ACAO
		try {
			service.alugarFilme(null, filmes);
			Assert.fail();
		} catch (LocadoraException e) {
			// VERIFICACAO
			//checando a msg
			Assert.assertThat(e.getMessage(), is("Usuario vazio!"));
		}
	}
	
	//forma nova
	@Test
	public void naoDeveAlugarFilmeSemFilme() throws FilmeSemEstoqueException, LocadoraException {
		//preparacao
//		Usuario usuario = new Usuario("Grazi");
		Usuario usuario = UsuarioBuilder.umUsuario().agora();
		//Espero...
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio!");
		//acao
		service.alugarFilme(usuario, null);
	}
	
//	@Test
//	public void devePagar75PctNoFilme3() throws LocadoraException, FilmeSemEstoqueException {
//		//cenario
//		Usuario usuario = new Usuario("Grazi");
//		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0),
//				new Filme("Filme 2", 2, 4.0), new Filme("Filme 3", 2, 4.0));
//		//acao
//		Locacao resultado = service.alugarFilme(usuario,filmes);
//		//verificacao
//		//4+4+3
//		Assert.assertThat(resultado.getValor(), is(11.0));
//	}
//	
//	@Test
//	public void devePagar50PctNoFilme4() throws LocadoraException, FilmeSemEstoqueException {
//		//cenario
//		Usuario usuario = new Usuario("Grazi");
//		List<Filme> filmes = Arrays.asList(
//				new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0),
//				new Filme("Filme 3", 2, 4.0), new Filme("Filme 4", 2, 4.0));
//		//acao
//		Locacao resultado = service.alugarFilme(usuario,filmes);
//		//verificacao
//		//4+4+3+2
//		Assert.assertThat(resultado.getValor(), is(13.0));
//	}
//	
//	@Test
//	public void devePagar25PctNoFilme5() throws LocadoraException, FilmeSemEstoqueException {
//		//cenario
//		Usuario usuario = new Usuario("Grazi");
//		List<Filme> filmes = Arrays.asList(
//				new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0),
//				new Filme("Filme 3", 2, 4.0), new Filme("Filme 4", 2, 4.0),
//				new Filme("Filme 5", 2, 4.0));
//		//acao
//		Locacao resultado = service.alugarFilme(usuario,filmes);
//		//verificacao
//		//4+4+3+2+1
//		Assert.assertThat(resultado.getValor(), is(14.0));
//	}
//	
//	@Test
//	public void devePagar0NoFilme6() throws LocadoraException, FilmeSemEstoqueException {
//		//cenario
//		Usuario usuario = new Usuario("Grazi");
//		List<Filme> filmes = Arrays.asList(
//				new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0),
//				new Filme("Filme 3", 2, 4.0), new Filme("Filme 4", 2, 4.0), 
//				new Filme("Filme 5", 2, 4.0), new Filme("Filme 6", 2, 4.0));
//		//acao
//		Locacao resultado = service.alugarFilme(usuario,filmes);
//		//verificacao
//		//4+4+3+2+1+0
//		Assert.assertThat(resultado.getValor(), is(14.0));
//	}
	
	@Test
	public void naoDeveDevolverFilmeNoDomingo() throws LocadoraException, FilmeSemEstoqueException {
		 Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		//só executa quando for sabado
		//cenario
		Usuario usuario = UsuarioBuilder.umUsuario().agora();
		List<Filme> filmes = Arrays.asList(
				FilmeBuilder.umFilme().agora());
		//acao
		Locacao resultado = service.alugarFilme(usuario,filmes);
		//verificacao
		//4+4+3+2+1+0
//		boolean ehSegunda = DataUtils.verificarDiaSemana(resultado.getDataRetorno(), Calendar.MONDAY);
//		Assert.assertTrue(ehSegunda);
//		Assert.assertThat(resultado.getDataRetorno(), MatchersProprios.caiEm(Calendar.MONDAY));
		Assert.assertThat(resultado.getDataRetorno(), MatchersProprios.caiNumaSegunda());
	}
	
//	public static void main(String[]args) {
//		new BuilderMaster().gerarCodigoClasse(Locacao.class);
//	}
	
	@Test
	public void naoDeveAlugarFilmeParaNegativacaoSPC() throws FilmeSemEstoqueException {
		//cenario
		Usuario usuario = UsuarioBuilder.umUsuario().agora();
//		Usuario usuario2 = UsuarioBuilder.umUsuario().comNome("Usuario 2").agora();
		List<Filme> filmes = Arrays.asList(FilmeBuilder.umFilme().agora());
		//o mockito por padrao ele retorna sempre falso
		//quando o spc possui nthrows LocadoraException, FilmeSemEstoqueException egativacao for chamado passando como parametro o usuario
		//entao retorne true
		Mockito.when(spc.possuiNegativacao(usuario)).thenReturn(true);
		//acao
		try {
			service.alugarFilme(usuario, filmes);
			//verificacao
			Assert.fail();
		} catch (LocadoraException e) {
			Assert.assertThat(e.getMessage(), is("Usuario negativado!"));
		}
		//verificando se o mock foi utilizado com os parametros esperados
		Mockito.verify(spc).possuiNegativacao(usuario);
	}
	
	@Test
	public void deveEnviarmockEmailParaLocacaoAtrasadas() {
		//cenario
		Usuario usuario = UsuarioBuilder.umUsuario().agora();
		List<Locacao> locacoes = 
				Arrays.asList(LocacaoBuilder.umLocacao()
						.comUsuario(usuario)
						.comDataRetorno(DataUtils.obterDataComDiferencaDias(-2))
						.agora());
		Mockito.when(dao.obterLocacoesPendentes()).thenReturn(locacoes);
		
		//acao
		service.notificarAtrasos();
		
		//verificacao
		Mockito.verify(email).notificarAtraso(usuario);;
	}
}