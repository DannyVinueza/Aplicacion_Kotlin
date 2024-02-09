package com.aplicacion.kotlin.ui.dashboard

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Spinner
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aplicacion.kotlin.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Decalaracion de cada componente
        val textView: TextView = binding.textDashboard
        val checkBox: CheckBox = binding.checkBox
        val spinner: Spinner = binding.spinner

        spinner.isEnabled = false

        // Configurar CheckBox para mostrar u ocultar datos adicionales
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Mostrar datos adicionales
                textView.text = ""
                textView.append("\nDesarrollo de Software\nMostrando Integrantes....")
                spinner.isEnabled = true
            } else {
                // Ocultar datos adicionales
                textView.text = "Integrantes"
                spinner.isEnabled = false
            }
        }

        // Configurar Spinner con opciones y manejar la selección
        val opciones = arrayOf("Seleccionar integrante...","Danny Vinueza", "Joel Tates", "José Panchi")
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                if(checkBox.isChecked && opciones[position] != "Seleccionar integrante..."){
                    textView.append("\nIntegrante: ${opciones[position]}")
                }else{
                    textView.text = ""
                    textView.append("Integrantes")
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // No hay ninguna opcion seleccionada
            }
        }
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}