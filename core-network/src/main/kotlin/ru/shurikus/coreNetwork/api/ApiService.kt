package ru.shurikus.coreNetwork.api

import retrofit2.Response
import retrofit2.http.*
import ru.shurikus.coreNetwork.models.request.LoginRequest
import ru.shurikus.coreNetwork.models.response.MovieDetailsResponse
import ru.shurikus.coreNetwork.models.response.MoviesTrendingResponse
import ru.shurikus.coreNetwork.models.response.NewTokenResponse
import ru.shurikus.coreNetwork.models.response.UserSessionResponse

internal interface ApiService {
    @GET("authentication/token/new")
    suspend fun requestNewToken(): NewTokenResponse

    @POST("authentication/token/validate_with_login")
    suspend fun createUserSession(@Body request: LoginRequest): UserSessionResponse

    @GET("trending/movie/day")
    suspend fun requestTrendingMoviesPage(@Query("page") page: Int): MoviesTrendingResponse

    @GET("movie/{movie_id}")
    suspend fun requestMovieDetails(@Path("movie_id") id: Int): Response<MovieDetailsResponse>
}
