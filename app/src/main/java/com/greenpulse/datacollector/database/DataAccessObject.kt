package com.greenpulse.datacollector.database
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AccelerometerEntityDao {
    @Insert
    suspend fun insert(accelerometerEntity: AccelerometerEntity)

    @Query("SELECT * FROM accelerometer ORDER BY time_stamp DESC")
    suspend fun getAll(): List<AccelerometerEntity>
}

@Dao
interface LinearAccelerationEntityDao {
    @Insert
    suspend fun insert(linearAccelerationEntity: LinearAccelerationEntity)

    @Query("SELECT * FROM linear_acceleration ORDER BY time_stamp DESC")
    suspend fun getAll(): List<LinearAccelerationEntity>
}

@Dao
interface RotationVectorEntityDao {
    @Insert
    suspend fun insert(rotationVectorEntity: RotationVectorEntity)

    @Query("SELECT * FROM rotation_vector ORDER BY time_stamp DESC")
    suspend fun getAll(): List<RotationVectorEntity>
}