package com.tuan.borutoheroes.presentation.components


import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tuan.borutoheroes.R
import com.tuan.borutoheroes.ui.theme.BorutoHeroesTheme
import com.tuan.borutoheroes.ui.theme.INFO_ICON_SIZE
import com.tuan.borutoheroes.ui.theme.SMALL_PADDING
import com.tuan.borutoheroes.ui.theme.welcomeScreenTitleColor

@Composable
fun InfoBox(
    @DrawableRes icon: Int,
    iconColor: Color,
    bigText: String,
    @StringRes smallText: Int,
    textColor: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(end = SMALL_PADDING)
                .size(size = INFO_ICON_SIZE),
            painter = painterResource(id = icon),
            contentDescription = stringResource(id = R.string.info_box_icon),
            tint = iconColor
        )
        Column {
            Text(
                text = bigText,
                color = textColor,
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Black
            )
            Text(
                modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                text = stringResource(id = smallText),
                color = textColor,
                fontSize = MaterialTheme.typography.overline.fontSize
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
private fun InfoBoxPreview() {
    BorutoHeroesTheme {
        InfoBox(
            icon = R.drawable.ic_bolt,
            bigText = "69",
            smallText = R.string.power,
            iconColor = MaterialTheme.colors.primary,
            textColor = MaterialTheme.colors.welcomeScreenTitleColor
        )
    }
}