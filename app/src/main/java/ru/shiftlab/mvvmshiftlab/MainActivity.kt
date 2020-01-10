package ru.shiftlab.mvvmshiftlab

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var bottomBar: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Получаем ссылку на NavHostFragment
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.myNavHostFragment) as NavHostFragment? ?: return
        navController = host.navController

        //Toolbar
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        val toolBar = findViewById<Toolbar>(R.id.toolbar)
        toolBar.setupWithNavController(navController, appBarConfiguration)

        bottomBar = findViewById(R.id.bottomNavigationView)
        bottomBar.setupWithNavController(navController)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            navController.navigate(item.itemId)
            return@setOnNavigationItemSelectedListener true
        }
    }

}
