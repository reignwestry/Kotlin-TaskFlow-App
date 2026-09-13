package com.global.taskflow

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.FloatingActionButton
import androidx.compose.ui.res.painterResource //Import a custom Icon
import androidx.compose.material3.Icon
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.global.taskflow.ui.theme.TaskFlowTheme

/**
 * Custom Material 3 Light Color Palette Definition for TaskFlow.
 * Maps clean, high-contrast tokens to structural background and text systems.
 */
private val TaskFlowLightColors = lightColorScheme(
    primary = Color(0xFF2563EB), //Royal Blue brand identifier
    background = Color(0xFFF9FAFB), //Crisp off-white workspace canvas
    surface = Color(0xFFFFFFFF), //Clear white element card surfaces
    onPrimary = Color(0xFFFFFFFF), //White text on primary buttons
    onBackground = Color(0xFF111827), //Deep charcoal body text
    onSurface = Color(0xFF111827), //Deep charcoal container titles
    secondaryContainer = Color(0xFFEFF6FF), //Soft light-blue background metrics frame
    onSecondaryContainer = Color(0xFF1E40F) //Dark blue metrics informational labels
)

/**
 * Custom Material 3 Dark Color Palette Definition for TaskFlow.
 * Provides optimized low-fatigue tones that preserve structural component visibility.
 */
private val TaskFlowDarkColors = darkColorScheme(
    primary = Color(0xFF60A5FA), //Vibrant neon-blue brand identifier for low light
    background = Color(0xFF111827), //Midnight background canvas surface
    surface = Color(0xFF1F2937), //Soft dark-slate element card surfaces
    onPrimary = Color(0xFF111827), //Dark text over radiant accent controls
    onBackground = Color(0xFFF9FAFB), //Crisp white body text readings
    onSurface = Color(0xFFF9FAFB), //Crisp white container titles
    secondaryContainer = Color(0xFF1E3A8A), //Deep dark-blue background metrics frame
    onSecondaryContainer = Color(0xFF93C5FD) //Pale sky-blue metrics labels
)

/**
 * Centralized Structural Theme Wrapper Component for the TaskFlow Application ecosystem.
 */
@Composable
fun TaskFlowTheme(
    darkTheme:Boolean = isSystemInDarkTheme(),
    content: @Composable ()-> Unit
){
    //Dynamically branching the active system color architecture based on system properties
    val targetColorScheme = if(darkTheme) TaskFlowDarkColors else TaskFlowLightColors

    MaterialTheme(
        colorScheme = targetColorScheme,
        content = content
    )
}

/**
 * MainActivity serves as the system bootstrap entry point for TaskFlow.
 * We wrap our primary container directly inside our fresh TaskFlowTheme engine block.
 */

class MainActivity:ComponentActivity(){
    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContent{
            TaskFlowTheme{
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
    val currentContext = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
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
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(16.dp))

            //Reusing the summary status tracking block built in the previous section
            TaskSummaryHeader(totalTasks = 2, pendingTasks = 2)

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Active Tasks",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(12.dp))

            //Appending our new custom parameterized layout row car components
            TaskDisplayCard(
                taskTitle = "Review Application Code Architecture",
                taskCategory = "Engineering",
                onCardClick ={
                    Toast.makeText(currentContext, "Clicked: Code Architecture", Toast.LENGTH_SHORT).show()
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            TaskDisplayCard(
                taskTitle = "Configure Room Database Local Cache",
                taskCategory = "Database Storage",
                onCardClick ={
                    Toast.makeText(currentContext, "Clicked: Room Database Setup", Toast.LENGTH_SHORT).show()
                }
            )


        }


        //Floating Action Button layered on top of the list via Box alignment properties
        FloatingActionButton(
            onClick = {
                Toast.makeText(currentContext, "Launch Task Creation Input Screen", Toast.LENGTH_SHORT).show()
                      },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp),

            containerColor = MaterialTheme.colorScheme.primary, //Rich action Blue identifier
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Icon( //REPLACE with Material3 Icon
                painter = painterResource(R.drawable.app_registration), //converted original to Custom Material3 Icon
                contentDescription = "Create Fresh Task Record Entry"
            )
        }
    }
}
/**
 * Functional status header utilizing standard width and background shape modifiers.
 */

@Composable
fun TaskSummaryHeader(totalTasks: Int, pendingTasks: Int, modifier:Modifier = Modifier){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer, shape = RoundedCornerShape(12.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column{
            Text(
                text = "Total Logged Tasks",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = "$totalTasks Items",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
        Column(horizontalAlignment = Alignment.End){
            Text(
                text = "Pending Active",
                fontSize = 14.sp,
                color = Color(0xFFB91C1C) //Semantic diagnostic color warning preserved intentionally
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
 * TaskDisplayCard leverages precise modifier ordering to inject interactive ripple states and prevent background color leakage outside rounded container borders.
 */
@Composable
fun TaskDisplayCard(
    taskTitle: String,
    taskCategory: String,
    onCardClick:()-> Unit,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp)) // 1. Clip bounding box parameters first
            .clickable {onCardClick()}, //2. Attach click interaction layer next
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
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
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ){
                    Text(
                        text = taskCategory,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}

/**
 * Light Mode Workspace Editor Preview Configuration.
 */

@Composable
fun TaskFlowDashboardLightPreview(){
    TaskFlowTheme(darkTheme = false){
        TaskFlowDashboardScreen()
    }
}
/**
 * Dark Mode Workspace Editor Preview Configuration.
 */
@Preview(name = "Dark Mode Preview", showBackground = true)
@Composable
fun TaskFlowDashboardDarkPreview(){
    TaskFlowTheme(darkTheme = true){
        TaskFlowDashboardScreen()
    }
}