package me.onebone.parvenu.serializer

import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.style.LineBreak
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@OptIn(ExperimentalTextApi::class)
public object LineBreakSerializer: KSerializer<LineBreak> {
    // Strategy: Simple/HighQuality/Balanced
    // Strictness: Default/Loose/Normal/Strict
    // WordBreak: Default/Phrase
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("LineBreak", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LineBreak) {
        val strategyString = when(value.strategy) {
            LineBreak.Strategy.Simple -> "Simple"
            LineBreak.Strategy.HighQuality -> "HighQuality"
            LineBreak.Strategy.Balanced -> "Balanced"
            else -> { throw IllegalArgumentException("Unknown LineBreak strategy while serializing: ${value.strategy}") }
        }
        val strictnessString = when(value.strictness) {
            LineBreak.Strictness.Default -> "Default"
            LineBreak.Strictness.Loose -> "Loose"
            LineBreak.Strictness.Normal -> "Normal"
            LineBreak.Strictness.Strict -> "Strict"
            else -> { throw IllegalArgumentException("Unknown LineBreak strictness while serializing: ${value.strictness}") }
        }
        val wordBreakString = when(value.wordBreak) {
            LineBreak.WordBreak.Default -> "Default"
            LineBreak.WordBreak.Phrase -> "Phrase"
            else -> { throw IllegalArgumentException("Unknown LineBreak wordBreak while serializing: ${value.wordBreak}") }
        }
        encoder.encodeString("$strategyString/$strictnessString/$wordBreakString")
    }

    override fun deserialize(decoder: Decoder): LineBreak {
        val string = decoder.decodeString()
        val (strategyString, strictnessString, wordBreakString) = string.split("/")
        val strategy = when(strategyString) {
            "Simple" -> LineBreak.Strategy.Simple
            "HighQuality" -> LineBreak.Strategy.HighQuality
            "Balanced" -> LineBreak.Strategy.Balanced
            else -> { throw IllegalArgumentException("Unknown LineBreak strategy while deserializing: $strategyString") }
        }
        val strictness = when(strictnessString) {
            "Default" -> LineBreak.Strictness.Default
            "Loose" -> LineBreak.Strictness.Loose
            "Normal" -> LineBreak.Strictness.Normal
            "Strict" -> LineBreak.Strictness.Strict
            else -> { throw IllegalArgumentException("Unknown LineBreak strictness while deserializing: $strictnessString") }
        }
        val wordBreak = when(wordBreakString) {
            "Default" -> LineBreak.WordBreak.Default
            "Phrase" -> LineBreak.WordBreak.Phrase
            else -> { throw IllegalArgumentException("Unknown LineBreak wordBreak while deserializing: $wordBreakString") }
        }
        return LineBreak(strategy, strictness, wordBreak)
    }
}