package io.github.mounismoun.plugin

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.AsyncPlayerChatEvent

class DiscordBridgeListener(
    private val plugin: DiscordBridgePlugin
): Listener {

    private var feature: DiscordFeature = DiscordFeature(plugin)

    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) {
        val message = event.deathMessage
        feature.sendDeathMessage(event.player, message.toString())
    }

    @EventHandler
    fun onChat(event: AsyncPlayerChatEvent) {
        val sender = event.player.name
        val message = event.message

        feature.sendMinecraftMessage(sender, message)
    }
}
