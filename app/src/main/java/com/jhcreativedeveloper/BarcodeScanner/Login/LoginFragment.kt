package com.jhcreativedeveloper.BarcodeScanner.Login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jhcreativedeveloper.BarcodeScanner.Games.BaseFragment
import com.jhcreativedeveloper.BarcodeScanner.Games.Utilites.isValidEmail
import com.jhcreativedeveloper.BarcodeScanner.MainActivity
import com.jhcreativedeveloper.BarcodeScanner.R
import com.jhcreativedeveloper.BarcodeScanner.databinding.FragmentLoginBinding


class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        statusBarColor(R.color.white)
        statusBarTextWhite()

        auth = Firebase.auth

        binding!!.registernow.setOnClickListener {
            ContainerActivity.openContainer(requireContext(),"RegisterFragment","")
        }
        binding!!.login.setOnClickListener {
            logintext()
        }

        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
//            reload()
        }

    }

    private fun logintext() {
        binding!!.apply {
            if (email.text.toString().isNullOrEmpty()){
                email.setError("Email cannot be empty")
            }else if (!email.text.toString().isValidEmail()){
                email.setError("Please Enter valid Email id")
            }else if (password.text.toString().isNullOrEmpty()){
                password.setError("Password Cannot be Empty")
            }else{
                LoginNow()
            }
        }
    }

    private fun LoginNow() {
        startActivity(Intent(requireContext(),MainActivity::class.java))
        activity?.finish()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            LoginFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}