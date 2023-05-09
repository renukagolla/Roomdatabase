package com.example.roomdbdemoapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdbdemoapplication.databinding.ListItemBinding
import com.example.roomdbdemoapplication.db.Subscriber


class MyReclerViewAdapter(private val subscriberList:List<Subscriber>, private val clickListener: (Subscriber)->Unit) : RecyclerView.Adapter<MyViewModelHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewModelHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(layoutInflater,R.layout.list_item,parent,false)
        return MyViewModelHolder(binding)
    }


    override fun onBindViewHolder(holder: MyViewModelHolder, position: Int) {
        holder.bind(subscriberList[position],clickListener)
    }

    override fun getItemCount(): Int {
        return subscriberList.size
    }

}

class MyViewModelHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(subscriber: Subscriber, clickListener: (Subscriber) -> Unit) {
          binding.nameText.text = subscriber.name
          binding.emailText.text = subscriber.email
        binding.listItemLayout.setOnClickListener {
            clickListener(subscriber)
        }
    }

}
