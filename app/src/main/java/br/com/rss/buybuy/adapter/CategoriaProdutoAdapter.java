package br.com.rss.buybuy.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import br.com.rss.buybuy.R;
import br.com.rss.buybuy.model.CategoriaModel;
import br.com.rss.buybuy.model.ProdutoModel;

public class CategoriaProdutoAdapter extends RecyclerView.Adapter<CategoriaProdutoAdapter.ViewHolder> {

    Context context;
    protected List<CategoriaModel> mList;
    protected LayoutInflater mLayoutInflater;

    public CategoriaProdutoAdapter(Context c, List<CategoriaModel> l){
        this.context = c;
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public CategoriaProdutoAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_rv_categorias, viewGroup, false);
        CategoriaProdutoAdapter.ViewHolder mvh = new CategoriaProdutoAdapter.ViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder categoriaProdutoVH, final int position) {

        categoriaProdutoVH.nome.setText(String.format(Locale.getDefault(), "%s", mList.get(position).getNome()));

        categoriaProdutoVH.mList = mList.get(position).getProdutos();
        categoriaProdutoVH.adapter = new ProdutoAdapter(this.context, categoriaProdutoVH.mList);
        categoriaProdutoVH.recyclerView.setAdapter(categoriaProdutoVH.adapter);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<CategoriaModel> getList() {
        return mList;
    }

    public void setList(List<CategoriaModel> mList) {
        this.mList = mList;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private List<ProdutoModel> mList;
        public TextView nome;
        public RecyclerView recyclerView;
        public ProdutoAdapter adapter;

        public ViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.tv_nome);
            recyclerView = itemView.findViewById(R.id.rv_lista_produtos);
            configRV(itemView);

        }

        private void configRV(View view) {

            recyclerView.setHasFixedSize(true);

            LinearLayoutManager llm = new LinearLayoutManager(context);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(llm);

        }

    }

}


