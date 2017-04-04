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

    private final OnItemClickListener listener;
    private List<ItemMercado> itemList;
    private Context context;

    public RecyclerViewAdapter(List<ItemMercado> items, OnItemClickListener listener) {
        this.itemList = items;
        this.listener = listener;
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
            holder.bind(item, listener);
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

        public void bind(final ItemMercado item, final OnItemClickListener listener) {
            nomeItem.setText(item.getNome());
            descricaoItem.setText(item.getDescricao());
            precoItem.setText(item.getPreco());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ItemMercado item);
    }
}
