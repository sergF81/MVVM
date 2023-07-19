package com.example.recycleviewtask.data

import androidx.lifecycle.LiveData

interface FlowerRepository {
    fun get(): LiveData<List<ItemFlower>>

}