package com.example.reignchallenge.view.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.reignchallenge.R
import com.example.reignchallenge.view.fragment.HitsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG : String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        showView()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            if (supportFragmentManager.backStackEntryCount != 1)
                onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showView() {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        val repositoryFragment = HitsFragment()
        fragmentTransaction.replace(R.id.fragment_container, repositoryFragment)
            .addToBackStack(HitsFragment().TAG)
            .commit()
    }
}
