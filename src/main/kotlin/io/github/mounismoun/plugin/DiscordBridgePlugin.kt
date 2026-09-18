package io.github.mounismoun.plugin

import org.bukkit.plugin.java.JavaPlugin

class DiscordBridgePlugin : JavaPlugin() {

    private lateinit var discordBot: DiscordBot

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
    }


}
