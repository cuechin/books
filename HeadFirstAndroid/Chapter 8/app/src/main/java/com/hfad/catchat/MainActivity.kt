package com.hfad.catchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.appbar.MaterialToolbar // The code uses the Material Toolbar class.
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar) // Use the toolbar like the app's default app bar.
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment // We're using this code because calling findNavController() from an activity's onCreate() method can fail.
        val navController = navHostFragment.navController // These lines get a reference to the navigation controller from the navigation host.
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val builder = AppBarConfiguration.Builder(navController.graph) // ...
        builder.setOpenableLayout(drawer)
        val appBarConfiguration = builder.build() // This builds a configuration linking the toolbar to the navigation graph.
        toolbar.setupWithNavController(navController, appBarConfiguration) // This lines applies the configuration to the toolbar.
        // val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        // bottomNavView.setupWithNavController(navController) // Link the bottom navigation bar to the navigation controller.
    }

    // Implementing this method adds any items in the menu resource file to the toolbar.
    // This method are only required for the toolbar menu. They're not needed for the bottom navigation bar.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu) // inflates the menu resource file. Behind the scenes, it creates a Menu object that represents the menu resource file, and any items the menu resource file contains are translated to MenuItem objects. These are then added to the toolbar.
        return super.onCreateOptionsMenu(menu)
    }

    // When an item in the toolbar is clicked, the navigation controller navigate to the correct destination.
    // This method are only required for the toolbar menu. They're not needed for the bottom navigation bar.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return item.onNavDestinationSelected(navController)
                || super.onOptionsItemSelected(item)
    }
}