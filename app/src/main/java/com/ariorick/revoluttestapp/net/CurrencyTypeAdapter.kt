package com.ariorick.revoluttestapp.net

import com.ariorick.revoluttestapp.model.Currency
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonIOException
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.io.IOException


class CurrencyTypeAdapter : TypeAdapter<List<Currency>>() {

    private val elementAdapter = Gson().getAdapter(JsonElement::class.java)

    override fun write(out: JsonWriter, value: List<Currency>) {

    }

    @Throws(IOException::class)
    override fun read(reader: JsonReader): List<Currency> {
        val jsonElement = elementAdapter.read(reader)

        if (jsonElement.isJsonObject) {
            val jsonObject = jsonElement.asJsonObject

            val base = jsonObject.get("base").asString

            val list = ArrayList<Currency>()
            list.add(Currency(base, base, 1f))
            jsonObject.getAsJsonObject("rates")
                .entrySet()
                .forEach { list.add(Currency(it.key, base, it.value.asFloat)) }

            return list
        }
        throw JsonIOException("Wrong json structure")
    }
}
