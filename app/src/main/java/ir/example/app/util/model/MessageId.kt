package ir.example.app.util.model

enum class MessageId(id: Byte) {
    BatteryConfiguration(1),
    BatteryOutput(2),
    CoreSituation(3),
    CoreConfiguration(4),
    ThrottleConfiguration(5),
    ThrottleSetting(6),
    PedalConfiguration(7),
    PedalSetting(8),
    MotorSituation(9),
    MotorSetting(10),
    LightSetting(11)
}