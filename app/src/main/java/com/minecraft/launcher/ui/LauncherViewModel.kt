package com.minecraft.launcher.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.minecraft.launcher.data.LauncherRepository

class LauncherViewModel : ViewModel() {
    private val repository = LauncherRepository()

    val uiState = mutableStateOf(
        com.minecraft.launcher.model.LauncherUiState(
            versions = repository.getVersions(),
            selectedVersion = repository.getVersions().first { it.recommended },
            accounts = repository.getAccounts(),
            modpacks = repository.getModpacks(),
            deviceProfiles = repository.getDeviceProfiles()
        )
    )
}
