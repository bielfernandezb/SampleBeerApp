package com.bielfernandezb.samplebeerapp.model.repository.local.utils

import androidx.room.TypeConverter
import com.bielfernandezb.samplebeerapp.model.entities.Hop
import com.bielfernandezb.samplebeerapp.model.entities.Malt
import com.bielfernandezb.samplebeerapp.model.entities.MashTemp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromStringList(value: List<String>): String {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromMashTemp(value: List<MashTemp>): String {
        val gson = Gson()
        val type = object : TypeToken<List<MashTemp>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toMashTemp(value: String): List<MashTemp> {
        val gson = Gson()
        val type = object : TypeToken<List<MashTemp>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromHop(value: List<Hop>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Hop>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toHop(value: String): List<Hop> {
        val gson = Gson()
        val type = object : TypeToken<List<Hop>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromMalt(value: List<Malt>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Malt>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toMalt(value: String): List<Malt> {
        val gson = Gson()
        val type = object : TypeToken<List<Malt>>() {}.type
        return gson.fromJson(value, type)
    }
}