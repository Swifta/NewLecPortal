package com.lonestarcell.mtn.controller.util;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.AbstractDataBean;
import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.controller.admin.AbstractDPgExportLimitUI;
import com.lonestarcell.mtn.controller.admin.DPgExportLimitUISub;
import com.lonestarcell.mtn.model.admin.IModel;
import com.lonestarcell.mtn.model.admin.MSub;
import com.lonestarcell.mtn.model.util.DateFormatFac;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class AllRowsActionsUIMerchant
		extends
		AllRowsActionsUISub {

	private static final long serialVersionUID = 1L;
	private Logger log = LogManager.getLogger(AllRowsActionsUIMerchant.class
			.getName());

	public AllRowsActionsUIMerchant(IModel mSub, Grid grid, In in,
			boolean allowDateFilters, boolean isHeader,
			PaginationUIController pageC) {
		super(mSub,grid,in,
				allowDateFilters,isHeader,
				 pageC);
	}
	

	@Override
	protected void attachBtnExportOps() {

		this.btnExportOps.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				log.debug( "Initializing data export.", this );
				new DPgExportLimitUISub( pageC, model, in, new ArrayList<Item>(), moreDropDown );
			}

		});
	}

}
