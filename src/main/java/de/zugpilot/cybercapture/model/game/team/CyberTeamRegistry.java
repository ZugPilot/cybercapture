package de.zugpilot.cybercapture.model.game.team;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.zugpilot.cybercapture.model.game.CyberGame;
import de.zugpilot.cybercapture.utils.ConfigurationSerializableAdapter;
import de.zugpilot.cybercapture.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class CyberTeamRegistry {

    private final CyberGame cyberGame;
    private final Map<String, CyberTeam> teams;
    private final List<CyberTeam> defaultTeamData;
    private final File file;

    /*
    ConfigurationSerializableAdapter.class and parts of the gson code was not made by me
    Source: https://www.spigotmc.org/threads/configurationserializable-to-json-using-gson.467776/
    Thanks for saving me a bunch of time!
     */

    public CyberTeamRegistry(CyberGame cyberGame){
        this.cyberGame = cyberGame;
        this.file = new File(cyberGame.getCyberPlugin().getDataFolder(), "teams.json");
        this.teams = new HashMap<>();
        this.defaultTeamData = new ArrayList<>();
        load();
    }

    public Collection<CyberTeam> getTeams(){
        return this.teams.values();
    }

    public List<CyberTeam> getDefaultTeamData() {
        return defaultTeamData;
    }

    /*
    Using defaultTeamData ensures that we won't have teams in our file that already have wrongly filled game fields, it will be the default form of a team
    Even though this is not the most ideal way to have a list of default teams, I currently have no idea how to do it in another way while keeping it somewhat modular
     */

    public void load() {
        try {
            if (!this.file.exists()) {
                this.file.createNewFile();
            }
            Reader reader = Files.newBufferedReader(file.toPath());
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .disableHtmlEscaping()
                    .registerTypeHierarchyAdapter(ConfigurationSerializable.class, new ConfigurationSerializableAdapter()).create();
            List<CyberTeam> entries = Arrays.asList(gson.fromJson(reader, CyberTeam[].class));
            for(CyberTeam team : entries){
                teams.put(team.getTeamIdentifier(), team);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO: Remember to delete this after development
        if(this.teams.isEmpty()){
            CyberTeam testTeam = new CyberTeam(cyberGame, new ItemBuilder(Material.GREEN_WOOL).displayName("§aTestTeam").build(), "§aTestTeam", 1);
            teams.put(testTeam.getTeamIdentifier(), testTeam);
        }
    }

    public void unload() {
        try {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .disableHtmlEscaping()
                    .registerTypeHierarchyAdapter(ConfigurationSerializable.class, new ConfigurationSerializableAdapter()).create();
            Writer writer = Files.newBufferedWriter(file.toPath());
            gson.toJson(this.defaultTeamData, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
