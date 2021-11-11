package iti.hepia.ch.lab4

import ForecastData
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity(), RecycleAdapter.OnItemClickListener {
    lateinit var recycleView: RecyclerView
    lateinit var parsedData: List<ForecastData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycleView = findViewById(R.id.recycler_view)
        Request5Jours(this).execute()
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra("humidity", parsedData.get(position).main.humidity.toString())
            putExtra("wind", parsedData.get(position).wind.speed.toString())
            putExtra("pressure", parsedData.get(position).main.pressure.toString())
            putExtra("temp", parsedData.get(position).main.temp.toString())
            putExtra("meteoDate", parsedData.get(position).dt_txt)
        }
        startActivity(intent)
    }
}