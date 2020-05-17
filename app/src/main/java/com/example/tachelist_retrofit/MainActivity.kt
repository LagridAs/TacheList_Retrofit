package com.example.tachelist_retrofit

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    private lateinit var adapterTodo:TacheAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    private var todoList: MutableList<ToDo> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTodoList()
        afficheList()


        /*val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(TacheInterface::class.java)
        val call : Call<List<ToDo?>?>? = service.getToDoListUser()

        call?.enqueue(object : Callback<List<ToDo?>?> {
            override fun onResponse(call: Call<List<ToDo?>?>, response: Response<List<ToDo?>?>) {
                if (response.code() == 200) {
                    todoList= response.body() as MutableList<ToDo>
                    afficheList(todoList)

                }
            }

            override fun onFailure(call: Call<List<ToDo?>?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Unable to load toDos", Toast.LENGTH_SHORT).show();
            }
        })*/
    }

    fun initTodoList(){

        val service = RetrofitFactory.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getToDoList()
            withContext(Dispatchers.Main) {
                try {
                    if (response != null) {
                        if (response.isSuccessful) {
                            val list = response.body() as MutableList<ToDo>
                            todoList.addAll(list)
                            adapterTodo.notifyDataSetChanged()
                        }
                        else {
                            Log.d(ContentValues.TAG,"Error: ${response.code()}")
                        }
                    }
                    else{
                        Log.d(ContentValues.TAG,"Error to get TODO list")

                    }
                } catch (e: HttpException) {
                    Log.d(ContentValues.TAG,"Exception ${e.message}")
                } catch (e: Throwable) {
                    Log.d(ContentValues.TAG,"Ooops: Something else went wrong")
                }
            }
        }
    }
    fun afficheList(){
        adapterTodo= TacheAdapter(todoList)
        linearLayoutManager = LinearLayoutManager(this@MainActivity)
        myRecyclerView.apply {
            layoutManager= linearLayoutManager
            adapter=adapterTodo
        }
    }
}
