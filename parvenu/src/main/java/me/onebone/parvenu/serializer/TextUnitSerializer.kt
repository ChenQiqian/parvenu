package me.onebone.parvenu.serializer

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public object TextUnitSerializer: KSerializer<TextUnit> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("TextUnit", PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: TextUnit) {
        when (value.type) {
            TextUnitType.Em -> encoder.encodeString(value.value.toString() + TextUnitType.Em.toString())
            TextUnitType.Sp -> encoder.encodeString(value.value.toString() + TextUnitType.Sp.toString())
            TextUnitType.Unspecified -> encoder.encodeString(value.value.toString())
            else -> throw IllegalArgumentException("Unsupported TextUnitType: ${value.type}")
        }
    }

    override fun deserialize(decoder: Decoder): TextUnit {
        val text = decoder.decodeString()
        val type = when {
            text.endsWith(TextUnitType.Em.toString()) -> TextUnitType.Em
            text.endsWith(TextUnitType.Sp.toString()) -> TextUnitType.Sp
            else -> TextUnitType.Unspecified
        }

        return when (type) {
            TextUnitType.Em -> text.dropLast(2).toFloat().sp
            TextUnitType.Sp -> text.dropLast(2).toFloat().sp
            TextUnitType.Unspecified -> text.toFloat().sp
            else -> {
                throw IllegalArgumentException("Unsupported TextUnitType: $type")
            }
        }
    }
}