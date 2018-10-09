package br.com.rss.buybuy.model;

/**
 * Created by Rodrigo Costa on 21/12/2017.
 */

public class UnidadeMedidaModel extends BaseModel {

    private String descricao;
    private String abreviacao;

    public UnidadeMedidaModel() {

    }

    public UnidadeMedidaModel(Long id, String abreviacao) {
        this.id = id;
        this.abreviacao = abreviacao;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(this.abreviacao);

        return sb.toString();

    }
}
