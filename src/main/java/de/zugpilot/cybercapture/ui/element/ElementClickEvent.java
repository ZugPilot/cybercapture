package de.zugpilot.cybercapture.ui.element;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface ElementClickEvent {

    void call(Player player, int slot, ItemStack itemStack);

}
