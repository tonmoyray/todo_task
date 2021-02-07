package com.example.taskmanager.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.taskmanager.R
import com.example.taskmanager.data.local.AppSharedPreference
import com.example.taskmanager.databinding.ActivityMainBinding
import com.example.taskmanager.databinding.FragmentLoginBinding
import com.example.taskmanager.utils.CommonHelper
import com.example.taskmanager.utils.CustomToast
import com.example.taskmanager.view.fragment.LoginFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * <h1>MainActivity</h1>
 * <p>
 *
 * </p>
 *
 * @author Tonmoy Chandra Ray
 */
@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var appSharedPreference : AppSharedPreference
    private lateinit var binding: ActivityMainBinding
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_main)
        initWidget()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout -> {
                appSharedPreference.setIsLoggedIn(false)
                navigateToLoginActivity()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun initWidget() {
        binding.welcomeText.setText( getString(R.string.hi,appSharedPreference.getUserName()))

    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity()
            return
        }

        this.doubleBackToExitPressedOnce = true
        CustomToast.makeText(this, getString(R.string.please_press_again_to_exit), Toast.LENGTH_SHORT).show()

        GlobalScope.launch {
            delay(2000)
            doubleBackToExitPressedOnce = false
        }

    }
}