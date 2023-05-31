package me.onebone.parvenu.serializer

import androidx.compose.ui.text.style.BaselineShift
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public object BaselineShiftSerializer: KSerializer<BaselineShift> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("BaselineShift", PrimitiveKind.STRING)
    // sub, super, none
    override fun deserialize(decoder: Decoder): BaselineShift {
        return when (val str = decoder.decodeString()) {
            "sub" -> BaselineShift.Subscript
            "super" -> BaselineShift.Superscript
            "none" -> BaselineShift.None
            else -> {
                throw IllegalArgumentException("Unknown baseline shift while deserializing: $str")
            }
        }
    }

    override fun serialize(encoder: Encoder, value: BaselineShift) {
        encoder.encodeString(
            when (value) {
                BaselineShift.Subscript -> "sub"
                BaselineShift.Superscript -> "super"
                BaselineShift.None -> "none"
                else -> {
                    throw IllegalArgumentException("Unknown baseline shift while serializing: $value")
                }
            }
        )
    }
}