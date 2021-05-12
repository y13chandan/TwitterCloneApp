package com.example.twittercloneapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.twittercloneapp.R
import com.example.twittercloneapp.commons.TwitterCloneAppData
import com.example.twittercloneapp.fragments.HomeFragment
import com.example.twittercloneapp.fragments.ProfileFragment
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
   @Inject lateinit var authentication: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handleBottomNavigation()
        if (TwitterCloneAppData.getUser() == null) {
            navigateToLoginActivity()
        }
    }

    private fun handleBottomNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.navigation_home -> {
                    openFragment(HomeFragment.newInstance())
                    return@setOnNavigationItemSelectedListener  true
                }
                R.id.navigation_profile -> {
                    openFragment(ProfileFragment.newInstance())
                    return@setOnNavigationItemSelectedListener  true
                }
            }
            return@setOnNavigationItemSelectedListener  false
        }
        bottom_navigation.selectedItemId = R.id.navigation_home
    }

    private fun openFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun navigateToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}