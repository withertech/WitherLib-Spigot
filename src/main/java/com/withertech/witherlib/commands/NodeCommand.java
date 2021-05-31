package com.withertech.witherlib.commands;

import org.bukkit.entity.Player;

import java.util.List;

public abstract class NodeCommand
{

	public abstract String getName();

	public abstract String getDescription();

	public abstract String getSyntax();

	public abstract String getPermission();

	public abstract boolean perform(Player player, String[] args);

	public abstract List<String> getSubcommandArguments(Player player, String[] args);

}