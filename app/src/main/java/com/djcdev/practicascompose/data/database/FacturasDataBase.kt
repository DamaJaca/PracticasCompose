package com.djcdev.practicascompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.djcdev.practicascompose.data.database.dao.FacturaDao
import com.djcdev.practicas.data.database.entities.FacturaEntity

@Database(entities = [FacturaEntity::class], version = 1)
abstract class FacturasDataBase :RoomDatabase(){

    abstract fun getFacturasDao(): FacturaDao

}