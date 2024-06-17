package com.example.horoscopeapp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscope.Horoscope
import com.example.horoscope.R
import android.graphics.Color
import android.text.Highlights
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.util.Log


class HoroscopeAdapter(private var dataSet: List<Horoscope>, private val onItemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<HoroscopeViewHolder>() {

    private var highlightText: String? = null

    // Con este método creamos nuevas celdas y creamos las necesarias para mostrarlas
    //A demás intenta reciclar las que no se ven
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_horoscope, parent, false)
        return HoroscopeViewHolder(view)
    }
    // Este método simplemente es para decir cuantos elementos queremos mostrar
    override fun getItemCount(): Int {
        return dataSet.size
    }
    // Este método se llama cada vez que se va a visualizar una celda,
    // y lo utilizaremos para mostrar los datos de esa celda
    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        val horoscope = dataSet[position]
        holder.render(horoscope)
        if (highlightText != null){
            holder.highlight(highlightText!!)
        }
        holder.itemView.setOnClickListener {
            onItemClickListener(position)
        }
    }

    //Este nñetodo sirve para actualizar los datos
    fun updateData (newDataset: List<Horoscope>) {
        updateData(newDataset, null)
    }
    fun updateData(newDataset: List<Horoscope>, highlight: String?) {
        this.highlightText = highlight
        dataSet = newDataset
        notifyDataSetChanged()
    }

}
// Esta clase se encarga de guardarnos la vista y no tener que inflarla de nuevo
class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val nameTextView: TextView
    private val descTextView: TextView
    private val logoImageView: ImageView

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

    // El texto que coincide con la busqueda se subraya
    fun highlight(text: String) {
        try {
            val highlighted = nameTextView.text.toString().highlight(text)
            nameTextView.text = highlighted
        } catch (e: Exception) {
            //Log.i("SEARCH", "No coincide la busqueda ($text) con el nombre (${nameTextView.text.toString()})")
        }
        try {
            val highlighted = descTextView.text.toString().highlight(text)
            descTextView.text = highlighted
        } catch (e: Exception) {
            //Log.i("SEARCH", "No coincide la busqueda ($text) con la descripción (${descTextView.text.toString()})")
        }
    }
}

fun String.highlight(text: String) : SpannableString {
    val str = SpannableString(this)
    val startIndex = str.indexOf(text, 0, true)
    str.setSpan(BackgroundColorSpan(Color.YELLOW), startIndex, startIndex + text.length, 0)
    return str
}