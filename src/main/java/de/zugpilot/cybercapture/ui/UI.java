package de.zugpilot.cybercapture.ui;

import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.ui.element.UIElement;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public abstract class UI {

    private final CyberGame cyberGame;
    private Inventory output;
    private String title;
    private int rows;
    private final Map<Integer, UIElement> elements;

    public UI(CyberGame cyberGame){
        this.cyberGame = cyberGame;
        this.elements = new HashMap<>();
    }

    public void addElement(int slot, UIElement uiElement){
        this.elements.put(slot, uiElement);
    }

    public void removeElement(int slot){
        this.elements.remove(slot);
    }

    public void setup(String title, int rows){
        this.title = title;
        this.rows = rows;
        this.output = Bukkit.createInventory(null, 9 * rows, Component.text(title));
    }

    public void build(){
        for(int key : elements.keySet()) {
            UIElement element = elements.get(key);
            output.setItem(key, element.getItemStack());
        }
    }
    
    public void clear(){
        output.clear();
        elements.clear();
    }

    public void update(){
        for(UUID uuid : UIHandler.getWatchingList(this)){
            Player player = Bukkit.getPlayer(uuid);
            if(player == null){
                //This should never come into use, but if there is somehow an error, this should prevent further damage
                //Technically a player is removed from watching if they disconnect, but reloading can break such things for example
                UIHandler.removeWatching(uuid);
                break;
            }
            close(player);
            open(player);
        }
    }

    public void open(Player player){
        if(UIHandler.isWatching(player.getUniqueId())){
            //This happens when a player tries to watch a ui while already watching a ui
            //Might happen with lag?
            System.out.println("[UI" + this.getTitle() + "] " + player.getName() + " tried to watch ui while already watching one!");
            return;
        }
        UIHandler.addWatching(player.getUniqueId(), this);
        player.openInventory(output);
    }

    public void close(Player player){
        if(!UIHandler.isWatching(player.getUniqueId())){
            return;
        }
        UIHandler.removeWatching(player.getUniqueId());
        player.closeInventory();
    }

    public void fill(ItemStack itemStack){
        for(int i = 0; i < output.getSize(); i++){
            addElement(i, new UIElement(itemStack));
        }
    }

    public void fill(int from, int to, ItemStack itemStack){
        if(from > output.getSize() || from < 0)return;
        if(to > output.getSize() || to < 0)return;
        for(int i = from; i < to; i++){
            addElement(i, new UIElement(itemStack));
        }
    }

}
