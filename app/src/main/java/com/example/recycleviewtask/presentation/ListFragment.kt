package com.example.recycleviewtask.presentation

//import android.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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



        binding.textInputItem.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                binding.buttonAddItem.isEnabled = true
            }
        })
        fun showToast(message: String) {
            Toast.makeText(
                requireActivity(), message, Toast.LENGTH_SHORT
            ).show()
        }

        binding.buttonAddItem.setOnClickListener {
            val flowerName = binding.textInputItem.text.toString()
            if (flowerName.isBlank()) {
                Toast.makeText(context, "empty content", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            flowerViewModel.addFlower(flowerName)
            adapter.notifyDataSetChanged()
            binding.textInputItem.setText("")
            binding.textInputItem.clearFocus()
            binding.buttonAddItem.isEnabled = false
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


