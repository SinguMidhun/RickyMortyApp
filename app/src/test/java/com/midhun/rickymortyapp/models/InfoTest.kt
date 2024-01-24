package com.midhun.rickymortyapp.models

import com.midhun.rickymortyapp.HelperTest.getInfo
import com.midhun.rickymortyapp.HelperTest.getIntForTest
import com.midhun.rickymortyapp.HelperTest.getStringForTest
import junit.framework.TestCase.assertEquals
import org.junit.Test

class InfoTest {

    @Test
    fun test(){
        val info = getInfo()
        assertEquals(info.count, getIntForTest())
        assertEquals(info.next, getStringForTest())
        assertEquals(info.pages, getStringForTest())
        assertEquals(info.prev, getStringForTest())
    }

}