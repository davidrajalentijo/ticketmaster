package com.test.livestyled

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.test.livestyled.models.Event
import kotlinx.android.synthetic.main.list_events.view.*
import java.text.SimpleDateFormat

/*
    Adapter to manage a list of events
 */
class EventsAdapter(val events: List<Event>, private val actionsListener: OnActionsClickListener<Event>) : RecyclerView.Adapter<EventsAdapter
.EventsAdapterViewHolder>() {

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_events, parent, false)
        return EventsAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventsAdapterViewHolder, position: Int) {
        holder?.bindGoal(position)
    }

    inner class EventsAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindGoal(position: Int) {
            val event = events[position]
            itemView.apply {
                title_event.text = event.name
                Picasso.get().load(event.images.first().url).into(image)
                title_venue.text = event.venues.venue.first().address.address1
                date.text = convertDateFormat(event.dates.start.dateTime)
                selectAllCbx.setOnClickListener { actionsListener.onMoveClicked(itemView, event) }
                selectAllCbx.isChecked = event.favorite
            }
        }
    }

    private fun convertDateFormat(date: String): String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val formatter = SimpleDateFormat("HH:mm dd/MM/yyyy")
        return formatter.format(parser.parse(date))
    }
}