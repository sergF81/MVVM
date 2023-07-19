package com.example.recycleviewtask.presentation

import com.example.recycleviewtask.data.FlowerRepository
import com.example.recycleviewtask.data.FlowerRepositoryInMemoryImpl
import androidx.lifecycle.ViewModel

class FlowerViewModel: ViewModel() {
    private val repository: FlowerRepository = FlowerRepositoryInMemoryImpl()
    val data = repository.get()
}



