package com.purnendu.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class InstagramUIClone : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            Column(modifier = Modifier.fillMaxSize()) {

                TopBar(name = "Purnendu")
                Spacer(modifier = Modifier.height(4.dp))
                ProfileSection()

            }

        }

    }

    @Composable
    fun TopBar(
        name: String,
        modifier: Modifier = Modifier
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack, contentDescription = "back button",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )

            Text(
                text = name,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_bell),
                contentDescription = "Notification",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_menu), contentDescription = "Menu",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )


        }

    }

    @Composable
    fun ProfileSection(
        modifier: Modifier = Modifier
    ) {
        Column(modifier = modifier.fillMaxWidth()) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                RoundImage(
                    painter = painterResource(id = R.drawable.my_pic),
                    modifier = Modifier
                        .size(100.dp)
                        //.weight(3f)
                )

               StatSection()

            }

        }

    }

    @Composable
    fun RoundImage(
        painter: Painter,
        modifier: Modifier = Modifier
    ) {
        Image(
            painter = painter, contentDescription = "Image",
            modifier = modifier
                .aspectRatio(1f, matchHeightConstraintsFirst = true)
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = CircleShape
                )
                .padding(5.dp)
                .clip(CircleShape)
        )

    }

    @Composable
    fun StatSection(
        modifier: Modifier = Modifier,
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileStat(numberText = "601", text = "Posts")
            ProfileStat(numberText = "99.8K", text = "Followers")
            ProfileStat(numberText = "72", text = "Following")


        }

    }

    @Composable
    fun ProfileStat(
        numberText: String,
        text: String,
        modifier: Modifier = Modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
        ) {
            Text(
                text = numberText,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = text,
                fontSize = 15.sp,
            )

        }

    }

}