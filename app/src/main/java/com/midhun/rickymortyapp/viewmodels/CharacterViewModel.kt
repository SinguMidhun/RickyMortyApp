package com.midhun.rickymortyapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.midhun.rickymortyapp.repository.Repository
import com.midhun.rickymortyapp.repository.room.FavCharactersTable
import com.midhun.rickymortyapp.repository.room.FavDatabase
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    private val repo = Repository()

    val networkStates get() = repo.networkStates
    val favStatus get() = repo.isLikedState

    fun getListOfCharacters(page : Int){
        viewModelScope.launch {
            repo.getListOfCharacters(page)
        }
    }

    fun checkIfCharacterFavourite(id : Int, roomInstance : FavDatabase){
        viewModelScope.launch {
            repo.checkIfCharacterFavourite(id,roomInstance)
        }
    }

    fun addToFavourites(favCharactersTable: FavCharactersTable, roomInstance : FavDatabase){
        viewModelScope.launch {
            repo.addToFavourites(favCharactersTable,roomInstance)
        }
    }

    fun removeFromFavourites(favCharactersTable: FavCharactersTable, roomInstance : FavDatabase){
        viewModelScope.launch {
            repo.removeFromFavourites(favCharactersTable,roomInstance)
        }
    }

}