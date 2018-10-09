package br.com.rss.buybuy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.List;

import br.com.rss.buybuy.R;
import br.com.rss.buybuy.adapter.ProdutoAdapter;
import br.com.rss.buybuy.business.ComboBS;
import br.com.rss.buybuy.business.ProdutoBS;
import br.com.rss.buybuy.business.ProdutoListaBaseBS;
import br.com.rss.buybuy.business.CrudBS;
import br.com.rss.buybuy.model.FrequenciaModel;
import br.com.rss.buybuy.model.ProdutoListaBaseModel;
import br.com.rss.buybuy.model.ProdutoModel;
import br.com.rss.buybuy.util.Utilitario;

public class ProdutoListaBaseActivity extends TemplateActivity<ProdutoListaBaseModel> {

    private ProdutoListaBaseBS produtoListaBaseBS;
    private ProdutoModel produtoModel;
    private EditText etQuantidade;
    private Spinner spFrequencia;
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

        this.etQuantidade = findViewById(R.id.et_quantidade);

        this.spFrequencia = findViewById(R.id.spinner_frequencia);

        this.carregarAdapters();

        this.configListeners();

        this.instanciarProdutoListaBaseModel();

    }

    private void carregarAdapters() {

        ArrayAdapter<ProdutoModel> spProdutoAdapter;
        ArrayAdapter<FrequenciaModel> spFrequenciaAdapter;

        List<ProdutoModel> produtos = new ProdutoBS(ProdutoListaBaseActivity.this).pesquisarAtivos(false);

        spProdutoAdapter = new ArrayAdapter<>(this, R.layout.spinner_dropdown_item, produtos);
        etProdutos.setAdapter(spProdutoAdapter);
        etProdutos.setThreshold(1);

        List<FrequenciaModel> frequencias = new ComboBS(ProdutoListaBaseActivity.this).pesquisarFrequencias();

        spFrequenciaAdapter = new ArrayAdapter<>(this, R.layout.spinner_dropdown_item, frequencias);
        spFrequencia.setAdapter(spFrequenciaAdapter);

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
                Intent intent = new Intent(ProdutoListaBaseActivity.this, CategoriaProdutoRVActivity.class);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // Verfica se o requestCode é o mesmo que foi passado
        if(requestCode==1 && resultCode == RESULT_OK)
        {

            this.crudModel = (ProdutoListaBaseModel) data.getSerializableExtra("registro");

            if (!Utilitario.isEmpty(this.crudModel.getProdutoModel()) && !Utilitario.isEmpty(this.crudModel.getProdutoModel().getId())) {
                etProdutos.setText(this.crudModel.getProdutoModel().getDescricao());
                this.produtoModel = this.crudModel.getProdutoModel();
            }

            if (!Utilitario.isEmpty(this.crudModel.getQuantidade())) {
                etQuantidade.setText(this.crudModel.getQuantidade().toString());
            }

            if (!Utilitario.isEmpty(this.crudModel.getFrequenciaModel()) && !Utilitario.isEmpty(this.crudModel.getFrequenciaModel().getId())) {
                spFrequencia.setSelection(((ArrayAdapter)spFrequencia.getAdapter()).getPosition(this.crudModel.getFrequenciaModel()));
            }

        }
    }

    private void instanciarProdutoListaBaseModel() {

        //a princípio não terá edição da lista base. O código está pronto mas não cairá vindo de edição.

        this.crudModel = (ProdutoListaBaseModel) getIntent().getSerializableExtra("registro");

        if (this.crudModel == null) {
            this.crudModel = new ProdutoListaBaseModel();
        } else {

            if (!Utilitario.isEmpty(this.crudModel.getProdutoModel()) && !Utilitario.isEmpty(this.crudModel.getProdutoModel().getId())) {
                etProdutos.setText(this.crudModel.getProdutoModel().getDescricao());
                this.produtoModel = this.crudModel.getProdutoModel();
            }

            if (!Utilitario.isEmpty(this.crudModel.getQuantidade())) {
                etQuantidade.setText(this.crudModel.getQuantidade().toString());
            }

            if (!Utilitario.isEmpty(this.crudModel.getFrequenciaModel()) && !Utilitario.isEmpty(this.crudModel.getFrequenciaModel().getId())) {
                spFrequencia.setSelection(((ArrayAdapter)spFrequencia.getAdapter()).getPosition(this.crudModel.getFrequenciaModel()));
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

        if (Utilitario.isEmpty(etQuantidade.getText())) {
            aviso = Utilitario.addAviso("Preencha a quantidade", aviso);
            valido = false;
        }

        if (Utilitario.isEmpty(spFrequencia.getSelectedItem()) || Utilitario.isEmpty(((FrequenciaModel) spFrequencia.getSelectedItem()).getId())) {
            aviso = Utilitario.addAviso("Selecione a frequência", aviso);
            valido = false;
        }

        if (!aviso.isEmpty()) {
            Utilitario.alertar(ProdutoListaBaseActivity.this, aviso);
        }

        return valido;
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.instanciarProdutoListaBaseModel();
    }

    protected void gravar() {

        if (!validaCampos()) {
            return;
        }

        this.crudModel.setProdutoModel(this.produtoModel);
        this.crudModel.setQuantidade(Utilitario.isEmpty(etQuantidade.getText().toString()) ? null : new Double(etQuantidade.getText().toString()));
        this.crudModel.setFrequenciaModel((FrequenciaModel) spFrequencia.getSelectedItem());

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
