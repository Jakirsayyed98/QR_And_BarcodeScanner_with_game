package com.jhcreativedeveloper.BarcodeScanner.Login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.jhcreativedeveloper.BarcodeScanner.Games.BaseFragment
import com.jhcreativedeveloper.BarcodeScanner.Games.Utilites.RandomeString
import com.jhcreativedeveloper.BarcodeScanner.Games.Utilites.isValidEmail
import com.jhcreativedeveloper.BarcodeScanner.Login.model.LoginModel
import com.jhcreativedeveloper.BarcodeScanner.MainActivity
import com.jhcreativedeveloper.BarcodeScanner.R
import com.jhcreativedeveloper.BarcodeScanner.databinding.FragmentRegisterBinding


class RegisterFragment :BaseFragment<FragmentRegisterBinding>(){

    private lateinit var auth: FirebaseAuth

    var firebaseDatabase: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null


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
        binding = FragmentRegisterBinding.inflate(layoutInflater,container,false)
        statusBarColor(R.color.white)
        statusBarTextWhite()
        auth = Firebase.auth
        binding!!.loginNow.setOnClickListener {
            ContainerActivity.openContainer(requireContext(),"","")
        }
        binding!!.register.setOnClickListener {
            logintext()
        }

        binding!!.repassword.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding!!.repassword.text.isNullOrEmpty().not()){
                    if (!binding!!.repassword.text.toString().equals(binding!!.password.text.toString())){
                        binding!!.repassword.setError("Re password should be match with PAssword")
                    }
                }
            }

        })
        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
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
            }else if (repassword.text.toString().isNullOrEmpty()){
                repassword.setError("Re-Password Cannot be Empty")
            }else{
                RegisterNow()
            }
        }
    }

    private fun RegisterNow() {

        val email = binding!!.email.text.toString()
        val password = binding!!.password.text.toString()
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful) {
                it.addOnSuccessListener {
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase!!.getReference("Users").child(it.user?.uid.toString());


                    val passwordET = binding!!.password.text.toString()
                    val emailET =binding!!.email.text.toString()

                    val loginmodel:LoginModel= LoginModel(it.user?.uid.toString(),emailET,passwordET)

                    databaseReference!!.addValueEventListener(object : ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            startActivity(Intent(requireContext(), MainActivity::class.java))
                            activity?.finish()
                            databaseReference!!.setValue(loginmodel)
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(requireContext(),error.toString(),Toast.LENGTH_SHORT).show()
                        }

                    })


                }
                it.addOnFailureListener {
                    Toast.makeText(requireContext(),it.message.toString(),Toast.LENGTH_SHORT).show()
                }

            }else{

                Toast.makeText(requireContext(),it.exception?.message.toString(),Toast.LENGTH_SHORT).show()
            }
        }

//        startActivity(Intent(requireContext(), MainActivity::class.java))
//        activity?.finish()
    }


    companion object {
          @JvmStatic
        fun newInstance() =
            RegisterFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}