package com.example.taskmanager.view.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.example.taskmanager.R
import com.example.taskmanager.databinding.ActivityLoginRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * <h1>MainActivity</h1>
 * <p>
 *
 * </p>
 *
 * @author Tonmoy Chandra Ray
 */
@AndroidEntryPoint
class LoginRegistrationActivity : BaseActivity() {

    lateinit var binding: ActivityLoginRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_registration)
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return
    }

}