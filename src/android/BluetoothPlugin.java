package com.example.bluetoothplugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;

// import android.support.v4.app.ActivityCompat;
public class BluetoothPlugin extends CordovaPlugin {  

    private static final int REQUEST_ENABLE_BLUETOOTH = 1;  // Declare request code
    private CallbackContext callbackContext; // Declare callbackContext at class level

    @Override   // Override execute method for cordova plugin
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext; // Assign callbackContext parameter to class-level variable
        if (action.equals("enableBluetooth")) {
            enableBluetooth();
            return true;
        } else if (action.equals("listDevices")) {
            listDevices();
            return true;
        }
        return false; // return false if action is not found
    }

    private void enableBluetooth() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            callbackContext.error("Bluetooth not supported");
            return;
        }
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            cordova.startActivityForResult(this, enableBluetoothIntent, REQUEST_ENABLE_BLUETOOTH);
        } else {
            callbackContext.success("Bluetooth already enabled");
        }
    }

    private void listDevices() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            callbackContext.error("Bluetooth not supported");
            return;
        }
        JSONArray devicesArray = new JSONArray();
        for (android.bluetooth.BluetoothDevice device : bluetoothAdapter.getBondedDevices()) {
            devicesArray.put(device.getName());
        }
        callbackContext.success(devicesArray);
    }

    @Override   // Here we use override onActivityResult
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE_BLUETOOTH) {
            if (resultCode == android.app.Activity.RESULT_OK) {
                callbackContext.success("Bluetooth enabled");
            } else {
                callbackContext.error("Bluetooth enable request cancelled");
            }
        }
    }
}
