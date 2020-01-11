package ru.shiftlab.mvvmshiftlab

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


// https://developer.android.com/training/animation/screen-slide
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
        bottomNavigationView
        visibilityNavElements(navController)
    }

    // Ставим слушатель для контроля видимости нижней панели на разных фрагментах
    private fun visibilityNavElements(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.vacancyDetailFragment -> {
                    configurableToolbar(
                        bottomNavigation = false,
                        backNavigation = true,
                        search = false,
                        title = true
                    )
                }
                R.id.profileFragment -> {
                    configurableToolbar(
                        menu = R.menu.profile_menu,
                        bottomNavigation = true,
                        backNavigation = false,
                        search = false,
                        title = true
                    )
                }
                R.id.vacancyFragment -> {
                    configurableToolbar(
                        bottomNavigation = true,
                        backNavigation = false,
                        search = true,
                        title = false
                    )
                }
            }
        }
    }

    private fun configurableToolbar(
        menu: Int? = null,
        bottomNavigation: Boolean = false,
        backNavigation: Boolean = false,
        search: Boolean = false,
        title: Boolean = true
    ) {
        supportActionBar?.setDisplayShowTitleEnabled(title)

        if (menu == null) toolbar.menu.clear()
        else toolbar.inflateMenu(menu)

        search_bar.visibility = if (search) View.VISIBLE else View.GONE

        bottomNavigationView?.visibility = if (bottomNavigation) View.VISIBLE else View.GONE

        if (backNavigation) toolbar.setNavigationIcon(R.drawable.ic_left_arrow)
    }
}
