package br.com.rss.buybuy.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.rss.buybuy.util.Constantes;

/**
 * Created by Roque Souza on 27/09/2018.
 */

public class BuyBuyDbHelper extends SQLiteOpenHelper {

    public static final String SQL_CREATE_CATEGORIA = "CREATE TABLE IF NOT EXISTS categoria (id INTEGER PRIMARY KEY, nome VARCHAR, flag_ativo BOOLEAN);";
    public static final String SQL_DELETE_CATEGORIA = "DROP TABLE IF EXISTS categoria;";
    public static final String SQL_CREATE_FREQUENCIA = "CREATE TABLE IF NOT EXISTS frequencia (id INTEGER PRIMARY KEY, descricao VARCHAR, dias INTEGER, ordem INTEGER);";
    public static final String SQL_DELETE_FREQUENCIA = "DROP TABLE IF EXISTS frequencia;";
    public static final String SQL_CREATE_UNIDADE_MEDIDA = "CREATE TABLE IF NOT EXISTS unidade_medida (id INTEGER PRIMARY KEY, descricao VARCHAR, abreviacao VARCHAR, ordem INTEGER);";
    public static final String SQL_DELETE_UNIDADE_MEDIDA = "DROP TABLE IF EXISTS unidade_medida;";
    public static final String SQL_CREATE_PRODUTO = "CREATE TABLE IF NOT EXISTS produto (id INTEGER PRIMARY KEY, descricao VARCHAR, categoria_id INTEGER, unidade_medida_id INTEGER, flag_ativo BOOLEAN, FOREIGN KEY(categoria_id) REFERENCES categoria(id), FOREIGN KEY(unidade_medida_id) REFERENCES unidade_medida(id));";
    public static final String SQL_DELETE_PRODUTO = "DROP TABLE IF EXISTS produto;";
    public static final String SQL_CREATE_LISTA_BASE = "CREATE TABLE IF NOT EXISTS lista_base (id INTEGER PRIMARY KEY, produto_id VARCHAR, frequencia_id INTEGER, quantidade DOUBLE, flag_ativo BOOLEAN, FOREIGN KEY(produto_id) REFERENCES produto(id), FOREIGN KEY(frequencia_id) REFERENCES frequencia(id));";
    public static final String SQL_DELETE_LISTA_BASE = "DROP TABLE IF EXISTS lista_base;";

    public static final String SQL_INSERT_USUARIO = "INSERT INTO usuario (id, nome, flag_ativo, usuario, senha, flag_administrador, cnes_id) values (1, 'smpep', 1, 'smpep', 'topsys', 1, 1);";



    public BuyBuyDbHelper(Context context) {
        super(context, Constantes.BUYBUY_DB, null, Constantes.DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_CATEGORIA);
        db.execSQL(SQL_CREATE_FREQUENCIA);
        db.execSQL(SQL_CREATE_UNIDADE_MEDIDA);
        db.execSQL(SQL_CREATE_PRODUTO);
        db.execSQL(SQL_CREATE_LISTA_BASE);

        carregarFrequencias(db);
        carregarUnidades(db);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_CATEGORIA);
        db.execSQL(SQL_DELETE_FREQUENCIA);
        db.execSQL(SQL_DELETE_UNIDADE_MEDIDA);
        db.execSQL(SQL_DELETE_PRODUTO);
        db.execSQL(SQL_DELETE_LISTA_BASE);

        onCreate(db);

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private void carregarFrequencias(SQLiteDatabase db) {

        db.execSQL("INSERT INTO FREQUENCIA (DESCRICAO, DIAS, ORDEM) VALUES ('SEMANAL', 7, 1);");
        db.execSQL("INSERT INTO FREQUENCIA (DESCRICAO, DIAS, ORDEM) VALUES ('QUINZENAL', 15, 2);");
        db.execSQL("INSERT INTO FREQUENCIA (DESCRICAO, DIAS, ORDEM) VALUES ('MENSAL', 30, 3);");
        db.execSQL("INSERT INTO FREQUENCIA (DESCRICAO, DIAS, ORDEM) VALUES ('BIMESTRAL', 60, 4);");
        db.execSQL("INSERT INTO FREQUENCIA (DESCRICAO, DIAS, ORDEM) VALUES ('TRIMESTRAL', 90, 5);");
        db.execSQL("INSERT INTO FREQUENCIA (DESCRICAO, DIAS, ORDEM) VALUES ('SEMESTRAL', 180, 6);");

    }

    private void carregarUnidades(SQLiteDatabase db) {

        db.execSQL("INSERT INTO UNIDADE_MEDIDA (DESCRICAO, ABREVIACAO, ORDEM) VALUES ('UNIDADE', 'UND', 1);");
        db.execSQL("INSERT INTO UNIDADE_MEDIDA (DESCRICAO, ABREVIACAO, ORDEM) VALUES ('QUILOGRAMA', 'KG', 2);");
        db.execSQL("INSERT INTO UNIDADE_MEDIDA (DESCRICAO, ABREVIACAO, ORDEM) VALUES ('GRAMA', 'G', 3);");
        db.execSQL("INSERT INTO UNIDADE_MEDIDA (DESCRICAO, ABREVIACAO, ORDEM) VALUES ('LITRO', 'L', 4);");
        db.execSQL("INSERT INTO UNIDADE_MEDIDA (DESCRICAO, ABREVIACAO, ORDEM) VALUES ('MILILITRO', 'ML', 5);");

    }

}
