package com.tuan.borutoheroes.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.tuan.borutoheroes.R
import com.tuan.borutoheroes.domain.model.Hero
import com.tuan.borutoheroes.ui.theme.*
import com.tuan.borutoheroes.util.Constants.ABOUT_TEXT_MAX_LINES

@Composable
fun BottomSheetContent(
    hero: Hero,
    infoBoxIconColor: Color = MaterialTheme.colors.primary,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.welcomeScreenTitleColor
) {
    Column(
        modifier = Modifier
            .background(color = sheetBackgroundColor)
            .padding(all = LARGE_PADDING)
    ) {
        BottomSheetHeader(
            hero = hero,
            contentColor = contentColor
        )
        BottomSheetInfoBoxes(
            hero = hero,
            infoBoxIconColor = infoBoxIconColor,
            contentColor = contentColor
        )
        BottomSheetAboutText(
            hero = hero,
            contentColor = contentColor
        )
    }
}

@Composable
private fun BottomSheetHeader(
    hero: Hero,
    contentColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = LARGE_PADDING),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(end = SMALL_PADDING)
                .size(INFO_ICON_SIZE),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = stringResource(id = R.string.app_logo),
            tint = contentColor
        )
        Text(
            text = hero.name,
            color = contentColor,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun BottomSheetInfoBoxes(
    hero: Hero,
    infoBoxIconColor: Color,
    contentColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = MEDIUM_PADDING),
        horizontalArrangement = SpaceBetween
    ) {
        InfoBox(
            icon = R.drawable.ic_bolt,
            iconColor = infoBoxIconColor,
            bigText = "${hero.power}",
            smallText = R.string.power,
            textColor = contentColor
        )
        InfoBox(
            icon = R.drawable.ic_calendar,
            iconColor = infoBoxIconColor,
            bigText = hero.month,
            smallText = R.string.month,
            textColor = contentColor
        )
        InfoBox(
            icon = R.drawable.ic_cake,
            iconColor = infoBoxIconColor,
            bigText = hero.day,
            smallText = R.string.birthday,
            textColor = contentColor
        )
    }
}

@Composable
private fun BottomSheetAboutText(
    hero: Hero,
    contentColor: Color
) {
    Column {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.about),
            color = contentColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .alpha(alpha = ContentAlpha.medium)
                .padding(bottom = MEDIUM_PADDING),
            text = hero.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines = ABOUT_TEXT_MAX_LINES,
            overflow = TextOverflow.Ellipsis
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = SpaceBetween
        ) {
            OrderedList(
                modifier = Modifier.weight(weight = 1f),
                title = R.string.family,
                items = hero.family,
                textColor = contentColor
            )
            OrderedList(
                modifier = Modifier.weight(weight = 1f),
                title = R.string.abilities,
                items = hero.abilities,
                textColor = contentColor
            )
            OrderedList(
                modifier = Modifier.weight(weight = 1f),
                title = R.string.nature_types,
                items = hero.natureTypes,
                textColor = contentColor
            )
        }
    }
}

@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun BottomSheetContentPreview() {
    BorutoHeroesTheme {
        BottomSheetContent(
            hero = Hero(
                id = 1,
                name = "Sasuke",
                image = "/images/sasuke.jpg",
                about = "Sasuke Uchiha (うちはサスケ, Uchiha Sasuke) is one of the last surviving members of Konohagakure's Uchiha clan. After his older brother, Itachi, slaughtered their clan, Sasuke made it his mission in life to avenge them by killing Itachi. He is added to Team 7 upon becoming a ninja and, through competition with his rival and best friend, Naruto Uzumaki.",
                rating = 5.0,
                power = 98,
                month = "July",
                day = "23rd",
                family = listOf(
                    "Fugaku",
                    "Mikoto",
                    "Itachi",
                    "Sarada",
                    "Sakura"
                ),
                abilities = listOf(
                    "Sharingan",
                    "Rinnegan",
                    "Sussano",
                    "Amateratsu",
                    "Intelligence"
                ),
                natureTypes = listOf(
                    "Lightning",
                    "Fire",
                    "Wind",
                    "Earth",
                    "Water"
                )
            )
        )
    }
}