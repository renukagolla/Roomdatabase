package com.example.roomdbdemoapplication.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface Subscriber_dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(subscriber: Subscriber) :Long // should always return long, list or Array list

    @Update
    suspend fun updateData(subscriber: Subscriber) //should always return the int

    @Delete
    suspend fun deleteData(subscriber: Subscriber) // should always return

    @Query("DELETE FROM Subscriber_data_table")
    fun deleteAllData()

    @Query("SELECT * FROM Subscriber_data_table")
    fun getAllSubscribers():LiveData<List<Subscriber>>
}
