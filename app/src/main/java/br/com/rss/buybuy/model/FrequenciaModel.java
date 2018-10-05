package br.com.rss.buybuy.model;

import java.io.Serializable;

/**
 * Created by Roque Souza on 27/09/2018.
 */

public class FrequenciaModel extends BaseModel {

    private String descricao;

    public FrequenciaModel(){}

    public FrequenciaModel(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(this.descricao);

        return sb.toString();

    }
}
