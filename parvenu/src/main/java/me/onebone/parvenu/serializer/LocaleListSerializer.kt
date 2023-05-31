package me.onebone.parvenu.serializer

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.intl.LocaleList
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public object LocaleListSerializer : KSerializer<LocaleList> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("LocaleList", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LocaleList) {
        encoder.encodeString(value.localeList.joinToString(","))
    }

    override fun deserialize(decoder: Decoder): LocaleList {
        return LocaleList(decoder.decodeString().split(",").map { Locale(it) })
    }
}