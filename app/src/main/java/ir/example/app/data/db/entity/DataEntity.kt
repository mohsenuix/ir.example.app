package ir.example.app.data.db.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class DataEntity {
    @NonNull
    @PrimaryKey
    var id: String? = null
}