package com.fangzsx.animu_db.ui.activities

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.fangzsx.animu_db.R
import com.fangzsx.animu_db.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(binding.btmNavigation, navController)
    }

    override fun onBackPressed() {
        val alertDialog = AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle("Exit Application")
            .setMessage("Are you sure you want to leave this application?")
            .setPositiveButton("YES", DialogInterface.OnClickListener{ _, _
                -> finish()
            })
            .setNegativeButton("NO", DialogInterface.OnClickListener { dialog, _
                -> dialog.dismiss()
            })

        alertDialog.show()
    }
}