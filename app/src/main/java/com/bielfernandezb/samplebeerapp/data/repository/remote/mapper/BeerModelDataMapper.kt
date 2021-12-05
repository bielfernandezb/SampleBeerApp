package com.bielfernandezb.samplebeerapp.data.repository.remote.mapper

import com.bielfernandezb.samplebeerapp.core.Resource
import com.bielfernandezb.samplebeerapp.data.repository.remote.dto.*
import com.bielfernandezb.samplebeerapp.domain.entity.*

class BeerModelDataMapper {

    fun transform(beersResource: Resource<List<BeerDto>>?): Resource<List<Beer>> {
        val beers = mutableListOf<Beer>()
        beersResource?.data?.forEach { it ->
            val beer = Beer(
                it.id,
                it.name,
                it.tagline,
                it.firstBrewed,
                it.description,
                it.imageURL,
                it.abv,
                it.ibu,
                it.targetFg,
                it.targetOg,
                it.ebc,
                it.srm,
                it.ph,
                it.attenuationLevel,
                mapBoilVolume(it.volume),
                mapBoilVolume(it.boilVolume),
                mapMethod(it.method),
                mapIngredients(it.ingredients),
                it.foodPairings,
                it.brewersTips,
                it.contributedBy
            )
            beers.add(beer)
        }

        return Resource(beersResource!!.status, beers, beersResource.message)
    }

    private fun mapBoilVolume(boilVolume: BoilVolume_?): BoilVolume {
        return BoilVolume(boilVolume?.value, boilVolume?.unit)
    }

    private fun mapMethod(method: Method_?): Method {
        return Method(
            mapMashTemp(method?.mashTemp),
            mapFermentation(method?.fermentation),
            method?.twist
        )
    }

    private fun mapIngredients(ingredients: Ingredients_?): Ingredients {
        return Ingredients(
            mapMalt(ingredients?.malt),
            mapHop(ingredients?.hops),
            ingredients?.yeast
        )
    }

    private fun mapFermentation(fermentation: Fermentation_?): Fermentation {
        return Fermentation(mapBoilVolume(fermentation?.temp))
    }

    private fun mapMashTemp(mashTemp_: List<MashTemp_>?): List<MashTemp> {
        val mashTemp = mutableListOf<MashTemp>()
        mashTemp_?.forEach { mashTemp.add(MashTemp(mapBoilVolume(it.temp), it.duration)) }
        return mashTemp
    }

    private fun mapMalt(malt_: List<Malt_>?): List<Malt> {
        val malt = mutableListOf<Malt>()
        malt_?.forEach { malt.add(Malt(it.name, mapBoilVolume(it.amount))) }
        return malt
    }

    private fun mapHop(hop_: List<Hop_>?): List<Hop> {
        val hop = mutableListOf<Hop>()
        hop_?.forEach { hop.add(Hop(it.name, mapBoilVolume(it.amount), it.add, it.attribute)) }
        return hop
    }
}