package br.ce.wcaquino.builders;

import br.ce.wcaquino.entidades.Usuario;

public class UsuarioBuilder {
	private Usuario usuario;
	
	private UsuarioBuilder() {
		
	}
	//metodo esta estatito pois ele pode ser acessado sem a necessidade de uma instancia
	//é a porta para criação de um usuario
	public static UsuarioBuilder umUsuario() {
		UsuarioBuilder builder = new UsuarioBuilder();
		builder.usuario = new Usuario();
		builder.usuario.setNome("Graziele");
		return builder;
	}
	
	public Usuario agora() {
		return usuario;
	}
}
