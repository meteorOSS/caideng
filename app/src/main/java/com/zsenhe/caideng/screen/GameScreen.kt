import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.zsenhe.caideng.R
import com.zsenhe.caideng.data.SampleData

/**
 * 游戏界面
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(navController: NavController) {
    val context = LocalContext.current
    val riddles = remember { SampleData.riddles.shuffled() }
    var currentRiddle by remember { mutableStateOf(riddles.first()) }
    var userAnswer by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var dialogContent by remember { mutableStateOf("") }

    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }

    // 是否开启调试模式(方便大作业演示)
    var enableDebug by remember {
        mutableStateOf(false)
    }

    // 累积回答正确次数
    var power by remember {
        mutableStateOf(0)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            RiddleBox(riddle = currentRiddle.question,8,16,100,16)
            RiddleBox(riddle = currentRiddle.type, 8,16,30,18)
            OutlinedTextField(
                value = userAnswer,
                onValueChange = { userAnswer = it },
                label = { Text(
                    if(enableDebug) "偷偷告诉你: 答案是 "+currentRiddle.answer
                    else "输入答案"
                ) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Text("累积回答正确: "+power)

            Button(
                onClick = {
                    val isCorrect = userAnswer.trim().equals(currentRiddle.answer, ignoreCase = true)
                    dialogContent = if (isCorrect)
                        "妙哉妙哉！君子之智，果然非凡。此谜被破，更添夜色几分光华。" else
                        "灯谜难破，亦是情常。才子勿忧，智者不惑，再细思量"
                    power = if(isCorrect) power+1 else 0; // 处理累积猜谜
                    showDialog = true // modal窗状态
                    if (isCorrect) {
                        mediaPlayer = playSound(context, R.raw.success)
                        currentRiddle = riddles.random()
                    }else {
                        mediaPlayer = playSound(context, R.raw.fail)
                    }
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("提交答案")
            }

            Button(
                onClick = {
                          enableDebug = !enableDebug;
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(if (enableDebug) "关闭答案显示" else "开启答案显示")
            }

            Button(
                onClick = {
                          navController.navigate("main")
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("返回")
            }


            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = {
                            Text(
                                text = if(power==0) "连胜中断" else "当前连续猜对了 "+power+" 道"
                            )
                    },
                    text = {
                        Text(
                            text = dialogContent,
                            fontSize = 22.sp ,
                        )
                    },
                    confirmButton = {
                        Button(onClick = {
                            showDialog = false
                            mediaPlayer?.stop()
                            mediaPlayer?.release()
                            userAnswer = ""
                            Log.d("sound", "stop play sound")
                        }
                        ) {
                            Text("确定")
                        }
                    }
                )
            }
        }
    }
}


// 播放声音
fun playSound(context: Context, soundId: Int) : MediaPlayer {
    val mediaPlayer = MediaPlayer.create(context, soundId)
    mediaPlayer.setOnCompletionListener { mp -> mp.release() }
    mediaPlayer.start()
    Log.d("sound","start play sound")
    return mediaPlayer
}

// 绘制矩形文本框
@Composable
fun RiddleBox(riddle: String,border: Int,padding: Int,height: Int,fontSize: Int) {
    Text("") //这就是安卓的nbps吗
    Box(
        modifier = Modifier
            .border(1.dp, Color.Gray, RoundedCornerShape(border.dp))
            .padding(padding.dp)
            .fillMaxWidth()
            .heightIn(min = height.dp, max = (height * 2).dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = riddle,
            fontSize = fontSize.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
