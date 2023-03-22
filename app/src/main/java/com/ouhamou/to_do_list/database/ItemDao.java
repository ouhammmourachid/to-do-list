package com.ouhamou.to_do_list.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ouhamou.to_do_list.models.Item;

import java.util.List;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM Item ;")
    LiveData<List<Item>> getItems();


    @Insert
    long insertItem(Item item);

    @Update
    int updateItem(Item item);

    @Query("DELETE FROM Item WHERE id = :itemId")
    int deleteItem(long itemId);
}
