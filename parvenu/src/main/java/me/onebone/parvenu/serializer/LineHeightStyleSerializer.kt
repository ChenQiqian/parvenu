package me.onebone.parvenu.serializer

import androidx.compose.ui.text.style.LineHeightStyle
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public object LineHeightStyleSerializer: KSerializer<LineHeightStyle> {
    // Alignment: Top/Center/Proportional/Bottom
    // Trim: FirstLineTop/LastLineBottom/Both/None
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("LineHeightStyle", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LineHeightStyle) {
        val alignmentString = when(value.alignment) {
            LineHeightStyle.Alignment.Top -> "Top"
            LineHeightStyle.Alignment.Center -> "Center"
            LineHeightStyle.Alignment.Proportional -> "Proportional"
            LineHeightStyle.Alignment.Bottom -> "Bottom"
            else -> { throw IllegalArgumentException("Unknown LineHeightStyle alignment while serializing: ${value.alignment}") }
        }
        val trimString = when(value.trim) {
            LineHeightStyle.Trim.FirstLineTop -> "FirstLineTop"
            LineHeightStyle.Trim.LastLineBottom -> "LastLineBottom"
            LineHeightStyle.Trim.Both -> "Both"
            LineHeightStyle.Trim.None -> "None"
            else -> { throw IllegalArgumentException("Unknown LineHeightStyle trim while serializing: ${value.trim}") }
        }
        encoder.encodeString("$alignmentString/$trimString")
    }

    override fun deserialize(decoder: Decoder): LineHeightStyle {
        val string = decoder.decodeString()
        val (alignmentString, trimString) = string.split("/")
        val alignment = when(alignmentString) {
            "Top" -> LineHeightStyle.Alignment.Top
            "Center" -> LineHeightStyle.Alignment.Center
            "Proportional" -> LineHeightStyle.Alignment.Proportional
            "Bottom" -> LineHeightStyle.Alignment.Bottom
            else -> { throw IllegalArgumentException("Unknown LineHeightStyle alignment while deserializing: $alignmentString") }
        }
        val trim = when(trimString) {
            "FirstLineTop" -> LineHeightStyle.Trim.FirstLineTop
            "LastLineBottom" -> LineHeightStyle.Trim.LastLineBottom
            "Both" -> LineHeightStyle.Trim.Both
            "None" -> LineHeightStyle.Trim.None
            else -> { throw IllegalArgumentException("Unknown LineHeightStyle trim while deserializing: $trimString") }
        }
        return LineHeightStyle(alignment, trim)
    }
}