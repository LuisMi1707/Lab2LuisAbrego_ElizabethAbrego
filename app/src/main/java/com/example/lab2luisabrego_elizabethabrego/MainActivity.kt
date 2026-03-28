package com.example.lab2luisabrego_elizabethabrego

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etEdad: EditText
    private lateinit var etDepartamento: EditText
    private lateinit var btnAgregar: Button
    private lateinit var listViewPersonas: ListView
    private lateinit var tvDetalles: TextView

    private val listaPersonas = mutableListOf<Persona>()
    private lateinit var adaptador: ArrayAdapter<Persona>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar vistas
        etNombre = findViewById(R.id.etNombre)
        etEdad = findViewById(R.id.etEdad)
        etDepartamento = findViewById(R.id.etDepartamento)
        btnAgregar = findViewById(R.id.btnAgregar)
        listViewPersonas = findViewById(R.id.listViewPersonas)
        tvDetalles = findViewById(R.id.tvDetalles)

        // Configurar adaptador para el ListView
        adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaPersonas)
        listViewPersonas.adapter = adaptador

        // Evento del botón Agregar
        btnAgregar.setOnClickListener {
            agregarPersona()
        }

        // Evento al hacer clic en un nombre de la lista
        listViewPersonas.setOnItemClickListener { _, _, position, _ ->
            mostrarDetalles(position)
        }
    }

    private fun agregarPersona() {
        // Obtener valores
        val nombre = etNombre.text.toString().trim()
        val edadStr = etEdad.text.toString().trim()
        val departamento = etDepartamento.text.toString().trim()

        // Validaciones
        if (nombre.isEmpty()) {
            etNombre.error = "Ingrese el nombre"
            etNombre.requestFocus()
            return
        }

        if (edadStr.isEmpty()) {
            etEdad.error = "Ingrese la edad"
            etEdad.requestFocus()
            return
        }

        if (departamento.isEmpty()) {
            etDepartamento.error = "Ingrese el departamento"
            etDepartamento.requestFocus()
            return
        }

        // Validar edad
        val edad = edadStr.toIntOrNull()
        if (edad == null || edad <= 0 || edad > 120) {
            etEdad.error = "Ingrese una edad válida (1-120)"
            etEdad.requestFocus()
            return
        }

        // Crear persona y agregar
        val persona = Persona(nombre, edad, departamento)
        listaPersonas.add(persona)
        adaptador.notifyDataSetChanged()

        // Limpiar campos
        etNombre.text.clear()
        etEdad.text.clear()
        etDepartamento.text.clear()
        etNombre.requestFocus()

        // Mensaje de éxito
        Toast.makeText(this, "Persona agregada correctamente", Toast.LENGTH_SHORT).show()
    }

    private fun mostrarDetalles(position: Int) {
        val persona = listaPersonas[position]

        // Mostrar los detalles en el TextView
        val detalles = "Nombre: ${persona.nombre}\n" +
                "Edad: ${persona.edad} años\n" +
                "Departamento: ${persona.departamento}"

        tvDetalles.text = detalles
    }
}