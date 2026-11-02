package com.minecraft.launcher.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.minecraft.launcher.model.Account
import com.minecraft.launcher.model.DeviceProfile
import com.minecraft.launcher.model.MinecraftVersion
import com.minecraft.launcher.model.Modpack

private enum class LauncherTab(val title: String) {
    VERSIONS("Versions"),
    ACCOUNTS("Accounts"),
    MODS("Mods"),
    SETTINGS("Settings")
}

@Composable
fun LauncherApp(viewModel: LauncherViewModel = viewModel()) {
    val state = viewModel.uiState
    var selectedTab by remember { mutableStateOf(LauncherTab.VERSIONS) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                LauncherTab.entries.forEach { tab ->
                    NavigationBarItem(
                        selected = selectedTab == tab,
                        onClick = { selectedTab = tab },
                        icon = {
                            val icon = when (tab) {
                                LauncherTab.VERSIONS -> Icons.Default.PlayArrow
                                LauncherTab.ACCOUNTS -> Icons.Default.AccountCircle
                                LauncherTab.MODS -> Icons.Default.CloudDownload
                                LauncherTab.SETTINGS -> Icons.Default.Settings
                            }
                            Icon(icon, contentDescription = tab.title)
                        },
                        label = { Text(tab.title) }
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            HeaderCard(
                selectedVersion = state.selectedVersion,
                activeAccount = state.accounts.firstOrNull { it.isActive },
                deviceProfile = state.deviceProfiles.first()
            )

            when (selectedTab) {
                LauncherTab.VERSIONS -> VersionsScreen(state.selectedVersion, state.modpacks)
                LauncherTab.ACCOUNTS -> AccountsScreen(state.accounts)
                LauncherTab.MODS -> ModsScreen(state.modpacks)
                LauncherTab.SETTINGS -> SettingsScreen(state.deviceProfiles)
            }
        }
    }
}

@Composable
private fun HeaderCard(
    selectedVersion: MinecraftVersion,
    activeAccount: Account?,
    deviceProfile: DeviceProfile
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Minecraft launcher",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Version ${selectedVersion.label} · ${selectedVersion.type}",
                color = Color(0xFF9AD0FF),
                modifier = Modifier.padding(top = 8.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Account: ${activeAccount?.username ?: "None"}")
                Text(text = "Device: ${deviceProfile.name}")
            }
            Button(
                onClick = { },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = null)
                Text(text = "Launch", modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}

@Composable
private fun VersionsScreen(selectedVersion: MinecraftVersion, modpacks: List<Modpack>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            Text(
                text = "Recommended versions",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = selectedVersion.label, fontWeight = FontWeight.Bold)
                    Text(text = selectedVersion.description, modifier = Modifier.padding(top = 8.dp))
                    Text(
                        text = "Version type: ${selectedVersion.type}",
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
        item {
            Divider(modifier = Modifier.padding(vertical = 8.dp))
        }
        item {
            Text(
                text = "Auto-installed modpacks",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
        items(modpacks) { modpack ->
            ModpackItem(modpack)
        }
    }
}

@Composable
private fun AccountsScreen(accounts: List<Account>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            Text(
                text = "Accounts",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
        items(accounts) { account ->
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = account.username, fontWeight = FontWeight.Bold)
                        Text(text = account.type, color = Color(0xFF9AD0FF))
                    }
                    Text(text = account.status)
                }
            }
        }
    }
}

@Composable
private fun ModsScreen(modpacks: List<Modpack>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            Text(
                text = "Recommended mods",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
        items(modpacks) { modpack ->
            ModpackItem(modpack)
        }
    }
}

@Composable
private fun SettingsScreen(deviceProfiles: List<DeviceProfile>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            Text(
                text = "Device optimization",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
        items(deviceProfiles) { profile ->
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = profile.name, fontWeight = FontWeight.Bold)
                    Text(text = "Memory: ${profile.memoryLimitMb} MB")
                    Text(text = "Render: ${profile.renderQuality}")
                    Text(text = "JVM: ${profile.recommendedJavaArgs}")
                }
            }
        }
    }
}

@Composable
private fun ModpackItem(modpack: Modpack) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = modpack.name, fontWeight = FontWeight.Bold)
                Text(text = modpack.version)
            }
            Text(
                text = modpack.compatibility,
                color = Color(0xFF9AD0FF),
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(text = modpack.description, modifier = Modifier.padding(top = 8.dp))
            Text(
                text = "Downloads: ${modpack.downloads}",
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
