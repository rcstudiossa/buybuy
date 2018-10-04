package br.com.rss.buybuy.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.rss.buybuy.model.CategoriaModel;

/**
 * Created by Rodrigo Costa on 11/12/2017.
 */

public interface CrudDAO<T> {

    public void alterar(T crudModel);

    public void inserir(T crudModel);

    public void excluir(T crudModel);

    public void restaurar(T crudModel);

    public List<T> pesquisar(T crudModel);

}
