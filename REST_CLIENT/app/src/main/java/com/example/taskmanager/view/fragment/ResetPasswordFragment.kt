package com.example.taskmanager.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentForgotPasswordBinding
import com.example.taskmanager.databinding.FragmentLoginBinding
import com.example.taskmanager.databinding.FragmentResetPasswordBinding
import com.example.taskmanager.utils.CommonHelper
import com.example.taskmanager.utils.CustomToast
import com.example.taskmanager.utils.Resource
import com.example.taskmanager.view.activity.BaseActivity
import com.example.taskmanager.view.activity.LoginRegistrationActivity
import com.example.taskmanager.viewmodel.LoginRegistrationViewModel

/**
 * <h1>ResetPasswordFragment</h1>
 * <p>
 *  All password reset Feature
 * </p>
 *
 * @author Tonmoy Chandra Ray
 */
class ResetPasswordFragment : BaseFragment(){

    private val loginRegistrationViewModel : LoginRegistrationViewModel by activityViewModels()

    private lateinit var binding: FragmentResetPasswordBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_reset_password, container, false)

        initWidget()

        return binding.root
    }

    /**
     * Initialize & listen for GUI events
     * */
    private fun initWidget() {

        binding.changePassword.setOnClickListener {

            if(!binding.resetCode.text.isNullOrEmpty()){
                binding.resetCodeInputLayout.error = null

                if(!binding.password.text.isNullOrEmpty()){
                    binding.passwordTextInputLayout.error = null

                    if(!binding.confirmPassword.text.isNullOrEmpty()){
                        if(binding.password.text.toString() == binding.confirmPassword.text.toString()){
                            binding.confirmPasswordTextInputLayout.error = null
                            CustomToast.makeText(activity as LoginRegistrationActivity, getString(R.string.change_password_successful), Toast.LENGTH_LONG)

                            val navAction =  ResetPasswordFragmentDirections.actionResetToLogin()
                            Navigation.findNavController(requireView()).navigate(navAction)

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
                binding.resetCodeInputLayout.error = getString(R.string.reset_code_empty)
                binding.resetCodeInputLayout.startAnimation(CommonHelper.shakeError())
            }
        }
    }

}