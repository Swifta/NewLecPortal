package com.lonestarcell.mtn.controller.util;

import com.lonestarcell.mtn.model.admin.MSub;
import com.vaadin.data.Item;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.ui.Component;
import com.vaadin.ui.PopupView;

public class PopupViewPropertyValueGenerator extends PropertyValueGenerator<Component> {

	private static final long serialVersionUID = 1L;
	private MSub mSub;
	public PopupViewPropertyValueGenerator( MSub mSub ){
		this.mSub = mSub;
	}
	@Override
	public Component getValue(Item item, Object itemId,
			Object propertyId) {
		PopupView v = new PopupView("...",
				new RowActionsUISub(mSub, item));
		v.setWidth("100%");
		v.setHeight("100%");
		return v;
	}

	@Override
	public Class<Component> getType() {
		return Component.class;
	}
}
