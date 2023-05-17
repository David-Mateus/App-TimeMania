package com.example.timemania

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlin.random.Random

class SecondActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    val times = arrayOf(
        "Santa Cruz",
        "Nautico",
        "Sport",
        "Bahia",
        "Treze",
        "CSA",
        "Fortaleza"
    )
    val imageTimes = arrayOf(
        R.drawable.santacruz,
        R.drawable.nautico,
        R.drawable.sport,
        R.drawable.bahia,
        R.drawable.treze,
        R.drawable.csa,
        R.drawable.fortaleza
    )
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second);

        val ediText : EditText = findViewById(R.id.editText_input)
        val resultNumber : TextView = findViewById(R.id.resultNumber)
        val resultName : TextView = findViewById(R.id.resultName)
        val btnGenerate : Button = findViewById(R.id.btn_generate)
        val imagesTime : ImageView = findViewById(R.id.image_times)

        // Save in memory
        prefs = getSharedPreferences("database", MODE_PRIVATE)
        val result = prefs.getString("result",null)
        val resultNameTime = prefs.getString("ResultName", null)

        if(result != null && resultNameTime != null){
            resultNumber.text = "Ultima aposta: $result"
            resultName.text = "Ultimo time do coração: $resultNameTime "

        }
        btnGenerate.setOnClickListener {
            val text = ediText.text.toString();
            numberGenerator(text, resultNumber, resultName, imagesTime)
        }
    }
    private fun numberGenerator(text:String, resultNumber:TextView, resultName:TextView, resultImage: ImageView ){
        if(text.isEmpty()){
            Toast.makeText(this, "Informe um numero entre 10 e 15", Toast.LENGTH_LONG).show();
            return
        }
        val qtd = text.toInt()
        if(qtd < 10 || qtd > 15){
            Toast.makeText(this, "Informe um numero entre 10 e 15", Toast.LENGTH_LONG).show();
            return
        }
        val numbers = mutableSetOf<Int>()
        val random = Random
        while (true){
            val number = random.nextInt(80)
            numbers.add(number+1)

            if(numbers.size == qtd){
                break
            }
        }
        val randomTime = Random
        val numberTime = randomTime.nextInt(5)
        resultNumber.text = numbers.joinToString("-")
        resultName.text = times[numberTime]
        resultImage.setImageResource(imageTimes[numberTime])


        val editor = prefs.edit()
        editor.putString("result", resultNumber.text.toString())
        editor.putString("ResultName", resultName.text.toString())

        editor.apply()
    }
}