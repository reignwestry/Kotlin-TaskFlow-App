package com.global.taskflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * MainActivity acts as the primary platform entry point for TaskFlow
 * This class coordinates our environment launch cycle and sets up our Compose UI canvas.
 */

class MainActivity:ComponentActivity(){
    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContent{
            MaterialTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    //Launching our core layout sequence container
                    TaskFlowDashboardScreen()
                }
            }
        }
    }
}

/**
 * Root layout orchestration container for the TaskFlow application screen layout.
 */

@Composable
fun TaskFlowDashboardScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9FAFB)) // Clean, modern off-white background canvas
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "TaskFlow",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827)
            )

            Spacer(modifier = Modifier.height(16.dp))

            //Reusing the summary status tracking block built in the previous section
            TaskSummaryHeader(totalTasks = 2, pendingTasks = 2)

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Active Tasks",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF374151)
            )

            Spacer(modifier = Modifier.height(12.dp))

            //Appending our new custom parameterized layout row car components
            TaskDisplayCard(
                taskTitle = "Review Application Code Architecture",
                taskCategory = "Engineering"
            )

            Spacer(modifier = Modifier.height(10.dp))

            TaskDisplayCard(
                taskTitle = "Configure Room Database Local Cache",
                taskCategory = "Database Storage"
            )


        }


        //Floating Action Button layered on top of the list via Box alignment properties
        FloatingActionButton(
            onClick = {/* Interactive navigation handling to be attached in later modules */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp),
            containerColor = Color(0xFF2563EB), //Rich action Blue identifier
            contentColor = Color.White
        ) {
            Icon( //REPLACE with Material3 Icon
                painter = painterResource(R.drawable.app_registration), //Custom Material3 Icon
                contentDescription = "Create Fresh Task Record Entry"
            )
        }
    }
}
/**
 * A highly reuseable Composable component that transforms raw task metrics into a visual header card.
 *
 * @param totalTasks The total count of recorded obligations currently managed by the app layer.
 * @param pendingTasks The count of unfinished tasks requiring immediate user attention.
 */

@Composable
fun TaskSummaryHeader(totalTasks: Int, pendingTasks: Int, modifier:Modifier = Modifier){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFEFF6FF), shape = RoundedCornerShape(12.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column{
            Text(
                text = "Total Logged Tasks",
                fontSize = 14.sp,
                color = Color(0xFF1E40AF)
            )
            Text(
                text = "$totalTasks Items",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E40AF)
            )
        }
        Column(horizontalAlignment = Alignment.End){
            Text(
                text = "Pending Active",
                fontSize = 14.sp,
                color = Color(0xFFB91C1C)
            )
            Text(
                text = "$pendingTasks Remaining",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFB91C1C)
            )
        }
    }
}

/**
 * TaskDisplayCard structures individual task listings using a Row and Column layout to position titles and categories cleanly.
 */
@Composable
fun TaskDisplayCard(taskTitle: String, taskCategory: String, modifier: Modifier = Modifier){
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            Text(
                text = taskTitle,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF111827)
            )

            Spacer(modifier = Modifier.height(8.dp))

            //Aligning categorization items horizontally inside the card space
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    modifier = Modifier
                        .background(Color(0xFFF3F4F6), shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ){
                    Text(
                        text = taskCategory,
                        fontSize = 12.sp,
                        color = Color(0xFF4B5563)
                    )
                }
            }
        }
    }
}