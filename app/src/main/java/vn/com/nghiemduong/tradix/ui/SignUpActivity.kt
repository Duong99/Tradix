package vn.com.nghiemduong.tradix.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import vn.com.nghiemduong.tradix.R
import vn.com.nghiemduong.tradix.databinding.ActivitySignUpBinding
import vn.com.nghiemduong.tradix.utils.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.tvForgotPassword.setOnClickListener {
            startActivity(Intent(this, ResendEmailActivity::class.java))
        }

        binding.tvSignUp.setOnClickListener {
            if (binding.etEmail.text.toString().isEmpty()) {
                Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show()
                binding.etEmail.requestFocus()
            } else if (binding.etPassword.text.toString().isEmpty()) {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
                binding.etPassword.requestFocus()
            } else {
                if (binding.etEmail.text.toString() != getEmailPref(applicationContext)) {
                    updateEmailPref(applicationContext, binding.etEmail.text.toString())
                    updatePasswordPref(applicationContext, binding.etPassword.text.toString())
                    Toast.makeText(this, "Sign up successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent()
                    intent.putExtra(EMAIL, binding.etEmail.text.toString())
                    intent.putExtra(PASSWORD, binding.etPassword.text.toString())
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                } else {
                    Toast.makeText(this, "Email exits", Toast.LENGTH_SHORT).show()
                    binding.etEmail.requestFocus()
                }
            }
        }
    }
}