package com.traveling.app.travelshare.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.traveling.app.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadProfile()
        setupListeners()
    }

    private fun loadProfile() {
        binding.tvProfileInitials.text = "T"
        binding.tvProfileName.text = "test"
        binding.tvProfileEmail.text = "test@test.com"
        binding.tvPhotosCount.text = "24"
        binding.tvLikesCount.text = "1342"
        binding.tvFollowersCount.text = "156"
        binding.tvFollowingCount.text = "89"
    }

    private fun setupListeners() {
        binding.btnBack.setOnClickListener { finish() }

        binding.btnTabPhotos.setOnClickListener {
            Toast.makeText(this, "Mes photos", Toast.LENGTH_SHORT).show()
        }

        binding.btnTabLikes.setOnClickListener {
            Toast.makeText(this, "Photos likées", Toast.LENGTH_SHORT).show()
        }

        binding.btnGroupes.setOnClickListener {
            startActivity(Intent(this, GroupsActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}
