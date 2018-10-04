package br.com.rss.buybuy.business;

import android.content.Context;

import java.util.List;

import br.com.rss.buybuy.dao.BuyBuyDbHelper;
import br.com.rss.buybuy.dao.ProdutoDAO;
import br.com.rss.buybuy.model.ProdutoModel;
import br.com.rss.buybuy.util.Utilitario;

/**
 * Created by Roque Souza on 27/09/2018.
 */

public class ProdutoBS extends CrudBS<ProdutoModel>{

    ProdutoDAO produtoDAO;

    public ProdutoBS() {
    }

    public ProdutoBS(Context context) {

        BuyBuyDbHelper buyBuyDbHelper = new BuyBuyDbHelper(context);
        this.produtoDAO = new ProdutoDAO(buyBuyDbHelper);

    }

    protected ProdutoDAO getCrudDAO() {

        return this.produtoDAO;

    }

    public List<ProdutoModel> pesquisarAtivos(Boolean ordenarPorCategoria) {

        return produtoDAO.pesquisarAtivos(ordenarPorCategoria);

    }

    public List<ProdutoModel> pesquisar(String query) {

        if (Utilitario.isEmpty(query)) {
           return produtoDAO.pesquisarAtivos(true);
        }

        return produtoDAO.pesquisar(query);

    }

    public void gravar(ProdutoModel produtoModel) {

        if (Utilitario.isEmpty(produtoModel.getId())) {
            produtoDAO.inserir(produtoModel);
        } else {
            produtoDAO.alterar(produtoModel);
        }

    }

}
