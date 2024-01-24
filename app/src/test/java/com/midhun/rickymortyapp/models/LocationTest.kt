package com.midhun.rickymortyapp.models

import com.midhun.rickymortyapp.HelperTest.getLocation
import com.midhun.rickymortyapp.HelperTest.getStringForTest
import junit.framework.TestCase.assertEquals
import org.junit.Test

class LocationTest {

    @Test
    fun test(){
        val location = getLocation()
        assertEquals(location.url, getStringForTest())
        assertEquals(location.name, getStringForTest())
    }

}