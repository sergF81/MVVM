package com.example.recycleviewtask.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.recycleviewtask.R
import com.example.recycleviewtask.databinding.FragmentInfoFlowerBinding
import com.squareup.picasso.Picasso

private const val NO_IMAGE_FLOWER =
    "https://creazilla-store.fra1.digitaloceanspaces.com/cliparts/1242803/under-construction-sign-clipart-xl.png"

class InfoFlowerFragment : Fragment() {

    private lateinit var binding: FragmentInfoFlowerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInfoFlowerBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val argsObjectsFlower: InfoFlowerFragmentArgs by navArgs()
        val imageFlower = argsObjectsFlower.chosenFlowerArg.sourceImageFlower
        val description = argsObjectsFlower.chosenFlowerArg.descriptionFlower

        binding.textChoseInfoFlower .text = description

        Picasso.with(binding.imageChoseViewFlower.context)
            .load(imageFlower)
            .into(binding.imageChoseViewFlower);

    }
}