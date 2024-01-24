package com.midhun.rickymortyapp.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favtable")
data class FavCharactersTable(
    @PrimaryKey
    val cId : Int,
    val cName : String
)
