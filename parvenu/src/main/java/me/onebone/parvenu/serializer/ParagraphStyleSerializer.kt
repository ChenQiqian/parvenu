package me.onebone.parvenu.serializer

import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.TextUnit
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/*
    val textAlign: TextAlign? = null,
    val textDirection: TextDirection? = null,
    val lineHeight: TextUnit = TextUnit.Unspecified,
    val textIndent: TextIndent? = null,
    val platformStyle: PlatformParagraphStyle? = null,
    val lineHeightStyle: LineHeightStyle? = null,
    @Suppress("OPT_IN_MARKER_ON_WRONG_TARGET")
    @get:ExperimentalTextApi
    @property:ExperimentalTextApi
    val lineBreak: LineBreak? = null,
    @Suppress("OPT_IN_MARKER_ON_WRONG_TARGET")
    @get:ExperimentalTextApi
    @property:ExperimentalTextApi
    val hyphens: Hyphens? = null

 */

@Serializable
@SerialName("ParagraphStyle")
private class ParagraphStyleSurrogate @OptIn(ExperimentalTextApi::class) constructor(
    @Serializable(with = TextAlignSerializer::class)
    val textAlign: TextAlign? = null,
    @Serializable(with = TextDirectionSerializer::class)
    val textDirection: TextDirection? = null,
    @Serializable(with = TextUnitSerializer::class)
    val lineHeight: TextUnit = TextUnit.Unspecified,
    @Serializable(with = TextIndentSerializer::class)
    val textIndent: TextIndent? = null,
    @Serializable(with = LineHeightStyleSerializer::class)
    val lineHeightStyle: LineHeightStyle? = null,
    @Serializable(with = LineBreakSerializer::class)
    val lineBreak: LineBreak? = null,
)

public object ParagraphStyleSerializer : KSerializer<ParagraphStyle> {
    override val descriptor: SerialDescriptor
        get() = ParagraphStyleSurrogate.serializer().descriptor

    @OptIn(ExperimentalTextApi::class)
    override fun deserialize(decoder: Decoder): ParagraphStyle {
        val surrogate = decoder.decodeSerializableValue(ParagraphStyleSurrogate.serializer())
        return ParagraphStyle(
            textAlign = surrogate.textAlign,
            textDirection = surrogate.textDirection,
            lineHeight = surrogate.lineHeight,
            textIndent = surrogate.textIndent,
            lineHeightStyle = surrogate.lineHeightStyle,
            lineBreak = surrogate.lineBreak,
        )
    }

    @OptIn(ExperimentalTextApi::class)
    override fun serialize(encoder: Encoder, value: ParagraphStyle) {
        encoder.encodeSerializableValue(
            ParagraphStyleSurrogate.serializer(),
            ParagraphStyleSurrogate(
                textAlign = value.textAlign,
                textDirection = value.textDirection,
                lineHeight = value.lineHeight,
                textIndent = value.textIndent,
                lineHeightStyle = value.lineHeightStyle,
                lineBreak = value.lineBreak,
            )
        )
    }

}