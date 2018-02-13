package org.frytki.bluetooth0

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import kotlin.collections.ArrayList

class Lista : Activity() {

    private lateinit var btnPaired: Button
    private lateinit var devicelist: ListView
    lateinit var myBluetooth: BluetoothAdapter
    lateinit var pairedDevices: Set<BluetoothDevice>
    internal val EXTRA_ADDRESS = "device_address"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        btnPaired = findViewById<Button>(R.id.button)
        devicelist = findViewById<ListView>(R.id.lista)
        myBluetooth = BluetoothAdapter.getDefaultAdapter()

        if(myBluetooth == null){
            Toast.makeText(applicationContext, "twój telefon nie ma bluetooth biedaku", Toast.LENGTH_LONG).show()
            finish()
        }
        else if(!myBluetooth.isEnabled){
            var turnBTon = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(turnBTon, 1)
        }

    }

    fun onClickButton(v: View){
        pairedDevicesList()
    }

    private fun pairedDevicesList(){
        pairedDevices = myBluetooth.bondedDevices
         var list = ArrayList<String>()

        if(pairedDevices.size > 0){
            for (bt in pairedDevices) {
                list.add(bt.name + "\n" + bt.address)
            }
        }
        else {
            Toast.makeText(applicationContext, "Nie znaleziono sparowanych urzondzeń. Idź do ustawień i sparuj swoje urzondzenie", Toast.LENGTH_LONG).show()
        }
        var arrayAdapter: ArrayAdapter<*> = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        devicelist.adapter = arrayAdapter
        devicelist.onItemClickListener = myListClickListener
    }

    private var myListClickListener: AdapterView.OnItemClickListener = AdapterView.OnItemClickListener{ av: AdapterView<*>, v: View, arg2: Int, arg3: Long ->
        var info = (v as TextView).text.toString()
        var address = info.substring(info.length - 17)
        var i: Intent = Intent(this, Sterowanie::class.java).apply {
            putExtra(Intent.EXTRA_TEXT, address)
        }
        startActivity(i)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_lista, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        var id = item.itemId

        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

}
