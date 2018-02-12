package org.frytki.bluetooth0

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class Loading : Activity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        val timerThread = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(700)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    val intent = Intent(this@Loading, Lista::class.java)
                    startActivity(intent)
                }
            }
        }
        timerThread.start()

    }

    override fun onPause(){
        super.onPause()
        finish()
    }

}
