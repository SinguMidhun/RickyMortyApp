package com.midhun.rickymortyapp.models

import com.midhun.rickymortyapp.HelperTest.getOrigin
import com.midhun.rickymortyapp.HelperTest.getStringForTest
import junit.framework.TestCase.assertEquals
import org.junit.Test

class OriginTest {

    @Test
    fun test(){
        val origin = getOrigin()
        assertEquals(origin.url, getStringForTest())
        assertEquals(origin.name, getStringForTest())
    }

}