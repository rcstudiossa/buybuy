package br.com.rss.buybuy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import br.com.rss.buybuy.R;
import br.com.rss.buybuy.adapter.CategoriaProdutoAdapter;
import br.com.rss.buybuy.adapter.ProdutoAdapter;
import br.com.rss.buybuy.business.CategoriaBS;
import br.com.rss.buybuy.model.CategoriaModel;
import br.com.rss.buybuy.model.ProdutoListaBaseModel;
import br.com.rss.buybuy.util.Utilitario;

public class ProdutoRVActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, ProdutoAdapter.CallbackInterface{

    private List<CategoriaModel> mList;
    private CategoriaBS categoriaBS;
    private CategoriaProdutoAdapter adapter;
    private Toolbar toolbar;
    protected RecyclerView mRecyclerView;
    protected FloatingActionButton fabNovo;
    protected SearchView searchView;

    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_rv_produto);

        super.onCreate(savedInstanceState);

        this.instanciarComponentes();

        this.configToolbar();

        this.actionFab();

    }

    @Override
    public void onResume() {

        super.onResume();

        mList = categoriaBS.pesquisarCategoriasProdutos();
        atualizarLista();

    }

    private void instanciarComponentes() {

        this.toolbar = findViewById(R.id.toolbar);

        this.fabNovo = findViewById(R.id.fab_novo);

        mRecyclerView = findViewById(R.id.rv_lista_categorias);
        configRV();

        categoriaBS = new CategoriaBS(this);

        mList = categoriaBS.pesquisarCategoriasProdutos();
        adapter = new CategoriaProdutoAdapter(this, mList);
        mRecyclerView.setAdapter(adapter);

    }

    private void configToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltar();
            }
        });

    }

    protected void actionFab() {

        this.fabNovo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            Intent intent = new Intent(ProdutoRVActivity.this, ProdutoCrudActivity.class);
            startActivity(intent);

            }
        });

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

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

    }

    private void pesquisarAtivos(String query) {

        mList = categoriaBS.pesquisarCategoriasProdutos(query);
        atualizarLista();

    }

    private void atualizarLista() {
        adapter.setList(mList);
        adapter.notifyDataSetChanged();
    }

    private void voltar() {

        finish();

    }

    @Override
    public void onHandleSelection(int position, ProdutoListaBaseModel model) {

        Intent intent = new Intent();
        intent.putExtra("registro", model);

        setResult(RESULT_OK, intent);

        finish();

    }


}
