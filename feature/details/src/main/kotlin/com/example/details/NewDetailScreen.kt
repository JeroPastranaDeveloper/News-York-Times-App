package com.example.details

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.designsystem.components.newSharedElement
import com.example.designsystem.theme.NewsTheme
import com.example.details.NewDetailViewContract.UiIntent
import com.example.details.NewDetailViewContract.UiState
import com.example.model.NewDetail
import com.example.navigation.boundsTransform
import com.example.navigation.currentComposeNavigator
import com.example.utils.formatToDatePattern
import com.kmpalette.palette.graphics.Palette
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.PalettePlugin
import org.koin.androidx.compose.koinViewModel
import com.example.core.designsystem.R as RR
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

@Composable
fun SharedTransitionScope.NewDetailScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: NewDetailViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState(UiState())

    val context = LocalContext.current

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            DetailsHeader(
                animatedVisibilityScope,
                state.new,
                onShareClick = { viewModel.sendIntent(UiIntent.OnSharePressed) },
                onQrShareClick = { viewModel.sendIntent(UiIntent.OnQrSharePressed) })
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(32.dp),
            onClick = { viewModel.sendIntent(UiIntent.OnFavoritePressed) }
        ) {
            Icon(
                painter = painterResource(
                    if (state.new.isFavorite) RR.drawable.ic_star_full
                    else RR.drawable.ic_star_empty
                ),
                contentDescription = "Add"
            )
        }

        // TODO: EXPLOTA

        val qrBitmap = createQRBitmap(state.new.webUrl)

        qrBitmap?.let { bitmap ->
            Image(bitmap = bitmap.asImageBitmap(), contentDescription = null)
        }

        if (state.showQrShareDialog) {
            LaunchedEffect(Unit) {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        context.getString(RR.string.share_article_message, state.new.webUrl)
                    )
                    type = "text/plain"
                }
                context.startActivity(
                    Intent.createChooser(shareIntent, "share new")
                )

                viewModel.sendIntent(UiIntent.CloseShareDialog)
            }
        }

        if (state.showShareDialog) {
            LaunchedEffect(Unit) {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        context.getString(RR.string.share_article_message, state.new.webUrl)
                    )
                    type = "text/plain"
                }
                context.startActivity(
                    Intent.createChooser(shareIntent, "share new")
                )

                viewModel.sendIntent(UiIntent.CloseShareDialog)
            }
        }
    }
}

private fun createQRBitmap(data: String?): Bitmap? {
    return try {
        val result = QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, 324, 324)
        val bitmap: Bitmap =
            Bitmap.createBitmap(result.width, result.height, Bitmap.Config.ARGB_8888)
        for (y in 0 until result.height) {
            for (x in 0 until result.width) {
                if (result[x, y]) {
                    bitmap.setPixel(x, y, Color.BLACK)
                }
            }
        }
        bitmap
    } catch (e: WriterException) {
        Bitmap.createBitmap(324, 324, Bitmap.Config.ARGB_8888)
    }
}

@Composable
private fun SharedTransitionScope.DetailsHeader(
    animatedVisibilityScope: AnimatedVisibilityScope,
    new: NewDetail,
    onShareClick: () -> Unit = {},
    onQrShareClick: () -> Unit = {}
) {
    val composeNavigator = currentComposeNavigator
    var palette by remember { mutableStateOf<Palette?>(null) }
    val shape = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomStart = 32.dp,
        bottomEnd = 32.dp,
    )

    val (backgroundBrush, iconColor) = palette.paletteBackgroundBrush().value

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(290.dp)
                .shadow(elevation = 8.dp, shape = shape)
                .background(brush = backgroundBrush, shape = shape),
        ) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .statusBarsPadding(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.clickable {
                        composeNavigator.navigateUp()
                    },
                    painter = painterResource(id = RR.drawable.ic_arrow),
                    tint = NewsTheme.colors.white,
                    contentDescription = null,
                )

                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = new.author,
                    color = NewsTheme.colors.white,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = new.pubDate.formatToDatePattern(),
                    color = NewsTheme.colors.white,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }

            GlideImage(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp, top = 64.dp)
                    .size(250.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .newSharedElement(
                        isLocalInspectionMode = LocalInspectionMode.current,
                        state = rememberSharedContentState(key = "image-${new.webUrl}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = boundsTransform,
                    ),
                imageModel = { new.imageUrl },
                imageOptions = ImageOptions(contentScale = ContentScale.Inside),
                component = rememberImageComponent {
                    +CrossfadePlugin()

                    if (!LocalInspectionMode.current) {
                        +PalettePlugin(
                            imageModel = new.imageUrl,
                            useCache = true,
                            paletteLoadedListener = { palette = it },
                        )
                    }
                }
            )
            Icon(
                painter = painterResource(id = RR.drawable.baseline_share_24),
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(top = 32.dp, end = 24.dp, bottom = 16.dp)
                    .clickable { onShareClick() }
            )

            Icon(
                painter = painterResource(id = RR.drawable.baseline_qr_code_24),
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(top = 32.dp, start = 24.dp, bottom = 16.dp)
                    .clickable { onQrShareClick() }
            )
        }

        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = new.abstract)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = new.leadParagraph)
        }
    }
}
