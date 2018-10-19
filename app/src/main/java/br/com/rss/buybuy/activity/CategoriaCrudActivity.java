package br.com.rss.buybuy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import br.com.rss.buybuy.R;
import br.com.rss.buybuy.business.CategoriaBS;
import br.com.rss.buybuy.business.CrudBS;
import br.com.rss.buybuy.model.CategoriaModel;
import br.com.rss.buybuy.util.Utilitario;

public class CategoriaCrudActivity extends TemplateActivity<CategoriaModel> {

    private CategoriaBS categoriaBS;
    private EditText nomeET;
    private CheckBox mFlagAtivo;


    public CategoriaCrudActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_crud_categoria);

        super.onCreate(savedInstanceState);

        this.categoriaBS = new CategoriaBS(getApplicationContext());

    }

    @Override
    protected void instanciarComponentes() {

        super.instanciarComponentes();
        this.nomeET = findViewById(R.id.et_nome_completo);
        this.mFlagAtivo = findViewById(R.id.flag_ativo);
        this.instanciarCategoriaModel();

    }



    private void instanciarCategoriaModel() {

        this.crudModel = (CategoriaModel) getIntent().getSerializableExtra("registro");

        if (this.crudModel == null) {
            this.crudModel = new CategoriaModel();
            mFlagAtivo.setChecked(Boolean.TRUE);
        } else {
            nomeET.setText(this.crudModel.getNome());
            mFlagAtivo.setChecked(this.crudModel.getFlagAtivo());
        }

    }

    @Override
    protected boolean validaCampos() {

        boolean valido = true;

        String aviso = "";

        if (Utilitario.isEmpty(nomeET.getText().toString())) {
            aviso = Utilitario.addAviso("Preencha o nome da categoria", aviso);
            valido = false;
        }

        if (!aviso.isEmpty()) {
            Utilitario.alertar(CategoriaCrudActivity.this, aviso);
        }

        return valido;
    }

    protected void gravar() {

        if (!validaCampos()) {
            return;
        }

        this.crudModel.setNome(nomeET.getText().toString());
        this.crudModel.setFlagAtivo(mFlagAtivo.isChecked());

        categoriaBS.gravar(this.crudModel);

        Utilitario.avisoSucesso(getApplicationContext());

        voltar();

    }

    protected CrudBS getCrudBS() {
        return categoriaBS;
    }

    protected void voltar() {

        Intent intent = new Intent(CategoriaCrudActivity.this, MainActivity.class);
        intent.putExtra("fragment", R.id.nav_categoria);
        startActivity(intent);
        finish();

    }

}
