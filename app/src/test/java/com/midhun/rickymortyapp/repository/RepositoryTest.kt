package com.midhun.rickymortyapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.midhun.rickymortyapp.HelperTest.getCharacterResponse
import com.midhun.rickymortyapp.HelperTest.getFavTable
import com.midhun.rickymortyapp.repository.room.FavDatabase
import com.midhun.rickymortyapp.utils.NetworkUtils
import io.mockk.every
import io.mockk.mockkObject
import junit.framework.TestCase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.Response
import retrofit2.Retrofit

class RepositoryTest {

    private val dispatcher = StandardTestDispatcher()

    private var remoteApi : RemoteApi? = null
    private var repository : Repository? = null
    private var localApi : LocalApi? = null

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    @Before
    fun setUp(){
        mockkObject(NetworkUtils)
        val retrofit = mock(Retrofit::class.java)
        repository = spy(Repository())
        remoteApi = mock(RemoteApi::class.java)
        localApi = mock(LocalApi::class.java)
        every { NetworkUtils.getRetrofitInstance() }.returns(retrofit)
        `when`(retrofit.create(RemoteApi::class.java)).thenReturn(remoteApi)
        repository = spy(Repository())
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun test_getListOfCharacters(){
        runTest {
            `when`(remoteApi?.getListOfCharacters(1)).thenReturn(Response.success(getCharacterResponse()))
            repository?.getListOfCharacters(1)
            val networkData = repository?.networkStates?.value?.data
            assertNotNull(networkData)
        }
    }

    @Test
    fun test_getListOfCharacters_failure(){
        runTest {
            `when`(remoteApi?.getListOfCharacters(anyInt())).thenReturn(Response.error(404,
                ResponseBody.create(null,"Failed")
            ))
            repository?.getListOfCharacters(1)
            dispatcher.scheduler.advanceUntilIdle()
            val errorMessage = repository?.networkStates?.value?.message
            assertEquals(errorMessage,"Failed to load the data")
        }
    }

    @Test
    fun test_checkIfCharacterFavourite(){
        runTest {
            val favDatabase = mock(FavDatabase::class.java)
            `when`(favDatabase.getProductsDao()).thenReturn(localApi)
            `when`(localApi?.isFavCharacter(anyInt())).thenReturn(true)
            repository?.checkIfCharacterFavourite(1,favDatabase)
            val data = repository?.isLikedState?.value?.data!!
            assertTrue(data)
        }
    }

    @Test
    fun test_addToFavourites(){
        runTest {
            val favDatabase = mock(FavDatabase::class.java)
            `when`(favDatabase.getProductsDao()).thenReturn(localApi)
            repository?.addToFavourites(getFavTable(),favDatabase)
        }
    }

    @Test
    fun test_removeFromFavourites(){
        runTest {
            val favDatabase = mock(FavDatabase::class.java)
            `when`(favDatabase.getProductsDao()).thenReturn(localApi)
            repository?.removeFromFavourites(getFavTable(),favDatabase)
        }
    }

    @After
    fun tearDown(){
        repository = null
        remoteApi = null
        localApi = null
    }
}