package br.com.rss.buybuy.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.List;
import java.util.Locale;

import br.com.rss.buybuy.R;
import br.com.rss.buybuy.activity.ProdutoActivity;
import br.com.rss.buybuy.business.ProdutoBS;
import br.com.rss.buybuy.model.ProdutoModel;

public class ProdutoAdapter extends TemplateAdapter<ProdutoModel> {

    public ProdutoAdapter(Context c, List<ProdutoModel> l){
        super(c,l);
    }

    @Override
    public void onBindViewHolder(TemplateAdapter.ViewHolder categoriaVH, final int position) {

        categoriaVH.nome.setText(String.format(Locale.getDefault(), "%s", mList.get(position).getDescricao()));

        categoriaVH.editBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), ProdutoActivity.class);
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

        ProdutoBS categoriaBS = new ProdutoBS(view.getContext());
        categoriaBS.restaurar(linhaSelecionada);
        mList.add(position, linhaSelecionada);
        notifyItemInserted(position);

    }

    @Override
    public void removerRegistro(View view, int position) {

        ProdutoBS categoriaBS = new ProdutoBS(view.getContext());
        linhaSelecionada = mList.get(position);
        categoriaBS.excluir(linhaSelecionada);
        mList.remove(position);
        notifyItemRemoved(position);

    }

    protected int getResource() {
        return R.layout.item_produto;
    }

}


