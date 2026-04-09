create or replace function atualizar_total_venda()
returns trigger as $$
begin
    update vendas
    set valor_total = (
        select coalesce(sum(total_item), 0)
        from itens_venda
        where fk_vendas_id = new.fk_vendas_id
    )
    where id = new.fk_vendas_id;

    return new;
end;
$$ language plpgsql;


create trigger trigger_atualizar_total_venda
after insert or update on itens_venda
for each row
execute function atualizar_total_venda();