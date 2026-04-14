package com.traveling.app.travelshare.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.traveling.app.databinding.ActivityFeedBinding
import com.traveling.app.travelshare.models.PhotoPost
import com.traveling.app.travelshare.models.ShareScope
import com.traveling.app.travelshare.models.User

class FeedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedBinding
    private var isAnonymous = true
    private var userName = "Voyageur"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isAnonymous = intent.getBooleanExtra("IS_ANONYMOUS", true)
        userName = intent.getStringExtra("USER_NAME") ?: "Voyageur"

        setupUI()
        setupFeed()
    }

    private fun setupUI() {
        if (isAnonymous) {
            binding.bannerAnonymous.visibility = View.VISIBLE
            binding.fabPublish.visibility = View.GONE

            binding.btnSignupBanner.setOnClickListener {
                startActivity(Intent(this, RegisterActivity::class.java))
            }
            binding.btnLoginBanner.setOnClickListener {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        } else {
            binding.bannerAnonymous.visibility = View.GONE
            binding.fabPublish.visibility = View.VISIBLE

            binding.fabPublish.setOnClickListener {
                startActivity(Intent(this, PublishActivity::class.java))
            }
        }

        binding.btnSearch.setOnClickListener {
            Toast.makeText(this, "Recherche (Lieu, auteur, tag...)", Toast.LENGTH_SHORT).show()
        }

        binding.btnLoginFeed.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun setupFeed() {
        val mockData = generateMockPhotos()

        val adapter = PhotoPostAdapter(
            posts = mockData,
            onLikeClicked = { post ->
                if (isAnonymous) {
                    Toast.makeText(this, "Connectez-vous pour liker.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "❤️ Vous aimez la photo de ${post.autheur.nomComplet} !", Toast.LENGTH_SHORT).show()
                }
            },
            onCommentClicked = { post ->
                if (isAnonymous) {
                    Toast.makeText(this, "Connectez-vous pour commenter.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Commenter la photo de ${post.autheur.nomComplet}...", Toast.LENGTH_SHORT).show()
                }
            },
            onPostClicked = { post ->
                val intent = Intent(this, PhotoDetailActivity::class.java)
                intent.putExtra("POST_ID", post.id)
                intent.putExtra("IS_ANONYMOUS", isAnonymous)
                startActivity(intent)
            }
        )
        binding.rvPhotoFeed.adapter = adapter
    }

    private fun generateMockPhotos(): List<PhotoPost> {
        return listOf(
            PhotoPost(
                id = "p1",
                autheur = User("u1", "Sophie Martin", "@sophie_m", ""),
                photoUrl = "",
                descriptionText = "Tour Eiffel, Paris",
                lieuNom = "Tour Eiffel, Paris",
                latitude = 48.8584,
                longitude = 2.2945,
                datePublicationMillis = System.currentTimeMillis() - 86400000,
                likesCount = 245,
                commentsCount = 18,
                scope = ShareScope.PUBLIC
            ),
            PhotoPost(
                id = "p2",
                autheur = User("u2", "Lucas Dupont", "@lucas_d", ""),
                photoUrl = "",
                descriptionText = "Coucher de soleil magique 🌅 #Tokyo",
                lieuNom = "Shibuya, Tokyo",
                latitude = 35.6598,
                longitude = 139.7006,
                datePublicationMillis = System.currentTimeMillis() - 172800000,
                likesCount = 312,
                commentsCount = 34,
                scope = ShareScope.PUBLIC
            ),
            PhotoPost(
                id = "p3",
                autheur = User("u3", "Emma Lefebvre", "@emma_voyage", ""),
                photoUrl = "",
                descriptionText = "Les ruines de Machu Picchu, inoubliable 🏔️ #Pérou",
                lieuNom = "Machu Picchu, Pérou",
                latitude = -13.1631,
                longitude = -72.5450,
                datePublicationMillis = System.currentTimeMillis() - 259200000,
                likesCount = 589,
                commentsCount = 62,
                scope = ShareScope.PUBLIC
            )
        )
    }
}
