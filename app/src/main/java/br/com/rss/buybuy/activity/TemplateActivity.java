package br.com.rss.buybuy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import br.com.rss.buybuy.R;
import br.com.rss.buybuy.business.CrudBS;


/**
 * Created by Roque Souza 28/09/2018.
 */

public abstract class TemplateActivity<T> extends AppCompatActivity {

    protected T crudModel;
    protected Button btnGravar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.instanciarComponentes();

        this.configToolbar();

    }

    protected void instanciarComponentes() {

        this.btnGravar = findViewById(R.id.btn_gravar);
        this.toolbar = findViewById(R.id.toolbar);

        this.btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gravar();
            }
        });

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

    protected boolean validaCampos() {
        return true;
    }

    protected void inserir() {

        if (!validaCampos()) {
            return;
        }

        getCrudBS().inserir(this.crudModel);
    }

    protected void alterar() {

        if (!validaCampos()) {
            return;
        }

        getCrudBS().alterar(this.crudModel);
    }

    protected void excluir() {
        getCrudBS().excluir(this.crudModel);
    }

    protected abstract CrudBS getCrudBS();

    protected abstract void gravar();

    protected abstract void voltar();


}
