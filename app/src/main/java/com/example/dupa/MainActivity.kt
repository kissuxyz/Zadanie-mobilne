package com.example.dupa

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputA = findViewById<EditText>(R.id.inputA)
        val inputB = findViewById<EditText>(R.id.inputB)
        val button = findViewById<Button>(R.id.button)
        val showEvent = findViewById<TextView>(R.id.showPassHandle)

        val passwordMatching = findViewById<CheckBox>(R.id.passwordMatching)
        val capitalLetter = findViewById<CheckBox>(R.id.capitalLetter)
        val smallLetter = findViewById<CheckBox>(R.id.smallLetter)
        val numberIncluded = findViewById<CheckBox>(R.id.numberIncluded)
        val specialIncluded = findViewById<CheckBox>(R.id.specialIncluded)

        button.setOnClickListener {
            val a = inputA.text.toString()
            val b = inputB.text.toString()

            if (a.isEmpty() || b.isEmpty()) {
                inputA.error = "Pole nie może być puste"
                inputB.error = "Pole nie może być puste"
                return@setOnClickListener
            }

            if (a != b) {
                inputA.error = "Hasła nie są takie same"
                inputB.error = "Hasła nie są takie same"
                return@setOnClickListener
            }

            passwordMatching.isChecked = a == b
            capitalLetter.isChecked = a.any { it.isUpperCase() }
            smallLetter.isChecked = a.any { it.isLowerCase() }
            numberIncluded.isChecked = a.any { it.isDigit() }
            specialIncluded.isChecked = a.any { !it.isLetterOrDigit() }

            if (passwordMatching.isChecked && capitalLetter.isChecked && smallLetter.isChecked && numberIncluded.isChecked && specialIncluded.isChecked) {
                val builder = android.app.AlertDialog.Builder(this)
                builder.setTitle("Spełniono wszystkie wymagania")
                builder.setMessage("Hasło spełnia wszystkie wymagania. Możesz bezpiecznie używać tego hasła")
                builder.setPositiveButton("OK", null)
                builder.show()
            } else {
                val builder = android.app.AlertDialog.Builder(this)
                builder.setTitle("Nie spełniono wszystkich wymagań")
                builder.setMessage("Hasło nie spełnia wszystkich wymagań. Zalecamy zmianę hasła według zaleceń. ")
                builder.setPositiveButton("OK", null)
                builder.show()
            }
        }

        showEvent.setOnClickListener {
            if (showEvent.text == "Pokaż hasło") {
                inputA.transformationMethod = null
                inputB.transformationMethod = null
                showEvent.text = "Ukryj hasło"
            } else {
                inputA.transformationMethod = android.text.method.PasswordTransformationMethod()
                inputB.transformationMethod = android.text.method.PasswordTransformationMethod()
                showEvent.text = "Pokaż hasło"
            }
        }
    }
}