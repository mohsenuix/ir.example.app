package ir.example.app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.example.app.data.db.dao.DataDao
import ir.example.app.data.db.entity.DataEntity


@Database(entities = [DataEntity::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun daoAccess(): DataDao?
}