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
import br.com.rss.buybuy.activity.CategoriaCrudActivity;
import br.com.rss.buybuy.adapter.CategoriaAdapter;
import br.com.rss.buybuy.business.CategoriaBS;
import br.com.rss.buybuy.model.CategoriaModel;

public class CategoriaFragment extends TemplateFragment {

    private List<CategoriaModel> mList;
    private CategoriaBS categoriaBS;
    private CategoriaAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_categoria, container, false);

        mRecyclerView = view.findViewById(R.id.rv_lista);
        configRV();

        categoriaBS = new CategoriaBS(getActivity());

        mList = categoriaBS.pesquisarAtivos();
        adapter = new CategoriaAdapter(getActivity(), mList);
        mRecyclerView.setAdapter(adapter);

        return view;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(R.string.activity_categoria);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);

        searchView.setQueryHint(getString(R.string.prompt_pesquisar_categoria));

    }

    protected void pesquisarAtivos(String query) {

        mList = categoriaBS.pesquisarAtivos(query);
        adapter.setList(mList);
        adapter.notifyDataSetChanged();

    }

    protected Class getCrudActivity() {

        return CategoriaCrudActivity.class;

    }


}
