package com.example.tachelist_retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TacheAdapter(private val dataList: MutableList<ToDo>): RecyclerView.Adapter<TacheAdapter.TodoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TodoHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
    }


    /********HolderVew********/

    inner class TodoHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.tache_item, parent, false)
    ){
        private var tacheTx:TextView?= null
        private var tacheChk:CheckBox?=null

        init {
            tacheTx=itemView.findViewById(R.id.todoText)
            tacheChk=itemView.findViewById(R.id.todoCheck)
        }

        fun bind(todo: ToDo){
            tacheTx?.text=todo.title
            if(todo.completed){
                tacheChk?.isChecked= true
            }

        }
    }


}