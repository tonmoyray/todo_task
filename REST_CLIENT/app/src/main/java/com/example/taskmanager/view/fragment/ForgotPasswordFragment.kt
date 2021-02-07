package com.example.taskmanager.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentForgotPasswordBinding
import com.example.taskmanager.databinding.FragmentLoginBinding
import com.example.taskmanager.utils.CommonHelper
import com.example.taskmanager.utils.Resource
import com.example.taskmanager.view.activity.BaseActivity
import com.example.taskmanager.viewmodel.LoginRegistrationViewModel

/**
 * <h1>ForgotPasswordFragment</h1>
 * <p>
 *  All password recovery Feature
 * </p>
 *
 * @author Tonmoy Chandra Ray
 */
class ForgotPasswordFragment : BaseFragment(){

    private val loginRegistrationViewModel : LoginRegistrationViewModel by activityViewModels()

    private lateinit var binding: FragmentForgotPasswordBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forgot_password, container, false)

        initWidget()
        initObserver()

        return binding.root
    }

    /**
     * Initialize & listen for GUI events
     * */
    private fun initWidget() {

        binding.sendRequest.setOnClickListener {

            if(CommonHelper.isValidEmail(binding.userName.text.toString())){
                binding.userNameTextInputLayout.error = null

                val navAction =  ForgotPasswordFragmentDirections.actionLoginToReset()
                Navigation.findNavController(requireView()).navigate(navAction)

            }else{
                binding.userNameTextInputLayout.error = getString(R.string.login_error_email_invalid)
                binding.userNameTextInputLayout.startAnimation(CommonHelper.shakeError())
            }
        }

    }

    private fun initObserver(){

        loginRegistrationViewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    //it.data?.data?.let { list -> updateCategoryList(list) }
                }

                Resource.Status.ERROR ->{
                    (activity as BaseActivity).showAlertDialog(getString(R.string.error_dialog_title),it.message.toString())
                }

                Resource.Status.LOADING -> {
                    (activity as BaseActivity).showProgressAlertDialog(getString(R.string.validating))
                }
            }
        })

    }

}