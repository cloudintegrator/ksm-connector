package com.ksm.internal;

import java.util.Optional;

import org.mule.runtime.properties.api.ConfigurationPropertiesProvider;
import org.mule.runtime.properties.api.ConfigurationProperty;

public class KeeperConfigPropertiesProvider implements ConfigurationPropertiesProvider {

    @Override
    public Optional<? extends ConfigurationProperty> provide(String s) {
        return Optional.empty();
    }

    @Override
    public String getDescription() {
        return null;
    }
}
