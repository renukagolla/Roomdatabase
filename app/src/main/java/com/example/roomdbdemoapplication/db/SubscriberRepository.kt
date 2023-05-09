package com.example.roomdbdemoapplication.db

class SubscriberRepository(private val dao: Subscriber_dao) {
    val subscriber = dao.getAllSubscribers()

    suspend fun insertData(subscriber: Subscriber){
          dao.insertData(subscriber)
    }
    suspend fun updateData(subscriber: Subscriber){
        dao.updateData(subscriber)
    }
    suspend fun deletedata(subscriber: Subscriber){
        dao.deleteData(subscriber)
    }
    suspend fun deleteAll(){
        dao.deleteAllData()
    }
}