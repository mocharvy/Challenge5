package com.programmer.challenge5_ma.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.programmer.challenge5_ma.adapter.CartAdapter
import com.programmer.challenge5_ma.databinding.FragmentCartBinding
import com.programmer.challenge5_ma.viewmodel.CartViewModel
import com.programmer.challenge5_ma.viewmodel.ViewModelFactory

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var viewModel: CartViewModel
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, ViewModelFactory(requireActivity().application))
            .get(CartViewModel::class.java)

        setupRecyclerView()
        observeCartItems()

        binding.btnpesan.setOnClickListener {
            val navController = findNavController()
            val action = CartFragmentDirections.actionCartFragmentToConfirmOrderActivity()
            navController.navigate(action)
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(viewModel) { cartItem ->
            viewModel.deleteCartItem(cartItem)
        }

        binding.rvMenuMakanan.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeCartItems() {
        viewModel.allCartItems.observe(viewLifecycleOwner) { cartItems ->
            cartAdapter.submitList(cartItems)
            val totalPrice = cartAdapter.calculateTotalPrice()
            binding.txtTotalPrice.text = "Total Price: Rp. $totalPrice"
        }
    }
}
