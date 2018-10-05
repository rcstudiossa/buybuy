package br.com.rss.buybuy.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import br.com.rss.buybuy.R;

public abstract class TemplateAdapter<T extends Serializable> extends RecyclerView.Adapter<TemplateAdapter.ViewHolder> {

    protected List<T> mList;
    protected LayoutInflater mLayoutInflater;
    protected T linhaSelecionada;

    public TemplateAdapter() {}

    public TemplateAdapter(Context c, List<T> l){
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = mLayoutInflater.inflate(getResource(), viewGroup, false);
        ViewHolder mvh = new ViewHolder(v);
        return mvh;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void removeListItem(View view, final int position) {

        removerRegistro(view, position);

        Snackbar snackbar = Snackbar.make(view, "O registro foi excluido", Snackbar.LENGTH_LONG).setAction("DESFAZER", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restaurarRegistro(view, position);
                Toast.makeText(view.getContext(), "Registro restaurado", Toast.LENGTH_SHORT).show();
            }
        });

        snackbar.setActionTextColor(Color.YELLOW);
        snackbar.show();

    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        public TextView descricaoLinha1;
        public TextView descricaoLinha2;
        public ImageButton editBT;
        public ImageButton deleteBT;

        public ViewHolder(View itemView) {
            super(itemView);
            descricaoLinha1 = itemView.findViewById(R.id.tv_descricao_linha_1);
            descricaoLinha2 = itemView.findViewById(R.id.tv_descricao_linha_2);
            editBT = itemView.findViewById(R.id.bt_edit);
            deleteBT = itemView.findViewById(R.id.bt_delete);

        }

    }

    public List<T> getList() {
        return mList;
    }

    public void setList(List<T> mList) {
        this.mList = mList;
    }

    protected abstract void removerRegistro(View view, int position);

    protected abstract void restaurarRegistro(View view, int position);

    protected abstract int getResource();

}


