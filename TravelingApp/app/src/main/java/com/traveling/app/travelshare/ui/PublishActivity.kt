package com.traveling.app.travelshare.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.traveling.app.databinding.ActivityPublishBinding

class PublishActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPublishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPublishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnCancel.setOnClickListener { finish() }

        binding.cardImagePicker.setOnClickListener {
            Toast.makeText(this, "Ouverture de la galerie...", Toast.LENGTH_SHORT).show()
        }

        binding.btnRecordAudio.setOnClickListener {
            Toast.makeText(this, "Enregistrement d'une note vocale...", Toast.LENGTH_SHORT).show()
        }

        binding.btnGenerateAITags.setOnClickListener {
            generateAITags()
        }

        binding.btnPublish.setOnClickListener {
            val description = binding.etDescription.text.toString()
            if (description.isBlank()) {
                Toast.makeText(this, "Veuillez ajouter une description.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(this, "Publication en cours...", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun generateAITags() {
        binding.chipGroupAITags.removeAllViews()
        val tags = listOf("#Nature", "#Montagne", "#Exploration", "#Détente")

        for (tag in tags) {
            val chip = Chip(this)
            chip.text = tag
            chip.isCloseIconVisible = true
            chip.setOnCloseIconClickListener { binding.chipGroupAITags.removeView(chip) }
            binding.chipGroupAITags.addView(chip)
        }

        Toast.makeText(this, "Tags générés par l'IA !", Toast.LENGTH_SHORT).show()
    }
}
