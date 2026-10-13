package com.nutritics.labelprinter

import android.app.Activity
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbManager
import android.os.Bundle

// Scanner-validation fixture mirroring the printdrivermob scan. See EXPECTED_FINDINGS.md.
class SettingsActivity : Activity() {

    private var printerAttached = false

    private val usbReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            printerAttached = intent.action == UsbManager.ACTION_USB_DEVICE_ATTACHED
        }
    }

    private val bluetoothReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            printerAttached = intent.action == BluetoothDevice.ACTION_ACL_CONNECTED
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val usbFilter = IntentFilter(UsbManager.ACTION_USB_DEVICE_ATTACHED)
        usbFilter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED)
        // Both receivers are registered with no broadcast permission and no export flag.
        registerReceiver(usbReceiver, usbFilter)

        val bluetoothFilter = IntentFilter(BluetoothDevice.ACTION_ACL_CONNECTED)
        bluetoothFilter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED)
        registerReceiver(bluetoothReceiver, bluetoothFilter)
    }

    override fun onDestroy() {
        unregisterReceiver(usbReceiver)
        unregisterReceiver(bluetoothReceiver)
        super.onDestroy()
    }
}
