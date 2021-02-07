package com.example.taskmanager.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.taskmanager.R
import com.example.taskmanager.data.local.AppSharedPreference
import com.example.taskmanager.databinding.FragmentLoginBinding
import com.example.taskmanager.databinding.FragmentSignUpBinding
import com.example.taskmanager.utils.CommonHelper
import com.example.taskmanager.utils.Resource
import com.example.taskmanager.view.activity.BaseActivity
import com.example.taskmanager.view.activity.LoginRegistrationActivity
import com.example.taskmanager.viewmodel.LoginRegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * <h1>Sign up fragment</h1>
 * <p>
 *  All sign up Feature
 * </p>
 *
 * @author Tonmoy Chandra Ray
 */
@AndroidEntryPoint
class SignUpFragment : BaseFragment(){

    private val loginRegistrationViewModel : LoginRegistrationViewModel by activityViewModels()

    private lateinit var binding: FragmentSignUpBinding

    @Inject
    lateinit var appSharedPreference : AppSharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)

        initWidget()
        initObserver()

        return binding.root
    }

    /**
     * Initialize & listen for GUI events
     * */
    private fun initWidget() {

        binding.signUp.setOnClickListener {

            if(!binding.userName.text.isNullOrEmpty()){
                binding.userNameTextInputLayout.error = null

                if(!binding.password.text.isNullOrEmpty()){
                    binding.passwordTextInputLayout.error = null

                    if(!binding.confirmPassword.text.isNullOrEmpty()){

                        if(binding.password.text.toString() == binding.confirmPassword.text.toString()){

                            binding.confirmPasswordTextInputLayout.error = null
                            loginRegistrationViewModel.register(
                                binding.userName.text.toString(),
                                binding.email.text.toString(),
                                binding.password.text.toString()
                            )
                        }else{
                            binding.confirmPasswordTextInputLayout.error = getString(R.string.password_does_not_match)
                            binding.confirmPasswordTextInputLayout.startAnimation(CommonHelper.shakeError())
                        }
                    }else{
                        binding.confirmPasswordTextInputLayout.error = getString(R.string.login_error_password_empty)
                        binding.confirmPasswordTextInputLayout.startAnimation(CommonHelper.shakeError())
                    }
                }else{
                    binding.passwordTextInputLayout.error = getString(R.string.login_error_password_empty)
                    binding.passwordTextInputLayout.startAnimation(CommonHelper.shakeError())
                }
            }else{
                binding.userNameTextInputLayout.error = getString(R.string.username_empty)
                binding.userNameTextInputLayout.startAnimation(CommonHelper.shakeError())
            }
        }

    }

    private fun initObserver(){

        loginRegistrationViewModel.registrationResponse.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    appSharedPreference.setIsLoggedIn(true)
                    it?.data?.user?.userName?.let { it1 -> appSharedPreference.setUserName(it1) }
                    it?.data?.user?.email?.let { it1 -> appSharedPreference.setEmail(it1) }
                    (activity as LoginRegistrationActivity).navigateToMainActivity()
                }

                Resource.Status.ERROR ->{
                    (activity as BaseActivity).showAlertDialog(getString(R.string.error_dialog_title),it.message.toString())
                }

                Resource.Status.LOADING -> {
                    (activity as BaseActivity).showProgressAlertDialog(getString(R.string.registering))
                }
            }
        })

    }

}