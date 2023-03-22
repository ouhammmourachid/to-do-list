package com.ouhamou.to_do_list.database;

import androidx.lifecycle.LiveData;

import com.ouhamou.to_do_list.models.Item;

import java.util.List;

public class ItemDataRepository {
    private final ItemDao itemDao;

    public ItemDataRepository(ItemDao itemDao) {
        this.itemDao = itemDao;
    }
    public LiveData<List<Item>> getItems(){
        return this.itemDao.getItems();
    }
    public void createItem(Item item){
        itemDao.insertItem(item);
    }
    public void deleteItem(long itemId){
        itemDao.deleteItem(itemId);
    }
    public void updateItem(Item item){
        itemDao.updateItem(item);
    }
}