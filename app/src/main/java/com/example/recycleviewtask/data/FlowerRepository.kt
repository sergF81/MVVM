package com.example.recycleviewtask.data

import androidx.lifecycle.LiveData

interface FlowerRepository {
    fun get(): LiveData<List<ItemFlower>>
    fun addFlower(flowerName: String)
    fun deleteFlower(id: Int)

}