package com.aplicacion.kotlin.ui.toast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aplicacion.kotlin.databinding.FragmentToastBinding
import android.widget.EditText
import android.widget.Toast

class ToastFragment : Fragment() {

    private var _binding: FragmentToastBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val toastViewModel =
            ViewModelProvider(this).get(ToastViewModel::class.java)

        _binding = FragmentToastBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val etl = binding.editTextNumber
        val num = (0..100000).random()
        val cadena = num.toString()

        val notificacion = Toast.makeText(requireContext(), cadena, Toast.LENGTH_LONG)
        notificacion.show()

        binding.button.setOnClickListener {
            val valorIngresado = etl.text.toString()

            try {
                val valor = valorIngresado.toInt()

                if (valor == num) {
                    Toast.makeText(requireContext(), "Muy bien, recordaste el número mostrado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Lo siento, pero no es el número mostrado", Toast.LENGTH_SHORT).show()
                }
            } catch (e: NumberFormatException) {
                // Manejar la excepción si el valor ingresado no es un número entero
                Toast.makeText(requireContext(), "Primero ingrese un numero", Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}