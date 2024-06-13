package com.kaer.menuw.data.service

import com.kaer.menuw.data.model.remote.request.RequestPatchMenuLikeDto
import com.kaer.menuw.data.model.remote.request.RequestPostRecipeDto
import com.kaer.menuw.data.model.remote.request.RequestPostRecommendMenuListDto
import com.kaer.menuw.data.model.remote.response.ResponseGetLikeMenuListDto
import com.kaer.menuw.data.model.remote.response.ResponsePatchMenuLikeDto
import com.kaer.menuw.data.model.remote.response.ResponsePostRecipeDto
import com.kaer.menuw.data.model.remote.response.ResponsePostRecommendMenuListDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST

interface MenuService {

    @POST("/menu/recipe")
    suspend fun postMenuRecipe(@Body request: RequestPostRecipeDto): ResponsePostRecipeDto

    @GET("/menu/like/list")
    suspend fun getLikeMenuList(): ResponseGetLikeMenuListDto

    @POST("/menu/list")
    suspend fun postRecommendMenuList(@Body request: RequestPostRecommendMenuListDto): List<ResponsePostRecommendMenuListDto>

    @PATCH("/menu/like")
    suspend fun patchMenuLike(
        @Header("Content-Type") contentType: String,
        @Header("Authorization") authorization: String,
        @Body request: RequestPatchMenuLikeDto
    ): ResponsePatchMenuLikeDto
}