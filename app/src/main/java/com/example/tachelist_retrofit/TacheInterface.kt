package com.example.tachelist_retrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface TacheInterface {
    @GET("/todos")
    suspend fun getToDoList(): Response<List<ToDo?>?>?

    @GET("/todos/{id}")
    suspend fun getSingleTodo(@Path("id") todoId: Int): Response<ToDo?>?

    @GET("/users/1/todos")
    suspend fun getToDoListUser(): Response<List<ToDo?>?>?

    @GET("/todos/")
    suspend fun getSingleTodoQuery(@Query("userId") userId: Int): Response<List<ToDo?>?>?


    @DELETE("/todos/{id}")
    fun deleteTodo(@Path("id") id: String): Response<ToDo>

    @PUT("/todos/{id}")
    fun edit(@Path("id") id: String,@Body todo: ToDo): Response<ToDo>

    @POST("/todos")
    fun addTodo(@Body todo: ToDo): Response<ToDo>
}