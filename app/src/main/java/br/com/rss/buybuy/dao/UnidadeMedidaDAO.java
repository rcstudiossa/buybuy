package br.com.rss.buybuy.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.rss.buybuy.model.UnidadeMedidaModel;

/**
 * Created by Rodrigo Costa on 11/12/2017.
 */

public class UnidadeMedidaDAO {

    SQLiteDatabase db;

    public UnidadeMedidaDAO(BuyBuyDbHelper buyBuyDbHelper) {
        this.db = buyBuyDbHelper.getWritableDatabase();
    }

    public List<UnidadeMedidaModel> pesquisar() {

        List<UnidadeMedidaModel> unidadeMedidaList = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT * FROM unidade_medida order by ordem;", null);

        if (c.moveToFirst()) {
            do {
                unidadeMedidaList.add(new UnidadeMedidaModel(c.getLong(0), c.getString(1)));
            } while (c.moveToNext());
        }

        c.close();

        return unidadeMedidaList;

    }

}
