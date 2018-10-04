package br.com.rss.buybuy.business;

import android.content.Context;

import java.io.Serializable;
import java.util.List;

import br.com.rss.buybuy.dao.BuyBuyDbHelper;
import br.com.rss.buybuy.dao.CategoriaDAO;
import br.com.rss.buybuy.dao.CrudDAO;
import br.com.rss.buybuy.model.CategoriaModel;
import br.com.rss.buybuy.util.Utilitario;

/**
 * Created by Rodrigo Costa on 11/12/2017.
 */

public abstract class CrudBS<T> {

    protected abstract CrudDAO<T> getCrudDAO();

    public void alterar(final T crudModel) {
        getCrudDAO().alterar(crudModel);
    }

    public void inserir(final T crudModel) {
        getCrudDAO().inserir(crudModel);
    }

    public void excluir(final T crudModel) {
        getCrudDAO().excluir(crudModel);
    }

    public void restaurar(final T crudModel) { getCrudDAO().restaurar(crudModel); }

    public List<T> pesquisar(final T crudModel) {
        return getCrudDAO().pesquisar(crudModel);
    }

}
