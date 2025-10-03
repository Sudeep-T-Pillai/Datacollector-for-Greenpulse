package com.greenpulse.datacollector.database
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accelerometer")
data class AccelerometerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "transportation_mode") val transportationMode: String?,
    @ColumnInfo(name = "time_stamp") val timeStamp: Long,
    @ColumnInfo(name = "acc_x") val accX: Double?,
    @ColumnInfo(name = "acc_y") val accY: Double?,
    @ColumnInfo(name = "acc_z") val accZ: Double?
)

@Entity(tableName = "linear_acceleration")
data class LinearAccelerationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "transportation_mode") val transportationMode: String?,
    @ColumnInfo(name = "time_stamp") val timeStamp: Long,
    @ColumnInfo(name = "las_x") val lasX: Double?,
    @ColumnInfo(name = "las_y") val lasY: Double?,
    @ColumnInfo(name = "las_z") val lasZ: Double?
)

@Entity(tableName = "rotation_vector")
data class RotationVectorEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "transportation_mode") val transportationMode: String?,
    @ColumnInfo(name = "time_stamp") val timeStamp: Long,
    @ColumnInfo(name = "ras_x") val rasX: Double?,
    @ColumnInfo(name = "ras_y") val rasY: Double?,
    @ColumnInfo(name = "ras_z") val rasZ: Double?,
    @ColumnInfo(name = "ras_sin") val rasSin: Double?,
    @ColumnInfo(name = "ras_cos") val rasCos: Double?
)
