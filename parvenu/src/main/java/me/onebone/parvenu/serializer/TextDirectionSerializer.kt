package me.onebone.parvenu.serializer

import androidx.compose.ui.text.style.TextDirection
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public object TextDirectionSerializer : KSerializer<TextDirection> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("TextDirection", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: TextDirection) {
        when (value) {
            TextDirection.Ltr -> encoder.encodeString("ltr")
            TextDirection.Rtl -> encoder.encodeString("rtl")
            TextDirection.Content -> encoder.encodeString("content")
            TextDirection.ContentOrLtr -> encoder.encodeString("contentOrLtr")
            TextDirection.ContentOrRtl -> encoder.encodeString("contentOrRtl")
            else -> { throw IllegalArgumentException("Unknown TextDirection while serializing: $value") }
        }
    }

    override fun deserialize(decoder: Decoder): TextDirection {
        return when (val string = decoder.decodeString()) {
            "ltr" -> TextDirection.Ltr
            "rtl" -> TextDirection.Rtl
            "content" -> TextDirection.Content
            "contentOrLtr" -> TextDirection.ContentOrLtr
            "contentOrRtl" -> TextDirection.ContentOrRtl
            else -> { throw IllegalArgumentException("Unknown TextDirection while deserializing: $string") }
        }
    }
}