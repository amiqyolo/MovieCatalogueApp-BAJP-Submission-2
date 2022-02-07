package com.android.moviecatalogueapp.ui.home

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.moviecatalogueapp.R
import com.android.moviecatalogueapp.databinding.ActivityHomeBinding
import com.android.moviecatalogueapp.ui.movies.MoviesFragment
import com.android.moviecatalogueapp.ui.tvshows.TvShowsFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    @StringRes
    private val tabsTitle = intArrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupNavigation()
    }

    private fun setupNavigation() {
        binding.viewPager2.adapter = SectionsPagerAdapter(this)
        TabLayoutMediator(binding.tabs, binding.viewPager2) { tab, position ->
            tab.text = resources.getString(tabsTitle[position])
        }.attach()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = resources.getString(R.string.app_name)
    }

    inner class SectionsPagerAdapter(activity: AppCompatActivity) :
        FragmentStateAdapter(activity) {

        override fun getItemCount(): Int = tabsTitle.size

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> MoviesFragment.newInstance()
                else -> TvShowsFragment.newInstance()
            }
        }
    }
}