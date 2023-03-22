package com.ouhamou.to_do_list.adapters;

import android.graphics.Paint;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ouhamou.to_do_list.R;
import com.ouhamou.to_do_list.databinding.ActivityTodoListItemBinding;
import com.ouhamou.to_do_list.models.Item;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    public ActivityTodoListItemBinding binding;

    public ItemViewHolder(ActivityTodoListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
    public void updateWithItem(Item item, ItemAdapter.Listener callback){
        binding.getRoot().setOnClickListener(view -> callback.onItemClick(item));
        binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                callback.onItemLogClick(item);
                return true;
            }
        });
        binding.activityTodoListItemRemove.setOnClickListener(view -> callback.onClickDeleteButton(item));
        binding.activityTodoListItemText.setText(item.getText());
        switch (item.getCategory()){
            case 0:
                setIconItem(R.drawable.ic_room_black_24px);
                break;
            case 1:
                setIconItem(R.drawable.ic_lightbulb_outline_black_24px);
                break;
            case 2:
                setIconItem(R.drawable.ic_local_cafe_black_24px);
                break;
        }
        if(item.isSelected()){
            binding.activityTodoListItemText.setPaintFlags(
                    binding.activityTodoListItemText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else {
            binding.activityTodoListItemText.setPaintFlags(
                    binding.activityTodoListItemText.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
    }
    private void setIconItem(int idDrawable){
        binding.activityTodoListItemImage.setBackgroundResource(idDrawable);
    }
}
