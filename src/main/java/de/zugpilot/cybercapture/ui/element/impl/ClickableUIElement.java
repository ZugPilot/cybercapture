package de.zugpilot.cybercapture.ui.element.impl;

import de.zugpilot.cybercapture.ui.element.ElementClickEvent;
import de.zugpilot.cybercapture.ui.element.UIElement;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

@Getter
public class ClickableUIElement extends UIElement {

    private final ElementClickEvent clickEvent;

    public ClickableUIElement(ItemStack itemStack, ElementClickEvent clickEvent) {
        super(itemStack);
        this.clickEvent = clickEvent;
    }
}
