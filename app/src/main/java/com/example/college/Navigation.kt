package com.example.college

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.GenericFontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.college.ui.theme.Poppins
import com.example.college.ui.theme.Shapes
import kotlin.math.ceil


@Composable
fun Navigation(){
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = Screen.WelcomeScreen.route){
        composable(route = Screen.WelcomeScreen.route){
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.OptionsScreen.route){ OptionsScreen(navController)}
        composable(route=Screen.Attendance.route){ Attendance()}
        composable(Screen.Sgpa.route){ Sgpa()}
        composable(Screen.Bill.route){ Bill()}
    }
}

@Composable
fun WelcomeScreen(navController: NavController){
    Box() {
        Image(
            painter = painterResource(id = R.drawable.calculatorapplandingpage),
            contentDescription ="" ,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

    }
    Column() {
        Text("CheckFast",
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 100.dp),
           color = Color(5, 33, 71, 255),
            style = TextStyle(fontFamily = Poppins)
        )
        Button(
            enabled = true,
            onClick = { navController.navigate(Screen.OptionsScreen.route)  },
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 20.dp)
                .width(150.dp)
                .height(60.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                backgroundColor =  Color(21, 61, 114, 255),
            )
        ) {
            Text(text = "Enter App", fontSize = 20.sp, color = Color.White, style = TextStyle(fontFamily = Poppins))
        }
    }
}

@Composable
fun OptionsScreen(navController: NavController){
Column(modifier = Modifier
    .padding(start = 20.dp, end = 20.dp)
    .verticalScroll(rememberScrollState(), enabled = true),
    verticalArrangement = Arrangement.SpaceEvenly) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(end = 10.dp, start = 10.dp, bottom = 10.dp, top = 40.dp)
        .height(150.dp),
        backgroundColor = Color(233,245,255,100),
        elevation = 0.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.checkcattendancecoverdesignedit),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )
    }
    Button(onClick = { navController.navigate(route = Screen.Attendance.route)},
        modifier = Modifier.align(CenterHorizontally),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor =  Color(21, 61, 114, 255),
        )
    ) {
        Row() {
            Text("Check Now",
                color = Color.White,
                fontSize = 18.sp,
                style = TextStyle(fontFamily = Poppins),

            )
            Image(painter = painterResource(id =  R.drawable.next),
                contentDescription ="" ,
            )
        }
    }
    //Spacer(modifier = Modifier.height(20.dp))
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .height(150.dp),
        backgroundColor = Color(233,245,255,100),
        elevation = 0.dp
    ) {
        Image(
            painter = painterResource(id = R.drawable.plancgpadesignedit),
            contentDescription = "", modifier = Modifier
                .fillMaxWidth(), contentScale = ContentScale.Crop
        )
    }
    Button(onClick = { navController.navigate(route = Screen.Sgpa.route)},
        modifier = Modifier.align(CenterHorizontally),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor =  Color(21, 61, 114, 255),
        )
    ) {
        Row() {
            Text("Plan Now", color = Color.White,
                fontSize = 18.sp,style = TextStyle(fontFamily = Poppins),

            )
            Image(painter = painterResource(id =  R.drawable.next),
                contentDescription ="" ,
            )
        }
    }
    //Spacer(modifier = Modifier.height(20.dp))
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .height(150.dp),
        backgroundColor = Color(233,245,255,100),
        elevation = 0.dp
    ) {
        Image(
            painter = painterResource(id = R.drawable.splitbillscoverdesignedit),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(), contentScale = ContentScale.Crop
        )
    }
    Button(onClick = { navController.navigate(route = Screen.Bill.route)},
        modifier = Modifier.align(CenterHorizontally),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor =  Color(21, 61, 114, 255),
        )
    ) {
        Row() {
            Text("Split Now",
                color = Color.White,
                fontSize = 18.sp,style = TextStyle(fontFamily = Poppins),

            )
            Image(painter = painterResource(id =  R.drawable.next),
                contentDescription ="" ,
            )
        }
    }
}
}

