package ir.example.app.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import ir.example.app.R
import ir.example.app.util.BluetoothHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {
    lateinit var recyclerAdapter : BluetoothAdapter
    var helper:BluetoothHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        val helper = BluetoothHelper(this)
        setContentView(R.layout.activity_main)
//        helper.runServer()

//        //enable bluetooth
//        val turnOn = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
//        startActivityForResult(turnOn, 0)
        recyclerAdapter =  BluetoothAdapter(helper.getPairedDevices().toList()) {
                bluetoothDevice -> helper.connect(bluetoothDevice)

        }
        pairedRecycler.layoutManager = LinearLayoutManager(this)
//        recycler.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation).also {
//            //todo deprecated
//            it.setDrawable(resources.getDrawable(R.drawable.white_divider))
//        })
        pairedRecycler.adapter = recyclerAdapter

        btnSend.setOnClickListener{
        }
        btnRunServer.setOnClickListener{
        }
//        helper.runServer()
//        helper.getPairedDevices()
//
//        btnSend.setOnClickListener{
//            var devices = helper.getPairedDevices()
//
//        }

    }

    override fun onDestroy() {
        helper?.disconnect()
        super.onDestroy()
    }

    fun showMessage(message: String){
        runOnUiThread {
            Snackbar.make(constraintLayout, message, Snackbar.LENGTH_SHORT)
//                    .setAction("CLOSE", object : OnClickListener() {
//                        fun onClick(view: View?) {}
//                    })
//                    .setActionTextColor(getResources().getColor(R.color.holo_red_light))
                .show()
        }
    }

}
