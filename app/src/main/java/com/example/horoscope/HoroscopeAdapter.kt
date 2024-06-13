package com.example.horoscopeapp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscope.Horoscope
import com.example.horoscope.R

class HoroscopeAdapter(private val dataset: List<Horoscope>, private val onItemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<HoroscopeViewHolder>() {

    // Con este método creamos nuevas celas y creamos las necesarias para mostrarlas
    //A demás intenta reciclar las que no se ven
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_horoscope, parent, false)
        return HoroscopeViewHolder(view)
    }
    // Este método simplemente es para decir cuantos elementos queremos mostrar
    override fun getItemCount(): Int {
        return dataset.size
    }
    // Este método se llama cada vez que se va a visualizar una celda,
    // y lo utilizaremos para mostrar los datos de esa celda
    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        val horoscope = dataset[position]
        holder.render(horoscope)
        holder.itemView.setOnClickListener {
            onItemClickListener(position)
        }
    }

}
// Esta clase se encarga de guardarnos la vista y no tener que inflarla de nuevo
class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nameTextView: TextView
    val descTextView: TextView
    val logoImageView: ImageView
    init {
        nameTextView = view.findViewById(R.id.nameTextView)
        descTextView = view.findViewById(R.id.descTextView)
        logoImageView = view.findViewById(R.id.logoImageView)
    }
    fun render(horoscope: Horoscope) {
        nameTextView.setText(horoscope.name)
        descTextView.setText(horoscope.description)
        logoImageView.setImageResource(horoscope.logo)
    }
}