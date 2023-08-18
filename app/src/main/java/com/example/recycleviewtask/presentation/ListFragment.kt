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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleviewtask.data.ChosenFlower
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
        RecycleViewItemAdapter(flowers, object : RecycleViewItemAdapter.MyListener {
            override fun myClick(flowerArray: MutableList<ItemFlower>, position: Int) {

                positionItem = position
                flower = flowerArray[positionItem]
                val imageOneFlower = flower.resourceImageFlower
                val descriptionOneFlower = flower.descriptionFlower
                val chosenFlower = ChosenFlower().apply {
                    sourceImageFlower = imageOneFlower
                    descriptionFlower = descriptionOneFlower
                }
                binding.buttonDeleteItem.isEnabled = true
                if(imageOneFlower.equals(null)) {
                    binding.buttonDeleteItem.isEnabled = true

                } else {
                    //создание action для передачи параметров во фрагмент InfoFlowerFragment
                    val action =
                        ListFragmentDirections.actionListFragmentToInfoFlowerFragment(chosenFlower)

                    // передача самого action через navController
                    findNavController().navigate(action)
                    //  binding.listItemRecycle.background = ic_launcher_foreground
                }
            }
        })

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
                if(p0.isNullOrEmpty()) {
                    binding.buttonAddItem.isEnabled = false
                } else binding.buttonAddItem.isEnabled = true
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
        binding.buttonDeleteItem.setOnClickListener {

          //    adapter.notifyItemRemoved(positionItem)
            flowerViewModel.delFlower(positionItem)

//            for (i in flower.id..flowers.size) {
//                flowers[i - 1].id = i
//
//            }
              //  adapter.notifyItemRangeChanged(positionItem-1, flowers.size)
            adapter.notifyDataSetChanged()
           // adapter.notifyItemRemoved(positionItem)
            binding.buttonDeleteItem.isEnabled = false
         //   getAllFlower()
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
