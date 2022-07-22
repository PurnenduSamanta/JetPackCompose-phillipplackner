package com.purnendu.jetpackcompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView

class TestActivityXml : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val generalCard = findViewById<CardView>(R.id.genral_category)
        val sportsCard = findViewById<CardView>(R.id.sports_category)
        val techCard = findViewById<CardView>(R.id.technology_category)
        val businessCard = findViewById<CardView>(R.id.business_category)
        val healthCard = findViewById<CardView>(R.id.health_categity)
        val scienceCard = findViewById<CardView>(R.id.science_category)

        generalCard.setOnClickListener {

            Toast.makeText(this, "Hello from general", Toast.LENGTH_SHORT).show()

        }

        sportsCard.setOnClickListener {

            Toast.makeText(this, "Hello from sports", Toast.LENGTH_SHORT).show()

        }

        techCard.setOnClickListener {

            Toast.makeText(this, "Hello from tech", Toast.LENGTH_SHORT).show()

        }

        businessCard.setOnClickListener {

            Toast.makeText(this, "Hello from business", Toast.LENGTH_SHORT).show()

        }

        healthCard.setOnClickListener {

            Toast.makeText(this, "Hello from health", Toast.LENGTH_SHORT).show()

        }

        scienceCard.setOnClickListener {

            Toast.makeText(this, "Hello from science", Toast.LENGTH_SHORT).show()

        }


    }
}