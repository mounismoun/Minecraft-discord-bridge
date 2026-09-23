package io.github.mounismoun.plugin

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class DiscordFeature(
    private val plugin: DiscordBridgePlugin,
    private val discordBot: DiscordBot,
) {
    fun sendLocation(player: Player, locationName: String = "") {

        val name = player.name
        val location = player.location
        val x = location.x
        val y = location.y
        val z = location.z
        val world = location.world.name

        discordBot.sendMessage("📍 **$locationName** · ${name}\n" +
            "`$world`  •  `X ${"%.1f".format(x)}` `Y ${"%.1f".format(y)}` `Z ${"%.1f".format(z)}`", "location-channelID")
    }

    fun sendDeathMessage(player: Player, deathMessage: String) {
        val location = player.location
        val x = location.x
        val y = location.y
        val z = location.z
        val world = location.world.name

        discordBot.sendMessage("$deathMessage\n" +
            "`$world`  •  `X ${"%.1f".format(x)}` `Y ${"%.1f".format(y)}` `Z ${"%.1f".format(z)}`", "location-channelID")
    }

    // Discord -> Minecraft
    fun sendDiscordMessage(sender: String, message: String){
        // jda에서 호출하기 때문에, Bukkit 스레드에서 실행
        Bukkit.getScheduler().runTask(plugin, Runnable{
            Bukkit.getServer().broadcast(
                Component.text("<${sender}> $message")
            )
        })
    }

    // Minecraft -> Discord
    fun sendMinecraftMessage(sender: String, message: String){
        discordBot.sendMessage("<${sender}> $message", "chat-channelID")
    }
}
