package me.onebone.parvenu.serializer

import androidx.compose.ui.text.font.FontStyle
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public object FontStyleSerializer: KSerializer<FontStyle> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("FontStyle", PrimitiveKind.INT)
    override fun serialize(encoder: Encoder, value: FontStyle) {
        encoder.encodeInt(value.value)
    }
    override fun deserialize(decoder: Decoder): FontStyle {
        return FontStyle.values()[decoder.decodeInt()]
    }
}