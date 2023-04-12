package com.ouhamou.to_do_list.api;

import com.ouhamou.to_do_list.models.Item;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ItemService {
    @GET("item/")
    Observable<List<Item>> getAllItems();
    @POST("item/")
    Observable<Item> addItem(@Body Item item);
    @PUT("item/{itemId}/")
    Observable<Item> updateItem(@Path("itemId") Long itemId,
                                @Body Item item);
    @DELETE("item/{itemId}/")
    Observable<String> deleteItem(@Path("itemId") Long itemId);
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://<IP_ADRESSE>:8080/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
