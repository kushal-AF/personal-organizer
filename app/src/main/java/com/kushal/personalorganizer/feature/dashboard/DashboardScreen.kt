package com.kushal.personalorganizer.feature.dashboard

import androidx.navigation.NavController
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kushal.personalorganizer.feature.tasks.TasksViewModel
import com.kushal.personalorganizer.ui.theme.*

private data class DashboardItem(
    val title: String,
    val count: String,
    val subtitle: String,
    val icon: ImageVector,
    val color: Color
)

@Composable
fun DashboardScreen(
    navController: NavController,
    tasksViewModel: TasksViewModel = hiltViewModel(),
    appViewModel: com.kushal.personalorganizer.navigation.AppViewModel = hiltViewModel()
) {
    val uiState by appViewModel.uiState.collectAsState()
    val userName = (uiState as? com.kushal.personalorganizer.navigation.AppUiState.Ready)?.userName ?: ""
    val tasks by tasksViewModel.tasks.collectAsState()
    val completedCount = tasks.count { it.isCompleted }
    val totalCount = tasks.size

    val items = listOf(
        DashboardItem("Tasks", "$completedCount", "of $totalCount done", Icons.Default.CheckCircle, AccentTasks),
        DashboardItem("Classes/Work", "0", "of 0 done", Icons.Default.Work, AccentClassesWork),
        DashboardItem("Reminders", "0", "Upcoming", Icons.Default.Notifications, AccentReminders),
        DashboardItem("Appointments", "0", "Today", Icons.Default.Event, AccentAppointments),
        DashboardItem("Notes", "0", "Notes", Icons.Default.Description, AccentNotes),
        DashboardItem("Habits", "0", "Active", Icons.Default.Repeat, AccentHabits),
        DashboardItem("Deadlines", "0", "Upcoming", Icons.Default.Flag, AccentDeadlines)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Good Morning,\n${userName?.takeIf { it.isNotBlank() } ?: "there"} 👋",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications",
                tint = TextSecondary
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Overview",
            fontSize = 14.sp,
            color = TextSecondary
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items.size) { index ->
                val item = items[index]
                DashboardCard(
                    item = item,
                    onClick = {
                        if (item.title == "Tasks") {
                            navController.navigate(com.kushal.personalorganizer.navigation.NavRoutes.Tasks.route)
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Composable
private fun DashboardCard(item: DashboardItem, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(CardBackground)
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.title,
                fontSize = 13.sp,
                color = TextSecondary
            )
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(item.color.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    tint = item.color,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = item.count,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )

        Text(
            text = item.subtitle,
            fontSize = 12.sp,
            color = TextSecondary
        )
    }
}