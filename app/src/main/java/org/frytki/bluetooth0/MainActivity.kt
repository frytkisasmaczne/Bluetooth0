package org.frytki.bluetooth0

import android.app.Activity
import android.app.ProgressDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import java.io.IOException
import java.util.*

class MainActivity : Activity() {

    //private val wyslij0: Button = findViewById(R.id.wyslij0)
    //private val wyslij1: Button = findViewById(R.id.wyslij1)
    //private val wyslij2: Button = findViewById(R.id.wyslij2)
    //private val wyslij3: Button = findViewById(R.id.wyslij3)
    //private val wyslij4: Button = findViewById(R.id.wyslij4)
    //private val wyslij5: Button = findViewById(R.id.wyslij5)
    //private val wyslij6: Button = findViewById(R.id.wyslij6)
    //private val rozlacz: Button = findViewById(R.id.rozlacz)
    //private lateinit var progress: ProgressDialog
    //private val progressBar: ProgressBar = findViewById(R.id.progressBar)
    internal var myBluetooth: BluetoothAdapter? = null
    internal var btSocket: BluetoothSocket? = null
    private var isBtConnected = false
    internal val myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    val address = intent.getStringExtra(Lista::EXTRA_ADDRESS.toString())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        //progressBar.visibility = View.INVISIBLE
        setContentView(R.layout.activity_main)
        ConnectBT().execute()

    }

    fun owyslij0(v:View){
        val wiadomosc: String = "0"
        if (btSocket != null) {
            try {
                btSocket!!.getOutputStream().write(wiadomosc.toByteArray())
                Log.i("Wysłano", wiadomosc)
            } catch (e: IOException) {
                Log.e("Error", wiadomosc)
            }

        }
        else Log.i("Kupa", """btSocket w ${wiadomosc}jest 'null'""")
    }
    fun owyslij1(v:View){
        val wiadomosc: String = "1"
        if (btSocket != null) {
            try {
                btSocket!!.getOutputStream().write(wiadomosc.toByteArray())
                Log.i("Wysłano", wiadomosc)
            } catch (e: IOException) {
                Log.e("Error", wiadomosc)
            }

        }
        else Log.i("Kupa", """btSocket w ${wiadomosc}jest 'null'""")
    }
    fun owyslij2(v:View){
        val wiadomosc: String = "2"
        if (btSocket != null) {
            try {
                btSocket!!.getOutputStream().write(wiadomosc.toByteArray())
                Log.i("Wysłano", wiadomosc)
            } catch (e: IOException) {
                Log.e("Error", wiadomosc)
            }

        }
        else Log.i("Kupa", """btSocket w ${wiadomosc}jest 'null'""")
    }
    fun owyslij3(v:View){
        val wiadomosc: String = "3"
        if (btSocket != null) {
            try {
                btSocket!!.getOutputStream().write(wiadomosc.toByteArray())
                Log.i("Wysłano", wiadomosc)
            } catch (e: IOException) {
                Log.e("Error", wiadomosc)
            }

        }
        else Log.i("Kupa", """btSocket w ${wiadomosc}jest 'null'""")
    }
    fun owyslij4(v:View){
        val wiadomosc: String = "4"
        if (btSocket != null) {
            try {
                btSocket!!.getOutputStream().write(wiadomosc.toByteArray())
                Log.i("Wysłano", wiadomosc)
            } catch (e: IOException) {
                Log.e("Error", wiadomosc)
            }

        }
        else Log.i("Kupa", """btSocket w ${wiadomosc}jest 'null'""")
    }
    fun owyslij5(v:View){
        val wiadomosc: String = "5"
        if (btSocket != null) {
            try {
                btSocket!!.getOutputStream().write(wiadomosc.toByteArray())
                Log.i("Wysłano", wiadomosc)
            } catch (e: IOException) {
                Log.e("Error", wiadomosc)
            }

        }
        else Log.i("Kupa", """btSocket w ${wiadomosc}jest 'null'""")
    }
    fun owyslij6(v:View){
        val wiadomosc: String = "6"
        if (btSocket != null) {
            try {
                btSocket!!.getOutputStream().write(wiadomosc.toByteArray())
                Log.i("Wysłano", wiadomosc)
            } catch (e: IOException) {
                Log.e("Error", wiadomosc)
            }

        }
        else Log.i("Kupa", """btSocket w ${wiadomosc} jest 'null'""")
    }
    fun rozlacz(v:View){
        if (btSocket != null)
        //If the btSocket is busy
        {
            try {
                btSocket!!.close() //close connection
                Log.i("rozlacz", "udalo sie rozlaczyc bluetooth")
            } catch (e: IOException) {
                Log.e("Error", "nie udało sie zamknac btSocket")
            }

        }
        else Log.i("rozlacz", "btSocket nie był wgl połączony")
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_sterowanie, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        return if(id == R.id.action_settings){
            true
        }
        else super.onOptionsItemSelected(item)
    }

    private inner class ConnectBT : AsyncTask<Void, Void, Void>  // UI thread
    () {
        private var ConnectSuccess = true //if it's here, it's almost connected

        override fun onPreExecute() {
           // progress = ProgressDialog.show(this@MainActivity, "Connecting...", "Please wait!!!")  //show a progress dialog
        }

        override fun doInBackground(vararg devices: Void) //while the progress dialog is shown, the connection is done in background
                : Void? {
            try {
                if (btSocket == null || !isBtConnected) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter()//get the mobile bluetooth device
                    var dispositivo = myBluetooth!!.getRemoteDevice(address)//connects to the device's address and checks if it's available
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID)//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
                    btSocket!!.connect()//start connection
                }
            } catch (e: IOException) {
                ConnectSuccess = false//if the try failed, you can check the exception here
            }

            return null
        }

        override fun onPostExecute(result: Void) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result)

            if (!ConnectSuccess) {
                Toast.makeText(applicationContext, "coś nie pykło,\nmoże już jest połączone?", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(applicationContext, "Połączono", Toast.LENGTH_SHORT).show()
                isBtConnected = true
            }
            //progress.dismiss()
        }
    }

}
