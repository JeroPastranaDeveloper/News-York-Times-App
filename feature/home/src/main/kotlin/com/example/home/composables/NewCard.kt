package com.example.home.composables

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.designsystem.components.newSharedElement
import com.example.model.New
import com.example.navigation.boundsTransform
import com.kmpalette.palette.graphics.Palette
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.PalettePlugin
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
fun SharedTransitionScope.NewCard(
    animatedVisibilityScope: AnimatedVisibilityScope,
    new: New,
    onClick: () -> Unit,
) {
    var palette by remember { mutableStateOf<Palette?>(null) }
    val (backgroundColor, textColor) = palette.paletteBackgroundColor().value
    Card(
        modifier = Modifier
            .newSharedElement(
                isLocalInspectionMode = LocalInspectionMode.current,
                state = rememberSharedContentState(key = "image-${new.articleUrl}"),
                animatedVisibilityScope = animatedVisibilityScope,
                boundsTransform = boundsTransform,
            )
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardColors(
            containerColor = backgroundColor,
            contentColor = backgroundColor,
            disabledContainerColor = backgroundColor,
            disabledContentColor = backgroundColor,
        )
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = new.title,
                color = textColor,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = new.description, color = textColor, fontSize = 12.sp)

            val image = new.imageUrl

            if (image.isNotEmpty()) {
                GlideImage(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    imageModel = { image },
                    imageOptions = ImageOptions(contentScale = ContentScale.Inside),
                    component = rememberImageComponent {
                        +CrossfadePlugin()
                        +ShimmerPlugin(
                            Shimmer.Resonate(
                                baseColor = Color.Transparent,
                                highlightColor = Color.LightGray,
                            ),
                        )

                        if (!LocalInspectionMode.current) {
                            +PalettePlugin(
                                imageModel = image,
                                useCache = true,
                                paletteLoadedListener = { palette = it }
                            )
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = new.author,
                color = textColor,
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}