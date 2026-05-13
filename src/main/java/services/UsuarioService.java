package services;

import models.Usuario;
import repositories.UsuarioRepository;

import java.time.LocalDate;
import java.util.List;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void cadastrarUsuario(Usuario usuario) {
        validarUsuario(usuario);

        if (usuario.getCriado_em() == null) {
            usuario.setCriado_em(LocalDate.now());
        }

        usuario.setAtivo(true);

        usuarioRepository.salvar(usuario);
    }

    public void atualizarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo.");
        }

        if (usuario.getId() == null || usuario.getId() <= 0) {
            throw new IllegalArgumentException("ID do usuário inválido.");
        }

        validarUsuario(usuario);

        Usuario usuarioExistente = usuarioRepository.buscarPorId(usuario.getId());

        if (usuarioExistente == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        usuarioRepository.atualizar(usuario);
    }

    public void desativarUsuario(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do usuário inválido.");
        }

        Usuario usuario = usuarioRepository.buscarPorId(id);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        usuario.setAtivo(false);
        usuarioRepository.atualizar(usuario);
    }

    public Usuario buscarPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID do usuário inválido.");
        }

        return usuarioRepository.buscarPorId(id);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.listarTodos();
    }

    public Usuario autenticar(String email, String senha) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email é obrigatório.");
        }
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha é obrigatória.");
        }

        Usuario usuario = usuarioRepository.buscarPorEmail(email.trim());

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        if (!usuario.isAtivo()) {
            throw new IllegalArgumentException("Usuário inativo.");
        }

        if (!usuario.getSenha().equals(senha)) {
            throw new IllegalArgumentException("Senha incorreta.");
        }

        return usuario;
    }

    private void validarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo.");
        }

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do usuário é obrigatório.");
        }

        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email do usuário é obrigatório.");
        }

        if (!usuario.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email inválido.");
        }

        if (usuario.getSenha() == null || usuario.getSenha().length() < 6) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 6 caracteres.");
        }

        if (usuario.getPerfil() == null) {
            throw new IllegalArgumentException("Perfil do usuário é obrigatório.");
        }
    }
}