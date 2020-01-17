package ir.example.app.ui.activity.home

import android.os.Bundle
import ir.example.app.R
import ir.example.app.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity<HomeViewState, HomeViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        createViewModel(HomeViewModel::class.java)
        speedometer.speedTo(50f, 4000)
        speedometer.withTremble = false
//        speedometer.
//        speedometer.setSpeedometerColor(Color.RED);
//        speedometer.setTrianglesColor(Color.GREEN);
//        speedometer.setIndicatorWidth(15);

    }

    override fun handleState(state: HomeViewState) {

    }

}