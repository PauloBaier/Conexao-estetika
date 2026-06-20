package controllers.usuario;

import controllers.usuario.dto.LoginResponse;

public interface UsuarioController {

    LoginResponse autenticarComLimiteTentativas(String email, String senha, int tentativaAtual, int maxTentativas);
}
