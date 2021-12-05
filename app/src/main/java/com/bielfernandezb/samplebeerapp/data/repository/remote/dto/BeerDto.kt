package com.bielfernandezb.samplebeerapp.data.repository.remote.dto

import com.google.gson.annotations.SerializedName

data class BeerDto(
    val id: Long,
    val name: String?,
    val tagline: String?,
    @SerializedName("first_brewed")
    val firstBrewed: String?,
    val description: String?,
    @SerializedName("image_url")
    val imageURL: String?,
    val abv: Double?,
    val ibu: Double?,
    @SerializedName("target_fg")
    val targetFg: Double?,
    @SerializedName("target_og")
    val targetOg: Double?,
    val ebc: Double?,
    val srm: Double?,
    val ph: Double?,
    @SerializedName("attenuation_level")
    val attenuationLevel: Double?,
    val volume: BoilVolume_,
    @SerializedName("boil_volume")
    val boilVolume: BoilVolume_?,
    val method: Method_?,
    val ingredients: Ingredients_?,
    @SerializedName("food_pairing")
    val foodPairings: List<String>?,
    @SerializedName("brewers_tips")
    val brewersTips: String?,
    @SerializedName("contributed_by")
    val contributedBy: String?
)

data class BoilVolume_(
    val value: Double?,
    val unit: String?
)

data class Ingredients_(
    val malt: List<Malt_>?,
    val hops: List<Hop_>?,
    val yeast: String?
)

data class Hop_(
    val name: String?,
    val amount: BoilVolume_?,
    val add: String?,
    val attribute: String?
)

data class Malt_(
    val name: String?,
    val amount: BoilVolume_?
)

data class Method_(
    @SerializedName("mash_temp")
    val mashTemp: List<MashTemp_>?,
    val fermentation: Fermentation_?,
    val twist: String?
)

data class Fermentation_(
    val temp: BoilVolume_?
)

data class MashTemp_(
    val temp: BoilVolume_?,
    val duration: Long?
)