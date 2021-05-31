package com.withertech.witherlib.commands;

import java.util.List;

public interface CommandTree
{
	void addCommand(NodeCommand cmd);

	List<NodeCommand> getCommands();
}
