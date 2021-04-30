package vn.com.nghiemduong.tradix.ui

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import vn.com.nghiemduong.tradix.R
import vn.com.nghiemduong.tradix.databinding.ActivityLoginBinding
import vn.com.nghiemduong.tradix.utils.*

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        if (getCheckLoginPref(applicationContext)) {
            startActivity(Intent(this, MainActivity::class.java))
        }

        setContentView(binding.root)

        binding.tvSignUp.setOnClickListener {
            startActivityForResult(
                Intent(this, SignUpActivity::class.java),
                REQUEST_CODE_SING_UP
            )
        }

        binding.tvForgotPassword.setOnClickListener {
            startActivity(Intent(this, ResendEmailActivity::class.java))
        }

        binding.tvLogin.setOnClickListener {
            if (binding.etEmail.text.toString().isEmpty()) {
                Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show()
                binding.etEmail.requestFocus()
            } else if (binding.etPassword.text.toString().isEmpty()) {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
                binding.etPassword.requestFocus()
            } else {
                if (binding.etEmail.text.toString() == getEmailPref(applicationContext)) {
                    if (binding.etPassword.text.toString() == getPasswordPref(applicationContext)) {
                        updateCheckLoginPref(applicationContext, true)
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        Toast.makeText(this, "Password not correct", Toast.LENGTH_SHORT).show()
                        binding.etPassword.requestFocus()
                    }
                } else {
                    Toast.makeText(this, "Email not correct", Toast.LENGTH_SHORT).show()
                    binding.etEmail.requestFocus()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == REQUEST_CODE_SING_UP)
                binding.etEmail.text = Editable.Factory.getInstance().newEditable(
                    data.getStringExtra(
                        EMAIL
                    )
                )
            binding.etPassword.text = Editable.Factory.getInstance().newEditable(
                data.getStringExtra(
                    PASSWORD
                )
            )
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}