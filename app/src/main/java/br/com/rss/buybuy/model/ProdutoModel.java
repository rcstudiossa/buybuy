package br.com.rss.buybuy.model;

import java.io.Serializable;

/**
 * Created by Roque Souza on 27/09/2018.
 */

public class ProdutoModel extends BaseModel {

    private String descricao;
    private CategoriaModel categoriaModel;
    private UnidadeMedidaModel unidadeMedidaModel;

    public ProdutoModel(){}



    public ProdutoModel(Long id, String descricao, UnidadeMedidaModel unidadeMedidaModel) {
        this.id = id;
        this.descricao = descricao;
        this.unidadeMedidaModel = unidadeMedidaModel;
    }

    public ProdutoModel(Long id, String descricao, CategoriaModel categoriaModel, UnidadeMedidaModel unidadeMedidaModel, Boolean flagAtivo) {
        this.id = id;
        this.descricao = descricao;
        this.categoriaModel = categoriaModel;
        this.unidadeMedidaModel = unidadeMedidaModel;
        this.flagAtivo = flagAtivo;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public CategoriaModel getCategoriaModel() {
        return categoriaModel;
    }

    public void setCategoriaModel(CategoriaModel categoriaModel) {
        this.categoriaModel = categoriaModel;
    }

    public UnidadeMedidaModel getUnidadeMedidaModel() {
        return unidadeMedidaModel;
    }

    public void setUnidadeMedidaModel(UnidadeMedidaModel unidadeMedidaModel) {
        this.unidadeMedidaModel = unidadeMedidaModel;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(this.descricao);

        return sb.toString();

    }
}
