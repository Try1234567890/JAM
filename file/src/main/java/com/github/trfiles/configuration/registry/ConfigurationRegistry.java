package com.github.trfiles.configuration.registry;


import com.github.trfiles.configuration.implementations.json.JsonConfiguration;
import com.github.trfiles.configuration.implementations.properties.PropertiesConfiguration;
import com.github.trfiles.configuration.implementations.toml.TomlConfiguration;
import com.github.trfiles.configuration.implementations.xml.XmlConfiguration;
import com.github.trfiles.configuration.implementations.yaml.YamlConfiguration;
import com.github.trfiles.exceptions.UnknownImplementationException;
import com.github.trfiles.management.FileExtension;
import com.github.utilities.registries.Registry;

import java.util.Optional;
import java.util.Set;

public class ConfigurationRegistry extends Registry<Set<FileExtension>, ConfigurationEntry> {
    private ConfigurationRegistry() {
        register(YamlConfiguration.ENTRY.extensions(), YamlConfiguration.ENTRY);
        register(TomlConfiguration.ENTRY.extensions(), TomlConfiguration.ENTRY);
        register(PropertiesConfiguration.ENTRY.extensions(), PropertiesConfiguration.ENTRY);
        register(XmlConfiguration.ENTRY.extensions(), XmlConfiguration.ENTRY);
        register(JsonConfiguration.ENTRY.extensions(), JsonConfiguration.ENTRY);
    }

    private record Holder() {
        private static final ConfigurationRegistry INSTANCE = new ConfigurationRegistry();
    }

    public static ConfigurationRegistry getInstance() {
        return Holder.INSTANCE;
    }

    public static ConfigurationEntry newConfiguration(ConfigurationEntry entry) {
        getInstance().register(entry.extensions(), entry);
        return entry;
    }

    public static boolean isKnown(FileExtension extension) {
        for (Set<FileExtension> fileExtensions : getInstance().keys()) {
            if (fileExtensions.contains(extension)) return true;
        }
        return false;
    }

    public static Optional<ConfigurationEntry> retrieve(FileExtension extension) {
        for (Set<FileExtension> fileExtensions : getInstance().keys()) {
            if (fileExtensions.contains(extension)) return getInstance().retrieve(fileExtensions);
        }
        return Optional.empty();
    }

    public static ConfigurationEntry retrieveOrNull(FileExtension extension) {
        return retrieve(extension).orElse(null);
    }

    public static ConfigurationEntry retrieveOrThrown(FileExtension extension) {
        return retrieve(extension).orElseThrow(() ->
                new UnknownImplementationException("The implementation with extension '" + extension +
                        "' is not found inside the ConfigurationRegistry."));
    }

}
