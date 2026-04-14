package com.traveling.app.travelshare.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.traveling.app.databinding.ActivityPhotoDetailBinding

class PhotoDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotoDetailBinding
    private var isAnonymous = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isAnonymous = intent.getBooleanExtra("IS_ANONYMOUS", true)
        val postId = intent.getStringExtra("POST_ID") ?: ""

        setupUI()
        loadPost(postId)
    }

    private fun setupUI() {
        binding.btnBack.setOnClickListener { finish() }

        binding.btnLikeDetail.setOnClickListener {
            if (isAnonymous) {
                Toast.makeText(this, "Connectez-vous pour liker.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "❤️ Like !", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnCommentDetail.setOnClickListener {
            if (isAnonymous) {
                Toast.makeText(this, "Connectez-vous pour commenter.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Saisir un commentaire...", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnReportDetail.setOnClickListener {
            Toast.makeText(this, "Signalement envoyé à l'administrateur.", Toast.LENGTH_SHORT).show()
        }

        binding.btnNavigate.setOnClickListener {
            navigateToLocation(48.8584, 2.2945)
        }

        binding.btnSubscribeAuthor.setOnClickListener {
            if (isAnonymous) {
                Toast.makeText(this, "Connectez-vous pour vous abonner.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "✅ Abonné !", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadPost(postId: String) {
        binding.tvDetailLocation.text = "Tour Eiffel, Paris"
        binding.tvDetailDescription.text = "Une vue imprenable sur la Tour Eiffel. Moment magique lors de mon séjour parisien ! 🗼 #Paris #France"
        binding.tvDetailAuthor.text = "Sophie Martin"
        binding.tvDetailLikes.text = "245 J'aime"
        binding.tvDetailComments.text = "18 commentaires"
    }

    private fun navigateToLocation(lat: Double, lng: Double) {
        val uri = Uri.parse("geo:$lat,$lng?q=$lat,$lng")
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
        mapIntent.setPackage("com.google.android.apps.maps")
        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        } else {
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }
}
