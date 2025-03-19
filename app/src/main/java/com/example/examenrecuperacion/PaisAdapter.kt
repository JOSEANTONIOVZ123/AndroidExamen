package com.example.examenrecuperacion


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class PaisAdapter(private val contactos: MutableList<Pais>, private val context: Context) :
    RecyclerView.Adapter<PaisAdapter.ContactoViewHolder>() {

    inner class ContactoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.textViewNombre)
        val telefono: TextView = itemView.findViewById(R.id.textViewTelefono)
        val imagen: ImageView = itemView.findViewById(R.id.imageViewContacto)
        val imagenUE: ImageView = itemView.findViewById(R.id.imageUE)

        init {
            itemView.setOnClickListener {
                val intent = Intent(context, DetalleActivity::class.java)
                intent.putExtra("id_contacto", adapterPosition)
                context.startActivity(intent)
            }

            itemView.setOnLongClickListener {
                mostrarMenu(it, adapterPosition)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ContactoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactoViewHolder, position: Int) {
        val contacto = contactos[position]
        holder.nombre.text = contacto.nombre
        holder.telefono.text = contacto.capital
        holder.imagen.setImageResource(contacto.bandera)
        holder.imagenUE.setImageResource(contacto.europa)
    }

    override fun getItemCount(): Int = contactos.size

    private fun mostrarMenu(view: View, position: Int) {
        val popupMenu = PopupMenu(context, view)
        popupMenu.menuInflater.inflate(R.menu.menu_contextual, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_editar -> {
                    val intent = Intent(context, EditarActivity::class.java)
                    intent.putExtra("id_contacto", position)
                    context.startActivity(intent)
                    true
                }
                R.id.menu_eliminar -> {
                    confirmarEliminarContacto(position)
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    private fun confirmarEliminarContacto(position: Int) {
        AlertDialog.Builder(context)
            .setTitle("Eliminar País")
            .setMessage("¿Estás seguro de que deseas eliminar este País?")
            .setPositiveButton("Sí") { _, _ ->
                contactos.removeAt(position)
                notifyItemRemoved(position)
            }
            .setNegativeButton("No", null)
            .show()
    }




}
