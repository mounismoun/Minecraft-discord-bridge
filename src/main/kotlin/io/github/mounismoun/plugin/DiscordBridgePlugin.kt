package io.github.mounismoun.plugin

import org.bukkit.plugin.java.JavaPlugin

class DiscordBridgePlugin : JavaPlugin() {

    private lateinit var discordCommand: DiscordBridgeCommand

    val discordBot: DiscordBot = DiscordBot(this)
    val feature: DiscordFeature = DiscordFeature(this, discordBot)

    override fun onEnable() {
        init()

        saveDefaultConfig()
        val token = config.getString("bot-token").toString()
        discordBot.botEnable(token)

        discordCommand.registerCommand()
        server.pluginManager.registerEvents(DiscordBridgeListener(this), this)
    }

    override fun onDisable() {
        discordBot.botDisable()
    }

    private fun init(){
        discordCommand = DiscordBridgeCommand(this, feature)
    }
}
