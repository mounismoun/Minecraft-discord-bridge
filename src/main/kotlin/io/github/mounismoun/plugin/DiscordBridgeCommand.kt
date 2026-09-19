package io.github.mounismoun.plugin

import io.github.mounismoun.command.CommandFramework
import io.github.mounismoun.command.argument.Argument
import io.github.mounismoun.command.argument.Arguments
import org.bukkit.entity.Player

class DiscordBridgeCommand(
    private val plugin: DiscordBridgePlugin,
) {
    private lateinit var feature: DiscordFeature
    private val framework = CommandFramework.create(plugin)

    fun registerCommand(){

        feature = DiscordFeature(plugin)

        framework.command("디코"){
            aliases("discord")
            description("디스코드 브릿지 명령어")

            // 디코
            executes { context ->
                context.sender.sendMessage("/디코 좌표 <name> <player> - 현재 좌표를 디스코드로 전송합니다. <player>는 생략 가능합니다.")
            }
            subCommand("좌표"){
                // 디코 좌표
                executes { context ->
                    feature.sendLocation(context.sender as Player)
                }
                val name = Argument("name", Arguments.string())
                argument(name) {
                    // 디코 좌표저장 <name>
                    executes { context ->
                        val name: String = context[name]
                        feature.sendLocation(context.sender as Player, name)
                        context.sender.sendMessage("현재 위치를 전송했습니다.")
                    }

                    val targetPlayer = Argument("player", Arguments.player())
                    argument(targetPlayer) {
                        // 디코 좌표저장 <name> <player>
                        executes { context ->
                            val name: String = context[name]
                            val targetPlayer: Player = context[targetPlayer]

                            feature.sendLocation(targetPlayer, name)
                            context.sender.sendMessage("${targetPlayer.name}님의 현재 위치를 전송했습니다.")
                        }
                    }
                }
            }
        }.register()
    }
}
