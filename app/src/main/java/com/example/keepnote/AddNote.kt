package com.example.keepnote

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.keepnote.Models.Note
import com.example.keepnote.databinding.ActivityAddnotesBinding
import com.example.keepnote.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date

class AddNote : AppCompatActivity() {

    private lateinit var binding: ActivityAddnotesBinding

    private lateinit var note: Note
    private lateinit var old_note : Note
    var isUpdate = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddnotesBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        try{
            old_note = intent.getSerializableExtra("current_note") as Note
            binding.etTitle.setText(old_note.title)
            binding.etNotes.setText(old_note.note)
            isUpdate = true
        }catch (e : Exception){
            e.printStackTrace()
        }

        binding.imgCheck.setOnClickListener{

            val title = binding.etTitle.text.toString()
            val notedt = binding.etNotes.text.toString()

            if(title.isNotEmpty() || notedt.isNotEmpty()){
                val formatter = SimpleDateFormat("EEE, d MMM YYYY HH:mm a")
                val currentDate = formatter.format(Date())

                if(isUpdate){

                    note = Note(old_note.id, title, notedt, currentDate)
                }else{
                    note = Note(0, title,notedt,currentDate)
                }

                val intent = Intent()
                intent.putExtra("note",note)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }else{
                Toast.makeText(this@AddNote,"Please enter Details", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

        binding.backArrow.setOnClickListener{

            onBackPressed()
        }

    }
}