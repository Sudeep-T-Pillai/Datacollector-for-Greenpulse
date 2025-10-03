package com.greenpulse.datacollector.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        AccelerometerEntity::class,
        LinearAccelerationEntity::class,
        RotationVectorEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accelerometerDao(): AccelerometerEntityDao
    abstract fun linearAccelerationDao(): LinearAccelerationEntityDao
    abstract fun rotationVectorDao(): RotationVectorEntityDao
}
