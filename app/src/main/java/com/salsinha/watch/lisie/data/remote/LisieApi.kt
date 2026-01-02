package com.salsinha.watch.lisie.data.remote

import com.salsinha.watch.lisie.data.model.ShoppingItem
import retrofit2.http.GET
import retrofit2.http.Query

interface LisieApi {
    @GET("UserLists/GetV4")
    suspend fun getShoppingList(@Query("userId") userId: String): List<ShoppingItem>
}
