package com.example.recycleviewtask.presentation

import androidx.lifecycle.MutableLiveData
import com.example.recycleviewtask.data.FlowerRepository
import com.example.recycleviewtask.data.FlowerRepositoryInMemoryImpl
import androidx.lifecycle.ViewModel
import com.example.recycleviewtask.data.ItemFlower


val empty = ItemFlower(
    id = 0,
    name = "HZ",
    imageFlower = "String"
)

class FlowerViewModel : ViewModel() {
    private val repository: FlowerRepository = FlowerRepositoryInMemoryImpl()
    val data = repository.get()
    private val edited = MutableLiveData(empty)

    fun addFlower(flowerName: String) {
        edited.value?.let {
            it.name = flowerName
            repository.addFlower(it)
        }
        edited.value = empty
    }
}



