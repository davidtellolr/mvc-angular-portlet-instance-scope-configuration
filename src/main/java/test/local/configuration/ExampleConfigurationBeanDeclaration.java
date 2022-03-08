package test.local.configuration;

import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;

import org.osgi.service.component.annotations.Component;

@Component
public class ExampleConfigurationBeanDeclaration
    implements ConfigurationBeanDeclaration {

    @Override
    public Class getConfigurationBeanClass() {
        return ExampleConfiguration.class;
    }

}
