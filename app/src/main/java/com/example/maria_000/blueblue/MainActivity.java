package com.example.maria_000.blueblue;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private BluetoothAdapter btt;
    TextView tt;
    ArrayAdapter<String> mNewDevicesArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // requestWindowFeature()
       // tt.setOnClickListener(this);
btt=BluetoothAdapter.getDefaultAdapter();
 tt= (TextView) findViewById(R.id.t2);
tt.append("Paired Device list\n");
paireddeviceslist();
//newdevices();


        // Register for broadcasts when a device is discovered
       // IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
       // this.registerReceiver(mReceiver, filter);

        // Register for broadcasts when discovery has finished
       // filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
      //  this.registerReceiver(mReceiver, filter);

    }
    public boolean onCreateOptionsMenu(Menu m){
        MenuInflater k= getMenuInflater();
        k.inflate(R.menu.btmenu, m);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem y){
        switch (y.getItemId()){
            case R.id.on:
                btt=BluetoothAdapter.getDefaultAdapter();
                if(btt==null){
                    Toast.makeText(this,"Bluetooth not supported",Toast.LENGTH_SHORT).show();
                }
                else if(btt.isEnabled()){
                    Toast.makeText(this,"Bluetooth is turned on",Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean btoff=!btt.isEnabled();
                    if(btoff){
                        Intent enable= new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(enable, 2);
                    }
                    else{
                        btt.disable();
                        Toast.makeText(this,"Bluetooth is disabled",Toast.LENGTH_SHORT).show();
                    }
                }

            default:
                return super.onOptionsItemSelected(y);
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==2){
            if(resultCode==RESULT_OK){
                Toast.makeText(this,"Bluetooth Successfully enabled",Toast.LENGTH_SHORT).show();
            }
            else if(resultCode==RESULT_CANCELED){
                Toast.makeText(this,"Bluetooth access denied",Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void paireddeviceslist(){
        BluetoothAdapter bb= BluetoothAdapter.getDefaultAdapter();

        ArrayAdapter<String> pairedDevicesArrayAdapter =
               new ArrayAdapter<String>(this, R.layout.pairedadapter);
        //getting the list
        if(btt.isEnabled()){
        Set<BluetoothDevice>paired= bb.getBondedDevices();
        if(paired.size()>0){
            //ListView marie=(findViewById(R.id.list));
           // marie.setAdapter(pairedDevicesArrayAdapter);
            for(BluetoothDevice d:paired){
                //get device name
               String name=d.getName();
               String address=d.getAddress();
                //pairedDevicesArrayAdapter.add(name +"\n");
                TextView t= findViewById(R.id.t2);
                t.append(name);
            }
        }

    }}



    /**
     * The BroadcastReceiver that listens for discovered devices and changes the title when
     * discovery is finished
     */
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // If it's already paired, skip it, because it's been listed already
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    mNewDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                }
                // When discovery is finished, change the Activity title
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                setProgressBarIndeterminateVisibility(false);
                setTitle("select Device");
                if (mNewDevicesArrayAdapter.getCount() == 0) {
                    String noDevices = getResources().getText(R.string.select).toString();
                    mNewDevicesArrayAdapter.add(noDevices);
                }
            }
        }
    };
   /* public void newdevices(){
        Intent filter = new Intent(BluetoothDevice.ACTION_FOUND);
        String action = filter.getAction();
        //Intent i;
        BluetoothDevice device= filter.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        //BluetoothDevice device = filter.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        // If it's already paired, skip it, because it's been listed already
        if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
            mNewDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            tt.append(device.getName());
        }
        else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
            setProgressBarIndeterminateVisibility(false);
            setTitle("select Device");
            if (mNewDevicesArrayAdapter.getCount() == 0) {
                String noDevices = getResources().getText(R.string.select).toString();
                mNewDevicesArrayAdapter.add(noDevices);
            }
        }
    }*/

    @Override
    public void onClick(View v) {
    }

    public void kik(View view) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

            // Setting Dialog Title
            alertDialog.setTitle("Send Message");

            // Setting Dialog Message
            alertDialog.setMessage("Do you want to text?");

            // Setting Icon to Dialog
            // alertDialog.setIcon(R.drawable.save);

            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // User pressed YES button. Write Logic Here
                    Intent i= new Intent(getApplicationContext(),text.class);
                    startActivity(i);
                }
            });

            // Setting Negative "NO" Button
            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // User pressed No button. Write Logic Here
                    Intent i= new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }
            });

            // Setting Netural "Cancel" Button
            alertDialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // User pressed Cancel button. Write Logic Here
                    Toast.makeText(getApplicationContext(), "You clicked on Cancel",
                            Toast.LENGTH_SHORT).show();
                }
            });

            // Showing Alert Message
            alertDialog.show();



        }

    }

