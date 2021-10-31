package yong.aop.aop.ggookcat.cat


import com.google.gson.annotations.SerializedName

data class itemlist(
    @SerializedName("depth")
    val depth: String? = "",
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("itemCnt")
    val itemCnt: String? = "",
    @SerializedName("no")
    val no: String? = ""
)