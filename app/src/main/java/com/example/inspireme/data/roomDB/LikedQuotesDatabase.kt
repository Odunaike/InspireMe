package com.example.inspireme.data.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [QuoteItem::class], version = 1, exportSchema = false)
abstract class LikedQuotesDatabase: RoomDatabase() {
    abstract fun quoteDao(): QuoteDao
    companion object {
        @Volatile
        private var Instance: LikedQuotesDatabase? = null
        fun getDatabase(context: Context): LikedQuotesDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, LikedQuotesDatabase::class.java, "liked_quotes_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}