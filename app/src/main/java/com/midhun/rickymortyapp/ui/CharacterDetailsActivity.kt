package com.midhun.rickymortyapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.midhun.rickymortyapp.R
import com.midhun.rickymortyapp.databinding.ActivityCharacterDetailsBinding
import com.midhun.rickymortyapp.repository.room.FavCharactersTable
import com.midhun.rickymortyapp.repository.room.FavDatabase
import com.midhun.rickymortyapp.utils.NetworkStates
import com.midhun.rickymortyapp.viewmodels.CharacterViewModel
import com.squareup.picasso.Picasso

class CharacterDetailsActivity : AppCompatActivity() {

    private var _binder : ActivityCharacterDetailsBinding? = null
    val binder get() = _binder!!

    private lateinit var viewModel: CharacterViewModel

    private lateinit var favDatabase: FavDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binder = ActivityCharacterDetailsBinding.inflate(layoutInflater)
        setContentView(binder.root)

        val characterDetails = intent.extras?.getBundle("characterDetails")!!
        val imageUrl = characterDetails.getString("image")
        val id = characterDetails.getInt("id")
        val name = characterDetails.getString("name")

        //room
        favDatabase = FavDatabase.getDatabase(this)

        viewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        setObserver()
        viewModel.checkIfCharacterFavourite(id,favDatabase)

        binder.characterId.text = id.toString()
        binder.characterName.text = name
        binder.characterGender.text = characterDetails.getString("gender")
        binder.characterLocation.text = characterDetails.getString("location")
        binder.characterOrigin.text = characterDetails.getString("origin")
        binder.characterSpecies.text = characterDetails.getString("species")
        binder.characterStatus.text = characterDetails.getString("status")
        binder.characterType.text = characterDetails.getString("type")

        binder.characterImage.let {
            Picasso.get().load(imageUrl).placeholder(R.drawable.ic_launcher_background).into(binder.characterImage)
        }

        binder.characterFav.setOnClickListener{
            if(binder.characterFav.isChecked){
                viewModel.addToFavourites(FavCharactersTable(id,name!!),favDatabase)
            }else{
                viewModel.removeFromFavourites(FavCharactersTable(id,name!!),favDatabase)
            }
        }

    }

    private fun setObserver() {
        viewModel.favStatus.observe(this){
            binder.detailProgress.visibility = View.GONE
            when(it){
                is NetworkStates.Success -> {
                    if(it.data!=null){
                        binder.characterFav.isChecked = it.data
                    }else{
                        Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
                    }
                }
                is NetworkStates.Error -> {
                    Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
                }
                is NetworkStates.Loading -> {
                    binder.detailProgress.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binder = null
    }
}