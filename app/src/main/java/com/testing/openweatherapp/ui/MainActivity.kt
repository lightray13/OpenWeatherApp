package com.testing.openweatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.testing.openweatherapp.R
import com.testing.openweatherapp.common.NavigationHost
import com.testing.openweatherapp.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationHost {

    companion object {
        private val TOOLBAR_DESTINATION = setOf(
            R.id.cityListFragment,
            R.id.weatherFragment
        )
    }

    private lateinit var appBarConfiguration: AppBarConfiguration

    private var navController: NavController? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainNavigationHostFragment) as? NavHostFragment ?: return
        navController = findNavController(R.id.mainNavigationHostFragment)
        appBarConfiguration = AppBarConfiguration(TOOLBAR_DESTINATION)
    }

    override fun registerToolbarWithNavigation(toolbar: Toolbar) {
        navController?.apply {
            toolbar.setupWithNavController(this, appBarConfiguration)
        }

        navController?.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id) {
                R.id.weatherFragment -> {
                    toolbar.title = arguments?.getString(Constants.EXTRAS_CITY_NAME)
                    toolbar.setNavigationIcon(R.drawable.baseline_arrow_back)
                }
            }
        }
    }

}