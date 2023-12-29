package me.cadenwolfdog.cadenplugin.listeners

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class PlayerJumpListener : Listener {

    @EventHandler
    fun playerJumpEvent(event: PlayerJumpEvent){
        val player = event.player
        player.sendMessage(Component.text("You jumped!").color(TextColor.color(255,0,0)).decorate(TextDecoration.BOLD))
    }
}