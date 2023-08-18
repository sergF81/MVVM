package com.example.recycleviewtask.presentation

import androidx.lifecycle.MutableLiveData
import com.example.recycleviewtask.data.FlowerRepository
import com.example.recycleviewtask.data.FlowerRepositoryInMemoryImpl
import androidx.lifecycle.ViewModel
import com.example.recycleviewtask.data.ItemFlower
import com.example.recycleviewtask.data.ItemViewType




class FlowerViewModel : ViewModel() {
    private val repository: FlowerRepository = FlowerRepositoryInMemoryImpl()
    val data = repository.get()

    fun addFlower(flowerName: String) {

        repository.addFlower(flowerName)
    }

    fun delFlower(id: Int)
    {
        repository.deleteFlower(id)
    }
}



