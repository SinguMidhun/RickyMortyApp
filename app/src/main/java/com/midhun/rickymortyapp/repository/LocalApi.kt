package com.midhun.rickymortyapp.repository

import androidx.room.*
import com.midhun.rickymortyapp.repository.room.FavCharactersTable
import retrofit2.Response

@Dao
interface LocalApi {

    @Query("SELECT EXISTS(SELECT * FROM favtable WHERE cId = :id)")
    suspend fun isFavCharacter(id : Int) : Boolean

    @Insert
    suspend fun addToFav(table : FavCharactersTable) : Unit

    @Delete
    suspend fun removeFromFav(table : FavCharactersTable) : Unit


}