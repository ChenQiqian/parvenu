package me.onebone.parvenu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.SpanStyle

/**
 * This is used to provide ability to choose from several conflicting SpanStyles (like color, font-size).
 * We want to make sure that at one place only one [SpanStyle] in the [spanStyleList] can be active.
 */
@Composable
public fun ParvenuSpanPicker(
	value: ParvenuEditorValue,
	onValueChange: (ParvenuEditorValue) -> Unit,
	spanStyleList: List<SpanStyle>,
	spanEqualPredicate: (SpanStyle, SpanStyle) -> Boolean,
	block: @Composable (
		currentSelectedIndex: Int,
		onToggle: (index: Int) -> Unit
	) -> Unit
) {
	// judge which span in the list can be pick now
	val currentSelectedIndex = remember(value) {
		if (value.selection.collapsed) {
			spanStyleList.indexOfFirst { span ->
				value.parvenuString.spanStyles.any { range ->
					// equal to this span
					spanEqualPredicate(range.item, span)
							&& shouldExpandSpanOnTextAddition(range, value.selection.start)
				}
			}

		} else {
			spanStyleList.indexOfFirst { span ->
				value.parvenuString.spanStyles.fillsRange(
					start = value.selection.min,
					end = value.selection.max,
					predicate = { detectedSpan ->
						spanEqualPredicate(span, detectedSpan)
					}
				)
			}
		}
	}

	block(
		currentSelectedIndex
	) { index ->
		val selection = value.selection

		if (index != currentSelectedIndex) {
			// 目前 index 的 SpanStyles 不是当前位置选择的
			// 先 clear 其他的 spanStyles, 再增加当前的 index
			onValueChange(
				value.copy(
					parvenuString = value.parvenuString.copy(
						spanStyles = value.parvenuString.spanStyles.minusSpansInRange(
							start = selection.min,
							endExclusive = selection.max,
							predicate = { span ->
								spanStyleList.any { spanEqualPredicate(span, it) }
							}
						)
					)
				).plusSpanStyle(
					ParvenuString.Range(
						item = spanStyleList[index],
						start = selection.min, end = selection.max,
						startInclusive = false, endInclusive = true
					)
				)
			)
		} else {
			// 目前 index 的 SpanStyles 是当前位置选择的
			onValueChange(
				value.copy(
					parvenuString = value.parvenuString.copy(
						spanStyles = value.parvenuString.spanStyles.minusSpansInRange(
							start = selection.min,
							endExclusive = selection.max,
							predicate = { span ->
								spanEqualPredicate(span, spanStyleList[index])
							}
						)
					)
				)
			)
		}
	}
}
