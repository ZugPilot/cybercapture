package de.zugpilot.cybercapture.ui;

import de.zugpilot.cybercapture.ui.element.UIElement;
import de.zugpilot.cybercapture.ui.element.impl.ClickableUIElement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

import java.util.*;

public class UIHandler implements Listener {

    public static Map<UUID, UI> WATCHERS = new HashMap<>();

    /*
    Gets all players watching a certain ui
    Useful for updating ui
     */

    public static List<UUID> getWatchingList(UI ui){
        List<UUID> watchingUI = new ArrayList<>();
        for(UUID watching : WATCHERS.keySet()){
            UI uiWatched = WATCHERS.get(watching);
            if(ui.getTitle().equals(uiWatched.getTitle())){
                watchingUI.add(watching);
            }
        }
        return watchingUI;
    }

    public static boolean isWatching(UUID uuid){
        return WATCHERS.containsKey(uuid);
    }

    public static void addWatching(UUID uuid, UI ui){
        WATCHERS.put(uuid, ui);
    }

    public static void removeWatching(UUID uuid){
        WATCHERS.remove(uuid);
    }

    public Optional<UI> getWatchingPlayer(UUID uuid){
        if(WATCHERS.containsKey(uuid)){
            return Optional.of(WATCHERS.get(uuid));
        }
        return Optional.empty();
    }

    /*
    This handles when a player closes their inventory
    and removes them from the watchers, this is tested
     */

    @EventHandler
    public void onCall(InventoryCloseEvent event){
        if(!(event.getPlayer() instanceof Player))return;
        Player player = (Player) event.getPlayer();
        Optional<UI> optional = getWatchingPlayer(player.getUniqueId());
        if(optional.isEmpty())return;
        UI ui = optional.get();
        ui.close(player);
    }


    @EventHandler
    public void onCall(InventoryClickEvent event){
        if(!(event.getWhoClicked() instanceof Player))return;
        Player player = (Player) event.getWhoClicked();
        Optional<UI> optional = getWatchingPlayer(player.getUniqueId());
        if(optional.isEmpty())return;
        UI ui = optional.get();
        int slot = event.getRawSlot();
        if(ui.getElements().containsKey(slot)){
            UIElement uiElement = ui.getElements().get(slot);
            event.setCancelled(true);
            if(uiElement instanceof ClickableUIElement clickableUIElement){
                if(clickableUIElement.getClickEvent() == null)return;
                clickableUIElement.getClickEvent().call(player, event.getRawSlot(), event.getCurrentItem());
            }
        }
    }

}
