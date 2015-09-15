package com.d0as8.perlrun.extensions;

import com.d0as8.perlrun.config.Config;
import com.intellij.execution.Location;
import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.RunConfigurationProducer;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;

public class RunConfigProducer extends RunConfigurationProducer<Config> {
    public RunConfigProducer() {
        super(ConfigurationType.getInstance());
    }

    @Override
    protected boolean setupConfigurationFromContext(Config runConfig, ConfigurationContext context, Ref<PsiElement> sourceElement) {
        Location location = context.getLocation();
        if (location == null) {
            return false;
        }

        VirtualFile file = location.getVirtualFile();

        return file != null;
    }

    @Override
    public boolean isConfigurationFromContext(Config runConfig, ConfigurationContext context) {
        Location location = context.getLocation();
        if (location == null) {
            return false;
        }

        VirtualFile file = location.getVirtualFile();

        return file != null && FileUtil.pathsEqual(file.getPath(), runConfig.getProgramName());
    }
}
