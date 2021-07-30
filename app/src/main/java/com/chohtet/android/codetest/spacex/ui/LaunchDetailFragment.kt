package com.chohtet.android.codetest.spacex.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.airbnb.lottie.LottieDrawable
import com.chohtet.android.codetest.spacex.MissionQuery
import com.chohtet.android.codetest.spacex.R
import com.chohtet.android.codetest.spacex.TAG
import com.chohtet.android.codetest.spacex.databinding.FragmentLaunchDetailBinding
import com.chohtet.android.codetest.spacex.ui.adapter.LaunchAdapter
import com.chohtet.android.codetest.spacex.utils.ViewState
import com.chohtet.android.codetest.spacex.viewmodel.LaunchViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [LaunchDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LaunchDetailFragment : DaggerFragment(){
    @Inject
    lateinit var viewModelFactory:ViewModelProvider.Factory
    private lateinit var viewModel:LaunchViewModel
    private lateinit var binding:FragmentLaunchDetailBinding
    private val args:LaunchDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this,viewModelFactory)[LaunchViewModel::class.java]
        observeLiveData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLaunchDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (args.item !=null){
            binding.launch = args.item
            if (!args.item!!.missionId.isNullOrEmpty() && !args.item!!.missionId!![0].isNullOrEmpty()){
                val missionId = args.item!!.missionId!![0]
                    viewModel.getMission(missionId!!)
            }
        }


    }
    private fun observeLiveData() {
        viewModel.mission.observe(this, { response ->
            when (response) {
                is ViewState.Loading -> {
                    Log.d(TAG, "show loading")
                    binding.group.visibility = View.GONE
                    binding.animView.visibility = View.VISIBLE
                    binding.animView.apply {
                        setAnimation("loading.json")
                        repeatCount = LottieDrawable.INFINITE
                        playAnimation()
                    }
                }
                is ViewState.Success -> {
                    Log.d(TAG, "success -> ${response.value}")
                    binding.group.visibility = View.VISIBLE
                    binding.animView.visibility = View.GONE
                    if (response.value !=null) {
                        binding.mission = response.value
                    }
                }
                is ViewState.Error -> {
                    Log.d(TAG, "fail -> ${response.message}")
                    binding.group.visibility = View.GONE
                    binding.animView.visibility = View.VISIBLE
                    binding.animView.apply {
                        setAnimation("network_error.json")
                        repeatCount = LottieDrawable.INFINITE
                        playAnimation()
                    }
                }
            }

        })
    }

}