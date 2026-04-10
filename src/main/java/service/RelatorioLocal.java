package service;

import Config.HibernateConfig;
import jakarta.persistence.*;
import model.*;
import model.enums.StatusConta;


import java.time.LocalDate;
import java.util.List;

public class RelatorioLocal{

    //  CONTAS A RECEBER
    public List<ContaReceber> contasReceber(LocalDate inicio, LocalDate fim, StatusConta status) {

        EntityManager em = HibernateConfig.getEntityManager();

        try {
            String jpql = "SELECT c FROM ContaReceber c WHERE 1=1";

            if (status != null) {
                jpql += " AND c.status = :status";
            }

            TypedQuery<ContaReceber> query = em.createQuery(jpql, ContaReceber.class);

            if (status != null) {
                query.setParameter("status", status);
            }

            return query.getResultList();

        } finally {
            em.close();
        }
    }

    //  CONTAS A PAGAR
    public List<ContaPagar> contasPagar(LocalDate inicio, LocalDate fim, StatusConta status) {

        EntityManager em = HibernateConfig.getEntityManager();

        try {
            String jpql = "SELECT c FROM ContaPagar c WHERE 1=1";

            if (status != null) {
                jpql += " AND c.status = :status";
            }

            TypedQuery<ContaPagar> query = em.createQuery(jpql, ContaPagar.class);

            if (status != null) {
                query.setParameter("status", status);
            }

            return query.getResultList();

        } finally {
            em.close();
        }
    }

    //  PRODUTOS COM ESTOQUE BAIXO
    public List<Produto> produtosEstoqueBaixo() {

        EntityManager em = HibernateConfig.getEntityManager();

        List<Produto> lista = em.createQuery(
                "SELECT p FROM Produto p WHERE p.quantidadeEstoque <= p.estoqueMinimo",
                Produto.class
        ).getResultList();

        em.close();

        return lista;
    }

    //  VENDAS POR PERÍODO
    public List<Venda> vendas(LocalDate inicio, LocalDate fim) {

        EntityManager em = HibernateConfig.getEntityManager();

        List<Venda> lista = em.createQuery(
                        "SELECT v FROM Venda v WHERE v.data BETWEEN :inicio AND :fim",
                        Venda.class
                )
                .setParameter("inicio", inicio.atStartOfDay())
                .setParameter("fim", fim.atTime(23,59))
                .getResultList();

        em.close();

        return lista;
    }

    // ================= RELATÓRIOS AUTOMÁTICOS =================

    //  VENDAS
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


    //  CONTAS RECEBER
    public List<ContaReceber> contasReceberMensal() {
        LocalDate hoje = LocalDate.now();
        return contasReceber(
                hoje.withDayOfMonth(1),
                hoje.withDayOfMonth(hoje.lengthOfMonth()),
                null
        );
    }


    //  CONTAS PAGAR
    public List<ContaPagar> contasPagarMensal() {
        LocalDate hoje = LocalDate.now();
        return contasPagar(
                hoje.withDayOfMonth(1),
                hoje.withDayOfMonth(hoje.lengthOfMonth()),
                null
        );
    }
}
