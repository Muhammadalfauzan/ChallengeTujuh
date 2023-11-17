package com.example.challengeempat.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengeempat.R
import com.example.challengeempat.adapter.CartAdapter
import com.example.challengeempat.databinding.FragmentKonfirmasiBinding
import com.example.challengeempat.managefragment.PaymenDialog
import com.example.challengeempat.model.ApiOrderRequest
import com.example.challengeempat.model.OrderItem
import com.example.challengeempat.ui.activity.MainActivity
import com.example.challengeempat.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Suppress("DEPRECATION")
class KonfirmasiFragment : Fragment() {

    private lateinit var binding: FragmentKonfirmasiBinding
    private lateinit var cartAdapter: CartAdapter

    private val cartViewModel: CartViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentKonfirmasiBinding.inflate(inflater, container, false)

        cartAdapter = CartAdapter(cartViewModel)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = cartAdapter

        cartViewModel.cartItems.observe(viewLifecycleOwner) {
            cartAdapter.updateDataCart(it)
        }

        cartViewModel.totalPrice.observe(viewLifecycleOwner) {
            binding.tvTotalHargaPesanan.text = it.toString()
        }
        cartViewModel.orderPlaced.observe(viewLifecycleOwner) {
            if (it) {
                Log.e("HomeFragment", "Data order : $it")
                val dialogFragment = PaymenDialog()
                dialogFragment.show(childFragmentManager, "Pesanan berhasil dialaog")
                cartViewModel.deleteAllItems()

            }
        }

        val mainActivity = requireActivity() as MainActivity
        (activity as MainActivity).hideButtomNav()
        mainActivity.supportActionBar?.hide()

        pesan()
        chooseDelivery()
        choosePayment()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_konfirmasiFragment_to_cartFragment2)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()

        (activity as MainActivity).showButtomNav()
        val mainActivity = requireActivity() as MainActivity

        mainActivity.supportActionBar?.show()

        mainActivity.showButtomNav()
    }

    private fun pesan() {
        binding.buttonPesanKonfirmasi.setOnClickListener {

            val orderItems = cartViewModel.cartItems.value ?: emptyList()
            if (orderItems.isNotEmpty()) {
                val total = cartViewModel.totalPrice.value ?: 0
                val orderRequest = ApiOrderRequest("Fauzan", total, orderItems.map {
                    OrderItem(it.nameFood, it.quantity, it.note ?: "", it.hargaPerItem)
                })

                cartViewModel.placeOrder(orderRequest)
            } else {
                Toast.makeText(requireContext(), "Keranjang anda kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun chooseDelivery() {
        binding.btnTakeAway.setOnClickListener {
            selectDeliveryOption(true)
        }

        binding.btnDelivery.setOnClickListener {
            selectDeliveryOption(false)
        }
    }
    private fun selectDeliveryOption(isTakeAway: Boolean) {
        if (isTakeAway) {
            binding.btnTakeAway.setBackgroundColor(resources.getColor(R.color.lightGreen400))
            binding.btnTakeAway.setTextColor(resources.getColor(R.color.white))

            binding.btnDelivery.setBackgroundColor(resources.getColor(R.color.lightGreen400))
            binding.btnDelivery.setTextColor(resources.getColor(R.color.black))

            binding.btnDelivery.setBackgroundColor(resources.getColor(R.color.gray400))
            binding.btnDelivery.setTextColor(resources.getColor(R.color.black))
        } else {
            binding.btnDelivery.setBackgroundColor(resources.getColor(R.color.lightGreen400))
            binding.btnDelivery.setTextColor(resources.getColor(R.color.white))

            binding.btnTakeAway.setBackgroundColor(resources.getColor(R.color.lightGreen400))
            binding.btnTakeAway.setTextColor(resources.getColor(R.color.black))

            binding.btnTakeAway.setBackgroundColor(resources.getColor(R.color.gray400))
            binding.btnTakeAway.setTextColor(resources.getColor(R.color.black))
        }
    }
    private fun choosePayment() {
        binding.buttonTunai.setOnClickListener {
            selectPaymentOption(true)
        }

        binding.buttonDigital.setOnClickListener {
            selectPaymentOption(false)
        }
    }
    private fun selectPaymentOption(isTunai: Boolean) {
        if (isTunai) {
            binding.buttonTunai.setBackgroundColor(resources.getColor(R.color.lightGreen400))
            binding.buttonTunai.setTextColor(resources.getColor(R.color.white))

            binding.buttonDigital.setBackgroundColor(resources.getColor(R.color.lightGreen400))
            binding.buttonDigital.setTextColor(resources.getColor(R.color.black))


            binding.buttonDigital.setBackgroundColor(resources.getColor(R.color.gray400))
            binding.buttonDigital.setTextColor(resources.getColor(R.color.black))
        } else {
            binding.buttonDigital.setBackgroundColor(resources.getColor(R.color.lightGreen400))
            binding.buttonDigital.setTextColor(resources.getColor(R.color.white))

            binding.buttonTunai.setBackgroundColor(resources.getColor(R.color.lightGreen400))
            binding.buttonTunai.setTextColor(resources.getColor(R.color.black))


            binding.buttonTunai.setBackgroundColor(resources.getColor(R.color.gray400))
            binding.buttonTunai.setTextColor(resources.getColor(R.color.black))
        }
    }
}
