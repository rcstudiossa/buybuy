package br.com.rss.buybuy.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import br.com.rss.buybuy.R;
import br.com.rss.buybuy.activity.MainActivity;
import br.com.rss.buybuy.util.Utilitario;

public abstract class TemplateFragment extends Fragment implements SearchView.OnQueryTextListener {

    protected RecyclerView mRecyclerView;
    protected FloatingActionButton fabNovo;
    protected SearchView searchView;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        this.fabNovo = getView().findViewById(R.id.fab_novo);
        this.actionFab();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);

        searchView = (SearchView) menu.findItem(R.id.search_bar).getActionView();
        searchView.setOnQueryTextListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        /*if (item.getItemId() == R.id.action_export) {
            exportar();
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        pesquisarAtivos(query);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        if (Utilitario.isEmpty(newText)) {
            return this.onQueryTextSubmit(newText);
        }

        return false;
    }

    protected void configRV() {

        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

    }

    protected void actionFab() {

        this.fabNovo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), getCrudActivity());
                startActivity(intent);

            }
        });

    }

    protected abstract Class getCrudActivity();

    protected abstract void pesquisarAtivos(String query);

}
