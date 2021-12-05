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
    val volume: BoilVolumeDto,
    @SerializedName("boil_volume")
    val boilVolume: BoilVolumeDto?,
    val method: MethodDto?,
    val ingredients: IngredientsDto?,
    @SerializedName("food_pairing")
    val foodPairings: List<String>?,
    @SerializedName("brewers_tips")
    val brewersTips: String?,
    @SerializedName("contributed_by")
    val contributedBy: String?
)

data class BoilVolumeDto(
    val value: Double?,
    val unit: String?
)

data class IngredientsDto(
    val malt: List<MaltDto>?,
    val hops: List<HopDto>?,
    val yeast: String?
)

data class HopDto(
    val name: String?,
    val amount: BoilVolumeDto?,
    val add: String?,
    val attribute: String?
)

data class MaltDto(
    val name: String?,
    val amount: BoilVolumeDto?
)

data class MethodDto(
    @SerializedName("mash_temp")
    val mashTemp: List<MashTempDto>?,
    val fermentation: FermentationDto?,
    val twist: String?
)

data class FermentationDto(
    val temp: BoilVolumeDto?
)

data class MashTempDto(
    val temp: BoilVolumeDto?,
    val duration: Long?
)