package io.github.mounismoun.plugin

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.exceptions.InvalidTokenException
import net.dv8tion.jda.api.hooks.ListenerAdapter

class DiscordBot(
    private val plugin: DiscordBridgePlugin
) {
    private lateinit var jda: JDA

    fun botEnable(token: String) {

        if (token.isBlank()){
            plugin.logger.info("디스코드 봇 토큰이 없습니다.")
            plugin.server.pluginManager.disablePlugin(plugin)
            return
        }

        try {
            jda = JDABuilder.createDefault(token)
                .addEventListeners(DiscordListener(plugin))
                .build()
            plugin.logger.info("봇 연결 중...")
        } catch (e: InvalidTokenException) {
            plugin.logger.severe("토큰이 유효하지 않습니다.")
            plugin.server.pluginManager.disablePlugin(plugin)
        }

    }

    fun botDisable(){
        if (::jda.isInitialized) {
            jda.shutdown()
        }
    }
}

class DiscordListener(
    private val plugin: DiscordBridgePlugin
): ListenerAdapter() {
    override fun onReady(event: ReadyEvent) {
        plugin.logger.info("디스코드 봇이 연결되었습니다. (${event.jda.selfUser.name})")
    }
}
