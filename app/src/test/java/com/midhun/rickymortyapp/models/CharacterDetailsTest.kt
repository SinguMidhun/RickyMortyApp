package com.midhun.rickymortyapp.models

import com.midhun.rickymortyapp.HelperTest.getCharacterDetails
import com.midhun.rickymortyapp.HelperTest.getIntForTest
import com.midhun.rickymortyapp.HelperTest.getLocation
import com.midhun.rickymortyapp.HelperTest.getOrigin
import com.midhun.rickymortyapp.HelperTest.getStringForTest
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CharacterDetailsTest {

    @Test
    fun test(){
        val characterDetails = getCharacterDetails()
        assertEquals(getStringForTest(),characterDetails.created)
        assertEquals(getStringForTest(),characterDetails.gender)
        assertEquals(getStringForTest(),characterDetails.image)
        assertEquals(getStringForTest(),characterDetails.name)
        assertEquals(getStringForTest(),characterDetails.species)
        assertEquals(getStringForTest(),characterDetails.status)
        assertEquals(getStringForTest(),characterDetails.type)
        assertEquals(getStringForTest(),characterDetails.url)
        assertEquals(getIntForTest(),characterDetails.id)
        assertEquals(getLocation(),characterDetails.location)
        assertEquals(getOrigin(),characterDetails.origin)
    }

}