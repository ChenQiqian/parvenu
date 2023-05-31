package me.onebone.parvenu.serializer

import androidx.compose.ui.text.style.TextGeometricTransform
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
@SerialName("TextGeometricTransform")
private class TextGeometricTransformSurrogate(val scaleX: Float, val skewX: Float)

public object TextGeometricTransformSerializer : KSerializer<TextGeometricTransform> {
    override val descriptor: SerialDescriptor = TextGeometricTransformSurrogate.serializer().descriptor

    override fun serialize(encoder: Encoder, value: TextGeometricTransform) {
        encoder.encodeSerializableValue(
            TextGeometricTransformSurrogate.serializer(),
            TextGeometricTransformSurrogate(value.scaleX, value.skewX)
        )
    }

    override fun deserialize(decoder: Decoder): TextGeometricTransform {
        val surrogate = decoder.decodeSerializableValue(TextGeometricTransformSurrogate.serializer())
        return TextGeometricTransform(surrogate.scaleX, surrogate.skewX)
    }
}