package br.com.rss.buybuy.model;

import java.util.List;

/**
 * Created by Rodrigo Costa on 21/12/2017.
 */

public class ProdutoListaBaseModel extends BaseModel {

    private ProdutoModel produtoModel;
    private Double quantidade;
    private FrequenciaModel frequenciaModel;

    public ProdutoListaBaseModel(){}

    public ProdutoListaBaseModel(Long id, ProdutoModel produtoModel, Double quantidade, FrequenciaModel frequenciaModel) {
        this.id = id;
        this.produtoModel = produtoModel;
        this.quantidade = quantidade;
        this.frequenciaModel = frequenciaModel;
    }

    public ProdutoModel getProdutoModel() {
        return produtoModel;
    }

    public void setProdutoModel(ProdutoModel produtoModel) {
        this.produtoModel = produtoModel;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public FrequenciaModel getFrequenciaModel() {
        return frequenciaModel;
    }

    public void setFrequenciaModel(FrequenciaModel frequenciaModel) {
        this.frequenciaModel = frequenciaModel;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(this.produtoModel.getDescricao()).append(", ").append(this.produtoModel.getUnidadeMedidaModel().getDescricao());

        return sb.toString();

    }

}
