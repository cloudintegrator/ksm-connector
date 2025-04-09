package com.ksm.internal;


import org.mule.runtime.config.api.dsl.model.ResourceProvider;
import org.mule.runtime.config.api.dsl.model.properties.ConfigurationProperty;
import org.mule.runtime.config.api.dsl.model.properties.DefaultConfigurationPropertiesProvider;

import java.util.Optional;

public class KeeperConfigPropertiesProvider extends DefaultConfigurationPropertiesProvider {


    public KeeperConfigPropertiesProvider(String fileLocation, ResourceProvider resourceProvider) {
        super(fileLocation, resourceProvider);
    }

    public Optional<ConfigurationProperty> getConfigurationProperty(String configurationAttributeKey) {
        if(configurationAttributeKey.startsWith("ksm::")){
            String effectiveKey=configurationAttributeKey.substring("ksm::".length());
            return Optional.of(new ConfigurationProperty() {
                @Override
                public Object getRawValue() {
                    return "localhost";
                }

                @Override
                public Object getSource() {
                    return effectiveKey;
                }

                @Override
                public String getKey() {
                    return effectiveKey;
                }
            });
        }

        return Optional.ofNullable((ConfigurationProperty)this.configurationAttributes.get(configurationAttributeKey));
    }
}
