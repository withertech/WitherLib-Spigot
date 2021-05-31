package com.withertech.witherlib.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class HelpCommand extends NodeCommand
{
	protected final int depth;

	public HelpCommand(int depth)
	{
		this.depth = depth;
	}

	@Override
	public boolean perform(Player p, String[] args)
	{

		CommandTree commandTree = getCommandTree();

		p.sendMessage(" ");
		p.sendMessage(getPrefix());
		p.sendMessage(" ");
		if (args.length >= depth && !args[depth].equalsIgnoreCase("help"))
		{
			for (int i = 0; i < commandTree.getSubCommands().size(); i++)
			{
				if (commandTree.getSubCommands().get(i).getName().equalsIgnoreCase(args[depth]))
				{
					p.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.YELLOW + commandTree.getSubCommands().get(i).getSyntax() + " - " + ChatColor.GRAY + commandTree.getSubCommands().get(i).getDescription());
				}
			}
		} else
		{
			for (int i = 0; i < commandTree.getSubCommands().size(); i++)
			{
				p.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.YELLOW + commandTree.getSubCommands().get(i).getSyntax() + " - " + ChatColor.GRAY + commandTree.getSubCommands().get(i).getDescription());
			}
		}

		p.sendMessage(" ");
		p.sendMessage(getSuffix());
		p.sendMessage(" ");
		return true;
	}

	protected abstract String getPrefix();

	protected abstract String getSuffix();

	protected abstract CommandTree getCommandTree();

	@Override
	public List<String> getSubcommandArguments(Player player, String[] args)
	{
		return null;
	}
}
