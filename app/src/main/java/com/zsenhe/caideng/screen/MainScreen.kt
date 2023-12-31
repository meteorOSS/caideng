import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zsenhe.caideng.R

/**
 * 主界面
 */
@SuppressLint("ResourceType")
@Composable
fun MainScreen(navController: NavController) {
    val logo = painterResource(id = R.raw.logo)
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Image(
                painter = logo,
                contentDescription = "Logo",
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)
            )



            LargeButton(
                onClick = { navController.navigate("game") },
                text = "开始游戏"
            )

            Spacer(modifier = Modifier.height(16.dp))

            LargeButton(
                onClick = { navController.navigate("about") },
                text = "关于"
            )

            Spacer(modifier = Modifier.height(16.dp))
            
            Text(text = "答案如灯火通明，宛若半卷诗书中，隐隐约约的墨香飘过，留下片刻的温暖")
        }
    }
}

@Composable
fun LargeButton(onClick: () -> Unit, text: String) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp) // 提高按钮的高度
    ) {
        Text(text, style = MaterialTheme.typography.titleMedium)
    }
}
