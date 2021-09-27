package pe.paku.scopesKotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Uso de Flows
        //Considerar el tipo de respuesta del Flow
        //Considerado como un "Producer" de respuestas
        val flow = flow<String>{
            for(i in 0..10){
                emit("Demo Flow")
                delay(1000L)
            }
        }
        //Considerado como el que escucha los cambios - Receptor
        //Uso del flow en el mismo coroutine, donde esta el producer y el Suscriber
        /*
        GlobalScope.launch {
            flow.collect {
                println(it)
                delay(3000L)
            }
        }
        */

        GlobalScope.launch {
            flow.buffer().collect {
                println(it)
                delay(2000L)
            }
        }

        //Recordar que esto puede hacer como receptor de un websocket para toda la informaci{on,
        //teniendo una carga grande de respuesta y que Flow está diseñado para eso.

    }
}