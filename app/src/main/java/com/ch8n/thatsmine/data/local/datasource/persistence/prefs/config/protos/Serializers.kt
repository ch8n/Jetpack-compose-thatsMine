package com.ch8n.thatsmine.data.local.datasource.persistence.prefs.config.protos

import androidx.datastore.CorruptionException
import androidx.datastore.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.ch8n.thatsmine.ProtoOwnedItems
import com.ch8n.thatsmine.ProtoSettings
import com.ch8n.thatsmine.Settings.CurrentUser.parseFrom
import java.io.InputStream
import java.io.OutputStream

object SerializersOwnedItem : Serializer<ProtoOwnedItems> {
    override fun readFrom(input: InputStream): ProtoOwnedItems {

        try {
            return ProtoOwnedItems.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override fun writeTo(
        t: ProtoOwnedItems,
        output: OutputStream
    ) {
        t.writeTo(output)
    }
}

object SerializersSettings : Serializer<ProtoSettings> {
    override fun readFrom(input: InputStream): ProtoSettings {

        try {
            return ProtoSettings.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override fun writeTo(
        t: ProtoSettings,
        output: OutputStream
    ) {
        t.writeTo(output)
    }
}