package vn.com.nghiemduong.tradix.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import vn.com.nghiemduong.tradix.R
import vn.com.nghiemduong.tradix.databinding.ActivityResendEmailBinding
import vn.com.nghiemduong.tradix.utils.updatePasswordPref

class ResendEmailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResendEmailBinding
    private var countTimer = 30
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResendEmailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.tvResendEmail.setOnClickListener {
            binding.tvResendEmail.isEnabled = false

            object : CountDownTimer(30000, 1000) {
                override fun onFinish() {
                    updatePasswordPref(applicationContext, "123")
                    Toast.makeText(
                        applicationContext,
                        "Reset password successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }

                @SuppressLint("SetTextI18n")
                override fun onTick(p0: Long) {
                    countTimer--
                    binding.tvCountDownResendEmail.text =
                        "Wait $countTimer seconds before sending it"
                }
            }.start()
        }
    }
}