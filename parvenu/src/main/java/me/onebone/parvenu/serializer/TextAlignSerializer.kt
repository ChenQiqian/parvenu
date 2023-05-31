package me.onebone.parvenu.serializer

import androidx.compose.ui.text.style.TextAlign
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public object TextAlignSerializer: KSerializer<TextAlign> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("TextAlign", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: TextAlign) {
        encoder.encodeInt(TextAlign.values().indexOf(value))
    }

    override fun deserialize(decoder: Decoder): TextAlign {
        return TextAlign.values()[decoder.decodeInt()]
    }
}