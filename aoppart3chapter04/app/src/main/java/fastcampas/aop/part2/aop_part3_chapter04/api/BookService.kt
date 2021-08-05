package fastcampas.aop.part2.aop_part3_chapter04.api

import fastcampas.aop.part2.aop_part3_chapter04.model.BestSellerDTO
import fastcampas.aop.part2.aop_part3_chapter04.model.SearchBookDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {

    @GET("/api/search.api?output=json")
    fun getBookByName(
        @Query("key") apiKey: String,
        @Query("query") keyword: String
    ): Call<SearchBookDTO>

    @GET("/api/bestSeller.api?output=json&categoryId=100")
    fun getBestSellerBooks(
        @Query("key") apuKey: String,

    ): Call<BestSellerDTO>
}