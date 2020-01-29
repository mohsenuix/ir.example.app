package ir.example.app.ui.activity.home

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import ir.example.app.R
import ir.example.app.ui.activity.bluetooth.BluetoothActivity
import ir.example.app.ui.activity.map.MapActivity
import ir.example.app.ui.activity.setting.SettingActivity
import ir.example.app.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*
import timber.log.Timber


class HomeActivity : BaseActivity<HomeViewState, HomeViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        createViewModel(HomeViewModel::class.java)
        LocalBroadcastManager.getInstance(this).registerReceiver(
            mMessageReceiver, IntentFilter("BluetoothUpdates")
        )



        speedometer.speedTo(50f, 1000)
        speedometer.withTremble = false
//        speedometer.
//        speedometer.setSpeedometerColor(Color.RED);
//        speedometer.setTrianglesColor(Color.GREEN);
//        speedometer.setIndicatorWidth(15);

        btnGps.setOnClickListener {
            startActivity(Intent(this,MapActivity::class.java))
        }
        btnSetting.setOnClickListener {
            startActivity(Intent(this,SettingActivity::class.java))
        }

        btnPower.setOnClickListener {
            startActivity(Intent(this,BluetoothActivity::class.java))
        }
        btnVolumeDown.setOnClickListener {
            val speed = Integer.parseInt(txtSpeed.text.toString())
            if (speed<4)
                txtSpeed.text = (speed+1).toString()
        }
        btnVolumeUp.setOnClickListener {
            val speed = Integer.parseInt(txtSpeed.text.toString())
            if (speed>0)
                txtSpeed.text = (speed-1).toString()
        }

        btnPower.setOnClickListener{
            startActivity(Intent(this,BluetoothActivity::class.java))
        }

    }

    override fun handleState(state: HomeViewState) {

    }


    private val mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(
            context: Context?,
            intent: Intent
        ) { // Get extra data included in the Intent
            val message = intent.getStringExtra("message")
            Timber.i("messageReceiver ${message.toByteArray().toList()}")

            val devices = intent.getParcelableArrayListExtra<BluetoothDevice>("devices")

        }
    }

}