package hr.dice.amilosevic_.githubapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.dice.amilosevic_.githubapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.activity_main)
    }
}