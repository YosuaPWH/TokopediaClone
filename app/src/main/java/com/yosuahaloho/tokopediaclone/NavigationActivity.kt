package com.yosuahaloho.tokopediaclone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.yosuahaloho.tokopediaclone.databinding.ActivityNavigationBinding
import com.yosuahaloho.tokopediaclone.ui.login.LoginActivity
import com.yosuahaloho.tokopediaclone.util.LoginPrefs

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_navigation)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_keranjang
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setOnItemSelectedListener {
            if (it.itemId == R.id.navigation_keranjang) {
                val pref = LoginPrefs(this)
                if (pref.getIsLogin()) {
                    Log.i("login", "Sudah login")
                    navController.navigate(it.itemId)

                } else {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }

            } else {
                navController.navigate(it.itemId)

            }

            return@setOnItemSelectedListener true
        }
    }

//    override fun onBackPressed() {
//        if (LoginPrefs(this).getIsLogin()) {
//            finishAffinity()
//            finish()
//        }
//    }
}