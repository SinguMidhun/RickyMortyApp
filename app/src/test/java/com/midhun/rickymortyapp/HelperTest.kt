package com.midhun.rickymortyapp

import com.midhun.rickymortyapp.models.*
import com.midhun.rickymortyapp.repository.room.FavCharactersTable
import org.mockito.Mockito

object HelperTest {

    fun getCharacterDetails() : CharacterDetails{
        return Mockito.spy(CharacterDetails(
            getStringForTest(),
            arrayListOf(getStringForTest()),
            getStringForTest(),
            getIntForTest(),
            getStringForTest(),
            getLocation(),
            getStringForTest(),
            getOrigin(),
            getStringForTest(),
            getStringForTest(),
            getStringForTest(),
            getStringForTest()
        ))
    }

    fun getLocation() : Location{
        return Location(getStringForTest(),getStringForTest())
    }

    fun getOrigin() : Origin{
        return Origin(getStringForTest(),getStringForTest())
    }

    fun getInfo() : Info{
        return Info(getIntForTest(), getStringForTest(), getStringForTest(), getStringForTest())
    }

    fun getCharacterResponse() : CharacterResponse{
        return CharacterResponse(getInfo(), arrayListOf(getCharacterDetails()))
    }

    fun getStringForTest() : String{
        return "dummy text"
    }

    fun getIntForTest() : Int{
        return 1
    }

    fun getFavTable() : FavCharactersTable{
        return FavCharactersTable(getIntForTest(), getStringForTest())
    }

}