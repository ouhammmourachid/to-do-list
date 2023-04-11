package com.ouhamou.to_do_list.adapters;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ouhamou.to_do_list.api.ItemStreams;
import com.ouhamou.to_do_list.database.ItemDataRepository;
import com.ouhamou.to_do_list.models.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import io.reactivex.observers.DisposableObserver;

public class ItemViewModel extends ViewModel {
    private final ItemDataRepository itemDataRepository;
    private final Executor executor;
    private StorageType storageType= StorageType.LOCAL_STORAGE;

    public StorageType getStorageType() {
        return storageType;
    }

    public void setStorageType(StorageType storageType) {
        this.storageType = storageType;
    }

    public ItemViewModel(ItemDataRepository itemDataRepository, Executor executor) {
        this.itemDataRepository = itemDataRepository;
        this.executor = executor;
    }

    public LiveData<List<Item>> getItems(){
        if(storageType.equals(StorageType.LOCAL_STORAGE)) {
            return itemDataRepository.getItems();
        }
        else {
            MutableLiveData<List<Item>> result = new MutableLiveData<>();
            result.setValue(new ArrayList<>());
            ItemStreams.streamFetchItems()
                    .subscribeWith(new DisposableObserver<List<Item>>() {
                        @Override
                        public void onNext(List<Item> value) {
                            result.setValue(value);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("ERROR","failed to load the items "+e.getStackTrace());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
            return result;
        }
    }
    public void createItem(String text,int category){
        executor.execute(()->{
            if(storageType.equals(StorageType.LOCAL_STORAGE)){
                itemDataRepository.createItem(new Item(text,category));
            }
            else {
                ItemStreams.streamAddItem(new Item(text, category))
                        .subscribeWith(new DisposableObserver<Item>() {
                            @Override
                            public void onNext(Item value) {
                                Log.e("SUCCED","you have added this folowing element "+value.getText());
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("ERROR","it Failed "+e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });
    }
    public void deleteItem(long itemId){
        executor.execute(()->{
            if(storageType.equals(StorageType.LOCAL_STORAGE)) {
                itemDataRepository.deleteItem(itemId);
            }
            else {
                ItemStreams.streamDeleteItem(itemId);
            }
        });
    }
    public void updateItem(Item item){
        executor.execute(()->{
            if(storageType.equals(StorageType.LOCAL_STORAGE)) {
                itemDataRepository.updateItem(item);
            }
            else {
                ItemStreams.streamUpdateItem(item.getId(), item);
            }
        });
    }
}
