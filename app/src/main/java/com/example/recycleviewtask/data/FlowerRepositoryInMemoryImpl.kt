package com.example.recycleviewtask.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FlowerRepositoryInMemoryImpl : FlowerRepository {

    private val NO_IMAGE_FLOWER =
        "https://creazilla-store.fra1.digitaloceanspaces.com/cliparts/1242803/under-construction-sign-clipart-xl.png"

    // сохраняем в памяти состояние списка

    private var nextId = 1

    private var flowers = listOf(
        ItemFlower(
            id = nextId++,
            name = "Роза",
            imageFlower = "https://upload.wikimedia.org/wikipedia/commons/b/bf/Rosa_Red_Chateau01.jpg"

        ),
        ItemFlower(
            id = nextId++,
            name = "Лилия",
            imageFlower = "https://upload.wikimedia.org/wikipedia/commons/1/1e/Lilium_candidum_2.jpg"

        ),
        ItemFlower(
            id = nextId++,
            name = "Тюльпан",
            imageFlower = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8f/Tulipa_Couleur_Cardinal.jpg/1200px-Tulipa_Couleur_Cardinal.jpg"

        ),
        ItemFlower(
            id = nextId++,
            name = "Гладиолус",
            imageFlower = "https://img.7dach.ru/image/600/00/00/53/2013/02/20/d9311a.jpg"

        ),
        ItemFlower(
            id = nextId++,
            name = "Гартензия",
            imageFlower = "https://korolevskysad.ru/wp-content/uploads/2019/02/Gortenziya-krupnolistnaya-Blyu-Bodensi-1.jpg"


        ),
        ItemFlower(
            id = nextId++,
            name = "Пион",
            imageFlower = "https://tsvetomania.ru/upload/resize_cache/iblock/873/710_605_1/8730652728d8f94dc77dfc27e1221c61.jpg"


        ),


        ItemFlower(
            id = nextId++,
            name = "Фиалка",
            imageFlower = "https://cdn.botanichka.ru/wp-content/uploads/2022/12/czvetochnye-motylki-fialka-sestrinskaya-v-sadu.jpg"


        )
    )

    private val _data = MutableLiveData(flowers)
    private val data: LiveData<List<ItemFlower>> = _data
    override fun get() = data

    override fun addFlower(flowerName: String) {
        val flower = ItemFlower(flowers.size + 1,flowerName,NO_IMAGE_FLOWER)
        flowers = flowers.plus(flower)

        _data.value = flowers
    }

    override fun deleteFlower(id: Int) {
        val flowers1 = flowers.filter { it.id < id + 1 }
        val flowers2 = flowers.filter { it.id > id + 1 }
        for (i in 0 until flowers2.size) {
            flowers2[i].id = flowers2[i].id - 1
        }
        flowers = flowers1.plus(flowers2)

        _data.value = flowers
    }

}


