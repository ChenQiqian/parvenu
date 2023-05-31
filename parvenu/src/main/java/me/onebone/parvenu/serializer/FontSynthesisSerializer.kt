package me.onebone.parvenu.serializer

import androidx.compose.ui.text.font.FontSynthesis
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public object FontSynthesisSerializer: KSerializer<FontSynthesis> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("FontSynthesis", PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: FontSynthesis) {
        encoder.encodeString(when (value) {
            FontSynthesis.None -> "None"
            FontSynthesis.All -> "All"
            FontSynthesis.Weight -> "Weight"
            FontSynthesis.Style -> "Style"
            else -> {
                throw IllegalArgumentException("Invalid FontSynthesis value while serializing: $value")
            }
        })
    }
    override fun deserialize(decoder: Decoder): FontSynthesis {
        return when (val value = decoder.decodeString()) {
            "None" -> FontSynthesis.None
            "All" -> FontSynthesis.All
            "Weight" -> FontSynthesis.Weight
            "Style" -> FontSynthesis.Style
            else -> throw IllegalArgumentException("Invalid FontSynthesis value while deserializing: $value")
        }
    }
}