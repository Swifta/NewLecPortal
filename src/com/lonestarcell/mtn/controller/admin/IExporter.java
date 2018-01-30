package com.lonestarcell.mtn.controller.admin;

import com.vaadin.data.util.BeanItemContainer;

public interface IExporter< T > {
	BeanItemContainer< T > getExportData();
	void attachBtnXLS();
	void attachBtnCSV();

}
