package com.ouhamou.to_do_list.adapters;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ouhamou.to_do_list.api.ItemService;
import com.ouhamou.to_do_list.api.ItemStreams;
import com.ouhamou.to_do_list.database.ItemDataRepository;
import com.ouhamou.to_do_list.models.Item;

import java.util.List;
import java.util.concurrent.Executor;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

public class ItemViewModel extends ViewModel {
    private final ItemDataRepository itemDataRepository;
    private final Executor executor;


    public ItemViewModel(ItemDataRepository itemDataRepository, Executor executor) {
        this.itemDataRepository = itemDataRepository;
        this.executor = executor;
    }

    public LiveData<List<Item>> getItems(){
        return itemDataRepository.getItems();
    }
    public void createItem(String text,int category){
        executor.execute(()->{
            itemDataRepository.createItem(new Item(text,category));
        });
    }
    public void deleteItem(long itemId){
        executor.execute(()->{
            itemDataRepository.deleteItem(itemId);
        });
    }
    public void updateItem(Item item){
        executor.execute(()->{
            itemDataRepository.updateItem(item);
        });
    }
}
