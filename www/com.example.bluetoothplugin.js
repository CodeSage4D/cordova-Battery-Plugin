var exec = require('cordova/exec');  // for cordova.exec function use in android for calling native code.
var channel = require("cordova/channel ");  // for cordova channel use in android for calling native code.

// This is the object that we'll return to the user.
var BluetoothPlugin = {

    // This is called when the user wants to enable bluetooth.
    enableBluetooth: function(successCallback, errorCallback) {
        exec(successCallback, errorCallback, 'BluetoothPlugin', 'enableBluetooth', []);
    },

    // This is called when the user wants to list all the bluetooth devices.
    listDevices: function(successCallback, errorCallback) {
        exec(successCallback, errorCallback, 'BluetoothPlugin', 'listDevices', []);
    },

    // This is called when the user wants to list all the bluetooth devices.
    listDevices: function(successCallback, errorCallback) {
        exec(successCallback, errorCallback, 'BluetoothPlugin', 'listDevices', []);
    },
};

// Register the plugin
channel.registerPlugin('BluetoothPlugin', BluetoothPlugin);
module.exports = BluetoothPlugin;   // this is the object that we'll return to the user.

