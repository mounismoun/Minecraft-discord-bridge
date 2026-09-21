package io.github.mounismoun.plugin

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.AsyncPlayerChatEvent

class DiscordBridgeListener(
    private val plugin: DiscordBridgePlugin
): Listener {

    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {
        val message = event.deathMessage
        plugin.feature.sendDeathMessage(event.player, message.toString())
    }

    @EventHandler
    fun onChat(event: AsyncPlayerChatEvent) {
        val sender = event.player.name
        val message = event.message

        plugin.feature.sendMinecraftMessage(sender, message)
    }
}
