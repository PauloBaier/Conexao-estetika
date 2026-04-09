
import java.util.List;

public class VendaService {


    private VendaRepository vendaRepository;

    public VendaService(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    public void cadastrar(Venda venda) {

        if (venda.getCliente() == null) {
            throw new IllegalArgumentException("A venda precisa ter um cliente.");
        }

        if (venda.getItens() == null || venda.getItens().isEmpty()) {
            throw new IllegalArgumentException("A venda precisa ter pelo menos um item.");
        }

        if (venda.getStatus() == null) {
            throw new IllegalArgumentException("O status da venda deve ser definido.");
        }

        if (venda.getFormaPagamento() == null) {
            throw new IllegalArgumentException("A forma de pagamento da venda deve ser definida.");
        }

        vendaRepository.salvar(venda);
    }

    public Venda buscar(Long id) {
        return vendaRepository.buscarPorId(id);
    }

    public List<Venda> listar() {
        return vendaRepository.listarTodos();
    }

    public void atualizar(Venda venda) {
        vendaRepository.atualizar(venda);
    }

    public void deletar(Long id) {
        vendaRepository.deletar(id);
    }
}