@Composable
fun Attendance(){
    var attended by remember { mutableStateOf("")}
    var totalClasses by remember { mutableStateOf("")}
    val attendedValue = attended.toDoubleOrNull()?:0.0
    val totalClassValue = totalClasses.toDoubleOrNull()?:0.0
    val result= calculateClasses(totalClassValue,attendedValue).toInt()

    Column( modifier = Modifier
        .padding(top = 40.dp, start = 25.dp, end = 25.dp)
        .verticalScroll(
            rememberScrollState(), enabled = true
        )) {
        Card(elevation = 10.dp, shape = Shapes.medium,
            backgroundColor = Color(21, 61, 114, 255),
            modifier = Modifier
                .height(450.dp)
                .align(CenterHorizontally)) {
            Column(modifier = Modifier.padding(all = 15.dp)) {
        Text("Enter number of classes you attended", fontSize = 24.sp, color = Color.White,style = TextStyle(fontFamily = Poppins))
                Spacer(modifier = Modifier.height(15.dp))
        TextField(
            value = attended,
            onValueChange = {attended=it},
            label = {Text(text = "Attended", style = TextStyle(color = Color.Black))},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFE9F5FF),
                textColor = Color(21, 61, 114, 255)
            ),
            textStyle = TextStyle(fontFamily = Poppins, ),
        )
                Spacer(modifier = Modifier.height(75.dp))
        Text("Enter number of classes conducted till now", fontSize = 24.sp,
            color = Color.White,style = TextStyle(fontFamily = Poppins))
                Spacer(modifier = Modifier.height(15.dp))
        TextField(
            value = totalClasses,
            onValueChange ={totalClasses=it},
            label = {Text(text="Conducted", style = TextStyle(color = Color.Black))},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFE9F5FF)
            ),
            textStyle = TextStyle(fontFamily = Poppins)
        )
    }
        }
        Spacer(modifier = Modifier.height(40.dp))
        if(result>=0) {
            Text(
                text = "You need to attend",
                fontSize = 24.sp,
                modifier = Modifier.align(CenterHorizontally),
                style = TextStyle(fontFamily = Poppins)
            )
            Text(
                text = "$result",
                fontSize = 36.sp,
                modifier = Modifier.align(CenterHorizontally),
                style = TextStyle(fontFamily = Poppins)
            )
            Text(
                "More Classes",
                fontSize = 24.sp,
                modifier = Modifier.align(CenterHorizontally),
                style = TextStyle(fontFamily = Poppins)
            )
        }else{
            Text(
                "Your attendance is 75% above",
                fontSize = 24.sp,
                modifier = Modifier.align(CenterHorizontally),
                style = TextStyle(fontFamily = Poppins)
            )
        }
    }
}

private fun calculateClasses(totalClasses: Double, attended: Double): Double {
    return ceil(((0.75 * totalClasses) - attended) /0.25)
}

@Composable
fun Sgpa(){
    var goal by remember { mutableStateOf("")}
    var current by remember { mutableStateOf("")}
    var sem by remember { mutableStateOf("")}
    val goalValue = goal.toDoubleOrNull()?:0.0
    val currentValue = current.toDoubleOrNull()?:0.0
    val semValue = sem.toDoubleOrNull()?:0.0
    val result= sgpa_calc(goalValue,currentValue,semValue.toInt())

    Column( modifier = Modifier
        .padding(top = 50.dp, start = 20.dp, end = 20.dp)
        .verticalScroll(
            rememberScrollState(),
            enabled = true
        )) {
        Card(elevation = 10.dp,
            shape = Shapes.medium,
            backgroundColor = Color(21, 61, 114, 255),
            modifier = Modifier
                .height(520.dp)
                .align(CenterHorizontally)) {
            Column(modifier = Modifier.padding(all = 15.dp)) {
                Text("Enter your goal", fontSize = 24.sp, color = Color.White, style = TextStyle(fontFamily = Poppins))
                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    value = goal,
                    onValueChange = {goal=it},
                    label = {Text(text = "Goal Cgpa", style = TextStyle(fontFamily = Poppins,
                        color = Color.Black))},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(0xFFE9F5FF)
                    ),
                    textStyle = TextStyle(fontFamily = Poppins)
                )
               Spacer(modifier = Modifier.height(40.dp))
                Text("Enter current cgpa", fontSize = 24.sp, color = Color.White,style = TextStyle(fontFamily = Poppins))
                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    value = current,
                    onValueChange ={current=it},
                    label = {Text(text="Current Cgpa",style = TextStyle(fontFamily = Poppins, color = Color.Black))},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(0xFFE9F5FF)
                    ),
                    textStyle = TextStyle(fontFamily = Poppins)
                )
               Spacer(modifier = Modifier.height(40.dp))
                Text("Enter your previous semester", fontSize = 24.sp, color = Color.White,style = TextStyle(fontFamily = Poppins))
                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    value = sem,
                    onValueChange ={sem=it},
                    label = {Text(text="Semester number",style = TextStyle(fontFamily = Poppins, color = Color.Black))},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(0xFFE9F5FF)
                    ),
                    textStyle = TextStyle(fontFamily = Poppins)
                )
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        Text(result, fontSize = 24.sp, style = TextStyle(fontFamily = Poppins))
    }
}

