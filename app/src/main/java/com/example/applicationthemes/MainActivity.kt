package com.example.applicationthemes

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toolbar
import com.example.applicationthemes.databinding.ActivityMainBinding

const val DEF_THEME = "default"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val nameTheme = sharedPref.getString(SHARED_KEY, DEF_THEME)

        when (nameTheme) {
            DEF_THEME -> setTheme(R.style.AppTheme)
            INDIGO -> setTheme(R.style.AppTheme_ThemeIndigo)
            LIME -> setTheme(R.style.AppTheme_ThemeLime)
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        super.onCreate(savedInstanceState)
        setContentView(view)

        binding.topAppBar.setOnMenuItemClickListener() {
            when(it.itemId) {
                R.id.settings ->
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SettingsFragment.newInstance())
                        .addToBackStack(null)
                        .commit()
                else -> {true}
            }
            true
        }
    }

}