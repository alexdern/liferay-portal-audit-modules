package ru.alexdern.liferay.security.config;

import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD(id = "ru.alexdern.liferay.security.config.AuditConfiguration")
public interface AuditConfiguration {

    @Meta.AD(required = false)
    public String transport();

    @Meta.AD(deflt="10",required = false)
    public String numberOfRecordsDisplayed();

    @Meta.AD(deflt="asc",required = false)
    public String sortOrder();

    @Meta.AD(required = false)
    public String unit();

}