private fun sgpa_calc(goal_cgpa: Double,current_cgpa: Double, prev_sem_number: Int): String {

    // goal_cgpa --> The CGPA which student want to achieve after next semester
    // current_cgpa --> The CGPA which student has scored now
    // prev_sem_number --> Previous Semester Number

    var value = 0.00
    while ((((prev_sem_number*current_cgpa) + value)/(prev_sem_number+1)) < goal_cgpa) {
        value += 0.01
    }

    if (value >= 10.00) {
        return "Not possible to achieve goal in this Semester"
    }

    value = Math.round(value * 100.0) / 100.0
    value = Math.floor(value)
    return "You will need to score $value this semester to get $goal_cgpa CGPA"
}

@Composable
fun Bill(){
    var bill by remember { mutableStateOf("")}
    var friends by remember { mutableStateOf("")}
    val billValue = bill.toDoubleOrNull()?:0.0
    var freindsCount = friends.toDoubleOrNull()?:0.0
    if(freindsCount<=0.0){
        freindsCount=0.0;
    }
    var result= (billValue/freindsCount)
    if (result>billValue){result= 0.0
    }
    if(billValue<=0.0){result=0.0}
    Column(
        modifier = Modifier
            .padding(top = 75.dp, start = 40.dp, end = 25.dp)
            .verticalScroll(
                rememberScrollState(),
                enabled = true
            ),
    ) {
        Card(elevation = 10.dp, shape = Shapes.medium,
            backgroundColor = Color(21, 61, 114, 255),
            modifier = Modifier
                .height(450.dp)
                .align(CenterHorizontally)) {
            Column(modifier = Modifier.padding(all = 15.dp)) {
                Text("Enter total bill amount", fontSize = 24.sp, color = Color.White,
                    style = TextStyle(fontFamily = Poppins))
                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    value = bill,
                    onValueChange = {bill=it},
                    label = {Text(text = "Amount",style = TextStyle(fontFamily = Poppins, color = Color.Black))},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(0xFFE9F5FF)
                    ),
                    textStyle = TextStyle(fontFamily = Poppins, color = Color(21, 61, 114, 255))
                )
                Spacer(modifier = Modifier.height(75.dp))
                Text("Enter friends count", fontSize = 24.sp, color = Color.White,
                    style = TextStyle(fontFamily = Poppins))
                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    value = friends,
                    onValueChange ={friends=it},
                    label = {Text(text="Count",style = TextStyle(fontFamily = Poppins, color = Color.Black))},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color(0xFFE9F5FF)
                    ),
                    textStyle = TextStyle(fontFamily = Poppins)
                )
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "You need to pay",
            fontSize = 24.sp,
            modifier = Modifier.align(CenterHorizontally),
            style = TextStyle(fontFamily = Poppins)
        )
        Text(text = "$result", fontSize = 36.sp, modifier = Modifier.align(CenterHorizontally),
            style = TextStyle(fontFamily = Poppins))
        Text("from your pocket", fontSize = 24.sp, modifier = Modifier.align(CenterHorizontally),
            style = TextStyle(fontFamily = Poppins))
    }
}