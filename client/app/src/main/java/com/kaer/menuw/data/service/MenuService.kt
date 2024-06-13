package com.kaer.menuw.data.service

import com.kaer.menuw.data.model.remote.request.RequestPostRecipeDto
import com.kaer.menuw.data.model.remote.request.RequestPostRecommendMenuListDto
import com.kaer.menuw.data.model.remote.response.ResponseGetLikeMenuListDto
import com.kaer.menuw.data.model.remote.response.ResponsePostRecipeDto
import com.kaer.menuw.data.model.remote.response.ResponsePostRecommendMenuListDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MenuService {

    @POST("/menu/recipe")
    suspend fun postMenuRecipe(@Body request: RequestPostRecipeDto): ResponsePostRecipeDto

    @GET("/menu/like/list")
    suspend fun getLikeMenuList(): ResponseGetLikeMenuListDto

    @POST("/menu/list")
    suspend fun postRecommendMenuList(@Body request: RequestPostRecommendMenuListDto): List<ResponsePostRecommendMenuListDto>
}