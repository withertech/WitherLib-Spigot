package com.withertech.witherlib.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class HelpCommand extends NodeCommand
{
	protected final int depth;

	protected final CommandTree cmd;

	public HelpCommand(int depth, CommandTree cmd)
	{
		this.depth = depth;
		this.cmd = cmd;
	}

	@Override
	public boolean perform(Player p, String[] args)
	{

		CommandTree commandTree = getCommandTree();

		p.sendMessage(" ");
		p.sendMessage(getPrefix());
		p.sendMessage(" ");
		if (args.length >= depth && !args[depth - 1].equalsIgnoreCase("help"))
		{
			for (int i = 0; i < commandTree.getCommands().size(); i++)
			{
				if (commandTree.getCommands().get(i).getName().equalsIgnoreCase(args[depth - 1]))
				{
					p.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.YELLOW + commandTree.getCommands().get(i).getSyntax() + " - " + ChatColor.GRAY + commandTree.getCommands().get(i).getDescription());
				}
			}
		} else
		{
			for (int i = 0; i < commandTree.getCommands().size(); i++)
			{
				p.sendMessage(ChatColor.DARK_GRAY + " - " + ChatColor.YELLOW + commandTree.getCommands().get(i).getSyntax() + " - " + ChatColor.GRAY + commandTree.getCommands().get(i).getDescription());
			}
		}

		p.sendMessage(" ");
		p.sendMessage(getSuffix());
		p.sendMessage(" ");
		return true;
	}

	protected abstract String getPrefix();

	protected abstract String getSuffix();

	protected CommandTree getCommandTree()
	{
		return cmd;
	}

	@Override
	public List<String> getSubcommandArguments(Player player, String[] args)
	{
		return null;
	}
}
