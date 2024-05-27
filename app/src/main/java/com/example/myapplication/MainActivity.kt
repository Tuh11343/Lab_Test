package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.SignInLayoutBinding
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private lateinit var binding: SignInLayoutBinding
    private lateinit var controller: Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignInLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        controller = ViewModelProvider(this)[Controller::class.java]

        binding.btnSignIn.setOnClickListener {
            btnSignInClick()
        }

        observeAccount()
    }

    private fun btnSignInClick() {
        controller.callAPI(
            "nvtest@tmmn",
            "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3",
            "22184a05bdd68cb1",
            "123456",
            "1.5.7",
            "Samsung SM-A315G"
        )
        /*if (binding.gmail.text!!.isNotBlank() || binding.password.text!!.isNotBlank()) {
            controller.callAPI(
                "nvtest@tmmn",
                "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3",
                "22184a05bdd68cb1",
                123456,
                "1.5.7",
                "Samsung SM-A315G"
            )
        }*/
    }

    private fun observeAccount() {
        controller.userNameVM.observe(this) { data ->
            if (data != null) {
                binding.userName.text=data.toString()
            }
        }

        controller.userRoleVM.observe(this) { data ->
            if (data != null) {
                binding.userRole.text=data.toString()
            }
        }
    }


}



