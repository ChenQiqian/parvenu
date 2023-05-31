package me.onebone.parvenu.serializer

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.toArgb
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public object ShadowSerializer : KSerializer<Shadow> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Shadow", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Shadow) {
        val color = value.color
        val offsetX = value.offset.x; val offsetY = value.offset.y
        val radius = value.blurRadius
        encoder.encodeString("${color.toArgb().toString(16).padStart(8, '0')},$offsetX,$offsetY,$radius")
    }

    override fun deserialize(decoder: Decoder): Shadow {
        val string = decoder.decodeString()
        val (color, offsetX, offsetY, radius) = string.split(",")
        return Shadow(
            Color(color.toInt(16)),
            Offset(offsetX.toFloat(), offsetY.toFloat()),
            radius.toFloat()
        )
    }
}