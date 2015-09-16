package com.d0as8.perlrun.config;

import com.d0as8.perlrun.runner.RunState;
import com.intellij.execution.CommonProgramRunConfigurationParameters;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configuration.AbstractRunConfiguration;
import com.intellij.execution.configuration.EnvironmentVariablesComponent;
import com.intellij.execution.configurations.*;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.components.PathMacroManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.util.DefaultJDOMExternalizer;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.JDOMExternalizerUtil;
import com.intellij.openapi.util.WriteExternalException;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;

public class Config extends AbstractRunConfiguration implements CommonProgramRunConfigurationParameters {
    private String interpreterOptions = "";
    private String interpreterName = "";
    private String programName = "";
    private String programParameters;

    public Config(String name, RunConfigurationModule configurationModule, ConfigurationFactory factory) {
        super(name, configurationModule, factory);
    }

    @Override
    public Collection<Module> getValidModules() {
        return Arrays.asList(ModuleManager.getInstance(getProject()).getModules());
    }

    @NotNull
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new Editor(getConfigurationModule().getModule());
    }

    @Nullable
    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment env) throws ExecutionException {
        return new RunState(this, env);
    }

    @Override
    public void checkConfiguration() throws RuntimeConfigurationException {
        super.checkConfiguration();
    }

    @Override
    public void readExternal(Element element) throws InvalidDataException {
        PathMacroManager.getInstance(getProject()).expandPaths(element);
        super.readExternal(element);

        DefaultJDOMExternalizer.readExternal(this, element);
        readModule(element);
        EnvironmentVariablesComponent.readExternal(element, getEnvs());

        interpreterOptions = JDOMExternalizerUtil.readField(element, "INTERPRETER_OPTIONS");
        interpreterName = JDOMExternalizerUtil.readField(element, "INTERPRETER_NAME");
        programName = JDOMExternalizerUtil.readField(element, "PROGRAM_NAME");
        setProgramParameters(JDOMExternalizerUtil.readField(element, "PROGRAM_PARAMETERS"));
    }

    @Override
    public void writeExternal(Element element) throws WriteExternalException {
        super.writeExternal(element);

        JDOMExternalizerUtil.writeField(element, "INTERPRETER_OPTIONS", interpreterOptions);
        JDOMExternalizerUtil.writeField(element, "INTERPRETER_NAME", interpreterName);
        JDOMExternalizerUtil.writeField(element, "PROGRAM_NAME", programName);
        JDOMExternalizerUtil.writeField(element, "PROGRAM_PARAMETERS", getProgramParameters());

        DefaultJDOMExternalizer.writeExternal(this, element);
        writeModule(element);

        PathMacroManager.getInstance(getProject()).collapsePathsRecursively(element);
    }

    @Nullable
    public String getProgramParameters() {
        return programParameters;
    }

    public void setProgramParameters(@Nullable String programParameters) {
        this.programParameters = programParameters;
    }

    @Nullable
    @Override
    public String getWorkingDirectory() {
        return null;
    }

    @Override
    public void setWorkingDirectory(@Nullable String s) {

    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }


    public String getInterpreterName() {
        return interpreterName;
    }

    public void setInterpreterName(String interpreterName) {
        this.interpreterName = interpreterName;
    }

    public String getInterpreterOptions() {
        return interpreterOptions;
    }

    public void setInterpreterOptions(String interpreterOptions) {
        this.interpreterOptions = interpreterOptions;
    }
}