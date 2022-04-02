package com.testing.openweatherapp.ui.cityList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.testing.openweatherapp.R
import com.testing.openweatherapp.adapter.CityListAdapter
import com.testing.openweatherapp.adapter.OnItemClickCallback
import com.testing.openweatherapp.common.MainNavigationFragment
import com.testing.openweatherapp.databinding.FragmentCityListBinding
import com.testing.openweatherapp.util.doOnChange
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_city_list.*

@AndroidEntryPoint
class CityListFragment : MainNavigationFragment(), OnItemClickCallback {
    private lateinit var binding: FragmentCityListBinding

    private val viewModel: CityListViewModel by viewModels()
    private var cityListAdapter = CityListAdapter(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCityListBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = this@CityListFragment.viewModel
            }
        observeViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
        viewModel.addCityList()
    }

    private fun initializeViews() {
        cityRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cityListAdapter
        }
    }

    override fun observeViewModel() {
        viewModel.cityListData.doOnChange(this) {
            cityListAdapter.updateList(it)
        }
    }

    override fun onItemClick(name: String) {
        navigate(R.id.action_cityListFragment_to_weatherFragment, name)
    }
}