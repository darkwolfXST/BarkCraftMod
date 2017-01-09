package me.totalfreedom.totalfreedommod.rank;

import lombok.Getter;
import org.bukkit.ChatColor;

public enum Title implements Displayable
{

    DEVELOPER("a", "Developer", ChatColor.DARK_PURPLE, "Dev"),
    OWNER("the", "Owner", ChatColor.BLUE, "Owner"),
    CO_OWNER("a", "Co-Owner", ChatColor.BLUE, "Co-Owner"),
    LEAD_DEVELOPER("a", "Lead-Developer", ChatColor.LIGHT_PURPLE, "Lead-Dev"),
    SYSTEM_ADMIN("a", "System Admin", ChatColor.DARK_RED, "System-Admin"),
    EXECUTIVE("an", "Executive", ChatColor.YELLOW, "Executive"),
    M1CE("the", "great M1CE", ChatColor.LIGHT_PURPLE, "M1CE"),
    PACKET_SQUAD("a", "PacketSquad member", ChatColor.LIGHT_PURPLE, "PacketSquad"),
    PLUGIN_DEVELOPER("a", "Plugin-Developer", ChatColor.LIGHT_PURPLE, "Plugin-Dev"),
    STAFF_MANAGER("a", "Staff Manager", ChatColor.DARK_RED, "Staff-Manager");

    private final String determiner;
    @Getter
    private final String name;
    @Getter
    private final String tag;
    @Getter
    private final String coloredTag;
    @Getter
    private final ChatColor color;

    private Title(String determiner, String name, ChatColor color, String tag)
    {
        this.determiner = determiner;
        this.name = name;
        this.tag = "[" + tag + "]";
        this.coloredTag = ChatColor.DARK_GRAY + "[" + color + tag + ChatColor.DARK_GRAY + "]" + color;
        this.color = color;
    }

    @Override
    public String getColoredName()
    {
        return color + name;
    }

    @Override
    public String getColoredLoginMessage()
    {
        return determiner + " " + color + ChatColor.ITALIC + name;
    }

}
