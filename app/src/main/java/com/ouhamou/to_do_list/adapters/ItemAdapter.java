package com.ouhamou.to_do_list.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ouhamou.to_do_list.databinding.ActivityTodoListItemBinding;
import com.ouhamou.to_do_list.models.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    public interface Listener {
        void onClickDeleteButton(Item item);
        void onItemClick(Item item);

        void onItemLogClick(Item item);
    }
    private final Listener callback;
    private List<Item> items;

    public ItemAdapter(Listener callback) {
        this.callback = callback;
        items = new ArrayList<Item>();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        return new ItemViewHolder(ActivityTodoListItemBinding.inflate(inflater,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.updateWithItem(items.get(position),this.callback);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void updateDate(List<Item> items){
        this.items = items;
        notifyDataSetChanged();
    }
}
