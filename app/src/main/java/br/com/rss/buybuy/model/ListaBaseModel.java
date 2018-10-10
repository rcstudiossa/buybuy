package br.com.rss.buybuy.model;

/**
 * Created by Rodrigo Costa on 21/12/2017.
 */

public class ListaBaseModel extends BaseModel {

    private String descricao;
    private String icone;
    private Boolean flagRecorrente;

    public ListaBaseModel(){}

    public ListaBaseModel(Long id, String descricao, Boolean flagRecorrente) {
        this.id = id;
        this.descricao = descricao;
        this.flagRecorrente = flagRecorrente;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(this.getDescricao());

        return sb.toString();

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public Boolean getFlagRecorrente() {
        return flagRecorrente;
    }

    public void setFlagRecorrente(Boolean flagRecorrente) {
        this.flagRecorrente = flagRecorrente;
    }
}
