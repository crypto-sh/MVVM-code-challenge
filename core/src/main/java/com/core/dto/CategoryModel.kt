package com.core.dto

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize


@Keep
@Parcelize
data class CategoryModel(
    @Keep val id            : String,
    @Keep val name          : String,
    @Keep val description   : String,
    @Keep val products      : List<ProductModel>
) : Parcelable

@Keep
@Parcelize
data class ProductModel(
    @Keep val id            : String,
    @Keep val categoryId    : String,
    @Keep val name          : String,
    @Keep val url           : String,
    @Keep val description   : String,
    @Keep val salePrice     : PriceModel
): Parcelable

@Keep
@Parcelize
data class PriceModel(
    @Keep val amount    : Double,
    @Keep val currency  : String
) : Parcelable {

    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "$currency : $amount"
    }
}