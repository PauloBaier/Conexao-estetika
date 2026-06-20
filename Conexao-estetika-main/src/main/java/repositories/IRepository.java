package repositories;

import java.util.List;

public interface IRepository<T> {
    void salvar(T objeto);
    void atualizar(T objeto);
    void delete(T objeto);
    T buscarPorId(long id);
    List<T> listarTodos();
}