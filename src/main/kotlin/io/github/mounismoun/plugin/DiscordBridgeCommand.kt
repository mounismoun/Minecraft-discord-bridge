package io.github.mounismoun.plugin

import io.github.mounismoun.command.CommandFramework
import io.github.mounismoun.command.argument.Argument
import io.github.mounismoun.command.argument.Arguments
import org.bukkit.entity.Player

class DiscordBridgeCommand(
    // plugin 사용 예정이라 프로퍼티로 남겼음.
    private val plugin: DiscordBridgePlugin,
    private val feature: DiscordFeature,
) {
    private val framework = CommandFramework.create(plugin)

    fun registerCommand(){

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
                    if (context.sender is Player){
                        feature.sendLocation(context.sender as Player)
                    }else{
                        context.sender.sendMessage("플레이어만 사용할 수 있습니다.")
                    }
                }
                val name = Argument("name", Arguments.string())
                argument(name) {
                    // 디코 좌표저장 <name>
                    executes { context ->
                        val name: String = context[name]

                        if (context.sender is Player){
                            feature.sendLocation(context.sender as Player, name)
                        }else{
                            context.sender.sendMessage("플레이어만 사용할 수 있습니다.")
                        }

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
