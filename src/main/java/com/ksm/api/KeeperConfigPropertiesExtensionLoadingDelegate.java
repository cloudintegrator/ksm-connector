package com.ksm.api;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.mule.metadata.api.builder.BaseTypeBuilder;
import org.mule.metadata.api.model.MetadataFormat;
import org.mule.runtime.api.meta.Category;
import org.mule.runtime.api.meta.model.declaration.fluent.ConfigurationDeclarer;
import org.mule.runtime.api.meta.model.declaration.fluent.ExtensionDeclarer;
import org.mule.runtime.api.meta.model.declaration.fluent.ParameterGroupDeclarer;
import org.mule.runtime.extension.api.loader.ExtensionLoadingContext;
import org.mule.runtime.extension.api.loader.ExtensionLoadingDelegate;

public class KeeperConfigPropertiesExtensionLoadingDelegate implements ExtensionLoadingDelegate {
    @Override
    public void accept(ExtensionDeclarer extensionDeclarer, ExtensionLoadingContext extensionLoadingContext) {
        Optional<Method> supportingJavaVersionsMethod = Arrays.stream(extensionDeclarer.getClass().getMethods()).filter((method) -> {
            return method.getName().equals("supportingJavaVersions");
        }).filter((method) -> {
            return Arrays.equals(method.getParameterTypes(), new Class[]{Set.class});
        }).findFirst();
        if (supportingJavaVersionsMethod.isPresent()) {
            try {
                extensionDeclarer = (ExtensionDeclarer) supportingJavaVersionsMethod.get().invoke(extensionDeclarer, new LinkedHashSet(Arrays.asList("1.8", "11", "17")));
            } catch (InvocationTargetException | IllegalAccessException var8) {
                throw new RuntimeException("Failed to initialize the extension when trying to declare `supportingJavaVersions` for the extension.", var8);
            }
        }
        ConfigurationDeclarer configurationDeclarer = extensionDeclarer.named("Keeper Security").withCategory(Category.SELECT).onVersion("1.0.3").fromVendor("Mulesoft").withConfig("config");

        ParameterGroupDeclarer defaultParameterGroup = configurationDeclarer.onDefaultParameterGroup();
        defaultParameterGroup.withRequiredParameter("Host").ofType(BaseTypeBuilder.create(MetadataFormat.JAVA).stringType().build());
        defaultParameterGroup.withRequiredParameter("Port").ofType(BaseTypeBuilder.create(MetadataFormat.JAVA).stringType().build());

    }
}
