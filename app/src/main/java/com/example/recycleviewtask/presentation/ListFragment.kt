package com.example.recycleviewtask.presentation

//import android.app.Fragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleviewtask.data.ItemFlower
import com.example.recycleviewtask.databinding.FragmentListBinding

private const val ERROR_MESSAGE = "Не могу добавить элемент, так как поле пустое"
private const val NO_IMAGE_FLOWER =
    "https://creazilla-store.fra1.digitaloceanspaces.com/cliparts/1242803/under-construction-sign-clipart-xl.png"

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private var flowerName: String = ""
    private lateinit var flower: ItemFlower
    private var flowers = mutableListOf<ItemFlower>()
    var positionItem: Int = 0

    /**
     * В реальности мы не можем написать код вот так,
     *
     * RecycleViewItemAdapter(getAllFlowersCase.getAll(), object : RecycleViewItemAdapter.MyListener {
     *
     * Потому, что юзкейс может вернуть ошибку,
     * например отпал интернет, или бд упала.
     * По этому нам приходится создавать отдельный список, в который мы по возможности будем загружать данные
     */

    val flowerViewModel: FlowerViewModel by viewModels()
    private val adapter =
        RecycleViewItemAdapter(flowers)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listItemRecycle.layoutManager = LinearLayoutManager(requireContext())
        binding.listItemRecycle.adapter = adapter

        fun showToast(message: String) {
            Toast.makeText(
                requireActivity(), message, Toast.LENGTH_SHORT
            ).show()
        }

        getAllFlower()
    }

    private fun getAllFlower() {
        //получение списка цветков
        flowerViewModel.data.observe(viewLifecycleOwner,
            Observer<List<Any>> { dataset ->
                //при любом изменении списка обновляется UI
                adapter?.updateItems(dataset as MutableList<ItemFlower>)
            })
    }
}


