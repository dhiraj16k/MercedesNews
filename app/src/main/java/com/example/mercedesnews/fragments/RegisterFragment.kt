package com.example.mercedesnews.fragments

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mercedesnews.R
import com.example.mercedesnews.databinding.FragmentRegisterBinding
import com.example.mercedesnews.ui.NewsActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var progressDialog: ProgressDialog
    private lateinit var firebaseAuth: FirebaseAuth

    private var email = ""
    private var password = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressDialog = ProgressDialog(requireContext())
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Creating Account")
        progressDialog.setCanceledOnTouchOutside(false)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnLogRegister.setOnClickListener{
            findNavController().navigate(RegisterFragmentDirections.
            actionRegisterFragmentToLoginFragment())
        }

        activity?.overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right)

        binding.buttonLogin.setOnClickListener{
            validateData()
        }

    }

    private fun validateData() {
        email = binding.newemailEt.text.toString().trim()
        password = binding.newPasswordEt.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.newemailEt.error = "Invalid Email Format"
        }
        else if (TextUtils.isEmpty(password)){
            binding.newPasswordEt.error = "Please Enter Password"
        }
        else if (password.length < 6){
            binding.newPasswordEt.error = "Atleast 6 Characters Required"
        }
        else{
            firebaseRegister()
        }
    }

    private fun firebaseRegister() {
        progressDialog.show()
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(requireContext(), "Account Created", Toast.LENGTH_SHORT).show()
                val intent = Intent (activity, NewsActivity::class.java)
                activity?.startActivity(intent)
            }
            .addOnFailureListener{ e->
                progressDialog.dismiss()
                Toast.makeText(requireContext(), "Registration Failed Due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}