package de.zugpilot.cybercapture.ui.element;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;

@Getter
public class UIElement {

    private final ItemStack itemStack;

    public UIElement(ItemStack itemStack){
        this.itemStack = itemStack;
    }

}
