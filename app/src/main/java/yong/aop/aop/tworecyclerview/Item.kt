package yong.aop.aop.ggookcat.cat


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("0")
    val a1: String? = "",
    @SerializedName("@0")
    val itemlist: itemlist? = itemlist(),
    @SerializedName("1")
    val a2: String? = "",
    @SerializedName("@1")
    val x1: itemlist? = itemlist(),
)