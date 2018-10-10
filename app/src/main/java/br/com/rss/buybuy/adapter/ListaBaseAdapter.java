package br.com.rss.buybuy.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.List;
import java.util.Locale;

import br.com.rss.buybuy.R;

import br.com.rss.buybuy.business.ListaBaseBS;
import br.com.rss.buybuy.model.ListaBaseModel;

public class ListaBaseAdapter extends TemplateAdapter<ListaBaseModel> {

    public ListaBaseAdapter(Context c, List<ListaBaseModel> l){
        super(c,l);
    }

    @Override
    public void onBindViewHolder(TemplateAdapter.ViewHolder categoriaVH, final int position) {

        categoriaVH.descricaoLinha1.setText(String.format(Locale.getDefault(), "%s", mList.get(position).getDescricao()));
        categoriaVH.descricaoLinha2.setText(String.format(Locale.getDefault(), "%s", mList.get(position).getFlagRecorrente() ? "Uso Recorrente" : null));

        categoriaVH.editBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Intent intent = new Intent(view.getContext(), ListaBaseActivity.class);
                //intent.putExtra("registro", mList.get(position));

                //view.getContext().startActivity(intent);

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

        ListaBaseBS categoriaBS = new ListaBaseBS(view.getContext());
        categoriaBS.restaurar(linhaSelecionada);
        mList.add(position, linhaSelecionada);
        notifyItemInserted(position);

    }

    @Override
    public void removerRegistro(View view, int position) {

        ListaBaseBS categoriaBS = new ListaBaseBS(view.getContext());
        linhaSelecionada = mList.get(position);
        categoriaBS.excluir(linhaSelecionada);
        mList.remove(position);
        notifyItemRemoved(position);

    }

    protected int getResource() {
        return R.layout.item_lista_base;
    }

}


