package com.test.livestyled.events

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.test.livestyled.*
import com.test.livestyled.models.Event

/*
    Manage the View of the events list
 */
class EventsFragment : Fragment(), OnActionsClickListener<Event> {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.events_fragment, container, false)

        val lv = rootView.findViewById(R.id.recyclerview_main_data_events) as RecyclerView
        lv.layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayout.VERTICAL, false)

        lv.layoutManager = LinearLayoutManager(activity!!.applicationContext, RecyclerView.VERTICAL, false)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(activity!!.applicationContext)).get(MainViewModel::class.java)
        viewModel.getEvents().observe(this, Observer<List<Event>> { data ->
            lv.adapter = EventsAdapter(data!!, this)
        })

        return rootView
    }

    override fun onMoveClicked(view: View, item: Event) {
        if (item.favorite) {
            item.favorite = false
            viewModel.updateEvent(item)
        } else {
            item.favorite = true
            viewModel.updateEvent(item)
        }
    }

    companion object {

        private val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(sectionNumber: Int): EventsFragment {
            val fragment = EventsFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }

}