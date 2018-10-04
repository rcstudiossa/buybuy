package br.com.rss.buybuy.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.rss.buybuy.model.FrequenciaModel;

/**
 * Created by Rodrigo Costa on 11/12/2017.
 */

public class FrequenciaDAO {

    SQLiteDatabase db;

    public FrequenciaDAO(BuyBuyDbHelper buyBuyDbHelper) {
        this.db = buyBuyDbHelper.getWritableDatabase();
    }

    public List<FrequenciaModel> pesquisar() {

        List<FrequenciaModel> frequenciaList = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT * FROM frequencia order by ordem;", null);

        if (c.moveToFirst()) {
            do {
                frequenciaList.add(new FrequenciaModel(c.getLong(0), c.getString(1)));
            } while (c.moveToNext());
        }

        c.close();

        return frequenciaList;

    }

}
