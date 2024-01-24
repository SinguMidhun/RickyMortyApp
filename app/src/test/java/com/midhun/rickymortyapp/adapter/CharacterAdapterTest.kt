package com.midhun.rickymortyapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.test.core.app.ApplicationProvider
import com.midhun.rickymortyapp.HelperTest
import com.midhun.rickymortyapp.databinding.SingleCharacterLayoutBinding
import com.midhun.rickymortyapp.models.CharacterDetails
import com.midhun.rickymortyapp.models.Location
import com.midhun.rickymortyapp.models.Origin
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.TestCase.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
class CharacterAdapterTest {

    private var adapter : CharacterAdapter? = null
    private var characterList : List<CharacterDetails>? = null
    private var callback : CharacterAdapter.onCharacterClick? = null
    private var context : Context? = null

    @Before
    fun setUp(){
        val characterDetails = HelperTest.getCharacterDetails()
        context = ApplicationProvider.getApplicationContext<Context>()
        characterList = arrayListOf(characterDetails,characterDetails)
        callback = mock(CharacterAdapter.onCharacterClick::class.java)
        adapter = CharacterAdapter(characterList!!,callback!!)
    }

    @Test
    fun test_onCreateViewHolder(){
        mockkStatic(LayoutInflater::class)
        mockkStatic(SingleCharacterLayoutBinding::class)
        val parent = mock(ViewGroup::class.java)
        val layoutInflater = mock(LayoutInflater::class.java)
        val view = mock(SingleCharacterLayoutBinding::class.java)
        `when`(parent.context).thenReturn(context)
        `when`(view.root).thenReturn(mock(CardView::class.java))
        every { LayoutInflater.from(any()) }.returns(layoutInflater)
        every { SingleCharacterLayoutBinding.inflate(any(),any(),any()) }.returns(view)
        val viewHolder = adapter?.onCreateViewHolder(parent,1)
        assertNotNull(viewHolder)
    }


    @Test
    fun test_getItemCount(){
        val count = adapter?.itemCount
        assertEquals(count,characterList?.size)
    }


    @After
    fun tearDown(){
        adapter = null
        characterList = null
        callback = null
    }

}