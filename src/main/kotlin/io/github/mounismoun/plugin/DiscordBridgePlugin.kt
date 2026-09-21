package io.github.mounismoun.plugin

import org.bukkit.plugin.java.JavaPlugin

class DiscordBridgePlugin : JavaPlugin() {

    lateinit var discordBot: DiscordBot
    private lateinit var discordCommand: DiscordBridgeCommand
    var feature: DiscordFeature = DiscordFeature(this)

    override fun onEnable() {
        init()
    }

    override fun onDisable() {
        discordBot.botDisable()
    }

    private fun init(){
        discordBot = DiscordBot(this)
        saveDefaultConfig()

        val token = config.getString("bot-token").toString()
        discordBot.botEnable(token)

        discordCommand = DiscordBridgeCommand(this)
        discordCommand.registerCommand()

        server.pluginManager.registerEvents(DiscordBridgeListener(this), this)
    }
}
