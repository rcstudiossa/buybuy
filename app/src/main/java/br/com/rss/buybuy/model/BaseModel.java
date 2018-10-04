package br.com.rss.buybuy.model;

import java.io.Serializable;

/**
 * Created by Rodrigo Costa on 21/12/2017.
 */

public abstract class BaseModel implements Serializable {

    protected Long id;
    protected Boolean flagAtivo;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getFlagAtivo() {
        return flagAtivo;
    }
    public void setFlagAtivo(Boolean flagAtivo) {
        this.flagAtivo = flagAtivo;
    }

    public String getSituacao() {
        return this.flagAtivo ? "Ativo" : "Inativo";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseModel categoriaModel = (BaseModel) o;

        return id != null ? id.equals(categoriaModel.id) : categoriaModel.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
