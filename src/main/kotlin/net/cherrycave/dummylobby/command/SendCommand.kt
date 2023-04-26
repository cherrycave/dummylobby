package net.cherrycave.dummylobby.command

import kotlinx.coroutines.launch
import net.axay.kspigot.commands.argument
import net.axay.kspigot.commands.command
import net.axay.kspigot.commands.getArgument
import net.cherrycave.birgid.GertrudClient
import net.cherrycave.birgid.command.sendRequest
import net.cherrycave.dummylobby.DummyLobbyPlugin
import net.cherrycave.dummylobby.coroutineScope

fun sendCommand(plugin: DummyLobbyPlugin, gertrudClient: GertrudClient) = command("send") {
    argument<String>("player") {
        argument<String>("server") {
            executes {
                val playerName = it.getArgument<String>("player")
                val server = it.getArgument<String>("server")
                val player = plugin.server.getPlayer(playerName) ?: return@executes 0
                coroutineScope.launch {
                    gertrudClient.sendRequest(listOf(player.uniqueId), server)
                }
                1
            }
        }
    }
}