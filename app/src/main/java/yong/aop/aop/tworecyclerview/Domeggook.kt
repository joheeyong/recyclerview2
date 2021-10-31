package yong.aop.aop.tworecyclerview

import com.google.gson.annotations.SerializedName
import yong.aop.aop.ggookcat.cat.Items

data class Domeggook(
    @SerializedName("items")
    val items: Items? = Items()
)