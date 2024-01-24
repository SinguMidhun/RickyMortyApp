package com.midhun.rickymortyapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.midhun.rickymortyapp.adapter.CharacterAdapter
import com.midhun.rickymortyapp.databinding.ActivityMainBinding
import com.midhun.rickymortyapp.models.CharacterDetails
import com.midhun.rickymortyapp.models.CharacterResponse
import com.midhun.rickymortyapp.utils.Constants.TAG
import com.midhun.rickymortyapp.utils.NetworkStates
import com.midhun.rickymortyapp.viewmodels.CharacterViewModel

class MainActivity : AppCompatActivity(), CharacterAdapter.onCharacterClick {

    private var _binder : ActivityMainBinding? = null
    private val binder get() = _binder!!
    private lateinit var viewModel: CharacterViewModel
    private val characterList : MutableList<CharacterDetails> = arrayListOf()
    private lateinit var latestCharacterResponse: CharacterResponse
    private lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binder.root)

        viewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        setObservers()
        viewModel.getListOfCharacters(1)

        binder.mainLoadmore.setOnClickListener{
            val nextPageUrl = latestCharacterResponse.info.next
            val nextPage = nextPageUrl.get(nextPageUrl.length-1).toString().toInt()
            Log.d(TAG, "onCreate: $nextPageUrl")
            Log.d(TAG, "onCreate: $nextPage")
            viewModel.getListOfCharacters(nextPage)
        }

        adapter = CharacterAdapter(characterList,this)
        binder.mainRecyclerView.layoutManager = GridLayoutManager(this,2)
        binder.mainRecyclerView.adapter = adapter

    }

    private fun setObservers() {
        viewModel.networkStates.observe(this) {
            binder.mainProgress.visibility = View.GONE
            when (it) {
                is NetworkStates.Success -> {
                    if(it.data!=null){
                        latestCharacterResponse = it.data
                        for(data in it.data.results){
                            characterList.add(data)
                        }
                        adapter.notifyDataSetChanged()
                        Log.d(TAG, "setObservers: ${characterList.size}")
                    }
                }
                is NetworkStates.Error -> {
                    Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "setObservers: ${it.message}")
                }
                is NetworkStates.Loading -> {
                    binder.mainProgress.visibility = View.VISIBLE
                    Log.d(TAG, "setObservers: Loading")
                }
            }
        }
    }

    override fun onItemClicker(position: Int) {
        val characterDetails = characterList.get(position)
        val bundle = Bundle()
        bundle.putString("name",characterDetails.name)
        bundle.putString("gender",characterDetails.gender)
        bundle.putInt("id",characterDetails.id)
        bundle.putString("location",characterDetails.location.name)
        bundle.putString("origin",characterDetails.origin.name)
        bundle.putString("species",characterDetails.species)
        bundle.putString("status",characterDetails.status)
        bundle.putString("type",characterDetails.type)
        bundle.putString("image",characterDetails.image)
        Intent(this,CharacterDetailsActivity::class.java).apply {
            putExtra("characterDetails",bundle)
            startActivity(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binder = null
    }
}