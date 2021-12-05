package com.bielfernandezb.samplebeerapp.domain.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Beers")
data class Beer(
    @PrimaryKey
    var id: Long,
    val name: String?,
    val tagline: String?,
    val firstBrewed: String?,
    val description: String?,
    val imageURL: String?,
    val abv: Double?,
    val ibu: Double?,
    val targetFg: Double?,
    val targetOg: Double?,
    val ebc: Double?,
    val srm: Double?,
    val ph: Double?,
    val attenuationLevel: Double?,
    @Embedded(prefix = "volume_")
    val volume: BoilVolume,
    @Embedded(prefix = "boilVolume_")
    val boilVolume: BoilVolume?,
    @Embedded
    val method: Method?,
    @Embedded
    val ingredients: Ingredients?,
    val foodPairings: List<String>?,
    val brewersTips: String?,
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