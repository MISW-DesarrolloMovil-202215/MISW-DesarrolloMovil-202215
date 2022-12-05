package com.example.vinilosapp_g18

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.URLUtil
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.vinilosapp_g18.network.NetworkServiceAdapter
import com.example.vinilosapp_g18.ui.ListAlbumes
import org.json.JSONObject
import java.util.*

class CreateAlbum : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_album)

        val genresSpinner: Spinner = findViewById(R.id.albumGenre_spinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.genres_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            genresSpinner.adapter = adapter
        }

        val recordLabelSpinner: Spinner = findViewById(R.id.albumRecordLabel_spinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.recordLabel_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            recordLabelSpinner.adapter = adapter
        }

        val dateEdt = findViewById<EditText>(R.id.idEdtDate)

        dateEdt.setOnClickListener {

            // on below line we are getting
            // the instance of our calendar.
            val c = Calendar.getInstance()

            // on below line we are getting
            // our day, month and year.
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our edit text.
                    var month = ""
                    var day = ""
                    var monthOfYearFixed = monthOfYear + 1
                    if(monthOfYearFixed.toString().length < 2) {
                        month = monthOfYearFixed.toString().padStart(2, '0')
                    } else {
                        month = monthOfYearFixed.toString()
                    }
                    if(dayOfMonth.toString().length < 2) {
                        day = dayOfMonth.toString().padStart(2, '0')
                    } else {
                        day = dayOfMonth.toString()
                    }

                    val dat = (year.toString() + "-" + month + "-" + day + "T05:00:00.000Z" )
                    dateEdt.setText(dat)
                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,
                month,
                day
            )
            // at last we are calling show
            // to display our date picker dialog.
            datePickerDialog.show()
        }

        val btnCreateAlbum = findViewById<Button>(R.id.btn_newAlbum)
        btnCreateAlbum.setOnClickListener {

            try {
                val albumName = findViewById<EditText>(R.id.albumName_text_input).text
                val albumCover = findViewById<EditText>(R.id.albumCover_text_input).text
                val albumReleaseDate = findViewById<EditText>(R.id.idEdtDate).text
                val albumDescription = findViewById<EditText>(R.id.albumDescripcion_text_input).text
                val albumGenre = genresSpinner.selectedItem.toString()
                val albumRecordLabel =  recordLabelSpinner.selectedItem.toString()
                if(albumName.isEmpty() || albumCover.isEmpty() || albumReleaseDate.isEmpty() || albumDescription.isEmpty() || albumGenre.isEmpty() || albumRecordLabel.isEmpty()) {
                    Toast.makeText(this@CreateAlbum, "Se deben completar todos los campos para crear un albúm!", Toast.LENGTH_SHORT).show()
                } else {
                    if (URLUtil.isValidUrl(albumCover.toString())) {
                        val album = JSONObject()
                        album.put("name", albumName)
                        album.put("cover", albumCover)
                        album.put("releaseDate", albumReleaseDate)
                        album.put("description", albumDescription)
                        album.put("genre", albumGenre)
                        album.put("recordLabel", albumRecordLabel)
                        NetworkServiceAdapter.getInstance(application).postNewAlbum(album)
                        Log.d("API", "" + albumReleaseDate)
                        intent = Intent(this, ListAlbumes::class.java)
                        Toast.makeText(this@CreateAlbum, "Se ha agregado un nuevo albúm!", Toast.LENGTH_SHORT).show()
                        finish()
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@CreateAlbum, "La URL del cover del albúm debe ser válida!", Toast.LENGTH_SHORT).show()
                    }
                }

            } catch (e: Exception) {
                Toast.makeText(this@CreateAlbum, "Ha ocurrido un error, por favor revise todos los campos!", Toast.LENGTH_SHORT).show()
            }

        }
    }
    override fun onBackPressed() {
        intent = Intent(this, ListAlbumes::class.java)
        startActivity(intent)
        finish()
    }
}