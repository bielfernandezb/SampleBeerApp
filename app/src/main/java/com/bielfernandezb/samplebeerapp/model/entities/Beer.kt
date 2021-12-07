package com.bielfernandezb.samplebeerapp.model.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Beers")
data class Beer(
    @PrimaryKey()
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
    @Embedded(prefix = "volume_")
    val volume: BoilVolume,
    @Embedded(prefix = "boilVolume_")
    @SerializedName("boil_volume")
    val boilVolume: BoilVolume?,
    @Embedded
    val method: Method?,
    @Embedded
    val ingredients: Ingredients?,
    @SerializedName("food_pairing")
    val foodPairings: List<String>?,
    @SerializedName("brewers_tips")
    val brewersTips: String?,
    @SerializedName("contributed_by")
    val contributedBy: String?
)

data class BoilVolume(
    val value: Double?,
    val unit: String?
)

data class Ingredients(
    val malt: List<Malt>?,
    val hops: List<Hop>?,
    val yeast: String?
)

data class Hop(
    val name: String?,
    @Embedded(prefix = "hop_")
    val amount: BoilVolume?,
    val add: String?,
    val attribute: String?
)

data class Malt(
    val name: String?,
    @Embedded(prefix = "malt_")
    val amount: BoilVolume?
)

data class Method(
    @SerializedName("mash_temp")
    val mashTemp: List<MashTemp>?,
    @Embedded
    val fermentation: Fermentation?,
    val twist: String?
)

data class Fermentation(
    @Embedded(prefix = "fermentation_")
    val temp: BoilVolume?
)

data class MashTemp(
    @Embedded(prefix = "mash_temp_")
    val temp: BoilVolume?,
    val duration: Long?
)