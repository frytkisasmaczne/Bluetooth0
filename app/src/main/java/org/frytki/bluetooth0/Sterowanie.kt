package org.frytki.bluetooth0

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import java.util.*

class Sterowanie : Activity() {

    /*var disconnect: Button = findViewById(R.id.disconnect)
    var send0: Button = findViewById(R.id.send0)
    var send1: Button = findViewById(R.id.send0)
    var send2: Button = findViewById(R.id.send0)
    var send3: Button = findViewById(R.id.send0)
    var send4: Button = findViewById(R.id.send0)
    var send5: Button = findViewById(R.id.send0)
    var send6: Button = findViewById(R.id.send0)*/
    /*internal lateinit var myBluetooth: BluetoothAdapter
    internal lateinit var btSocket: BluetoothSocket
    private var isBtConnected = false
    internal val myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sterowanie)


        val address = intent.getStringExtra(Lista::EXTRA_ADDRESS.toString())




    }




}
