package br.com.rss.buybuy.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.rss.buybuy.model.FrequenciaModel;
import br.com.rss.buybuy.model.ListaBaseModel;
import br.com.rss.buybuy.model.ProdutoModel;
import br.com.rss.buybuy.model.UnidadeMedidaModel;

/**
 * Created by Rodrigo Costa on 11/12/2017.
 */

public class ListaBaseDAO implements CrudDAO<ListaBaseModel> {

    SQLiteDatabase db;

    public ListaBaseDAO() {
    }

    public ListaBaseDAO(BuyBuyDbHelper buyBuyDbHelper) {
        this.db = buyBuyDbHelper.getWritableDatabase();
    }

    public void alterar(ListaBaseModel listaBaseModel) {

    }

    public void inserir(ListaBaseModel listaBaseModel) {

        Object[] args = {listaBaseModel.getDescricao(), listaBaseModel.getIcone(), listaBaseModel.getFlagRecorrente(), Boolean.TRUE};

        db.execSQL("INSERT INTO lista_base (descricao, icone, flag_recorrente, flag_ativo) VALUES(?, ?, ?, ?);", args);
    }

    public void excluir(ListaBaseModel listaBaseModel) {

        Object[] args = {Boolean.FALSE, listaBaseModel.getId()};

        db.execSQL("UPDATE lista_base SET flag_ativo = ? where id = ? ", args);

    }

    public void restaurar(ListaBaseModel listaBaseModel) {

        Object[] args = {Boolean.TRUE, listaBaseModel.getId()};

        db.execSQL("UPDATE lista_base SET flag_ativo = ? where id = ? ", args);

    }

    public List<ListaBaseModel> pesquisar(ListaBaseModel listaBaseModel) {

        List<ListaBaseModel> listas = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT id, decricao, flag_recorrente from lista_base where flag_ativo order by descricao;", null);

        ProdutoModel produtoModel;

        if (c.moveToFirst()) {
            do {

                listas.add(new ListaBaseModel(c.getLong(0), c.getString(1), c.getInt(2) > 0));

            } while (c.moveToNext());
        }

        c.close();

        return listas;

    }

    public List<ListaBaseModel> pesquisarAtivos(String query) {

        String s = "%" + query.toUpperCase() + "%";

        String[] args = {s};

        List<ListaBaseModel> listas = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT id, decricao, flag_recorrente from lista_base where flag_ativo and upper(descricao) like upper(?) order by descricao;", args);

        if (c.moveToFirst()) {
            do {

                listas.add(new ListaBaseModel(c.getLong(0), c.getString(1), c.getInt(2) > 0));

            } while (c.moveToNext());
        }

        c.close();

        return listas;

    }

}
