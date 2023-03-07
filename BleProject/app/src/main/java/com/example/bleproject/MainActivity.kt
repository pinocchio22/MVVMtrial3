package com.example.bleproject

import android.Manifest
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.le.ScanResult
import android.content.Intent
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.example.bleproject.databinding.ActivityMainBinding
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission


class MainActivity : AppCompatActivity(), BluetoothGuide.OnCheckModelListener, BluetoothGuide.OnNotifyValueListener<BleDevice> {
    lateinit var binding : ActivityMainBinding
    private val bluetoothGuide: BluetoothGuide = BluetoothGuide()
    var BleDevice : BleDevice ?= null
    var permissionListener: PermissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
            // Start Scan Device
            bluetoothGuide.scanDevices()
        }

        override fun onPermissionDenied(deniedPermissions: List<String?>?) {}
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (bluetoothGuide.onActivityResult(requestCode, resultCode)) {
            TedPermission.create()
                .setPermissionListener(permissionListener)
                .setDeniedMessage("Denied Permission.")
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .check()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Add Listeners
        bluetoothGuide
            .setOnCheckModelListener(this)

        // Bluetooth System On with permission
        if (bluetoothGuide.isOn()) {
            TedPermission.create()
                .setPermissionListener(permissionListener)
                .setDeniedMessage("Denied Permission.")
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .check()
        } else {
            bluetoothGuide.on(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Clear the resources
        bluetoothGuide.disconnectGATTAll()
        bluetoothGuide.onComplete()
    }

    override fun isChecked(bytes: ByteArray?): Boolean {
        TODO("Not yet implemented")
    }

    override fun scannedDevice(result: ScanResult?) {
        // Start Connecting device.
        bluetoothGuide.connGATT(applicationContext, result!!.device)
    }



    override fun onValue(deivce: BluetoothDevice?, value: Any?) {
        // Show the data that is notified value
        binding.deviceId.text = java.lang.String.valueOf(BleDevice!!.device)
    }

    override fun formatter(characteristic: BluetoothGattCharacteristic?): BleDevice {

        return BleDevice(characteristic.toString(), characteristic!!.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 0))
    }
}