package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.is;
import java.util.Date;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.Assert;

public class LocacaoServiceTest {
	@Test
	public void teste() {
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
		
		Assert.assertThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.equalTo(5.0)));
		Assert.assertThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.not(6.0)));
		Assert.assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
		Assert.assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));
		
		 
	}
}
