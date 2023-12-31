package com.zsenhe.caideng.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zsenhe.caideng.R
import kotlinx.coroutines.delay


/**
 * 加载动画
 */
@SuppressLint("ResourceType")
@Composable
fun SplashScreen(onSplashEnded: () -> Unit) {

    val logo = painterResource(id = R.raw.logo)


    LaunchedEffect(key1 = true) {
        delay(2000)
        onSplashEnded() // 回调主界面
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = logo,
            contentDescription = "Logo",
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.Center)
        )


    }
}
