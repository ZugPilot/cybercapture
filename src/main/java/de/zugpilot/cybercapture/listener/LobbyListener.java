package de.zugpilot.cybercapture.listener;

import de.zugpilot.cybercapture.CyberConstants;
import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.model.game.GameState;
import de.zugpilot.cybercapture.model.game.player.CyberPlayer;
import de.zugpilot.cybercapture.model.game.team.LobbySelectorUI;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

public class LobbyListener implements Listener {

    private final CyberGame cyberGame;

    public LobbyListener(CyberGame cyberGame){
        this.cyberGame = cyberGame;
    }

    int i = 0;

    @EventHandler
    public void onCall(PlayerInteractEvent event){
        if(!(event.getPlayer().getGameMode() == GameMode.CREATIVE && event.getPlayer().hasPermission(CyberConstants.PERMISSION_PREFIX + "build"))){
            event.setCancelled(true);
        }
        if(cyberGame.getGameState() != GameState.LOBBY)return;
        if(event.getHand() == EquipmentSlot.OFF_HAND)return;
        if(event.getItem() == null)return;
        if(event.getAction().isRightClick()){
            ItemStack itemStack = event.getItem();
            Player player = event.getPlayer();
            Optional<CyberPlayer> optional = cyberGame.getPlayerRegistry().getCyberPlayer(player.getUniqueId());
            if(optional.isEmpty())return;
            CyberPlayer cyberPlayer = optional.get();
            switch (cyberGame.getGameState()){
                case LOBBY:
                    if(itemStack.getType() == Material.COMPASS){
                        LobbySelectorUI teamSelectorUI = new LobbySelectorUI(cyberGame, cyberPlayer);
                        teamSelectorUI.build();
                        teamSelectorUI.open(player);
                    }
                    break;
            }
        }
    }

    @EventHandler
    public void onCall(PlayerInteractEntityEvent event){
        if(!(event.getPlayer().getGameMode() == GameMode.CREATIVE && event.getPlayer().hasPermission(CyberConstants.PERMISSION_PREFIX + "build"))){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onCall(PlayerDropItemEvent event){
        if(cyberGame.getGameState() != GameState.LOBBY)return;
        event.setCancelled(true);
    }


    @EventHandler
    public void onCall(EntityDamageEvent event){
        if(cyberGame.getGameState() != GameState.LOBBY)return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onCall(FoodLevelChangeEvent event){
        if(cyberGame.getGameState() != GameState.LOBBY)return;
        event.setFoodLevel(20);
    }

    @EventHandler
    public void onCall(BlockBreakEvent event){
        if(event.getPlayer().getGameMode() == GameMode.CREATIVE && event.getPlayer().hasPermission(CyberConstants.PERMISSION_PREFIX + "build"))return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onCall(BlockPlaceEvent event){
        if(event.getPlayer().getGameMode() == GameMode.CREATIVE && event.getPlayer().hasPermission(CyberConstants.PERMISSION_PREFIX + "build"))return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onCall(HangingBreakByEntityEvent event){
        if(event.getRemover() instanceof Player player){
            if(!(player.getGameMode() == GameMode.CREATIVE && player.hasPermission(CyberConstants.PERMISSION_PREFIX + "build"))){
                event.setCancelled(true);
                return;
            }
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onCall(HangingBreakEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onCall(HangingPlaceEvent event){
        if(event.getPlayer() == null)return;
        if(event.getPlayer().getGameMode() == GameMode.CREATIVE && event.getPlayer().hasPermission(CyberConstants.PERMISSION_PREFIX + "build"))return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onCall(WeatherChangeEvent event) {
        if (!event.toWeatherState()) {
            return;
        }
        event.setCancelled(true);
        event.getWorld().setWeatherDuration(0);
        event.getWorld().setThundering(false);
    }

}
