package com.aplicacion.kotlin.ui.phone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aplicacion.kotlin.databinding.FragmentPhoneBinding

class PhoneFragment : Fragment() {

    private var _binding: FragmentPhoneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val phoneViewModel =
            ViewModelProvider(this).get(PhoneViewModel::class.java)

        _binding = FragmentPhoneBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val tv1: TextView = binding.tv1
        phoneViewModel.text.observe(viewLifecycleOwner) {
            tv1.text = it
        }

        // Este método se ejecutará cuando se presione el ImageButton
        binding.bi1.setOnClickListener {
            tv1.text = "LLAMANDO"
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
