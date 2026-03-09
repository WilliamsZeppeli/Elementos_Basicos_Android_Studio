package com.example.elementosbasicos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.elementosbasicos.databinding.ActivityMainBinding
import com.example.elementosbasicos.fragments.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            loadFragment(TextFieldsFragment())
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.nav_textfields -> TextFieldsFragment()
                R.id.nav_buttons    -> ButtonsFragment()
                R.id.nav_selection  -> SelectionFragment()
                R.id.nav_lists      -> ListsFragment()
                R.id.nav_info       -> InfoFragment()
                else                -> TextFieldsFragment()
            }
            loadFragment(fragment)
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}