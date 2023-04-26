package net.cherrycave.dummylobby.listener

import net.axay.kspigot.event.listen
import net.cherrycave.dummylobby.DummyLobbyPlugin
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.JoinConfiguration
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

private val miniMessage = MiniMessage.miniMessage()

fun joinListener(plugin: DummyLobbyPlugin) = listen<PlayerJoinEvent> { event ->
    plugin.luckPerms.userManager.getUser(event.player.uniqueId)?.let { user ->
        val primaryGroup = plugin.luckPerms.groupManager.getGroup(user.primaryGroup) ?: return@let

        event.joinMessage(
            Component.join(
                JoinConfiguration.noSeparators(),
                miniMessage.deserialize(primaryGroup.cachedData.metaData.prefix ?: ""),
                event.player.displayName().color(
                    NamedTextColor.NAMES.value((primaryGroup.cachedData.metaData.getMetaValue("color") ?: "blue").toString())
                ),
                Component.text(" joined the game").color(NamedTextColor.GRAY)
            )
        )
    }
}

fun quitListener(plugin: DummyLobbyPlugin) = listen<PlayerQuitEvent> { event ->
    plugin.luckPerms.userManager.getUser(event.player.uniqueId)?.let { user ->
        val primaryGroup = plugin.luckPerms.groupManager.getGroup(user.primaryGroup) ?: return@let

        event.quitMessage(
            Component.join(
                JoinConfiguration.noSeparators(),
                miniMessage.deserialize(primaryGroup.cachedData.metaData.prefix ?: ""),
                event.player.displayName().color(
                    NamedTextColor.NAMES.value((primaryGroup.cachedData.metaData.getMetaValue("color") ?: "blue").toString())
                ),
                Component.text(" left the game").color(NamedTextColor.GRAY)
            )
        )
    }
}
