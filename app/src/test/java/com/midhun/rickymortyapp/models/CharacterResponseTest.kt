package com.midhun.rickymortyapp.models

import com.midhun.rickymortyapp.HelperTest.getCharacterResponse
import com.midhun.rickymortyapp.HelperTest.getInfo
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CharacterResponseTest {

    @Test
    fun test(){
        val characterResponse = getCharacterResponse()
        assertEquals(characterResponse.info, getInfo())
        assertEquals(characterResponse.results.size,1)
    }

}