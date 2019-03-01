package com.test.livestyled

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.test.livestyled.events.EventsFragment
import com.test.livestyled.favorites.FavouritesFragment
import kotlinx.android.synthetic.main.activity_main.*

/*
    Activity to manage EventFragment and FavouritesFragment
 */
class MainActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        viewpager.adapter = mSectionsPagerAdapter

        sliding_tabs.setupWithViewPager(viewpager)
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            if (position == 0) {
                return EventsFragment.newInstance(position + 1)
            } else {
                return FavouritesFragment.newInstance(position + 1)
            }
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            if (position == 0) {
                return resources.getString(R.string.tab_text_1)
            } else {
                return resources.getString(R.string.tab_text_2)
            }
        }
    }
}
