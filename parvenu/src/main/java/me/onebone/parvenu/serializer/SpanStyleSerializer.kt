package me.onebone.parvenu.serializer

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.unit.TextUnit
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/*
    args for 'class SpanStyle'
    internal val textForegroundStyle: TextForegroundStyle,
    val fontSize: TextUnit = TextUnit.Unspecified,
    val fontWeight: FontWeight? = null,
    val fontStyle: FontStyle? = null,
    val fontSynthesis: FontSynthesis? = null,
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

@Serializable
@SerialName("SpanStyle")
private class SpanStyleSurrogate(
    @Serializable(with = TextUnitSerializer::class)
    val fontSize: TextUnit = TextUnit.Unspecified,
    @Serializable(with = FontWeightSerializer::class)
    val fontWeight: FontWeight? = null,
    @Serializable(with = FontStyleSerializer::class)
    val fontStyle: FontStyle? = null,
    @Serializable(with = FontSynthesisSerializer::class)
    val fontSynthesis: FontSynthesis? = null,
    @Serializable(with = FontFamilySerializer::class)
    val fontFamily: FontFamily? = null,
    val fontFeatureSettings: String? = null,
    @Serializable(with = TextUnitSerializer::class)
    val letterSpacing: TextUnit = TextUnit.Unspecified,
    @Serializable(with = BaselineShiftSerializer::class)
    val baselineShift: BaselineShift? = null,
    @Serializable(with = TextGeometricTransformSerializer::class)
    val textGeometricTransform: TextGeometricTransform? = null,
    @Serializable(with = LocaleListSerializer::class)
    val localeList: LocaleList? = null,
    @Serializable(with = ColorSerializer::class)
    val background: Color = Color.Unspecified,
    @Serializable(with = TextDecorationSerializer::class)
    val textDecoration: TextDecoration? = null,
    @Serializable(with = ShadowSerializer::class)
    val shadow: Shadow? = null
)

public object SpanStyleSerializer : KSerializer<SpanStyle> {
    override val descriptor: SerialDescriptor = SpanStyleSurrogate.serializer().descriptor

    override fun deserialize(decoder: Decoder): SpanStyle {
        val surrogate = decoder.decodeSerializableValue(SpanStyleSurrogate.serializer())
        return SpanStyle(
            fontSize = surrogate.fontSize,
            fontWeight = surrogate.fontWeight,
            fontStyle = surrogate.fontStyle,
            fontSynthesis = surrogate.fontSynthesis,
            fontFamily = surrogate.fontFamily,
            fontFeatureSettings = surrogate.fontFeatureSettings,
            letterSpacing = surrogate.letterSpacing,
            baselineShift = surrogate.baselineShift,
            textGeometricTransform = surrogate.textGeometricTransform,
            localeList = surrogate.localeList,
            background = surrogate.background,
            textDecoration = surrogate.textDecoration,
            shadow = surrogate.shadow
        )
    }

    override fun serialize(encoder: Encoder, value: SpanStyle) {
        encoder.encodeSerializableValue(
            SpanStyleSurrogate.serializer(),
            SpanStyleSurrogate(
                fontSize = value.fontSize,
                fontWeight = value.fontWeight,
                fontStyle = value.fontStyle,
                fontSynthesis = value.fontSynthesis,
                fontFamily = value.fontFamily,
                fontFeatureSettings = value.fontFeatureSettings,
                letterSpacing = value.letterSpacing,
                baselineShift = value.baselineShift,
                textGeometricTransform = value.textGeometricTransform,
                localeList = value.localeList,
                background = value.background,
                textDecoration = value.textDecoration,
                shadow = value.shadow
            )
        )
    }
}