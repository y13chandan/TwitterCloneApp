package com.example.twittercloneapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.twittercloneapp.R
import com.example.twittercloneapp.activity.LoginActivity
import com.example.twittercloneapp.commons.TwitterCloneAppData
import com.example.twittercloneapp.activity.SignupActivity
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    @Inject lateinit var authentication: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        TwitterCloneAppData.getUser()?.let {
            tvName.text = it.name
            tvPhone.text = it.phone
            tvEmail.text = it.email
        }
        logout()
    }

    private fun logout() {
        btnLogout.setOnClickListener {
            authentication.signOut()
        }
        authentication.addAuthStateListener {
            if (it.currentUser == null)  {
                TwitterCloneAppData.setUserData(null)
                navigateToLoginActivity()
            }
        }
    }

    private fun navigateToLoginActivity() {
        val intent = Intent(context, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context?.startActivity(intent)
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            ProfileFragment()
    }
}