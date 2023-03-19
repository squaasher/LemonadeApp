package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                    LemonadeApp()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LemonadeApp(){
    var stateCount by remember{
        mutableStateOf( 1 )
    }
    var squeezeCount by remember{
        mutableStateOf(0)
    }
    when (stateCount){
        1 -> LemonadeWithTextAndImages(
            R.string.select_lemon,
            R.drawable.lemon_tree,
            onImageClick = {stateCount++
                           squeezeCount = (2..4).random()},
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
        )
        2 -> LemonadeWithTextAndImages(
            R.string.squeeze_lemon,
            R.drawable.lemon_squeeze,
            onImageClick = {
                squeezeCount--
                if(squeezeCount == 0){
                    stateCount++
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
        )
        3 -> LemonadeWithTextAndImages(
            R.string.drink_lemonade,
            R.drawable.lemon_drink,
            onImageClick = {stateCount++},
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
        )
        4 -> LemonadeWithTextAndImages(
            R.string.restart,
            R.drawable.lemon_restart,
            onImageClick = {stateCount = 1},
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
        )
    }
}

@Composable
fun LemonadeWithTextAndImages(
    textResource: Int,
    imageId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(textResource),
            fontSize = 18.sp,
            modifier = Modifier.
                    padding(bottom = 30.dp)
        )

        Image(
            painter = painterResource(imageId),
            contentDescription = null,
            modifier = Modifier
                .clickable(
                onClick = onImageClick
                )
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
        )
    }
}