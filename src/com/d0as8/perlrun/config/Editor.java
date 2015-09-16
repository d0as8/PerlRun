package com.d0as8.perlrun.config;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

class Editor extends SettingsEditor<Config> {
    private Form form;

    public Editor(Module module) {
        this.form = new Form();
        this.form.setModuleContext(module);
    }

    @Override
    protected void resetEditorFrom(Config config) {
        form.reset(config);
    }

    @Override
    protected void applyEditorTo(Config config) throws ConfigurationException {
        form.applyTo(config);
    }

    @Override
    @NotNull
    protected JComponent createEditor() {
        return form;
    }

    @Override
    protected void disposeEditor() {
        form = null;
    }
}