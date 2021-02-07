package com.example.taskmanager.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskmanager.data.local.db.dao.DummyDao
import com.example.taskmanager.data.local.db.entity.DummyEntity

import javax.inject.Singleton


/**
 * <h1>DatabaseService</h1>
 * <p>
 *
 * </p>
 *
 * @author Tonmoy Chandra Ray
 */
@Singleton
@Database(
    entities = [
        DummyEntity::class
    ],
    exportSchema = false,
    version = 1
)
abstract class DatabaseService : RoomDatabase() {

    abstract fun dummyDao(): DummyDao
}