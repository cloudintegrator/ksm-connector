package com.ksm.api;

import java.util.function.UnaryOperator;

import com.ksm.internal.KeeperConfigPropertiesProvider;
import org.mule.runtime.api.component.ComponentIdentifier;
import org.mule.runtime.ast.api.ComponentAst;
import org.mule.runtime.config.api.dsl.model.ConfigurationParameters;
import org.mule.runtime.config.api.dsl.model.ResourceProvider;
import org.mule.runtime.config.api.dsl.model.properties.ConfigurationPropertiesProvider;
import org.mule.runtime.config.api.dsl.model.properties.ConfigurationPropertiesProviderFactory;

public class KeeperConfigPropertiesProviderFactory implements ConfigurationPropertiesProviderFactory {
    public static final ComponentIdentifier KSM_CONFIGURATION_PROPERTIES = ComponentIdentifier.builder().namespace("keeper-properties").name("config").build();

    @Override
    public ComponentIdentifier getSupportedComponentIdentifier() {
        return KSM_CONFIGURATION_PROPERTIES;
    }

    @Override
    public ConfigurationPropertiesProvider createProvider(ConfigurationParameters parameters, ResourceProvider externalResourceProvider) {
        KeeperConfigPropertiesProvider keeperConfigPropertiesProvider=new KeeperConfigPropertiesProvider(null,externalResourceProvider);
        return keeperConfigPropertiesProvider;
    }

}
