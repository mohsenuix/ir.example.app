package ir.example.app.ui.activity.home

import ir.example.app.ui.base.BaseViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor() : BaseViewModel<HomeViewState>() {
    var overCurrent : Boolean = false
    var overTemperature : Boolean = false
    var pedal : Boolean = false
    var throttle : Boolean = false
    var underVoltage : Boolean = false
    var motorSensor : Boolean = false
    var drive : Boolean = false
    var gps : Boolean = false
    var cruise : Boolean = false
    var totalOdo :Float? = null
    var totalTrip : Float? =null
    var currentSpeed : Int?= null
    var maxSpeed : Float?=null
    var chargeBattery : Int?=null
    var maxChargeBattery : Int? =null
    var pedalMode : Int? =null
    var light : Boolean? = null
}