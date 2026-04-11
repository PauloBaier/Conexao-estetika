package services;

import Config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import models.ContaPagar;
import models.ContaReceber;
import models.Produto;
import models.Venda;
import models.enums.StatusConta;

import java.time.LocalDate;
import java.util.List;

public class RelatorioLocal {

    public List<ContaReceber> contasReceber(LocalDate inicio, LocalDate fim, StatusConta status) {
        EntityManager em = HibernateConfig.getEntityManager();

        try {
            StringBuilder jpql = new StringBuilder("SELECT c FROM ContaReceber c WHERE 1=1");

            if (status != null) {
                jpql.append(" AND c.status = :status");
            }

            if (inicio != null) {
                jpql.append(" AND c.dataEmissao >= :inicio");
            }

            if (fim != null) {
                jpql.append(" AND c.dataEmissao <= :fim");
            }

            TypedQuery<ContaReceber> query = em.createQuery(jpql.toString(), ContaReceber.class);

            if (status != null) {
                query.setParameter("status", status);
            }

            if (inicio != null) {
                query.setParameter("inicio", inicio);
            }

            if (fim != null) {
                query.setParameter("fim", fim);
            }

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<ContaPagar> contasPagar(LocalDate inicio, LocalDate fim, StatusConta status) {
        EntityManager em = HibernateConfig.getEntityManager();

        try {
            StringBuilder jpql = new StringBuilder("SELECT c FROM ContaPagar c WHERE 1=1");

            if (status != null) {
                jpql.append(" AND c.status = :status");
            }

            if (inicio != null) {
                jpql.append(" AND c.dataEmissao >= :inicio");
            }

            if (fim != null) {
                jpql.append(" AND c.dataEmissao <= :fim");
            }

            TypedQuery<ContaPagar> query = em.createQuery(jpql.toString(), ContaPagar.class);

            if (status != null) {
                query.setParameter("status", status);
            }

            if (inicio != null) {
                query.setParameter("inicio", inicio);
            }

            if (fim != null) {
                query.setParameter("fim", fim);
            }

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Produto> produtosEstoqueBaixo() {
        EntityManager em = HibernateConfig.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT p FROM Produto p WHERE p.quantidadeEstoque <= p.estoqueMinimo",
                    Produto.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Venda> vendas(LocalDate inicio, LocalDate fim) {
        EntityManager em = HibernateConfig.getEntityManager();

        try {
            if (inicio == null || fim == null) {
                throw new IllegalArgumentException("Data inicial e final são obrigatórias.");
            }

            return em.createQuery(
                            "SELECT v FROM Venda v WHERE v.data BETWEEN :inicio AND :fim",
                            Venda.class
                    )
                    .setParameter("inicio", inicio)
                    .setParameter("fim", fim)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    public List<Venda> vendasDiarias() {
        LocalDate hoje = LocalDate.now();
        return vendas(hoje, hoje);
    }

    public List<Venda> vendasMensais() {
        LocalDate hoje = LocalDate.now();
        return vendas(
                hoje.withDayOfMonth(1),
                hoje.withDayOfMonth(hoje.lengthOfMonth())
        );
    }

    public List<Venda> vendasAnuais() {
        LocalDate hoje = LocalDate.now();
        return vendas(
                hoje.withDayOfYear(1),
                hoje.withDayOfYear(hoje.lengthOfYear())
        );
    }

    public List<ContaReceber> contasReceberMensal() {
        LocalDate hoje = LocalDate.now();
        return contasReceber(
                hoje.withDayOfMonth(1),
                hoje.withDayOfMonth(hoje.lengthOfMonth()),
                null
        );
    }

    public List<ContaPagar> contasPagarMensal() {
        LocalDate hoje = LocalDate.now();
        return contasPagar(
                hoje.withDayOfMonth(1),
                hoje.withDayOfMonth(hoje.lengthOfMonth()),
                null
        );
    }
}