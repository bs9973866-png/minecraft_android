package com.minecraft.launcher.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.minecraft.launcher.data.LauncherRepository
import com.minecraft.launcher.launch.LaunchExecutionResult
import com.minecraft.launcher.launch.LaunchExecutor
import com.minecraft.launcher.model.Account
import com.minecraft.launcher.model.ClientMod
import com.minecraft.launcher.model.ControlPreset
import com.minecraft.launcher.model.DeviceProfile
import com.minecraft.launcher.model.LaunchConfig
import com.minecraft.launcher.model.LaunchPlan
import com.minecraft.launcher.model.LauncherSetupState
import com.minecraft.launcher.model.LauncherUiState
import com.minecraft.launcher.model.MinecraftVersion
import com.minecraft.launcher.model.ModLoader
import com.minecraft.launcher.model.Modpack
import com.minecraft.launcher.model.ResourcePack
import com.minecraft.launcher.model.ShaderPack

class LauncherViewModel : ViewModel() {
    private val repository = LauncherRepository()
    private val executor = LaunchExecutor()

    private val defaultVersion = repository.getVersions().first { it.recommended }
    private val defaultAccount = repository.getAccounts().first { it.isActive }
    private val defaultProfile = repository.getDeviceProfiles().first()

    var state by mutableStateOf(
        LauncherUiState(
            versions = repository.getVersions(),
            selectedVersion = defaultVersion,
            accounts = repository.getAccounts(),
            selectedAccountId = defaultAccount.id,
            modpacks = repository.getModpacksForVersion(defaultVersion.id),
            deviceProfiles = repository.getDeviceProfiles(),
            selectedDeviceProfileId = defaultProfile.id
        )
    )
        private set

    var promptState by mutableStateOf(
        LauncherSetupState(
            selectedLoader = repository.getModLoaders().first(),
            selectedModpack = repository.getModpacks().first { it.recommended },
            selectedMods = repository.getModSelection().filter { it.recommended },
            selectedShader = repository.getShaders().first { it.recommended },
            selectedResourcePack = repository.getResourcePacks().first { it.recommended },
            selectedControlPreset = repository.getControlPresets().first { it.recommended }
        )
    )
        private set

    val activeAccount: Account
        get() = state.accounts.first { it.id == state.selectedAccountId }

    val activeProfile: DeviceProfile
        get() = state.deviceProfiles.first { it.id == state.selectedDeviceProfileId }

    val launchPlan: LaunchPlan
        get() = repository.buildLaunchPlan(
            version = state.selectedVersion,
            account = activeAccount,
            deviceProfile = activeProfile
        )

    val activeLaunchConfig: LaunchConfig
        get() = repository.buildLaunchConfig(
            version = state.selectedVersion,
            account = activeAccount,
            deviceProfile = activeProfile
        )

    fun selectVersion(version: MinecraftVersion) {
        state = state.copy(
            selectedVersion = version,
            modpacks = repository.getModpacksForVersion(version.id)
        )
    }

    fun selectAccount(account: Account) {
        state = state.copy(
            accounts = state.accounts.map {
                it.copy(isActive = it.id == account.id)
            },
            selectedAccountId = account.id
        )
    }

    fun selectDeviceProfile(profile: DeviceProfile) {
        state = state.copy(selectedDeviceProfileId = profile.id)
    }

    fun selectLoader(loader: ModLoader) {
        promptState = promptState.copy(selectedLoader = loader)
    }

    fun selectModpack(modpack: Modpack) {
        promptState = promptState.copy(selectedModpack = modpack)
    }

    fun toggleMod(mod: ClientMod) {
        val newList = if (promptState.selectedMods.any { it.id == mod.id }) {
            promptState.selectedMods.filterNot { it.id == mod.id }
        } else {
            promptState.selectedMods + mod
        }
        promptState = promptState.copy(selectedMods = newList)
    }

    fun selectShader(shader: ShaderPack) {
        promptState = promptState.copy(selectedShader = shader)
    }

    fun selectResourcePack(pack: ResourcePack) {
        promptState = promptState.copy(selectedResourcePack = pack)
    }

    fun selectControlPreset(control: ControlPreset) {
        promptState = promptState.copy(selectedControlPreset = control)
    }

    fun buildPreparedLaunchCommand(): String {
        val mods = promptState.selectedMods.joinToString(",") { it.name }
        val selected = listOf(
            "loader=${promptState.selectedLoader.name}",
            "modpack=${promptState.selectedModpack.name}",
            "mods=${mods.ifBlank { "none" }}",
            "shader=${promptState.selectedShader.name}",
            "resourcePack=${promptState.selectedResourcePack.name}",
            "controls=${promptState.selectedControlPreset.name}"
        )
        return selected.joinToString(" | ")
    }

    fun launchMinecraft(): LaunchExecutionResult {
        return executor.execute(activeLaunchConfig)
    }
}
