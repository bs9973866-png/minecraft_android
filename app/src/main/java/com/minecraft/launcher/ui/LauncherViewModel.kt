package com.minecraft.launcher.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.minecraft.launcher.data.LauncherRepository

class LauncherViewModel : ViewModel() {
    private val repository = LauncherRepository()

    var uiState by mutableStateOf(
        com.minecraft.launcher.model.LauncherUiState(
            selectedVersion = repository.getVersions().first { it.recommended },
            accounts = repository.getAccounts(),
            modpacks = repository.getModpacks(),
            deviceProfiles = repository.getDeviceProfiles()
        )
    )
        private set
}
