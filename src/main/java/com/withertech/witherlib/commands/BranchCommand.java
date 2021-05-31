package com.withertech.witherlib.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class BranchCommand extends NodeCommand implements CommandTree
{
	List<NodeCommand> commands = new ArrayList<>();

	protected abstract HelpCommand getHelpCommand();

	protected final int depth;

	public BranchCommand(int depth)
	{
		this.depth = depth;
	}

	@Override
	public boolean perform(Player player, String[] args)
	{
		if (args.length > depth)
		{
			for (int i = 0; i < getCommands().size(); i++)
			{
				if (args[depth].equalsIgnoreCase(getCommands().get(i).getName()))
				{
					if (getCommands().get(i).getPermission() == null || player.hasPermission(getCommands().get(i).getPermission()))
					{
						if (getCommands().get(i).perform(player, args))
						{
							return true;
						} else
						{
							getHelpCommand().perform(player, args);
						}
					} else
					{
						player.sendMessage(ChatColor.RED + "You do not have permission to use this command");
					}
				}
			}
		} else if (args.length == depth)
		{

			getHelpCommand().perform(player, args);

		}
		return true;
	}

	@Override
	public List<String> getSubcommandArguments(Player player, String[] args)
	{
		if (args.length == depth + 1)
		{ //mail box <subcommand> <args>
			ArrayList<String> subcommandsArguments = new ArrayList<>();

			for (int i = 0; i < getCommands().size(); i++)
			{
				subcommandsArguments.add(getCommands().get(i).getName());
			}

			return subcommandsArguments;
		} else if (args.length >= depth + 2)
		{
			for (int i = 0; i < getCommands().size(); i++)
			{
				if (args[depth].equalsIgnoreCase(getCommands().get(i).getName()))
				{
					return getCommands().get(i).getSubcommandArguments(player, args);
				}
			}
		}

		return null;
	}

	@Override
	public List<NodeCommand> getCommands()
	{
		return commands;
	}

	@Override
	public void addCommand(NodeCommand cmd)
	{
		commands.add(cmd);
	}
}
