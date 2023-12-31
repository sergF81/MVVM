package com.example.recycleviewtask.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FlowerRepositoryInMemoryImpl: FlowerRepository {



        // сохраняем в памяти состояние списка

        private var nextId = 0

        private val flowers = listOf(
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

        private val data = MutableLiveData(flowers)

        override fun get(): LiveData<List<ItemFlower>> = data

}


