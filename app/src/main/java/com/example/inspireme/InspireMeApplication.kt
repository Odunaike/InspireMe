package com.example.inspireme


import android.app.Application
import com.example.inspireme.data.roomDB.LikedQuotesDatabase

class InspireMeApplication: Application() {
   val database : LikedQuotesDatabase by lazy {
       LikedQuotesDatabase.getDatabase(this)
   }
}