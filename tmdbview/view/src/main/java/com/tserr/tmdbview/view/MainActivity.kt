package com.tserr.tmdbview.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import com.tserr.tmdbview.view.navigation.TmdbViewNavHost
import com.tserr.tmdbview.view.ui.theme.TMDBViewTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TmdbView()
        }
    }
}

@Composable
fun TmdbView() {
    TMDBViewTheme {
        TmdbViewNavHost()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TmdbView()
}


/*
@Composable
fun Greeting(name: String) {
    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(if (isSelected) Color.Red else Color.Transparent)
    Text(
        text = "Hello $name!",
        modifier = Modifier
            .padding(24.dp)
            .background(color = backgroundColor)
            .clickable { isSelected = !isSelected }
    )
}

@Composable
fun ScreenSample(names: List<String> = List(1000) { "Hello Compose #$it" }) {
    val counterState = remember { mutableStateOf(0) }
    Column(modifier = Modifier.fillMaxHeight()) {
        NameList(names = names, modifier = Modifier.weight(1f))
        Counter(counterState.value) { newCount -> counterState.value = newCount }
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(names.size) { index ->
            Greeting(name = names[index])
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    Button(
            onClick = { updateCount(count + 1) },
            modifier = Modifier.padding(12.dp),
            colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (count > 10) Color.Green else Color.Cyan
            )
    ) {
        Text(text = "I've been clicked $count times",  style = MaterialTheme.typography.h1)
    }

}
*/
