package repository;

import java.util.List;

public interface IRepository<T>{
    public void salvar(T objeto);
    public void atualizar(T objeto);
    public void delete(T objeto);
    public T buscarPorId(long id);
    public List<T> listarTodos();
}
