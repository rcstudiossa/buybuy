package br.com.rss.buybuy.business;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.rss.buybuy.dao.BuyBuyDbHelper;
import br.com.rss.buybuy.dao.ProdutoListaBaseDAO;
import br.com.rss.buybuy.dao.ProdutoDAO;
import br.com.rss.buybuy.model.ProdutoListaBaseModel;
import br.com.rss.buybuy.model.ProdutoModel;
import br.com.rss.buybuy.util.Utilitario;

/**
 * Created by Roque Souza on 27/09/2018.
 */

public class ProdutoListaBaseBS extends CrudBS<ProdutoListaBaseModel>{

    ProdutoListaBaseDAO produtoListaBaseDAO;
    BuyBuyDbHelper buyBuyDbHelper;

    public ProdutoListaBaseBS() {
    }

    public ProdutoListaBaseBS(Context context) {

        this.buyBuyDbHelper = new BuyBuyDbHelper(context);
        this.produtoListaBaseDAO = new ProdutoListaBaseDAO(this.buyBuyDbHelper);

    }

    protected ProdutoListaBaseDAO getCrudDAO() {

        return this.produtoListaBaseDAO;

    }

    public void gravar(ProdutoListaBaseModel produtoListaBaseModel) {

        if (Utilitario.isEmpty(produtoListaBaseModel.getId())) {
            produtoListaBaseDAO.inserir(produtoListaBaseModel);
        } else {
            produtoListaBaseDAO.alterar(produtoListaBaseModel);
        }

    }

    public List<ProdutoListaBaseModel> pesquisar(String query) {

        if (Utilitario.isEmpty(query)) {
           return produtoListaBaseDAO.pesquisar(new ProdutoListaBaseModel());
        }

        return produtoListaBaseDAO.pesquisar(query);

    }

}
