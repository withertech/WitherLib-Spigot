package com.withertech.witherlib.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class RootCommand implements TabExecutor, CommandTree
{
	List<NodeCommand> commands = new ArrayList<>();

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;

			if (args.length > 0)
			{
				for (int i = 0; i < getCommands().size(); i++)
				{
					if (args[0].equalsIgnoreCase(getCommands().get(i).getName()))
					{
						if (getCommands().get(i).getPermission() == null || p.hasPermission(getCommands().get(i).getPermission()))
						{
							if (getCommands().get(i).perform(p, args))
							{
								return true;
							} else
							{
								getHelpCommand().perform(p, args);
							}
						} else
						{
							p.sendMessage(ChatColor.RED + "You do not have permission to use this command");
						}
					}
				}
			} else
			{
				getHelpCommand().perform(p, args);
			}
		}
		return true;
	}

	protected abstract HelpCommand getHelpCommand();

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args)
	{

		if (args.length == 1)
		{ //mail <subcommand> <args>
			ArrayList<String> subcommandsArguments = new ArrayList<>();

			for (int i = 0; i < getCommands().size(); i++)
			{
				subcommandsArguments.add(getCommands().get(i).getName());
			}

			return subcommandsArguments;
		} else if (args.length >= 2)
		{
			for (int i = 0; i < getCommands().size(); i++)
			{
				if (args[0].equalsIgnoreCase(getCommands().get(i).getName()))
				{
					return getCommands().get(i).getSubcommandArguments((Player) sender, args);
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
