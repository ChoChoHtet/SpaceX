package com.chohtet.android.codetest.spacex

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.chohtet.android.codetest.spacex.databinding.ActivityMainBinding
import com.chohtet.android.codetest.spacex.ui.adapter.LaunchAdapter
import com.chohtet.android.codetest.spacex.utils.ViewState
import com.chohtet.android.codetest.spacex.viewmodel.LaunchViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

const val TAG = "space_x"

class MainActivity : DaggerAppCompatActivity() {
//    @Inject
//    lateinit var viewModelFactory: ViewModelProvider.Factory
//    private lateinit var viewModel: LaunchViewModel
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var navController: NavController
  //  private val launchAdapter by lazy { LaunchAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        val navHostFragment =supportFragmentManager.
        findFragmentById(activityMainBinding.navHostFragmentContainer.id) as NavHostFragment
        navController = navHostFragment.findNavController()
        setupActionBarWithNavController(navController)
       // viewModel = ViewModelProvider(this, viewModelFactory)[LaunchViewModel::class.java]
       // activityMainBinding.launchList.adapter = launchAdapter
        //viewModel.getLaunchList()
        //observeLiveData()
    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp()
        return super.onSupportNavigateUp()
    }


}