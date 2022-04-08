package de.zugpilot.cybercapture.model.game.features.computer.process.types.attacking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;

@Getter
@AllArgsConstructor
public enum AttackVector {

    MALWARE("§cMalware", new String[]{"§cMalware ist eine einfache und günstige Art einem Gegner zu schaden"}, Material.IRON_SWORD),
    ENCRYPTION("§cEncryption", new String[]{"§cEncrypte den Computer deines Gegners mit einem Crypto-Trojaner", "§cDein Gegner muss seinen Computer dann mit Points freikaufen, um ihn wieder nutzen zu können."}, Material.BARRIER),
    BACK_DOOR("§cBackdoor", new String[]{"§cHöre deinen Gegner ab, um an mehr wertvolle Informationen zu gelangen."}, Material.NOTE_BLOCK),
    ;

    private final String coloredAttackVectorName;
    private final String[] attackVectorDescription;
    private final Material attackVectorIcon;

}
