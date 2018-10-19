package br.com.rss.buybuy.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.rss.buybuy.R;

import br.com.rss.buybuy.activity.ProdutoListaBaseCrudActivity;
import br.com.rss.buybuy.adapter.CategoriaProdutoAdapter;
import br.com.rss.buybuy.adapter.CategoriaProdutoListaBaseAdapter;
import br.com.rss.buybuy.business.CategoriaBS;
import br.com.rss.buybuy.model.CategoriaModel;

public class ProdutoListaBaseFragment extends TemplateFragment {

    private List<CategoriaModel> mList;
    private CategoriaBS categoriaBS;
    private CategoriaProdutoListaBaseAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_produto_lista_base, container, false);

        mRecyclerView = view.findViewById(R.id.rv_lista_categorias);
        configRV();

        this.categoriaBS = new CategoriaBS(getActivity());

        mList = this.categoriaBS.pesquisarCategoriasProdutosListaBase();
        adapter = new CategoriaProdutoListaBaseAdapter(getActivity(), mList);
        mRecyclerView.setAdapter(adapter);

        return view;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(R.string.activity_lista_base);

    }

    @Override
    public void onResume() {

        super.onResume();

        mList = categoriaBS.pesquisarCategoriasProdutosListaBase();
        atualizarLista();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);

        searchView.setQueryHint(getString(R.string.prompt_pesquisar_lista_base));

    }

    private void atualizarLista() {
        adapter.setList(mList);
        adapter.notifyDataSetChanged();
    }

    protected void pesquisarAtivos(String query) {

        mList = categoriaBS.pesquisarCategoriasProdutosListaBase(query);
        atualizarLista();

    }

    protected Class getCrudActivity() {

        return ProdutoListaBaseCrudActivity.class;

    }


}
