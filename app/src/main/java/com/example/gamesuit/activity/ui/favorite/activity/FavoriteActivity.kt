package com.example.gamesuit.activity.ui.favorite.activity

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.gamesuit.R
import com.example.gamesuit.activity.ui.favorite.fragment.FragmentMovie


class FavoriteActivity : AppCompatActivity() {
    var toolbarTitle: TextView? = null
    var toolbarDescription: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        initToolbar()
    }

    private fun initToolbar() {
        if (Build.VERSION.SDK_INT >= 21) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        }
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        initComponent()
    }

    private fun initComponent() {
        toolbarTitle = findViewById(R.id.toolbarTitle)
        toolbarDescription = findViewById(R.id.toolbarDescription)
        defaultFragment()
    }

    private fun launchFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content, fragment, null)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun defaultFragment() {
        val defaultfragment: Fragment = FragmentMovie()
        launchFragment(defaultfragment)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}