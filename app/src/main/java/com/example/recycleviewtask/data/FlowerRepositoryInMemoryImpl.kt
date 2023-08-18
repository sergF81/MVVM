package com.example.recycleviewtask.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FlowerRepositoryInMemoryImpl : FlowerRepository {

    private val NO_IMAGE_FLOWER =
        "https://creazilla-store.fra1.digitaloceanspaces.com/cliparts/1242803/under-construction-sign-clipart-xl.png"

    // сохраняем в памяти состояние списка

    private var empty = ItemFlower(
        id = 0,
        name = "HZ",
        descriptionFlower = "Новый цветок",
        resourceImageFlower = "String",
        itemDifferentHolder = ItemViewType.TYPE_IMAGE
    )
    private var nextId = 1

    private var flowers = listOf(
        ItemFlower(
            id = nextId++,
            name = "Роза",
            descriptionFlower = "Одна прекрасная роза среди навоза!",
            resourceImageFlower = "https://upload.wikimedia.org/wikipedia/commons/b/bf/Rosa_Red_Chateau01.jpg",
            ItemViewType.TYPE_IMAGE

        ),
        ItemFlower(
            id = nextId++,
            name = "Лилия",
            descriptionFlower = null,
            resourceImageFlower = null,
            ItemViewType.TYPE_BUTTON
        ),
        ItemFlower(
            id = nextId++,
            name = "Тюльпан",
            descriptionFlower = null,
            resourceImageFlower = null,
            ItemViewType.TYPE_TEXT
        ),
        ItemFlower(
            id = nextId++,
            name = "Гладиолус",
            descriptionFlower = null,
            resourceImageFlower = null,
            ItemViewType.TYPE_TEXT

        ),
        ItemFlower(
            id = nextId++,
            name = "Гартензия",
            descriptionFlower = "Много цветков в одном кусте.",
            resourceImageFlower = "https://korolevskysad.ru/wp-content/uploads/2019/02/Gortenziya-krupnolistnaya-Blyu-Bodensi-1.jpg",
            ItemViewType.TYPE_IMAGE


        ),
        ItemFlower(
            id = nextId++,
            name = "Пион",
            descriptionFlower = null,
            resourceImageFlower = null,
            ItemViewType.TYPE_TEXT

        ),


        ItemFlower(
            id = nextId++,
            name = "Фиалка",
            descriptionFlower = null,
            resourceImageFlower = null,
            ItemViewType.TYPE_TEXT

        )
    )

    private val data = MutableLiveData(flowers)

    override fun get(): LiveData<List<ItemFlower>> = data

    override fun addFlower(flowerName: String) {

        flowers = flowers + listOf(
            empty.copy(
                id = nextId++, name = flowerName, resourceImageFlower = NO_IMAGE_FLOWER
            )
        )
        data.value = flowers
    }

    override fun deleteFlower(id: Int) {
        flowers = flowers.filter { it.id != id + 1 }
        nextId--
        flowers = flowers.map { it ->
            when {
                it.id < id -> it
                it.id > id -> it.copy(it.id - 1)
                else -> it
            }
        }
        data.value = flowers
    }
}


