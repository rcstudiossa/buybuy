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
import br.com.rss.buybuy.activity.ProdutoCrudActivity;
import br.com.rss.buybuy.adapter.CategoriaProdutoAdapter;
import br.com.rss.buybuy.business.CategoriaBS;
import br.com.rss.buybuy.model.CategoriaModel;

public class ProdutoFragment extends TemplateFragment {

    private List<CategoriaModel> mList;
    private CategoriaBS categoriaBS;
    private CategoriaProdutoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_produto, container, false);

        mRecyclerView = view.findViewById(R.id.rv_lista_categorias);
        configRV();

        categoriaBS = new CategoriaBS(getActivity());

        mList = categoriaBS.pesquisarCategoriasProdutos();
        adapter = new CategoriaProdutoAdapter(getActivity(), mList);
        mRecyclerView.setAdapter(adapter);

        return view;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(R.string.activity_produto);

    }

    @Override
    public void onResume() {

        super.onResume();

        mList = categoriaBS.pesquisarCategoriasProdutos();
        atualizarLista();

    }

    private void atualizarLista() {
        adapter.setList(mList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);

        searchView.setQueryHint(getString(R.string.prompt_pesquisar_produto));

    }

    protected void pesquisarAtivos(String query) {

        mList = categoriaBS.pesquisarCategoriasProdutos(query);
        atualizarLista();

    }

    protected Class getCrudActivity() {

        return ProdutoCrudActivity.class;

    }


}
