package iti.hepia.ch.lab4

import Forecast
import android.net.Uri
import android.os.AsyncTask
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.net.URL
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Request5Jours(private var activity: MainActivity?) : AsyncTask<Void, Void, Forecast>() {
    val queryParam : String = "q"
    val appidParam : String = "appid"
    val unitsParam : String = "units"

    override fun doInBackground(vararg p0: Void?): Forecast? {
        var forecastResult : Forecast = Forecast()
        val uriBuilder = Uri.Builder()
        uriBuilder.scheme("https")
                .authority("api.openweathermap.org")
                .appendPath("data")
                .appendPath("2.5")
                .appendPath("forecast")
                .appendQueryParameter(queryParam, "geneve")
                .appendQueryParameter(unitsParam, "metric")
                .appendQueryParameter(appidParam, "e47694d3f3d6ce95b99ed8b7263d6c11")
                .build()
        val url = URL(uriBuilder.toString())

        val client = OkHttpClient()
        val request = Request.Builder()
                .url(url)
                .build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
            val bodyResponse = response.body!!.string()
            val gson = Gson()
            forecastResult = gson.fromJson(bodyResponse, Forecast::class.java)
        }
        return forecastResult
    }

    override fun onPostExecute(result: Forecast?) {
        super.onPostExecute(result)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val parsedData = result?.list?.filter {
            LocalDateTime.parse(it.dt_txt, formatter).hour == 15
        }
        activity?.recycleView?.adapter = parsedData?.let { activity?.let { it1 ->
            RecycleAdapter(it,
                it1
            )
        } }
        activity?.recycleView?.layoutManager = LinearLayoutManager(activity)
        activity?.recycleView?.hasFixedSize()
        if (parsedData != null) {
            activity?.parsedData = parsedData
        }
    }
}