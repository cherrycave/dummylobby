package net.cherrycave.dummylobby

import kotlinx.coroutines.*
import net.axay.kspigot.main.KSpigot
import net.cherrycave.birgid.GertrudClient
import net.cherrycave.birgid.closeConnection
import net.cherrycave.birgid.connect
import net.cherrycave.dummylobby.command.sendCommand
import net.cherrycave.dummylobby.listener.joinListener
import net.cherrycave.dummylobby.listener.protectionListener
import net.cherrycave.dummylobby.listener.quitListener
import net.luckperms.api.LuckPerms

val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

class DummyLobbyPlugin : KSpigot() {

    lateinit var luckPerms: LuckPerms

//    private val gertrudClient = GertrudClient {
//        host = "cherrycave-backend.cap.stckoverflw.net"
//        port = 80
//        identifier = "lobby"
//        apiKey = "UKFo@BYKWsG#rcnYzyW^jBVkP53&etky7zan*vMr6A"
//    }

    override fun startup() {
        luckPerms = loadLuckPerms() ?: throw IllegalStateException("LuckPerms not found")

//        sendCommand(this, gertrudClient)

        joinListener(this)
        quitListener(this)
        protectionListener()

//        coroutineScope.launch {
//            gertrudClient.connect()
//        }
    }

    override fun shutdown() {
//        runBlocking {
//            gertrudClient.closeConnection()
//        }
    }

    private fun loadLuckPerms(): LuckPerms? {
        val provider = server.servicesManager
            .getRegistration(LuckPerms::class.java)
        return provider?.provider
    }

}