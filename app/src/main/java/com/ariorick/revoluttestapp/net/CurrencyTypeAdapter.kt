package com.ariorick.revoluttestapp.net

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonIOException
import com.google.gson.TypeAdapter
import com.google.gson.internal.bind.ArrayTypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

import java.io.IOException
import java.util.*

class CurrencyTypeAdapter : TypeAdapter<List<Currency>>() {

    private val mGson = Gson()
    private val delegate = mGson.getAdapter<Currency>(Currency::class.java)
    private val mArrayTypeAdapter = ArrayTypeAdapter<Currency>(mGson, delegate, Currency::class.java)
    private val elementAdapter = mGson.getAdapter(JsonElement::class.java)

    override fun write(out: JsonWriter, value: List<Currency>) {

    }

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): List<Currency> {
        val jsonElement = elementAdapter.read(`in`)

        if (jsonElement.isJsonObject) {
            var jsonObject = jsonElement.asJsonObject
            if (jsonObject.has("android") && jsonObject.get("android").isJsonObject) {
                jsonObject = jsonObject.getAsJsonObject("android")

                var locale = Locale.getDefault().language
                if (!jsonObject.has(locale)) {
                    locale = "ru"
                }
                if (!jsonObject.has(locale)) {
                    throw JsonIOException("No such locale in json")
                }

                if (jsonObject.get(locale).isJsonArray) {
                    val jsonArray = jsonObject.getAsJsonArray(locale)
                    return Arrays.asList<Currency>(*mArrayTypeAdapter.fromJsonTree(jsonArray) as Array<Currency>)
                }
            }
        }
        throw JsonIOException("Wrong json structure")
    }
}
