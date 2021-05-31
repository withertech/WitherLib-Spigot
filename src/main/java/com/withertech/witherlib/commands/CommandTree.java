package com.withertech.witherlib.commands;

import java.util.ArrayList;

public interface CommandTree
{
	ArrayList<NodeCommand> commands = new ArrayList<>();

	default ArrayList<NodeCommand> getSubCommands()
	{
		return commands;
	}
}
