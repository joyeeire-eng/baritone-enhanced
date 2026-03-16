package com.baritone.enhanced.commands;

import java.util.List;

public abstract class EnhancedCommand {
    protected final List<String> names;
    protected final String description;

    public EnhancedCommand(List<String> names, String description) {
        this.names = names;
        this.description = description;
    }

    public List<String> getNames() {
        return names;
    }

    public String getName() {
        return names.get(0);
    }

    public String getDescription() {
        return description;
    }

    public abstract void execute(String[] args);

    public abstract String getUsage();
}
