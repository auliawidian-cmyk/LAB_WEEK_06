package com.example.lab_week_06

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatModel
import com.example.lab_week_06.model.CatBreed
import com.example.lab_week_06.model.Gender

class MainActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recycler_view) }

    private val catAdapter by lazy {
        CatAdapter(layoutInflater, GlideImageLoader(this), object : CatAdapter.OnClickListener {
            override fun onItemClick(cat: CatModel) = showSelectionDialog(cat)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup adapter
        recyclerView.adapter = catAdapter

        // Setup layout manager
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        // Pasang ItemTouchHelper untuk swipe-to-delete
        val itemTouchHelper = ItemTouchHelper(catAdapter.swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        // Tambahkan data dummy
        catAdapter.setData(
            listOf(
                CatModel(Gender.Male, CatBreed.BalineseJavanese, "Iqbaal", "Silent and deadly", "https://cdn2.thecatapi.com/images/7dj.jpg"),
                CatModel(Gender.Female, CatBreed.ExoticShorthair, "Aulia", "Cuddly assassin", "https://cdn2.thecatapi.com/images/egv.jpg"),
                CatModel(Gender.Male, CatBreed.AmericanCurl, "Cakwee", "Award winning investigator", "https://cdn2.thecatapi.com/images/bar.jpg"),
                CatModel(Gender.Male, CatBreed.BalineseJavanese, "Alip", "Loves sunbeams", "https://cdn2.thecatapi.com/images/1.jpg"),
                CatModel(Gender.Female, CatBreed.ExoticShorthair, "Helehele", "Fast asleep", "https://cdn2.thecatapi.com/images/2.jpg"),
                CatModel(Gender.Unknown, CatBreed.AmericanCurl, "Nala", "Queen of the sofa", "https://cdn2.thecatapi.com/images/3.jpg"),
                CatModel(Gender.Male, CatBreed.BalineseJavanese, "Ollie", "Chases laser dots", "https://cdn2.thecatapi.com/images/4.jpg"),
                CatModel(Gender.Unknown, CatBreed.ExoticShorthair, "Zelda", "Curious about boxes", "https://cdn2.thecatapi.com/images/5.jpg"),
                CatModel(Gender.Female, CatBreed.AmericanCurl, "Poppy", "Snack enthusiast", "https://cdn2.thecatapi.com/images/6.jpg"),
                CatModel(Gender.Male, CatBreed.BalineseJavanese, "Simba", "Really loud purr", "https://cdn2.thecatapi.com/images/7.jpg")
            )
        )
    }

    // Tampilkan pop-up dialog saat item diklik
    private fun showSelectionDialog(cat: CatModel) {
        AlertDialog.Builder(this)
            .setTitle("Cat Selected")
            .setMessage("You have selected cat ${cat.name}")
            .setPositiveButton("OK") { _, _ -> }
            .show()
    }
}
