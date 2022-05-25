package fastcampas.aop.part2.aop_part3_chapter04.api

import fastcampas.aop.part2.aop_part3_chapter04.model.BestSellerDTO
import fastcampas.aop.part2.aop_part3_chapter04.model.SearchBookDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BookService {

    @GET("v1/search/book.json")
    fun getBookByName(
        @Header("X-Naver-Client-Id") Id: String,
        @Header("X-Naver-Client-Secret") secretKey: String,
        @Query("query") keyword: String
    ): Call<SearchBookDTO>
}