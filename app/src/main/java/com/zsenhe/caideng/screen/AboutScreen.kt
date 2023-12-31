package com.zsenhe.caideng.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import android.content.Intent
import android.net.Uri
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.NavController

/**
 * 关于界面
 */
@Composable
fun AboutScreen(navController: NavController) {
    val uriHandler = LocalUriHandler.current
    val annotatedString = buildAnnotatedString {
        append("该应用仅作为作业展示使用(22软件8班郑森鹤)，灯谜等数据皆是硬编码。\n\n")
        append("设计尽可能符合官方的规范，且注释详细，可作为学习参考使用。\n\n")
        withStyle(style = SpanStyle(color = Color.Blue, fontSize = 16.sp)) {
            append("GitHub仓库地址: ")
            pushStringAnnotation(tag = "URL", annotation = "https://github.com/meteorOSS/caideng")
            withStyle(style = SpanStyle(color = Color.Red, fontSize = 16.sp)) {
                append("点击访问")
            }
            pop()
        }
        append("\n\n感谢菘蓝姐的配音,太棒了!")
        append("\n\n一行xml都不写，这就是jetpackcompose带给我的骄傲!")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = annotatedString,
            modifier = Modifier.clickable {
                annotatedString.getStringAnnotations(tag = "URL", start = 0, end = annotatedString.length).firstOrNull()?.let {
                    uriHandler.openUri(it.item)
                }
            },
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(24.dp)) // 添加一些空间

        // 返回主界面的按钮
        Button(onClick = { navController.navigate("main") }) {
            Text(text = "返回主界面")
        }
    }
}
