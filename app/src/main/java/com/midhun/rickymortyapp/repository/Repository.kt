package com.midhun.rickymortyapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.midhun.rickymortyapp.models.CharacterResponse
import com.midhun.rickymortyapp.repository.room.FavCharactersTable
import com.midhun.rickymortyapp.repository.room.FavDatabase
import com.midhun.rickymortyapp.utils.NetworkStates
import com.midhun.rickymortyapp.utils.NetworkUtils

class Repository {

    val remoteApi = NetworkUtils.getRetrofitInstance().create(RemoteApi::class.java)

    private val _networkState = MutableLiveData<NetworkStates<CharacterResponse>>(NetworkStates.Loading())
    val networkStates : LiveData<NetworkStates<CharacterResponse>> get() = _networkState

    private val _isLikedState = MutableLiveData<NetworkStates<Boolean>>(NetworkStates.Loading())
    val isLikedState : LiveData<NetworkStates<Boolean>> get() = _isLikedState

    suspend fun getListOfCharacters(page : Int){
        _networkState.postValue(NetworkStates.Loading())
        val response = remoteApi.getListOfCharacters(page)
        if(response.isSuccessful && response.body()!=null){
            _networkState.postValue(NetworkStates.Success(response.body()))
        }else{
            _networkState.postValue(NetworkStates.Error("Failed to load the data"))
        }
    }

    suspend fun checkIfCharacterFavourite(id : Int, roomInstance : FavDatabase){
        _isLikedState.postValue(NetworkStates.Loading())
        val response = roomInstance.getProductsDao().isFavCharacter(id)
        if(response!=null){
            _isLikedState.postValue(NetworkStates.Success(response))
        }else{
            _isLikedState.postValue(NetworkStates.Error("Something went wrong"))
        }
    }

    suspend fun addToFavourites(favCharactersTable: FavCharactersTable, roomInstance : FavDatabase){
        roomInstance.getProductsDao().addToFav(favCharactersTable)
    }

    suspend fun removeFromFavourites(favCharactersTable: FavCharactersTable, roomInstance : FavDatabase){
        roomInstance.getProductsDao().removeFromFav(favCharactersTable)
    }

}