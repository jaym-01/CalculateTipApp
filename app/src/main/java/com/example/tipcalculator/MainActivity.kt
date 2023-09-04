package com.example.tipcalculator

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import java.lang.NumberFormatException
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowApp() {
    MainApp()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(modifier: Modifier = Modifier) {

    var amount: String by remember {
        mutableStateOf("")
    }

    var tip: String by remember{
        mutableStateOf("")
    }

    var switchState: Boolean by remember {
        mutableStateOf(false)
    }

    Column(

        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(40.dp), verticalArrangement = Arrangement.Center) {

        Text(text = stringResource(R.string.calculate_tip), Modifier.padding(bottom = 20.dp))
        TextField(value = amount, onValueChange = {it:String -> amount = it}, modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text= stringResource(R.string.total_cost))
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            leadingIcon = {
                androidx.compose.material3.Icon(painter = painterResource(id = R.drawable.money_24px), contentDescription = stringResource(id = R.string.money_icon))

            })
        TipDiscount(txtVal = tip, valChange = {
            tip = it
        })
        Round(switchState, {
            switchState = it
        })
        Text(text = stringResource(R.string.tip_amount_1, calculateTip(amount, tip, switchState)), fontSize = 35.sp,
            lineHeight = 38.sp,
            textAlign = TextAlign.Center, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp))
    }
}

fun calculateTip(amount: String, tip: String, round: Boolean): String {
    var new_amount: Double = amount.toDoubleOrNull() ?: 0.0
    var new_tip: Double = tip.toDoubleOrNull() ?: 0.0

    var tmpTip: Double = new_amount * (new_tip/100)

    if(round){
        tmpTip = ceil(tmpTip)
    }

    return NumberFormat.getCurrencyInstance().format(tmpTip)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipDiscount(txtVal:String, valChange:(String) -> Unit, modifier: Modifier = Modifier) {
    TextField(value = txtVal,
        onValueChange = valChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        label = { Text(text = stringResource(R.string.tip_percentage))},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
        leadingIcon = {
            androidx.compose.material3.Icon(
                painter = painterResource(id = R.drawable.percent_24px),
                contentDescription = stringResource(R.string.percentage_icon)
            )
        })
}

@Composable
fun Round(state: Boolean, stateChange: (Boolean) -> Unit, modifier: Modifier = Modifier) {
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(top = 20.dp)) {
        Text(text = stringResource(R.string.round_up_tip), fontSize = 15.sp, modifier = modifier.padding(12.dp))
        Switch(checked = state, onCheckedChange = stateChange, modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End))
    }
}