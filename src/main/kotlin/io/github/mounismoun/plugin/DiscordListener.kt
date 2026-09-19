package io.github.mounismoun.plugin

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class DiscordListener(
    private val plugin: DiscordBridgePlugin
): ListenerAdapter() {

    private var feature: DiscordFeature = DiscordFeature(plugin)

    override fun onReady(event: ReadyEvent) {
        plugin.logger.info("디스코드 봇이 연결되었습니다. (${event.jda.selfUser.name})")
    }

    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.author.isBot) return
        if (event.channel.id != plugin.config.getString("chat-channelID")) return

        val sender = event.member?.effectiveName ?: event.author.name
        val content = event.message.contentRaw
        feature.sendDiscordMessage(sender, content)
    }
}
