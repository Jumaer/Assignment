package com.periscope.jumaer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.periscope.jumaer.databinding.ActivityMainBinding

class PeriscopeMainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNav()



    }





    private fun setNav() {
        val navGraph: NavGraph
        val navController: NavController
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        navGraph.apply {
            setStartDestination(R.id.launcherFragment)
        }
        navController.setGraph(navGraph,intent.extras)
    }
}