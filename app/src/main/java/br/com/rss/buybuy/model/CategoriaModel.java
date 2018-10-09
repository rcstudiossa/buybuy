package br.com.rss.buybuy.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rodrigo Costa on 21/12/2017.
 */

public class CategoriaModel extends BaseModel {

    private String nome;
    private List<ProdutoModel> produtos;

    public CategoriaModel(){}

    public CategoriaModel(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CategoriaModel(Long id, String nome, Boolean flagAtivo) {
        this.id = id;
        this.nome = nome;
        this.flagAtivo = flagAtivo;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ProdutoModel> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoModel> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(this.nome);

        return sb.toString();

    }
}
