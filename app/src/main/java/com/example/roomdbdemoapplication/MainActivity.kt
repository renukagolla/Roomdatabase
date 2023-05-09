package com.example.roomdbdemoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdbdemoapplication.databinding.ActivityMainBinding
import com.example.roomdbdemoapplication.db.Subscriber
import com.example.roomdbdemoapplication.db.SubscriberDatabase
import com.example.roomdbdemoapplication.db.SubscriberRepository
import com.example.roomdbdemoapplication.db.Subscriber_dao

class MainActivity : AppCompatActivity() {
   private lateinit var binding: ActivityMainBinding
   private lateinit var viewModel: SubscriberViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao = SubscriberDatabase.getInstance(application).subscriberDao
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        viewModel = ViewModelProvider(this,factory)[SubscriberViewModel::class.java]
        binding.myViewModel = viewModel
        binding.lifecycleOwner = this
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.subscriberRecyclerViewdata.layoutManager = LinearLayoutManager(this)
        displayList()
    }

    private fun displayList() {
        viewModel.subscriber.observe(this, Observer {
            Log.d("MyTAG",it.toString())
            binding.subscriberRecyclerViewdata.adapter = MyReclerViewAdapter(it,{selectedItem:Subscriber->listItemClicked(selectedItem)})
        })
    }

    fun listItemClicked(subscriber: Subscriber){
        Toast.makeText(this,"${subscriber.name}",Toast.LENGTH_LONG).show()
        viewModel.initUpdateDelete(subscriber)
    }
}