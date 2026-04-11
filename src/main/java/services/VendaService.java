package services;

import models.Caixa;
import models.ContaReceber;
import models.ItemVenda;
import models.MovimentacaoCaixa;
import models.Venda;
import models.enums.StatusConta;
import models.enums.StatusVenda;
import models.enums.TipoMovimento;
import repositories.VendaRepository;

import java.time.LocalDate;
import java.util.List;

public class VendaService {

    private final VendaRepository vendaRepository;
    private final CaixaService caixaService;
    private final ItemVendaService itemVendaService;
    private final ContaReceberService contaReceberService;
    private final MovimentacaoCaixaService movimentacaoCaixaService;

    public VendaService(
            VendaRepository vendaRepository,
            CaixaService caixaService,
            ItemVendaService itemVendaService,
            ContaReceberService contaReceberService,
            MovimentacaoCaixaService movimentacaoCaixaService
    ) {
        this.vendaRepository = vendaRepository;
        this.caixaService = caixaService;
        this.itemVendaService = itemVendaService;
        this.contaReceberService = contaReceberService;
        this.movimentacaoCaixaService = movimentacaoCaixaService;
    }

    public void cadastrar(Venda venda) {
        Caixa caixaAberto = caixaService.buscarCaixaAberto();

        if (caixaAberto == null) {
            throw new IllegalArgumentException("Não é possível vender. Caixa fechado.");
        }

        if (venda == null) {
            throw new IllegalArgumentException("Venda inválida.");
        }

        if (venda.getData() == null) {
            venda.setData(LocalDate.now());
        }

        if (venda.getItens() == null || venda.getItens().isEmpty()) {
            throw new IllegalArgumentException("A venda precisa ter pelo menos um item.");
        }

        if (venda.getStatus() == null) {
            throw new IllegalArgumentException("O status da venda deve ser definido.");
        }

        if (venda.getStatus() == StatusVenda.PAGO && venda.getFormaPagamento() == null) {
            throw new IllegalArgumentException("A forma de pagamento deve ser definida para venda paga.");
        }

        double totalCalculado = 0.0;

        for (ItemVenda item : venda.getItens()) {
            if (item == null) {
                throw new IllegalArgumentException("Item de venda inválido.");
            }

            if (item.getProduto() == null) {
                throw new IllegalArgumentException("Todo item precisa ter produto.");
            }

            if (item.getQuantidade() <= 0) {
                throw new IllegalArgumentException("Quantidade inválida no item da venda.");
            }

            item.setVenda(venda);
            item.setPrecoUnitario(item.getProduto().getPrecoVenda());
            item.setTotalItem(item.getPrecoUnitario() * item.getQuantidade());

            totalCalculado += item.getTotalItem();
        }

        venda.setValorTotal(totalCalculado);

        vendaRepository.salvar(venda);

        for (ItemVenda item : venda.getItens()) {
            item.setVenda(venda);
            itemVendaService.cadastrar(item);
        }

        if (venda.getStatus() == StatusVenda.PAGO) {
            MovimentacaoCaixa mov = new MovimentacaoCaixa();
            mov.setCaixa(caixaAberto);
            mov.setTipo(TipoMovimento.ENTRADA);
            mov.setValor(venda.getValorTotal());
            mov.setDescricao("Recebimento da venda ID " + venda.getId());

            movimentacaoCaixaService.registrarMovimentacao(mov);

        } else if (venda.getStatus() == StatusVenda.PENDENTE) {
            ContaReceber conta = new ContaReceber();
            conta.setDescricao("Recebimento da venda ID " + venda.getId());
            conta.setValor(venda.getValorTotal());
            conta.setDataEmissao(LocalDate.now());
            conta.setDataVencimento(LocalDate.now().plusDays(30));
            conta.setDataPagamento(null);
            conta.setStatus(StatusConta.PENDENTE);
            conta.setCliente(venda.getCliente());
            conta.setVenda(venda);

            contaReceberService.cadastrar(conta);
        }
    }

    public Venda buscar(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return vendaRepository.buscarPorId(id);
    }

    public List<Venda> listar() {
        return vendaRepository.listarTodos();
    }

    public void atualizar(Venda venda) {
        if (venda == null || venda.getId() == null) {
            throw new IllegalArgumentException("Venda inválida.");
        }

        Venda existente = vendaRepository.buscarPorId(venda.getId());
        if (existente == null) {
            throw new IllegalArgumentException("Venda não encontrada.");
        }

        vendaRepository.atualizar(venda);
    }

    public void deletar(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        Venda existente = vendaRepository.buscarPorId(id);
        if (existente == null) {
            throw new IllegalArgumentException("Venda não encontrada.");
        }

        vendaRepository.deletar(id);
    }
}