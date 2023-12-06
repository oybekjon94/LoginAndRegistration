package com.oybekdev.loginandregistration

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.View
import com.oybekdev.loginandregistration.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() , View.OnClickListener,View.OnFocusChangeListener,View.OnKeyListener{

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fullNameEt.onFocusChangeListener = this
        binding.emailEt.onFocusChangeListener = this
        binding.passwordEt.onFocusChangeListener = this
        binding.cPasswordEt.onFocusChangeListener = this
    }

    private fun validateFullName():Boolean{
        var errorMessage:String? = null
        val value:String = binding.fullNameEt.text.toString()
        if (value.isEmpty()){
            errorMessage = "Full name is required"
        }

        if (errorMessage != null){
            binding.fullNameTill.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validateEmail():Boolean{
        var errorMessage:String? = null
        val value = binding.emailEt.text.toString()
        if (value.isEmpty()){
            errorMessage = "Email is required"
        }else if (!Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            errorMessage = "Email address is invalid"
        }
        if (errorMessage != null){
            binding.emailTill.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validatePassword():Boolean{
        var errorMessage:String? = null
        val value = binding.passwordEt.text.toString()
        if (value.isEmpty()){
            errorMessage = "Password is required"
        }else if (value.length < 6){
            errorMessage = "Password must be 6 characters long"
        }

        if (errorMessage != null){
            binding.passwordTill.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validateConfirmPassword():Boolean{
        var errorMessage:String? = null
        val value = binding.cPasswordEt.text.toString()
        if (value.isEmpty()){
            errorMessage = "Confirm password is required"
        }else if (value.length < 6){
            errorMessage = "Confirm password must be 6 characters long"
        }

        if (errorMessage != null){
            binding.cPasswordTill.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validatePasswordAndConfirmPassword():Boolean{
        var errorMessage:String? = null
        val password = binding.passwordEt.text.toString()
        val confirmPassword = binding.cPasswordEt.text.toString()
        if (password != confirmPassword){
            errorMessage = "Confirm password doen't match with password"
        }

        if (errorMessage != null){
            binding.cPasswordTill.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    override fun onClick(view: View?) {

    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if (view != null){
            when(view.id){
                R.id.fullNameEt -> {
                    if (hasFocus){
                        if (binding.fullNameTill.isErrorEnabled){
                            binding.fullNameTill.isErrorEnabled = false
                        }
                    }else{
                        validateFullName()
                    }
                }
                R.id.emailEt -> {
                    if (hasFocus){
                        if (binding.emailTill.isErrorEnabled){
                            binding.emailTill.isErrorEnabled = false
                        }
                    }else{
                        if (validateEmail()){
                            //do validate for its uniqueness

                        }
                    }
                }
                R.id.passwordEt -> {
                    if (hasFocus){
                        if (binding.passwordTill.isErrorEnabled){
                            binding.passwordTill.isErrorEnabled = false
                        }
                    }else{
                        if (validatePassword() && binding.cPasswordEt.text!!.isNotEmpty() && validateConfirmPassword() &&
                                validatePasswordAndConfirmPassword()){
                            if (binding.cPasswordTill.isErrorEnabled){
                                binding.cPasswordTill.isErrorEnabled = false
                            }
                            binding.cPasswordTill.apply {
                                setStartIconDrawable(R.drawable.baseline_check_circle_24)
                                setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                            }
                        }
                    }
                }
                R.id.cPasswordEt -> {
                    if (hasFocus){
                        if (binding.cPasswordTill.isErrorEnabled){
                            binding.cPasswordTill.isErrorEnabled = false
                        }
                    }else{
                        if (validateConfirmPassword() && validatePassword() && validatePasswordAndConfirmPassword()){
                            if (binding.passwordTill.isErrorEnabled){
                                binding.passwordTill.isErrorEnabled = false
                            }
                            binding.cPasswordTill.apply {
                                setStartIconDrawable(R.drawable.baseline_check_circle_24)
                                setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onKey(view: View?, event: Int, keyEvent: KeyEvent?): Boolean {
        return false
    }
}