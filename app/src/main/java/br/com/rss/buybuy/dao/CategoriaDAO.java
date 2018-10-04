package br.com.rss.buybuy.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.rss.buybuy.model.CategoriaModel;

/**
 * Created by Rodrigo Costa on 11/12/2017.
 */

public class CategoriaDAO implements CrudDAO<CategoriaModel> {

    SQLiteDatabase db;

    public CategoriaDAO() {
    }

    public CategoriaDAO(BuyBuyDbHelper buyBuyDbHelper) {
        this.db = buyBuyDbHelper.getWritableDatabase();
    }

    public void alterar(CategoriaModel categoriaModel) {

        Object[] args = {categoriaModel.getNome(), categoriaModel.getFlagAtivo(), categoriaModel.getId()};

        db.execSQL("UPDATE categoria set nome = ?, flag_ativo = ? WHERE id = ?;", args);

    }

    public void inserir(CategoriaModel categoriaModel) {

        Object[] args = {categoriaModel.getNome(), categoriaModel.getFlagAtivo()};

        db.execSQL("INSERT INTO categoria (nome, flag_ativo) VALUES(?, ?);", args);
    }

    public void excluir(CategoriaModel categoriaModel) {

        Object[] args = {Boolean.FALSE, categoriaModel.getId()};

        db.execSQL("UPDATE categoria SET flag_ativo = ? where id = ? ", args);

    }

    public void restaurar(CategoriaModel categoriaModel) {

        Object[] args = {Boolean.TRUE, categoriaModel.getId()};

        db.execSQL("UPDATE categoria SET flag_ativo = ? where id = ? ", args);

    }

    public List<CategoriaModel> pesquisar(CategoriaModel categoriaModel) {

        List<CategoriaModel> categoriaList = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT * FROM categoria order by id;", null);

        if (c.moveToFirst()) {
            do {
                categoriaList.add(new CategoriaModel(c.getLong(0), c.getString(1), c.getInt(2) > 0));
            } while (c.moveToNext());
        }

        c.close();

        return categoriaList;

    }

    public List<CategoriaModel> pesquisarAtivos() {

        List<CategoriaModel> categoriaList = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT * FROM categoria where flag_ativo = 1 order by id;", null);

        if (c.moveToFirst()) {
            do {
                categoriaList.add(new CategoriaModel(c.getLong(0), c.getString(1), c.getInt(2) > 0));
            } while (c.moveToNext());
        }

        c.close();

        return categoriaList;

    }

    public List<CategoriaModel> pesquisarAtivos(String query) {

        List<CategoriaModel> categoriaList = new ArrayList<>();

        String s = "%" + query.toUpperCase() + "%";

        String[] args = {s};

        Cursor c = db.rawQuery("SELECT * FROM categoria where flag_ativo = 1 and upper(nome) like upper(?) order by id;", args);

        if (c.moveToFirst()) {
            do {
                categoriaList.add(new CategoriaModel(c.getLong(0), c.getString(1), c.getInt(2) > 0));
            } while (c.moveToNext());
        }

        c.close();

        return categoriaList;

    }

}
