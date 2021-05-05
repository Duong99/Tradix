package vn.com.nghiemduong.tradix.ui

import android.annotation.SuppressLint
import android.content.Intent
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
    private lateinit var countDownTimer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResendEmailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        countDownTimer = object : CountDownTimer(30000, 1000) {
            override fun onFinish() {
                updatePasswordPref(applicationContext, "123")
                Toast.makeText(
                    applicationContext,
                    "Reset password successfully",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(applicationContext, PasswordChangedActivity::class.java))
            }

            @SuppressLint("SetTextI18n")
            override fun onTick(p0: Long) {
                countTimer--
                binding.tvCountDownResendEmail.text =
                    "Wait $countTimer seconds before sending it"
            }
        }

        binding.tvResendEmail.setOnClickListener {
            binding.tvResendEmail.isEnabled = false

            countDownTimer.start()
        }
    }

    override fun onPause() {
        countDownTimer.cancel()
        super.onPause()
    }
}