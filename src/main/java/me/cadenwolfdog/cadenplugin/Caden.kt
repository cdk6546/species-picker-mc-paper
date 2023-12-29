package me.cadenwolfdog.cadenplugin

import me.cadenwolfdog.cadenplugin.listeners.ClassCommand
import me.cadenwolfdog.cadenplugin.listeners.PlayerJoinListener
import me.cadenwolfdog.cadenplugin.listeners.PlayerJumpListener
import org.bukkit.plugin.java.JavaPlugin

class Caden : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        logger.info("The plugin finished loading.")
        registerListeners()
        val classCommand = ClassCommand()
        getCommand("chooseclass")?.setExecutor(classCommand)
    }

    override fun onDisable() {
        // Plugin shutdown logic
        logger.info("The plugin finished shutting down.")

    }

    private fun registerListeners(){
        server.pluginManager.registerEvents(PlayerJumpListener(), this)
        server.pluginManager.registerEvents(PlayerJoinListener(), this)
        logger.info("Listeners registered.")
    }
}