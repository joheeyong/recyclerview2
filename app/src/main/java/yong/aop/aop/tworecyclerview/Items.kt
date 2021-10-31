package yong.aop.aop.ggookcat.cat


import com.google.gson.annotations.SerializedName

data class Items(
    @SerializedName("item")
    val item: Item? = Item()
)