package com.testing.openweatherapp.common

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.testing.openweatherapp.R
import com.testing.openweatherapp.util.Constants

interface InitViews {
    fun observeViewModel()
}

interface NavigationHost {
    fun registerToolbarWithNavigation(toolbar: Toolbar)
}

abstract class MainNavigationFragment: Fragment(), InitViews {
    private var navigationHost: NavigationHost? = null
    private var itemView: View? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationHost) {
            navigationHost = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        navigationHost = null
        itemView = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemView = view
    }

    override fun onResume() {
        super.onResume()

        val host = navigationHost ?: return
        val mainToolbar: Toolbar = itemView?.findViewById(R.id.toolbar) ?: return

        host.registerToolbarWithNavigation(mainToolbar)
    }

    fun navigate(resId: Int, name: String) {
        val bundle = bundleOf(Constants.EXTRAS_CITY_NAME to name)
        findNavController().navigate(resId, bundle)
    }
}