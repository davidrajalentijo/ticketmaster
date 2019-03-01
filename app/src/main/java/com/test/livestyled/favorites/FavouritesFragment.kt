package com.test.livestyled.favorites

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
    Manage the view of the events favorite list
 */
class FavouritesFragment : Fragment(), OnActionsClickListener<Event> {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.favorite_fragment, container, false)

        val lv = rootView.findViewById(R.id.recyclerview_main_data_favorite) as RecyclerView
        lv.layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayout.VERTICAL, false)

        lv.layoutManager = LinearLayoutManager(activity!!.applicationContext, RecyclerView.VERTICAL, false)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(activity!!.applicationContext)).get(MainViewModel::class.java)
        viewModel.getFavoriteEvents(true).observe(this, Observer<List<Event>> { data ->
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

        fun newInstance(sectionNumber: Int): FavouritesFragment {
            val fragment = FavouritesFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }

}