package com.chohtet.android.codetest.spacex.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.chohtet.android.codetest.spacex.GetLaunchListQuery
import com.chohtet.android.codetest.spacex.TAG
import com.chohtet.android.codetest.spacex.databinding.FragmentLaunchListBinding
import com.chohtet.android.codetest.spacex.entity.LaunchEntity
import com.chohtet.android.codetest.spacex.ui.adapter.LaunchAdapter
import com.chohtet.android.codetest.spacex.utils.ViewState
import com.chohtet.android.codetest.spacex.viewmodel.LaunchViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_launch_list.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [LaunchListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LaunchListFragment : DaggerFragment(), LaunchAdapter.ClickListener {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: LaunchViewModel
    private lateinit var binding: FragmentLaunchListBinding
    private val launchAdapter by lazy { LaunchAdapter(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[LaunchViewModel::class.java]
        observeLiveData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLaunchListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.launchList.adapter = launchAdapter
        viewModel.getLaunchList()
    }

    private fun observeLiveData() {
        viewModel.launchesList.observe(this, { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "show loading")
                    binding.errorView.visibility = View.VISIBLE
                    binding.launchList.visibility = View.GONE
                   // binding.errorView.setAnimation()
                    binding.errorView.apply {
                        setAnimation("loading.json")
                        playAnimation()
                    }
                }
                is ViewState.Success -> {
                    Log.d(TAG, "success -> ${response.value?.size}")
                    if (!response.value.isNullOrEmpty()) {
                        launchAdapter.submitList(response.value)
                    }
                    binding.errorView.visibility = View.GONE
                    binding.launchList.visibility = View.VISIBLE
                }
                is ViewState.Error -> {
                    Log.d(TAG, "fail -> ${response.message}")
                    binding.errorView.visibility = View.VISIBLE
                    binding.launchList.visibility = View.GONE
                    binding.errorView.apply {
                        setAnimation("network_error.json")
                        repeatCount = LottieDrawable.INFINITE
                        playAnimation()
                    }
                }
            }

        })
    }

    override fun onItemClick(item: GetLaunchListQuery.Launch) {
        //Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
        val entity = LaunchEntity(
            id = item.id,
            siteName = item.launch_site?.site_name_long,
            details = item.details,
            missionId = item.mission_id
        )
        findNavController().navigate(
            LaunchListFragmentDirections.actionLaunchListFragmentToLaunchDetailFragment(
                item = entity
            )
        )
    }

}