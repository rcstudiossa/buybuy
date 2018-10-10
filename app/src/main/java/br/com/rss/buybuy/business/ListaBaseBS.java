package br.com.rss.buybuy.business;

import android.content.Context;

import java.util.List;

import br.com.rss.buybuy.dao.BuyBuyDbHelper;
import br.com.rss.buybuy.dao.ListaBaseDAO;
import br.com.rss.buybuy.dao.ProdutoListaBaseDAO;
import br.com.rss.buybuy.model.ListaBaseModel;
import br.com.rss.buybuy.util.Utilitario;

/**
 * Created by Roque Souza on 27/09/2018.
 */

public class ListaBaseBS extends CrudBS<ListaBaseModel>{

    ListaBaseDAO listaBaseDAO;
    BuyBuyDbHelper buyBuyDbHelper;

    public ListaBaseBS() {
    }

    public ListaBaseBS(Context context) {

        this.buyBuyDbHelper = new BuyBuyDbHelper(context);
        this.listaBaseDAO = new ListaBaseDAO(this.buyBuyDbHelper);

    }

    protected ListaBaseDAO getCrudDAO() {

        return this.listaBaseDAO;

    }

    public void gravar(ListaBaseModel listaBaseModel) {

        if (Utilitario.isEmpty(listaBaseModel.getId())) {
            listaBaseDAO.inserir(listaBaseModel);
        } else {
            listaBaseDAO.alterar(listaBaseModel);
        }

    }

    public List<ListaBaseModel> pesquisarAtivos(String query) {

        if (Utilitario.isEmpty(query)) {
           return listaBaseDAO.pesquisar(new ListaBaseModel());
        }

        return listaBaseDAO.pesquisarAtivos(query);

    }

}
