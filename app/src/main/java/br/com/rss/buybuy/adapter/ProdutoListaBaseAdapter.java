package br.com.rss.buybuy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import br.com.rss.buybuy.R;
import br.com.rss.buybuy.activity.ProdutoListaBaseActivity;
import br.com.rss.buybuy.business.ProdutoListaBaseBS;
import br.com.rss.buybuy.model.ProdutoListaBaseModel;

public class ProdutoListaBaseAdapter extends TemplateAdapter<ProdutoListaBaseModel> {

    public ProdutoListaBaseAdapter(Context c, List<ProdutoListaBaseModel> l){
        super(c,l);
    }

    @Override
    public void onBindViewHolder(TemplateAdapter.ViewHolder categoriaVH, final int position) {

        categoriaVH.descricaoLinha1.setText(String.format(Locale.getDefault(), "%s", mList.get(position).getProdutoModel().getDescricao()));
        categoriaVH.descricaoLinha2.setText(String.format(Locale.getDefault(), "%s %s, %s", mList.get(position).getQuantidade(), mList.get(position).getProdutoModel().getUnidadeMedidaModel().getAbreviacao(), mList.get(position).getFrequenciaModel().getDescricao()));

        categoriaVH.editBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), ProdutoListaBaseActivity.class);
                intent.putExtra("registro", mList.get(position));

                view.getContext().startActivity(intent);

            }

        });

        categoriaVH.deleteBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeListItem(view, position);
            }
        });

    }

    @Override
    public void restaurarRegistro(View view, int position) {

        ProdutoListaBaseBS categoriaBS = new ProdutoListaBaseBS(view.getContext());
        categoriaBS.restaurar(linhaSelecionada);
        mList.add(position, linhaSelecionada);
        notifyItemInserted(position);

    }

    @Override
    public void removerRegistro(View view, int position) {

        ProdutoListaBaseBS categoriaBS = new ProdutoListaBaseBS(view.getContext());
        linhaSelecionada = mList.get(position);
        categoriaBS.excluir(linhaSelecionada);
        mList.remove(position);
        notifyItemRemoved(position);

    }

    protected int getResource() {
        return R.layout.item_produto_lista_base;
    }

}


