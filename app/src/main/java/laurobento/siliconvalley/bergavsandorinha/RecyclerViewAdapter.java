package laurobento.siliconvalley.bergavsandorinha;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import laurobento.siliconvalley.bergavsandorinha.model.ItemMercado;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<ItemMercado> itemList;
    private Context context;

    public RecyclerViewAdapter(List<ItemMercado> items) {
        itemList = items;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemMercado item = getItem(position);
        if (item != null){
            holder.nomeItem.setText(item.getNome());
            holder.descricaoItem.setText(item.getDescricao());
            holder.precoItem.setText(item.getPreco());
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public ItemMercado getItem(int position){
        return itemList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nomeItem;
        private TextView descricaoItem;
        private TextView precoItem;

        public ViewHolder(View itemView) {
            super(itemView);
            nomeItem = (TextView) itemView.findViewById(R.id.tvNomeItem);
            descricaoItem = (TextView) itemView.findViewById(R.id.tvDescicaoItem);
            precoItem = (TextView) itemView.findViewById(R.id.tvPrecoItem);
        }
    }
}
