package com.ouhamou.to_do_list.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ouhamou.to_do_list.models.Item;

@Database(
        entities = {Item.class},
        version = 1,
        exportSchema = false
)
public abstract class SaveMyTripDatabase extends RoomDatabase {

    private static volatile SaveMyTripDatabase instance;

    /**
     * Item dao item dao.
     *
     * @return the item dao
     */
    public abstract ItemDao itemDao();


    /**
     * Get instance save my trip database.
     *
     * @param context the context
     * @return the save my trip database
     */
    public static SaveMyTripDatabase getInstance(Context context){
        if (instance == null){
            synchronized (SaveMyTripDatabase.class){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),SaveMyTripDatabase.class,"MyDatabase.db")
                            .build();
                }
            }
        }
        return instance;
    }


}
