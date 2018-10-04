package br.com.rss.buybuy.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.rss.buybuy.model.CategoriaModel;
import br.com.rss.buybuy.model.ProdutoModel;
import br.com.rss.buybuy.model.UnidadeMedidaModel;

/**
 * Created by Rodrigo Costa on 11/12/2017.
 */

public class ProdutoDAO implements CrudDAO<ProdutoModel> {

    SQLiteDatabase db;

    public ProdutoDAO() {
    }

    public ProdutoDAO(BuyBuyDbHelper buyBuyDbHelper) {
        this.db = buyBuyDbHelper.getWritableDatabase();
    }

    public void alterar(ProdutoModel produtoModel) {

        Object[] args = {produtoModel.getDescricao(), produtoModel.getCategoriaModel().getId(), produtoModel.getUnidadeMedidaModel().getId(), produtoModel.getFlagAtivo(), produtoModel.getId()};

        db.execSQL("UPDATE produto set descricao = ?, categoria_id = ?, unidade_medida_id = ?, flag_ativo = ? WHERE id = ?;", args);

    }

    public void inserir(ProdutoModel produtoModel) {

        Object[] args = {produtoModel.getDescricao(), produtoModel.getCategoriaModel().getId(), produtoModel.getUnidadeMedidaModel().getId(), produtoModel.getFlagAtivo()};

        db.execSQL("INSERT INTO produto (descricao, categoria_id, unidade_medida_id, flag_ativo) VALUES(?, ?, ?, ?);", args);
    }

    public void excluir(ProdutoModel produtoModel) {

        Object[] args = {Boolean.FALSE, produtoModel.getId()};

        db.execSQL("UPDATE produto SET flag_ativo = ? where id = ? ", args);

    }

    public void restaurar(ProdutoModel produtoModel) {

        Object[] args = {Boolean.TRUE, produtoModel.getId()};

        db.execSQL("UPDATE produto SET flag_ativo = ? where id = ? ", args);

    }

    public List<ProdutoModel> pesquisar(ProdutoModel produtoModel) {

        List<ProdutoModel> produtoList = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT p.id, p.descricao, p.categoria_id, (select nome from categoria c where c.id = p.categoria_id), p.unidade_medida_id, (select descricao from unidade_medida um where um.id = p.unidade_medida_id), flag_ativo FROM produto p where flag_ativo = 1 order by 4, 2;", null);

        if (c.moveToFirst()) {
            do {
                produtoList.add(new ProdutoModel(c.getLong(0), c.getString(1), new CategoriaModel(c.getLong(2), c.getString(3)), new UnidadeMedidaModel(c.getLong(4), c.getString(5)), c.getInt(6) > 0));
            } while (c.moveToNext());
        }

        c.close();

        return produtoList;

    }

    public List<ProdutoModel> pesquisar(String query) {

        List<ProdutoModel> produtoList = new ArrayList<>();

        String s = "%" + query.toUpperCase() + "%";

        String[] args = {s};

        Cursor c = db.rawQuery("SELECT p.id, p.descricao, p.categoria_id, (select nome from categoria c where c.id = p.categoria_id), p.unidade_medida_id, (select descricao from unidade_medida um where um.id = p.unidade_medida_id), flag_ativo FROM produto p where flag_Ativo = 1 and upper(descricao) like upper(?) order by 4, 2;", args);

        if (c.moveToFirst()) {
            do {
                produtoList.add(new ProdutoModel(c.getLong(0), c.getString(1), new CategoriaModel(c.getLong(2), c.getString(3)), new UnidadeMedidaModel(c.getLong(4), c.getString(5)), c.getInt(6) > 0));
            } while (c.moveToNext());
        }

        c.close();

        return produtoList;

    }

    public List<ProdutoModel> pesquisarAtivos(Boolean ordenarPorCategoria) {

        List<ProdutoModel> produtoList = new ArrayList<>();

        String s = "4, 2";

        if (!ordenarPorCategoria) {
            s = "2";
        }

        String[] ordem = {s};

        Cursor c = db.rawQuery("SELECT p.id, p.descricao, p.categoria_id, (select nome from categoria c where c.id = p.categoria_id), p.unidade_medida_id, (select descricao from unidade_medida um where um.id = p.unidade_medida_id), flag_ativo FROM produto p WHERE p.flag_ativo = 1 order by ?;", ordem);

        if (c.moveToFirst()) {
            do {
                produtoList.add(new ProdutoModel(c.getLong(0), c.getString(1), new CategoriaModel(c.getLong(2), c.getString(3)), new UnidadeMedidaModel(c.getLong(4), c.getString(5)), c.getInt(6) > 0));
            } while (c.moveToNext());
        }

        c.close();

        return produtoList;

    }

}
