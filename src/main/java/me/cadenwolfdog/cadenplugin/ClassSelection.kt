package me.cadenwolfdog.cadenplugin

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.entity.Player

class ClassSelection {
    fun sendClassSelectionMenu(player: Player) {
        val foxMessage = createClassMessage("Fox", "WIP")
                .color(TextColor.color(255, 100, 0))
        val dogMessage = createClassMessage("Dog", "WIP")
                .color(TextColor.color(150, 100, 0))
        val wolfMessage = createClassMessage("Wolf", "WIP")
                .color(TextColor.color(200, 200, 200))
        val mouseMessage = createClassMessage("Mouse", "WIP")
                .color(TextColor.color(100, 100, 100))
        val bunnyMessage = createClassMessage("Bunny", "WIP")
                .color(TextColor.color(200, 100, 100))
        val hyenaMessage = createClassMessage("Hyena", "WIP")
                .color(TextColor.color(200, 0, 100))
        val catMessage = createClassMessage("Cat",
                "Acrobatics - Never take fall damage\n" +
                "\n" +
                "Quiet Paws - Does not make any vibration; wont be noticed by other monsters (unless seen)\n" +
                "\n" +
                "Catlike Apperance - Creepers are scared of them\n" +
                "\n" +
                "Nocturnal - See slightly in the dark when not in water\n" +
                "\n" +
                "Fish Lover - Loves fish (Fish gives 1.5x saturation)")
                .color(TextColor.color(0,0,200))
        val comma = Component.text(", ")
        val mainMessage = Component.text("Choose a class. Hover over each to see their abilities.: ")
                .color(TextColor.color(0, 200, 250))
                .append(foxMessage)
                .append(comma)
                .append(dogMessage)
                .append(comma)
                .append(wolfMessage)
                .append(comma)
                .append(mouseMessage)
                .append(comma)
                .append(bunnyMessage)
                .append(comma)
                .append(hyenaMessage)
                .append(comma)
                .append(catMessage)

        player.sendMessage(mainMessage)
    }

    private fun createClassMessage(className: String, classDescription: String): Component {
        val headerComponent = Component.text(className + "\n")
                .decorate(TextDecoration.BOLD)
        val bodyComponent = Component.text(classDescription)
                .decorate(TextDecoration.ITALIC)
                .color(TextColor.color(75,75,75))
        val combinedComponent = headerComponent.append(bodyComponent)
        return Component.text(className)
                .hoverEvent(HoverEvent.showText(combinedComponent))
                .clickEvent(ClickEvent.runCommand("/chooseclass $className"))
    }
}