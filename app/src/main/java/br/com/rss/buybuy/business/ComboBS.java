package br.com.rss.buybuy.business;

import android.content.Context;

import java.util.List;

import br.com.rss.buybuy.dao.BuyBuyDbHelper;
import br.com.rss.buybuy.dao.CategoriaDAO;
import br.com.rss.buybuy.dao.FrequenciaDAO;
import br.com.rss.buybuy.dao.UnidadeMedidaDAO;
import br.com.rss.buybuy.model.CategoriaModel;
import br.com.rss.buybuy.model.FrequenciaModel;
import br.com.rss.buybuy.model.UnidadeMedidaModel;
import br.com.rss.buybuy.util.Utilitario;

/**
 * Created by Roque Souza on 27/09/2018.
 */

public class ComboBS {

    BuyBuyDbHelper buyBuyDbHelper;

    public ComboBS(Context context) {
        this.buyBuyDbHelper = new BuyBuyDbHelper(context);
    }

    public List<FrequenciaModel> pesquisarFrequencias() {

        return new FrequenciaDAO(this.buyBuyDbHelper).pesquisar();

    }

    public List<UnidadeMedidaModel> pesquisarUnidades() {

        return new UnidadeMedidaDAO(this.buyBuyDbHelper).pesquisar();

    }

}
