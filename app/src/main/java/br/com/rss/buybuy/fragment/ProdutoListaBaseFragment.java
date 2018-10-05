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
import br.com.rss.buybuy.activity.ProdutoListaBaseActivity;
import br.com.rss.buybuy.adapter.ProdutoListaBaseAdapter;
import br.com.rss.buybuy.business.ProdutoListaBaseBS;
import br.com.rss.buybuy.model.ProdutoListaBaseModel;

public class ProdutoListaBaseFragment extends TemplateFragment {

    private List<ProdutoListaBaseModel> mList;
    private ProdutoListaBaseBS produtoListaBaseBS;
    private ProdutoListaBaseAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_produto_lista_base, container, false);

        mRecyclerView = view.findViewById(R.id.rv_lista);
        configRV();

        produtoListaBaseBS = new ProdutoListaBaseBS(getActivity());

        mList = produtoListaBaseBS.pesquisar(new ProdutoListaBaseModel());
        adapter = new ProdutoListaBaseAdapter(getActivity(), mList);
        mRecyclerView.setAdapter(adapter);

        return view;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(R.string.activity_produto_lista_base);

    }

    @Override
    public void onResume() {

        super.onResume();

        mList = produtoListaBaseBS.pesquisar(new ProdutoListaBaseModel());
        atualizarLista();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);

        searchView.setQueryHint(getString(R.string.prompt_pesquisar_produto));

    }

    private void atualizarLista() {
        adapter.setList(mList);
        adapter.notifyDataSetChanged();
    }

    protected void pesquisarAtivos(String query) {

        mList = produtoListaBaseBS.pesquisarAtivos(query);
        atualizarLista();

    }

    protected Class getCrudActivity() {

        return ProdutoListaBaseActivity.class;

    }


}
