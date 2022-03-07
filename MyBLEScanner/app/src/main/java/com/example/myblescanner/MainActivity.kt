package com.example.myblescanner

import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.annotation.RequiresApi
import android.content.pm.PackageManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView


class MainActivity : AppCompatActivity() {
    val devices = ArrayList<String>()
    private lateinit var arrayAdapter : ArrayAdapter<String>

    lateinit var start: Button
    private val MY_PERMISSION_lOCATION_REQUEST_NUMBER = 1

    private val bluetoothLeScanner: BluetoothLeScanner
        get() {
            val bluetoothManager =
                applicationContext.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
            val bluetoothAdapter = bluetoothManager.adapter
            return bluetoothAdapter.bluetoothLeScanner
        }

    private val bleScanner = object : ScanCallback() {
        @RequiresApi(Build.VERSION_CODES.R)
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            super.onScanResult(callbackType, result)
            if (result != null) {
                val name = result.device.name
                if (name != null && !devices.contains(name)) {
                    devices.add(name)
                    arrayAdapter.notifyDataSetChanged()
                }
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start = findViewById(R.id.start)

        start.setOnClickListener {
            bluetoothLeScanner.startScan(bleScanner)
        }


        val listDevices = findViewById<ListView>(R.id.simpleListView)


        arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, devices
        )
        listDevices.adapter = arrayAdapter
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSION_lOCATION_REQUEST_NUMBER -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permissions granted.
                    Toast.makeText(this@MainActivity, "Permissions granted", LENGTH_SHORT).show()
                } else {
                    // no permissions granted.
                    Toast.makeText(this@MainActivity, "No permissions!", LENGTH_SHORT).show()
                }
                return
            }
        }
    }

}