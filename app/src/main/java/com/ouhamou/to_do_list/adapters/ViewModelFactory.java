package com.ouhamou.to_do_list.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ouhamou.to_do_list.database.ItemDataRepository;
import com.ouhamou.to_do_list.database.SaveMyTripDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final ItemDataRepository itemdataSource;
    private final Executor executor;
    private static ViewModelFactory factory;

    public ViewModelFactory(Context context) {
        SaveMyTripDatabase database = SaveMyTripDatabase.getInstance(context);
        itemdataSource = new ItemDataRepository(database.itemDao());
        executor = Executors.newSingleThreadExecutor();
    }

    public static ViewModelFactory getInstance(Context context){
        if(factory == null){
            synchronized (ViewModelFactory.class){
                if( factory == null){
                    factory = new ViewModelFactory(context);
                }
            }
        }
        return factory;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(ItemViewModel.class)){
            return (T) new ItemViewModel(itemdataSource,executor);
        }
        throw new IllegalArgumentException("UnKnown ViewModel class");
    }
}
