package controllers.usuario.impl;

import controllers.usuario.UsuarioController;
import controllers.usuario.dto.LoginResponse;
import models.Usuario;
import services.UsuarioService;

public class UsuarioControllerImpl implements UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioControllerImpl(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public LoginResponse autenticarComLimiteTentativas(String email, String senha, int tentativaAtual, int maxTentativas) {
        Usuario usuario = usuarioService.autenticarComLimiteTentativas(email, senha, tentativaAtual, maxTentativas);
        return new LoginResponse(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getPerfil().name());
    }
}
