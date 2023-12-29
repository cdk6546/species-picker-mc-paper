package me.cadenwolfdog.cadenplugin.listeners

import me.cadenwolfdog.cadenplugin.ClassSelection
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import java.io.File
import java.lang.Exception

class PlayerJoinListener : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player

        if (!hasSelectedClass(player)) {
            sendClassSelectionPrompt(player)
        }
    }

    private fun sendClassSelectionPrompt(player: Player) {
        val classSelection = ClassSelection()
        classSelection.sendClassSelectionMenu(player)
    }

    fun hasSelectedClass(player: Player): Boolean {
        val configFile = File("src/main/java/me/cadenwolfdog/cadenplugin/data/classdata.yaml")
        val config = YamlConfiguration.loadConfiguration(configFile)
        val username = player.name

        try {
            val playersSection = config.getConfigurationSection("players")
            if (playersSection != null) {
                for (playerName in playersSection.getKeys(false)) {
                    val playerData = playersSection.getConfigurationSection(playerName)
                    val playerUsername = playerData?.getString("username")

                    return playerUsername == username
                }
            }
        }
        catch (e: Exception){
           println("An exception occurred: $e")
        }

        return false

    }
}