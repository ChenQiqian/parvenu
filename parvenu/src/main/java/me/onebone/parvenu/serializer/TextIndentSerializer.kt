@file:UseSerializers(TextUnitSerializer::class)
package me.onebone.parvenu.serializer

import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.TextUnit
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
private class TextIndentSurrogate(
    val firstLine: TextUnit,
    val restLine: TextUnit
)

public object TextIndentSerializer : KSerializer<TextIndent> {
    override val descriptor: SerialDescriptor
        get() = TextIndentSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: TextIndent) {
        encoder.encodeSerializableValue(
            TextIndentSurrogate.serializer(),
            TextIndentSurrogate(value.firstLine, value.restLine)
        )
    }

    override fun deserialize(decoder: Decoder): TextIndent {
        val surrogate = decoder.decodeSerializableValue(TextIndentSurrogate.serializer())
        return TextIndent(surrogate.firstLine, surrogate.restLine)
    }
}