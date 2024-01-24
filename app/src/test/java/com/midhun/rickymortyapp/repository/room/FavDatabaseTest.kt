package com.midhun.rickymortyapp.repository.room

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import junit.framework.TestCase.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class FavDatabaseTest {

    private var favDatabase : FavDatabase? = null

    @Test
    fun test(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        favDatabase = FavDatabase.getDatabase(context)
        assertNotNull(favDatabase)
    }

}