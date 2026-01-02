package com.salsinha.watch.lisie.data.model

import com.google.gson.annotations.SerializedName

data class ShoppingItem(
    @SerializedName("Id") val id: Int,
    @SerializedName("ProductId") val productId: Int,
    @SerializedName("Quantity") val quantity: Int,
    @SerializedName("QuantityWeight") val quantityWeight: Double,
    @SerializedName("Barcode") val barcode: String?,
    @SerializedName("Name") val name: String,
    @SerializedName("Weight") val weight: String?,
    @SerializedName("Price") val price: Double,
    @SerializedName("Brand") val brand: String?,
    @SerializedName("Category") val category: String?,
    @SerializedName("ItemType") val itemType: String,
    @SerializedName("ImageUrl") val imageUrl: String?,
    @SerializedName("Unit") val unit: String?
)
