package com.midhun.rickymortyapp.models

data class CharacterResponse(
    val info: Info,
    val results: List<CharacterDetails>
)