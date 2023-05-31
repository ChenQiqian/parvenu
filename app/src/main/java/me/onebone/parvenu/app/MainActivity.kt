package me.onebone.parvenu.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.serialization.json.Json
import me.onebone.parvenu.*

class MainActivity : ComponentActivity() {
	private val json = Json { ignoreUnknownKeys = true }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			var serialized by remember { mutableStateOf("") }
			var editorValue by remember { mutableStateOf(
				ParvenuEditorValue(
					parvenuString = ParvenuString(
						text = "normal bold both",
						spanStyles = listOf(
							ParvenuString.Range(
								item = SpanStyle(fontWeight = FontWeight.Bold),
								start = 7, end = 11,
								startInclusive = false,
								endInclusive = true
							)
						),
						paragraphStyles = listOf(
							ParvenuString.Range(
								item = ParagraphStyle(textIndent = TextIndent(16.sp, 16.sp)),
								start = 12, end = 16,
								startInclusive = true,
								endInclusive = true
							)
						)
					),
					selection = TextRange.Zero,
					composition = null
				)
			) }

			Column(modifier = Modifier.fillMaxSize()) {
				Row(
					modifier = Modifier
						.fillMaxWidth()
						.padding(8.dp),
					horizontalArrangement = Arrangement.spacedBy(8.dp)
				) {
					ParvenuSpanToggle(
						value = editorValue,
						onValueChange = {
							editorValue = it
						},
						spanFactory = { SpanStyle(fontStyle = FontStyle.Italic) },
						spanEqualPredicate = { style ->
							style.fontStyle == FontStyle.Italic
						}
					) { enabled, onToggle ->
						Button(
							modifier = Modifier
								.alpha(if (enabled) 1f else 0.3f),
							onClick = { onToggle() }
						) {
							Text(text = "italic")
						}
					}

					ParvenuSpanToggle(
						value = editorValue,
						onValueChange = {
							editorValue = it
						},
						spanFactory = { SpanStyle(fontWeight = FontWeight.Bold) },
						spanEqualPredicate = { style ->
							style.fontWeight == FontWeight.Bold
						}
					) { enabled, onToggle ->
						Button(
							modifier = Modifier
								.alpha(if (enabled) 1f else 0.3f),
							onClick = { onToggle() }
						) {
							Text(text = "bold")
						}
					}

					ParvenuParagraphToggle(
						value = editorValue,
						onValueChange = {
							editorValue = it
						},
						paragraphFactory = { ParagraphStyle(textIndent = TextIndent(16.sp, 16.sp)) },
						paragraphEqualPredicate = { style ->
							style.textIndent?.firstLine == 16.sp
						}
					) { enabled, onToggle ->
						Button(
							modifier = Modifier
								.alpha(if (enabled) 1f else 0.3f),
							onClick = { onToggle() }
						) {
							Text(text = "indent")
						}
					}
				}
				Row (
					modifier = Modifier
						.fillMaxWidth()
						.padding(8.dp),
					horizontalArrangement = Arrangement.spacedBy(8.dp)
				) {
					Button(
						onClick = {
							serialized = json.encodeToString(
								ParvenuString.serializer(),
								editorValue.parvenuString
							)
						}
					) {
						Text("Serialize")
					}
					Button(
						onClick = {
							editorValue = editorValue.copy(
								parvenuString =
								json.decodeFromString(
									ParvenuString.serializer(),
									serialized
								)
							)
						}
					) {
						Text("Deserialize")
					}
				}


				ParvenuEditor(
					value = editorValue,
					onValueChange = {
						editorValue = it
					}
				) { value, onValueChange ->
					TextField(
						modifier = Modifier
							.fillMaxWidth()
							.padding(16.dp),
						value = value,
						onValueChange = onValueChange,
						textStyle = TextStyle(fontSize = 21.sp)
					)
				}
				TextField(
					value = serialized,
					onValueChange = { serialized = it },
					modifier = Modifier.padding(16.dp).fillMaxWidth()
				)
			}
		}
	}
}
