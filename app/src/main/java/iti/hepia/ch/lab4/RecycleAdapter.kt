package iti.hepia.ch.lab4

import ForecastData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RecycleAdapter(private val meteoList: List<ForecastData>, private val listener: OnItemClickListener) : RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val ivIcon: ImageView = itemView.findViewById(R.id.ivIcon)
        val tvTemp: TextView = itemView.findViewById(R.id.tvTemp)
        val tvJour: TextView = itemView.findViewById(R.id.tvJour)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_meteo, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = meteoList[position]
        holder.ivIcon.setImageResource(R.drawable.eclipse)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        holder.tvJour.text = LocalDateTime.parse(currentItem.dt_txt, formatter).dayOfWeek.toString() + ", " + LocalDateTime.parse(currentItem.dt_txt, formatter).dayOfMonth.toString() + "." + LocalDateTime.parse(currentItem.dt_txt, formatter).monthValue.toString()
        holder.tvTemp.text = currentItem.main.temp.toString() + " ˚C"
    }

    override fun getItemCount(): Int {
        return meteoList.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}