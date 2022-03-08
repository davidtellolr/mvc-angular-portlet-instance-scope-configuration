package test.local.portlet;

import com.liferay.frontend.js.loader.modules.extender.npm.NPMResolver;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import test.local.configuration.ExampleConfiguration;
import test.local.constants.DTM2AngularPortletKeys;

/**
 * @author neotello
 */
@Component(
	configurationPid = "test.local.configuration.ExampleConfiguration",
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + DTM2AngularPortletKeys.DTM2Angular,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class DTM2AngularPortlet extends MVCPortlet {

	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderRequest.setAttribute(
			"mainRequire",
			_npmResolver.resolveModuleName("DTM2Angular") + " as main");

	
		try {
			_configuration=_configurationProvider.getCompanyConfiguration(ExampleConfiguration.class, CompanyThreadLocal.getCompanyId());
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_configuration.favoriteColor();
		try {
			_configurationProvider.getSystemConfiguration(ExampleConfiguration.class).favoriteColor();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       renderRequest.setAttribute(
                ExampleConfiguration.class.getName(), _configuration);

		
		super.doView(renderRequest, renderResponse);
	}

/*
	@Reference(unbind = "-")
	protected void setConfigurationProvider(
		ConfigurationProvider configurationProvider) {

		_configurationProvider = configurationProvider;
	}

	*/
   /* @Activate
    @Modified
    protected void activate(Map<String, Object> properties) {
        _configuration = ConfigurableUtil.createConfigurable(
        ExampleConfiguration.class, properties);
    }
	*/
    private volatile ExampleConfiguration _configuration;
    
    @Reference
    protected void setConfigurationProvider(ConfigurationProvider configurationProvider) {
        _configurationProvider = configurationProvider;
    }
    
	private ConfigurationProvider _configurationProvider;

    
	@Reference
	private NPMResolver _npmResolver;

}