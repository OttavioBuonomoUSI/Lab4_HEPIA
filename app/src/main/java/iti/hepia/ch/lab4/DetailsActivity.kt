package iti.hepia.ch.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val humidity = intent.getStringExtra("humidity")
        val wind = intent.getStringExtra("wind")
        val pressure = intent.getStringExtra("pressure")
        val temp = intent.getStringExtra("temp")
        val meteoDate = intent.getStringExtra("meteoDate")
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        this.findViewById<TextView>(R.id.tvHumidite).text = String.format("Humidité: %s", humidity) + "%"
        this.findViewById<TextView>(R.id.tvPression).text = String.format("Pression: %s hPa", pressure)
        this.findViewById<TextView>(R.id.tvTempDetails).text = String.format("Température: %s ˚C", temp)
        this.findViewById<TextView>(R.id.tvVent).text = String.format("Vent: %s km/h", wind)
        this.findViewById<TextView>(R.id.tvDate).text = LocalDateTime.parse(meteoDate, formatter).dayOfWeek.toString() + ", " + LocalDateTime.parse(meteoDate, formatter).dayOfMonth.toString() + "." + LocalDateTime.parse(meteoDate, formatter).monthValue.toString()

    }
}