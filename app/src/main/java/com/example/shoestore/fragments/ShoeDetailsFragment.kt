package com.example.shoestore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentShoeDetailsBinding
import com.example.shoestore.viewmodel.ShoeListViewModel

class ShoeDetailsFragment : Fragment() {

    lateinit var binding: FragmentShoeDetailsBinding
    private val viewModel : ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_shoe_details,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addBtn.setOnClickListener {
            getData()
            val action = ShoeDetailsFragmentDirections.actionShoeDetailsToShoeListFragment()
            findNavController().navigate(action)
        }

        binding.cancelButton.setOnClickListener {
            val action = ShoeDetailsFragmentDirections.actionShoeDetailsToShoeListFragment()
            findNavController().navigate(action)
        }
    }

    private fun getData() {
        binding.apply {
            val name = ShoeNameTextInputLayout.text.toString()
            val size = ShoeSizeTextInputLayout.text.toString().toDoubleOrNull()
            val company = CompanyTextInputLayout.text.toString()
            val description = DescriptionTextInputLayout.text.toString()
            viewModel.addShoe(name, (size?:0.0).toDouble(), company , description)
        }
    }

}