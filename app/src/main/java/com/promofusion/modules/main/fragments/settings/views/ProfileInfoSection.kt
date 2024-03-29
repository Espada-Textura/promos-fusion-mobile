package com.promofusion.modules.main.fragments.settings.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ProfileInfoSection (
    Name: String,
    Email: String,
) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = "https://cdn.discordapp.com/attachments/1027209533220728867/1195531835879473223/user.png?ex=65b454c9&is=65a1dfc9&hm=800835208310ad880a8b79120056fbc1ec7b4ea5a644247168ca71a517be1ddc&",
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(168.dp)
                .clip(CircleShape)
        )
        Text(
            text = Name,
            fontWeight = FontWeight.ExtraBold,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
                .padding(0.dp, 8.dp, 0.dp, 0.dp)
        )
        Text(
            text = Email,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.scrim,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
                .padding(0.dp, 8.dp)
        )
    }
}
