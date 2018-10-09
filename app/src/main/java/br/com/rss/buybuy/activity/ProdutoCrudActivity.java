package br.com.rss.buybuy.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import br.com.rss.buybuy.R;
import br.com.rss.buybuy.business.CategoriaBS;
import br.com.rss.buybuy.business.ComboBS;
import br.com.rss.buybuy.business.ProdutoBS;
import br.com.rss.buybuy.business.CrudBS;
import br.com.rss.buybuy.model.CategoriaModel;
import br.com.rss.buybuy.model.ProdutoModel;
import br.com.rss.buybuy.model.UnidadeMedidaModel;
import br.com.rss.buybuy.util.Utilitario;

public class ProdutoCrudActivity extends TemplateActivity<ProdutoModel> {

    private ProdutoBS produtoBS;
    private EditText etDescricao;
    private Spinner spCategoria;
    private Spinner spUnidadeMedida;
    private CheckBox mFlagAtivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_produto_crud);

        super.onCreate(savedInstanceState);

        this.produtoBS = new ProdutoBS(getApplicationContext());

    }

    @Override
    protected void instanciarComponentes() {

        super.instanciarComponentes();

        this.etDescricao = findViewById(R.id.et_descricao);
        this.spCategoria = findViewById(R.id.spinner_categoria);
        this.spUnidadeMedida = findViewById(R.id.spinner_unidade_medida);
        this.mFlagAtivo = findViewById(R.id.flag_ativo);

        this.carregarSpinners();

        this.instanciarProdutoModel();


    }



    private void instanciarProdutoModel() {

        this.crudModel = (ProdutoModel) getIntent().getSerializableExtra("registro");

        if (this.crudModel == null) {
            this.crudModel = new ProdutoModel();
            mFlagAtivo.setChecked(Boolean.TRUE);
        } else {
            etDescricao.setText(this.crudModel.getDescricao());
            spCategoria.setSelection(new CategoriaBS(getApplicationContext()).pesquisarAtivos().indexOf(this.crudModel.getCategoriaModel()));
            spUnidadeMedida.setSelection(new ComboBS(getApplicationContext()).pesquisarUnidades().indexOf(this.crudModel.getUnidadeMedidaModel()));
            mFlagAtivo.setChecked(this.crudModel.getFlagAtivo());
        }

    }

    private void carregarSpinners() {

        ArrayAdapter<CategoriaModel> spCategoriaAdapter;

        spCategoriaAdapter = new ArrayAdapter<>(this, R.layout.spinner_item_habilitado, new CategoriaBS(getApplicationContext()).pesquisarAtivos());
        spCategoriaAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spCategoria.setAdapter(spCategoriaAdapter);

        ArrayAdapter<UnidadeMedidaModel> spUnidadeAdapter;

        spUnidadeAdapter = new ArrayAdapter<>(this, R.layout.spinner_item_habilitado, new ComboBS(getApplicationContext()).pesquisarUnidades());
        spUnidadeAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spUnidadeMedida.setAdapter(spUnidadeAdapter);

    }


    @Override
    protected boolean validaCampos() {

        boolean valido = true;

        String aviso = "";

        if (Utilitario.isEmpty(etDescricao.getText().toString())) {
            aviso = Utilitario.addAviso("Preencha o nome do produto", aviso);
            valido = false;
        }

        if (!aviso.isEmpty()) {
            Utilitario.alertar(ProdutoCrudActivity.this, aviso);
        }

        return valido;
    }

    protected void gravar() {

        if (!validaCampos()) {
            return;
        }

        this.crudModel.setDescricao(etDescricao.getText().toString());
        this.crudModel.setCategoriaModel((CategoriaModel) spCategoria.getSelectedItem());
        this.crudModel.setUnidadeMedidaModel((UnidadeMedidaModel) spUnidadeMedida.getSelectedItem());
        this.crudModel.setFlagAtivo(mFlagAtivo.isChecked());

        produtoBS.gravar(this.crudModel);

        Utilitario.avisoSucesso(getApplicationContext());

        voltar();

    }

    protected CrudBS getCrudBS() {
        return produtoBS;
    }

    protected void voltar() {

        finish();

    }

}
