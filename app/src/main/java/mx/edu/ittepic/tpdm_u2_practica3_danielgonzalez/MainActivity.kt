package mx.edu.ittepic.tpdm_u2_practica3_danielgonzalez

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {


    var boton: ImageButton? = null
    var boton2: ImageButton? = null
    var numero1: EditText? = null
    var numero2: EditText? = null
    var primos: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numero1 = findViewById(R.id.edt_1)
        numero2 = findViewById(R.id.edt_2)
        boton = findViewById(R.id.boton_Generar)
        boton2 = findViewById(R.id.boton_Mostrar)
        primos = findViewById(R.id.txtPrimos)

        val alerta = AlertDialog.Builder(this)

        alerta.setTitle("ATENCION").setMessage("Inserta N , Posteriomente M Debe Ser Mayor A N")
            .setPositiveButton("ACEPTAR") { dialogInterface, i -> }
            .show()

        boton2?.isEnabled = false

        boton?.setOnClickListener {
            if (numero1?.text.toString().isNotEmpty() && numero2?.text.toString().isNotEmpty()) {
                var n = numero1?.text.toString().toInt()
                var m = numero2?.text.toString().toInt()
                if (n < m) {
                    primos?.setText("")
                    ClassAsincrona(
                        edt_1?.text.toString(),
                        edt_2?.text.toString(),
                        applicationContext
                    ).execute()
                    val alerta = AlertDialog.Builder(this)
                    alerta.setTitle("ATENCION").setMessage("GUARDADO!!!")
                        .setPositiveButton("ACEPTAR") { dialogInterface, i -> }
                        .show()
                    boton2?.isEnabled = true
                } else {
                    val alerta = AlertDialog.Builder(this)

                    alerta.setTitle("ATENCION")
                        .setMessage("Inserta N , Posteriomente M Debe Ser Mayor A N")
                        .setPositiveButton("ACEPTAR") { dialogInterface, i -> }
                        .show()
                }
            } else {
                val alerta = AlertDialog.Builder(this)

                alerta.setTitle("ATENCION").setMessage("Debe Llenar Ambos Campos")
                    .setPositiveButton("ACEPTAR") { dialogInterface, i -> }
                    .show()

            }
        }
        boton2?.setOnClickListener {
            val abrirArchivo = BufferedReader(InputStreamReader(openFileInput("primos.txt")))
            var cadena = ""
            cadena = abrirArchivo.readLine()
            primos?.setText(cadena)
        }
    }

}