package net.cherrycave.dummylobby.listener

import net.axay.kspigot.event.listen
import org.bukkit.Material
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerSwapHandItemsEvent

fun destroyListener() = listen<BlockBreakEvent> {
    it.isCancelled = true
}

fun placeListener() = listen<BlockPlaceEvent> {
    it.isCancelled = true
}

fun inventoryClick() = listen<InventoryClickEvent> {
    if (it.clickedInventory == it.whoClicked.inventory) {
        it.isCancelled = true
    }
}

fun interactListener() = listen<PlayerInteractEvent> {
    if (it.item?.type != Material.FIREWORK_ROCKET) {
        it.isCancelled = true
    }
}

fun dropListener() = listen<PlayerDropItemEvent> {
    it.isCancelled = true
}

fun swapHandItemsListener() = listen<PlayerSwapHandItemsEvent> {
    it.isCancelled = true
}

fun protectionListener() {
    destroyListener()
    placeListener()
    inventoryClick()
    interactListener()
    dropListener()
    swapHandItemsListener()
}
