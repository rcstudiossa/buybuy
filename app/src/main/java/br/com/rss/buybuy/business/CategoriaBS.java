package br.com.rss.buybuy.business;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.rss.buybuy.dao.BuyBuyDbHelper;
import br.com.rss.buybuy.dao.CategoriaDAO;
import br.com.rss.buybuy.dao.CrudDAO;
import br.com.rss.buybuy.dao.ProdutoDAO;
import br.com.rss.buybuy.model.CategoriaModel;
import br.com.rss.buybuy.model.ProdutoModel;
import br.com.rss.buybuy.util.Utilitario;

/**
 * Created by Roque Souza on 27/09/2018.
 */

public class CategoriaBS extends CrudBS<CategoriaModel>{

    CategoriaDAO categoriaDAO;
    BuyBuyDbHelper buyBuyDbHelper;

    public CategoriaBS() {
    }

    public CategoriaBS(Context context) {

        this.buyBuyDbHelper = new BuyBuyDbHelper(context);
        this.categoriaDAO = new CategoriaDAO(this.buyBuyDbHelper);

    }

    protected CategoriaDAO getCrudDAO() {

        return this.categoriaDAO;

    }

    public void gravar(CategoriaModel categoriaModel) {

        if (Utilitario.isEmpty(categoriaModel.getId())) {
            categoriaDAO.inserir(categoriaModel);
        } else {
            categoriaDAO.alterar(categoriaModel);
        }

    }

    public List<CategoriaModel> pesquisarAtivos() {

        return categoriaDAO.pesquisarAtivos();

    }

    public List<CategoriaModel> pesquisarAtivos(String query) {

        if (Utilitario.isEmpty(query)) {
           return categoriaDAO.pesquisarAtivos();
        }

        return categoriaDAO.pesquisarAtivos(query);

    }



    public List<CategoriaModel> pesquisarProdutos() {

        List<ProdutoModel> produtos = new ProdutoDAO(this.buyBuyDbHelper).pesquisar(new ProdutoModel());

        return getProdutos(produtos);

    }

    public List<CategoriaModel> pesquisarProdutos(String query) {

        List<ProdutoModel> produtos = new ProdutoDAO(this.buyBuyDbHelper).pesquisar(query);

        return getProdutos(produtos);

    }

    private List<CategoriaModel> getProdutos(List<ProdutoModel> produtos) {

        List<CategoriaModel> categorias = new ArrayList<CategoriaModel>();

        CategoriaModel categoriaModel = null;

        for (ProdutoModel produtoModel: produtos) {

            if (!produtoModel.getCategoriaModel().equals(categoriaModel)) {
                categoriaModel = new CategoriaModel(produtoModel.getCategoriaModel().getId(), produtoModel.getCategoriaModel().getNome());
                categoriaModel.setProdutos(new ArrayList<ProdutoModel>());
                categorias.add(categoriaModel);
            }

            categoriaModel.getProdutos().add(produtoModel);

        }

        return categorias;

    }



}
