package com.tuan.borutoheroes.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.tuan.borutoheroes.R
import com.tuan.borutoheroes.ui.theme.BorutoHeroesTheme
import com.tuan.borutoheroes.ui.theme.SMALL_PADDING
import com.tuan.borutoheroes.ui.theme.welcomeScreenTitleColor

@Composable
fun OrderedList(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    items: List<String>,
    textColor: Color
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = title),
            color = textColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(height = SMALL_PADDING))
        items.forEachIndexed { index, item ->
            Text(
                modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                text = "${index + 1}. $item",
                color = textColor,
                fontSize = MaterialTheme.typography.body1.fontSize,
            )
        }
    }
}

@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun OrderedListPreview() {
    BorutoHeroesTheme {
        OrderedList(
            title = R.string.family,
            items = listOf(
                "Fugaku",
                "Mikoto",
                "Itachi",
                "Sarada",
                "Sakura"
            ),
            textColor = MaterialTheme.colors.welcomeScreenTitleColor
        )
    }
}