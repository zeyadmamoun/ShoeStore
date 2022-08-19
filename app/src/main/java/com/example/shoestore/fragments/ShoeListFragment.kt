package com.example.shoestore.fragments

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentShoeListBinding
import com.example.shoestore.databinding.ShoelistItemBinding
import com.example.shoestore.viewmodel.ShoeListViewModel

class ShoeListFragment : Fragment(){

    private lateinit var binding: FragmentShoeListBinding
    private val viewModel : ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list,container,false)
        setHasOptionsMenu(true)
        buildShoeList(inflater,container)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            val action = ShoeListFragmentDirections.actionShoeListFragmentToShoeDetails()
            findNavController().navigate(action)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.loginFragment)
            logout()
        return true
    }

    private fun logout() {
        viewModel.onLogout()
        val action = ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment()
        findNavController().navigate(action)
    }

    private fun buildShoeList(inflater: LayoutInflater,container: ViewGroup?){
        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->
            if (shoeList.isNotEmpty()){
                shoeList.forEach{ shoe ->
                    val itemBinding: ShoelistItemBinding =
                        DataBindingUtil.inflate(inflater, R.layout.shoelist_item, container, false)
                    itemBinding.shoe = shoe
                    binding.linearLayout.addView(itemBinding.root)
                }
            }
        })
    }
}