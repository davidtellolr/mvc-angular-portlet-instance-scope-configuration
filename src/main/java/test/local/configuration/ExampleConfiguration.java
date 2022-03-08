package test.local.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
		category = "DTM Portlet New", scope = ExtendedObjectClassDefinition.Scope.COMPANY
	)

@Meta.OCD(id = "test.local.configuration.ExampleConfiguration",
description = "Tello POC Configuration",
name = "DTM Config")
public interface ExampleConfiguration {

    @Meta.AD(
        deflt = "blue",
        required = false
    )
    public String favoriteColor();

    @Meta.AD(required = false)
    public int itemsPerPage();

}