package br.com.rss.buybuy.model;

/**
 * Created by Rodrigo Costa on 21/12/2017.
 */

public class UnidadeMedidaModel extends BaseModel {

    private String descricao;

    public UnidadeMedidaModel() {
    }

    public UnidadeMedidaModel(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
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

        sb.append(id).append(": ").append(this.descricao);

        return sb.toString();

    }
}
