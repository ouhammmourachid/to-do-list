package com.ouhamou.to_do_list.api;

import com.ouhamou.to_do_list.models.Item;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ItemStreams {
    public static Observable<List<Item>> streamFetchItems() {
        ItemService itemService = ItemService.retrofit.create(ItemService.class);
        return itemService.getAllItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
    public static Observable<Item> streamAddItem(Item item) {
        ItemService itemService = ItemService.retrofit.create(ItemService.class);
        return itemService.addItem(item)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
    public static Observable<Item> streamUpdateItem(Long itemId, Item item) {
        ItemService itemService = ItemService.retrofit.create(ItemService.class);
        return itemService.updateItem(itemId, item)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
    public static Observable<String> streamDeleteItem(Long itemId) {
        ItemService itemService = ItemService.retrofit.create(ItemService.class);
        return itemService.deleteItem(itemId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
}
