package com.d0as8.perlrun.extensions;

import com.d0as8.perlrun.Plugin;
import com.d0as8.perlrun.config.Config;
import com.intellij.execution.configuration.ConfigurationFactoryEx;
import com.intellij.execution.configurations.ConfigurationTypeBase;
import com.intellij.execution.configurations.ConfigurationTypeUtil;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class ConfigurationType extends ConfigurationTypeBase {
    private ConfigurationType() {
        super("ConfigurationType", Plugin.NAME, Plugin.DESCRIPTION, Plugin.ICON);

        addFactory(new ConfigurationFactory(this));
    }

    @NotNull
    public static ConfigurationType getInstance() {
        return ConfigurationTypeUtil.findConfigurationType(ConfigurationType.class);
    }

    private static class ConfigurationFactory extends ConfigurationFactoryEx {
        public ConfigurationFactory(ConfigurationType configurationType) {
            super(configurationType);
        }

        @Override
        public RunConfiguration createTemplateConfiguration(Project project) {
            return new Config(new RunConfigurationModule(project), this);
        }
    }
}
