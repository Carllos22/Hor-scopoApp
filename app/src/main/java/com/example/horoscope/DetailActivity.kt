package com.example.horoscope

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_HOROSCOPE_ID = "HOROSCOPE_ID"
    }

    lateinit var horoscope: Horoscope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getStringExtra(EXTRA_HOROSCOPE_ID)

        //Comentamos las dos variables para añadir a la funcion onCreate,
        //el HoroscopeProvider y los busque por sus respectivos ID.
        //val name = intent.getIntExtra("HOROSCOPE_NAME", -1)
        //val logo = intent.getIntExtra("HOROSCOPE_LOGO", -1)
        horoscope = HoroscopeProvider.findById(id!!)!!

        //Volvemos a comentar estos dos finView porque ahora mostraremos,
        //el texto y la imagen a través de la llamada en el DetailActivity
        //findViewById<TextView>(R.id.textView).setText(name)
        //findViewById<ImageView>(R.id.imageView).setImageResource(logo)
        findViewById<TextView>(R.id.textView).setText(horoscope.name)
        findViewById<ImageView>(R.id.imageView).setImageResource(horoscope.logo)
    }
}