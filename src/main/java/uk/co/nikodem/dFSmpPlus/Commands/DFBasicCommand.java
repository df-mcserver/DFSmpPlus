package uk.co.nikodem.dFSmpPlus.Commands;

import io.papermc.paper.command.brigadier.BasicCommand;

import java.util.Collection;

public interface DFBasicCommand extends BasicCommand {
    String getLabel();
    String getDescription();
    Collection<String> getAliases();
}
