package me.onebone.parvenu.serializer

import androidx.compose.ui.text.font.FontFamily
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/*
create serializers for these types:
val fontFamily: FontFamily? = null,
val fontFeatureSettings: String? = null,
val letterSpacing: TextUnit = TextUnit.Unspecified,
val baselineShift: BaselineShift? = null,
val textGeometricTransform: TextGeometricTransform? = null,
val localeList: LocaleList? = null,
val background: Color = Color.Unspecified,
val textDecoration: TextDecoration? = null,
val shadow: Shadow? = null,
val platformStyle: PlatformSpanStyle? = null
*/

public object FontFamilySerializer: KSerializer<FontFamily> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("FontFamily", PrimitiveKind.STRING)
    // default, sans-serif, serif, monospace, cursive
    override fun deserialize(decoder: Decoder): FontFamily {
        return when (val str = decoder.decodeString()) {
            "default" -> FontFamily.Default
            "sans-serif" -> FontFamily.SansSerif
            "serif" -> FontFamily.Serif
            "monospace" -> FontFamily.Monospace
            "cursive" -> FontFamily.Cursive
            else -> {
                throw IllegalArgumentException("Unknown font family while deserializing: $str")
            }
        }
    }

    override fun serialize(encoder: Encoder, value: FontFamily) {
        encoder.encodeString(
            when (value) {
                FontFamily.Default -> "default"
                FontFamily.SansSerif -> "sans-serif"
                FontFamily.Serif -> "serif"
                FontFamily.Monospace -> "monospace"
                FontFamily.Cursive -> "cursive"
                else -> {
                    throw IllegalArgumentException("Unknown font family while serializing: $value")
                }
            }
        )
    }
}

