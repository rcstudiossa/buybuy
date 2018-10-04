package br.com.rss.buybuy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import java.util.List;

import br.com.rss.buybuy.R;
import br.com.rss.buybuy.business.ProdutoBS;
import br.com.rss.buybuy.business.ProdutoListaBaseBS;
import br.com.rss.buybuy.business.CrudBS;
import br.com.rss.buybuy.fragment.ProdutoFragment;
import br.com.rss.buybuy.model.ProdutoListaBaseModel;
import br.com.rss.buybuy.model.ProdutoModel;
import br.com.rss.buybuy.util.Utilitario;

public class ProdutoListaBaseActivity extends TemplateActivity<ProdutoListaBaseModel> {

    private ProdutoListaBaseBS produtoListaBaseBS;
    private ProdutoModel produtoModel;
    private ImageView ivListarProdutos;
    private AutoCompleteTextView etProdutos;


    public ProdutoListaBaseActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_produto_lista_base_crud);

        super.onCreate(savedInstanceState);

        this.produtoListaBaseBS = new ProdutoListaBaseBS(getApplicationContext());

    }

    @Override
    protected void instanciarComponentes() {

        super.instanciarComponentes();

        this.etProdutos = findViewById(R.id.et_produtos);

        this.ivListarProdutos = findViewById(R.id.iv_listar_produtos);

        this.instanciarProdutoListaBaseModel();

        this.carregarAdapters();

        this.configListeners();

    }

    private void carregarAdapters() {

        ArrayAdapter<ProdutoModel> spProdutoAdapter;

        List<ProdutoModel> produtos = new ProdutoBS(ProdutoListaBaseActivity.this).pesquisarAtivos(false);

        spProdutoAdapter = new ArrayAdapter<>(this, R.layout.spinner_dropdown_item, produtos);
        etProdutos.setAdapter(spProdutoAdapter);
        etProdutos.setThreshold(1);

    }

    private void configListeners() {

        etProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                produtoModel = ((ProdutoModel) etProdutos.getAdapter().getItem(position));
            }
        });

        etProdutos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                produtoModel = null;
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        etProdutos.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
            if (Utilitario.isEmpty(produtoModel)) {
                etProdutos.getText().clear();
            }
            }
        });

        ivListarProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProdutoListaBaseActivity.this, ProdutoFragment.class);
                startActivity(intent);
            }
        });

    }

    private void instanciarProdutoListaBaseModel() {

        this.crudModel = (ProdutoListaBaseModel) getIntent().getSerializableExtra("registro");

        if (this.crudModel == null) {
            this.crudModel = new ProdutoListaBaseModel();
        } else {

            if (!Utilitario.isEmpty(this.crudModel.getProdutoModel()) && !Utilitario.isEmpty(this.crudModel.getProdutoModel().getId())) {
                etProdutos.setSelection(((ArrayAdapter)etProdutos.getAdapter()).getPosition(this.crudModel.getProdutoModel()));
            }

        }

    }

    @Override
    protected boolean validaCampos() {

        boolean valido = true;

        String aviso = "";

        if (Utilitario.isEmpty(produtoModel)) {
            aviso = Utilitario.addAviso("Selecione o produto", aviso);
            valido = false;
        }

        if (!aviso.isEmpty()) {
            Utilitario.alertar(ProdutoListaBaseActivity.this, aviso);
        }

        return valido;
    }

    protected void gravar() {

        if (!validaCampos()) {
            return;
        }

        this.crudModel.setProdutoModel(produtoModel);

        produtoListaBaseBS.gravar(this.crudModel);

        Utilitario.avisoSucesso(getApplicationContext());

        voltar();

    }

    protected CrudBS getCrudBS() {
        return produtoListaBaseBS;
    }

    protected void voltar() {

        finish();

    }

}
