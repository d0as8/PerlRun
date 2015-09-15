package com.d0as8.perlrun.extensions;

import com.d0as8.perlrun.Plugin;
import com.d0as8.perlrun.config.Config;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.runners.DefaultProgramRunner;
import org.jetbrains.annotations.NotNull;

public class ProgramRunner extends DefaultProgramRunner {
    @NotNull
    public String getRunnerId() {
        return Plugin.NAME;
    }

    @Override
    public boolean canRun(@NotNull String s, @NotNull RunProfile runProfile) {
        return runProfile instanceof Config;
    }
}