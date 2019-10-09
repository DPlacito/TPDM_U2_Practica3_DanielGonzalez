package mx.edu.ittepic.tpdm_u2_practica3_danielgonzalez

import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import java.io.OutputStreamWriter
import kotlin.random.Random

class ClassAsincrona (n: String, m: String, context: Context): AsyncTask<Void, Void, List<Int>>(){

    var n = n.toInt()
    var m = m.toInt()
    var c = context

    override fun doInBackground(vararg p0: Void?): List<Int>? {
        val generador = List(2000){ Random.nextInt(n,m)}
        println(generador)

        return generador
    }
    override fun onPostExecute(result: List<Int>?) {
        super.onPostExecute(result)
        var cont = 0
        var et=""
        var numerosPrimos="Primos:  "
        (0..1999).forEach {
            cont=0
            et=result?.get(it).toString()
            (1..et.toInt()).forEach {
                if (et.toInt() % it == 0) {
                    cont++
                }
            }
            if (cont <= 2 && et.toInt()>1) {
                numerosPrimos=numerosPrimos + et+ ", "
            } else {

            }
        }
        println(numerosPrimos)
        guardarPrimos(numerosPrimos)

    }

    fun guardarPrimos(numerosPrimos:String){
        var guardarArchivo = OutputStreamWriter(c.openFileOutput("primos.txt", Activity.MODE_PRIVATE))
        guardarArchivo.write(numerosPrimos)
        guardarArchivo.flush()
        guardarArchivo.close()
        Toast.makeText(c,"Guardado!!!", Toast.LENGTH_LONG).show()
    }
}