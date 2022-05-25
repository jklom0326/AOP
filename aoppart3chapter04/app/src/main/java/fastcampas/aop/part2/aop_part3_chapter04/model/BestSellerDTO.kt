package fastcampas.aop.part2.aop_part3_chapter04.model

import com.google.gson.annotations.SerializedName

data class BestSellerDTO(
    @SerializedName("items") val books: List<Book>,
)
