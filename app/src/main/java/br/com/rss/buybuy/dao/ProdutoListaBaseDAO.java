package br.com.rss.buybuy.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.rss.buybuy.model.FrequenciaModel;
import br.com.rss.buybuy.model.ProdutoListaBaseModel;
import br.com.rss.buybuy.model.ProdutoModel;
import br.com.rss.buybuy.model.UnidadeMedidaModel;

/**
 * Created by Rodrigo Costa on 11/12/2017.
 */

public class ProdutoListaBaseDAO implements CrudDAO<ProdutoListaBaseModel> {

    SQLiteDatabase db;

    public ProdutoListaBaseDAO() {
    }

    public ProdutoListaBaseDAO(BuyBuyDbHelper buyBuyDbHelper) {
        this.db = buyBuyDbHelper.getWritableDatabase();
    }

    public void alterar(ProdutoListaBaseModel produtoListaBaseModel) {

    }

    public void inserir(ProdutoListaBaseModel produtoListaBaseModel) {

        Object[] args = {produtoListaBaseModel.getProdutoModel().getId(), produtoListaBaseModel.getQuantidade(), produtoListaBaseModel.getFrequenciaModel().getId(), Boolean.TRUE};

        db.execSQL("INSERT INTO lista_base (produto_id, quantidade, frequencia_id, flag_ativo) VALUES(?, ?, ?, ?);", args);
    }

    public void excluir(ProdutoListaBaseModel produtoListaBaseModel) {

        Object[] args = {Boolean.FALSE, produtoListaBaseModel.getId()};

        db.execSQL("UPDATE lista_base SET flag_ativo = ? where id = ? ", args);

    }

    public void restaurar(ProdutoListaBaseModel produtoListaBaseModel) {

        Object[] args = {Boolean.TRUE, produtoListaBaseModel.getId()};

        db.execSQL("UPDATE lista_base SET flag_ativo = ? where id = ? ", args);

    }

    public List<ProdutoListaBaseModel> pesquisar(ProdutoListaBaseModel produtoListaBaseModel) {

        List<ProdutoListaBaseModel> produtos = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT lb.id, p.id, p.descricao, p.unidade_medida_id, (select um.abreviacao from unidade_medida um where um.id = p.unidade_medida_id), lb.quantidade, lb.frequencia_id, (select f.descricao from frequencia f where f.id = lb.frequencia_id) FROM lista_base lb, produto p where lb.produto_id = p.id and p.flag_ativo and lb.flag_ativo order by 3;", null);

        ProdutoModel produtoModel;

        if (c.moveToFirst()) {
            do {

                produtoModel = new ProdutoModel(c.getLong(1), c.getString(2), new UnidadeMedidaModel(c.getLong(3), c.getString(4)));

                produtos.add(new ProdutoListaBaseModel(c.getLong(0), produtoModel, c.getDouble(5), new FrequenciaModel(c.getLong(6), c.getString(7))));

            } while (c.moveToNext());
        }

        c.close();

        return produtos;

    }

    public List<ProdutoListaBaseModel> pesquisarAtivos(String query) {

        String s = "%" + query.toUpperCase() + "%";

        String[] args = {s};

        List<ProdutoListaBaseModel> produtos = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT lb.id, p.id, p.descricao, p.unidade_medida_id, (select um.abreviacao from unidade_medida um where um.id = p.unidade_medida_id), lb.quantidade, lb.frequencia_id, (select f.descricao from frequencia f where f.id = lb.frequencia_id) FROM lista_base lb, produto p where lb.produto_id = p.id and p.flag_ativo and lb.flag_ativo and upper(p.descricao) like upper(?) order by 3;", args);

        ProdutoModel produtoModel;

        if (c.moveToFirst()) {
            do {

                produtoModel = new ProdutoModel(c.getLong(1), c.getString(2), new UnidadeMedidaModel(c.getLong(3), c.getString(4)));

                produtos.add(new ProdutoListaBaseModel(c.getLong(0), produtoModel, c.getDouble(5), new FrequenciaModel(c.getLong(6), c.getString(7))));

            } while (c.moveToNext());
        }

        c.close();

        return produtos;

    }

}
