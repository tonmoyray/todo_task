package com.example.taskmanager.view.activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.taskmanager.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.progress_dialog.view.*


/**
 * <h1>BaseActivity</h1>
 * <p>
 *  Parent of all activities
 * </p>
 *
 * @author Tonmoy Chandra Ray
 */
@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {

    private lateinit var alertDialog: AlertDialog
    private lateinit var loadingAlertDialog: AlertDialog
    private val LOG_TAG = BaseActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun navigateToMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    fun navigateToLoginActivity(){
        val intent = Intent(this, LoginRegistrationActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    fun showAlertDialog(title: String, message : String){
        hideAlertDialog()
        hideProgressAlertDialog()

        alertDialog = MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setNeutralButton(getString(R.string.dialog_button_ok)){ dialog, which -> }
            .setMessage(message)
            .setCancelable(false)
            .show()
    }
    fun hideAlertDialog(){
        if(::alertDialog.isInitialized && alertDialog.isShowing){
            alertDialog.dismiss()
        }
    }


    fun showProgressAlertDialog(message : String){

        hideAlertDialog()
        hideProgressAlertDialog()

        val customLayout: View = layoutInflater.inflate(R.layout.progress_dialog, null)
        customLayout.text_progress_bar.text = message

        loadingAlertDialog = AlertDialog.Builder(this).setView(customLayout).create()
        loadingAlertDialog.setCanceledOnTouchOutside(false)
        loadingAlertDialog.show()
    }

    fun hideProgressAlertDialog(){
        if(::loadingAlertDialog.isInitialized && loadingAlertDialog.isShowing){
            loadingAlertDialog.dismiss()
        }
    }

    fun hideAllDialog(){
        hideAlertDialog()
        hideProgressAlertDialog()
    }

}