package com.example.recycleviewtask.presentation


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewtask.R
import com.example.recycleviewtask.data.ItemFlower
import com.example.recycleviewtask.data.ItemViewType
import com.example.recycleviewtask.databinding.ListRowItemBinding
import com.example.recycleviewtask.databinding.RowTypeButtonBinding
import com.example.recycleviewtask.databinding.RowTypeImageBinding
import com.example.recycleviewtask.databinding.RowTypeTextBinding
import com.example.recycleviewtask.presentation.Const.HASBUTTON
import com.example.recycleviewtask.presentation.Const.HASIMAGE
import com.example.recycleviewtask.presentation.Const.HASTEXT
import com.squareup.picasso.Picasso


//class RecycleViewItemAdapter() :
//    ListAdapter<ItemFlower, RecycleViewItemAdapter.ItemHolder>(FlowerDiffCallback()) {
class RecycleViewItemAdapter(
    private var dataSet: MutableList<ItemFlower>,
    private var myListener: MyListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateItems(list: MutableList<ItemFlower>) {
        dataSet = list
        notifyItemRangeInserted(0, list.size)
    }

    class ItemHolderWithoutImage(itemView: RowTypeTextBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val binding = RowTypeTextBinding.bind(itemView.root)
        fun bind(s: ItemFlower) {
            binding.textIdTextTypeView.text = s.id.toString()
            binding.textNameTextTypeView.text = s.name
        }
    }


    class ItemHolderWithImage(itemView: RowTypeImageBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        private val binding = RowTypeImageBinding.bind(itemView.root)
        fun bind(s: ItemFlower) {
            binding.textIdImageTypeView.text = s.id.toString()
            binding.textNameImageTypeView.text = s.name
            Picasso.with(binding.imageFlowerImageTypeView.context)
                .load(s.resourceImageFlower)
                .into(binding.imageFlowerImageTypeView);
        }
    }




    class ItemHolderWithButton(itemView: RowTypeButtonBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        private val binding = RowTypeButtonBinding.bind(itemView.root)
        fun bind(s: ItemFlower) {
            binding.textIdButtonTypeView.text = s.id.toString()
            binding.buttonButtonTypeView.isEnabled
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (dataSet[position].itemDifferentHolder == ItemViewType.TYPE_IMAGE) {
            HASIMAGE
        } else {
            if (dataSet[position].itemDifferentHolder == ItemViewType.TYPE_TEXT) HASTEXT else HASBUTTON
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HASIMAGE) {
            val view =
                RowTypeImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ItemHolderWithImage(view)
        } else {
            if (viewType == HASTEXT) {
                val view =
                    RowTypeTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ItemHolderWithoutImage(view)
            } else {
                val view =
                    RowTypeButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ItemHolderWithButton(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == HASIMAGE) {
            (holder as ItemHolderWithImage).bind(dataSet[position])
            holder.itemView.setOnClickListener {
                myListener.myClick(dataSet, position)
            }

        } else {
            if (getItemViewType(position) == HASTEXT) {
                (holder as ItemHolderWithoutImage).bind(dataSet[position])
                holder.itemView.setOnClickListener {
                    myListener.myClick(dataSet, position)
                }

            } else {
                (holder as ItemHolderWithButton).bind(dataSet[position])
                holder.itemView.setOnClickListener {
                    myListener.myClick(dataSet, position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }





    interface MyListener {
        fun myClick(userArray: MutableList<ItemFlower>, position: Int)
    }

}

private object Const {
    const val HASIMAGE = 0
    const val HASTEXT = 1
    const val HASBUTTON = 2
}