package me.cadenwolfdog.cadenplugin.listeners

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import java.io.File
import java.io.IOException
import java.lang.Exception
import java.util.*

class ClassCommand : CommandExecutor {
    private val species = arrayOf("Fox", "Dog", "Wolf", "Mouse", "Bunny", "Hyena", "Cat")
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player && !hasSelectedClass(sender)) {
            if (args != null && args.isNotEmpty()) {
                val selectedSpecies = args[0]
                if (species.contains(selectedSpecies)) {
                    sender.sendMessage("You selected the species: $selectedSpecies")
                    val playerUsername = sender.name
                    val configFile = File("src/main/java/me/cadenwolfdog/cadenplugin/data/classdata.yaml")
                    val config = YamlConfiguration.loadConfiguration(configFile)
                    val entryId = UUID.randomUUID().toString()

                    val playerSection = config.createSection("players.$entryId")

                    playerSection.set("id", entryId.toString())
                    playerSection.set("username", playerUsername)
                    playerSection.set("selectedClass", "default")

                    try {
                        config.save(configFile)
                        sender.sendMessage("Your entry has been added to the database.")
                        return true
                    } catch (e: IOException) {
                        e.printStackTrace()
                        sender.sendMessage("An error occurred while saving the database.")
                        return false
                    }

                } else {
                    sender.sendMessage("Invalid species. Available species: ${species.joinToString(", ")}")
                    return false
                }
            } else {
                sender.sendMessage("Usage: /chooseclass <species>")
                return false
            }
            return false
        }
        sender.sendMessage("You already have a class.")
        return false
    }
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

                    if (playerUsername == username) {
                        return true
                    }
                }
            }
        } catch (e: Exception) {
            println("An exception occurred: $e")
        }

        return false
    }

