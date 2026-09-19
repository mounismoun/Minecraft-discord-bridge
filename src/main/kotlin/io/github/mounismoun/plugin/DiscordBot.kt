package io.github.mounismoun.plugin

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.exceptions.InvalidTokenException
import net.dv8tion.jda.api.requests.GatewayIntent

class DiscordBot(
    private val plugin: DiscordBridgePlugin
) {
    private lateinit var jda: JDA

    fun botEnable(token: String) {

        if (token.isBlank()) {
            plugin.logger.info("디스코드 봇 토큰이 없습니다.")
            plugin.server.pluginManager.disablePlugin(plugin)
            return
        }

        try {
            jda = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(DiscordListener(plugin))
                .build()
            plugin.logger.info("봇 연결 중...")
        } catch (e: InvalidTokenException) {
            plugin.logger.severe("토큰이 유효하지 않습니다.")
            plugin.server.pluginManager.disablePlugin(plugin)
        }

    }

    fun botDisable() {
        if (::jda.isInitialized) {
            jda.shutdown()
            jda.awaitShutdown()
        }
    }

    fun sendMessage(content: String, channel: String) {
        val channelID = plugin.config.getString(channel)!!
        val channel = jda.getTextChannelById(channelID)

        if (channel == null) {
            plugin.logger.warning("디스코드 채팅 채널을 찾을 수 없습니다. 채널 아이디를 확인하세요.")
            return
        }

        channel.sendMessage(content).queue()
    }
}
