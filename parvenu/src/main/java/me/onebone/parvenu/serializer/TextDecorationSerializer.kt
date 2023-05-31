package me.onebone.parvenu.serializer

import androidx.compose.ui.text.style.TextDecoration
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public object TextDecorationSerializer : KSerializer<TextDecoration> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("TextDecoration", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: TextDecoration) {
        encoder.encodeString(
            when (value) {
                TextDecoration.None -> "none"
                TextDecoration.Underline -> "underline"
                TextDecoration.LineThrough -> "line-through"
                else -> {
                    throw IllegalArgumentException("Unknown text decoration while serializing: $value")
                }
            }
        )
    }

    override fun deserialize(decoder: Decoder): TextDecoration {
        return when (val str = decoder.decodeString()) {
            "none" -> TextDecoration.None
            "underline" -> TextDecoration.Underline
            "line-through" -> TextDecoration.LineThrough
            else -> {
                throw IllegalArgumentException("Unknown text decoration while deserializing: $str")
            }
        }
    }
}