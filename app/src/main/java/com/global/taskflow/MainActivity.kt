package com.global.taskflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TaskFlowDashboard()
                }
            }
        }
    }
}
/** * TaskFlowDashboard orchestrates our primary screen layout, housing * our interactive, state-driven task items. */
@Composable
fun TaskFlowDashboard() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAFB))
            .padding(16.dp)
    ) {
        Text(
            text = "TaskFlow Dashboard",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF111827)
        )
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        // Deploying our interactive, stateful task components InteractiveTaskCard(title = "Review Code Architecture", category = "Engineering")
    Spacer(modifier = Modifier.height(12.dp))
        InteractiveTaskCard(title = "Configure Room Database Cache", category = "Storage")
    }
}
/** * A production-style task card component that utilizes internal state * to dynamically control its visual aesthetics and completion indicators. */
@Composable
fun InteractiveTaskCard(title: String, category: String) { // Instantiating a reactive local state variable tracking task completion var isCompleted by remember { mutableStateOf(false) } Card( modifier = Modifier .fillMaxWidth() .clip(RoundedCornerShape(10.dp)) .clickable { isCompleted = !isCompleted }, // Toggling state value on click colors = CardDefaults.cardColors( containerColor = if (isCompleted) Color(0xFFEFF6FF) else Color.White ), elevation = CardDefaults.cardElevation(defaultElevation = 2.dp) ) { Row( modifier = Modifier .fillMaxWidth() .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically ) { Column { Text( text = title, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = if (isCompleted) Color(0xFF1E40AF) else Color(0xFF111827) )


/**
 * An upgraded, interactive task card that utilizes mutableStateOf and remember
 * to manage local visual priority states cleanly.
 */
@Composable
fun TaskDisplayCard(title: String, category: String, modifier: Modifier = Modifier){
    //1. Establish our state wrappers using the property delegate syntax
    var isHighPriority by remember {mutableStateOf(false)}

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        colors = CardDefaults.cardColors(
            //The container background color updates dynamically based on the priority state
            containerColor = if(isHighPriority) Color(0xFFFEF2F2) else Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(modifier = Modifier.weight(1f)){
                Text(text = title, fontSize = 16.sp, color = Color(0xFF111827))
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = category, fontSize = 12.sp, color = Color(0xFF6B7280))
            }

            // 2. Interactive Text Button that acts as our state transformation trigger
            Text(
                text = if(isHighPriority)"'\u2605' High" else "'\u2606' Normal",
                fontSize = 12.sp,
                color = if(isHighPriority) Color(0xFFDC2626) else Color(0xFF4B5563),
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(if(isHighPriority) Color(0xFFFEE2E2) else Color(0xFFF3F4F6))
                    .clickable{isHighPriority = !isHighPriority} //Toggling our state variable
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            )
        }
    }
}
