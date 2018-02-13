package org.frytki.bluetooth0

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.view.WindowManager
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_sterowanie.*
import java.io.IOException
import java.util.*

class Sterowanie : Activity() {

    lateinit var address: String
    internal var myBluetooth: BluetoothAdapter? = null
    internal var btSocket: BluetoothSocket? = null
    internal var isBtConnected = false
    internal val myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sterowanie)

        textView3.text = "lądowanie, poczekaj momencik"
        address = intent.getStringExtra(Intent.EXTRA_TEXT)
        ConnectBT().execute()

    }

    fun owyslij0(v: View){
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
    fun owyslij1(v: View){
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
    fun owyslij2(v: View){
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
    fun owyslij3(v: View){
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
    fun owyslij4(v: View){
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
    fun owyslij5(v: View){
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
    fun owyslij6(v: View){
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
    fun rozlacz(v: View){
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
            progressBar3.visibility = ProgressBar.VISIBLE
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            textView3.visibility = TextView.VISIBLE
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

        override fun onPostExecute(result: Void?) //after the doInBackground, it checks if everything went fine
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
            progressBar3.visibility = ProgressBar.INVISIBLE
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            textView3.visibility = TextView.INVISIBLE
        }
    }

}
