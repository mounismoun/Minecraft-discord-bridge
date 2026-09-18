package io.github.mounismoun.plugin

import io.github.mounismoun.command.CommandFramework

class DiscordBridgeCommand(
    private val plugin: DiscordBridgePlugin,
    private val feature: DiscordFeature
) {

    private val framework = CommandFramework.create(plugin)

    fun registerCommand(){
        framework.command("discordbridge"){
            aliases("discord")
            aliases("디코")
            description("디스코드 브릿지 명령어")

            // /디코
            executes { context ->
                context.sender.sendMessage("/디코 좌표저장 <player>")
            }
            subCommand("좌표저장"){
                executes { context ->

                }
            }

        }.register()
    }

}
