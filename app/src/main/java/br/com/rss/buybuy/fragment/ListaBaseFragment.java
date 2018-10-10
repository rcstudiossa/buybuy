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

import br.com.rss.buybuy.adapter.ListaBaseAdapter;
import br.com.rss.buybuy.business.ListaBaseBS;
import br.com.rss.buybuy.model.ListaBaseModel;

public class ListaBaseFragment extends TemplateFragment {

    private List<ListaBaseModel> mList;
    private ListaBaseBS listaBaseBS;
    private ListaBaseAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_base, container, false);

        mRecyclerView = view.findViewById(R.id.rv_lista);
        configRV();

        listaBaseBS = new ListaBaseBS(getActivity());

        mList = listaBaseBS.pesquisar(new ListaBaseModel());
        adapter = new ListaBaseAdapter(getActivity(), mList);
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

        mList = listaBaseBS.pesquisar(new ListaBaseModel());
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

        mList = listaBaseBS.pesquisarAtivos(query);
        atualizarLista();

    }

    protected Class getCrudActivity() {

        return null;//ListaBaseActivity.class;

    }


}
