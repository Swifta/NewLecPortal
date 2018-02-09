package com.lonestarcell.mtn.controller.util;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.AbstractDataBean;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.model.admin.IModel;
import com.lonestarcell.mtn.model.util.EnumPermission;
import com.lonestarcell.mtn.spring.fundamo.repo.Subscriber001Repo;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;

public class AllRowsActionsUISubReg
		extends
		AbstractAllRowsActionsUI<Subscriber001Repo, AbstractDataBean, TextChangeListenerSub<AbstractDataBean>> {

	private static final long serialVersionUID = 1L;
	private Logger log = LogManager.getLogger(AllRowsActionsUISubReg.class
			.getName());

	public AllRowsActionsUISubReg(IModel< Subscriber001Repo >mSub, Grid grid, In in,
			boolean allowDateFilters, boolean isHeader,
			PaginationUIController pageC) {
		super(in, allowDateFilters, isHeader, pageC);
		this.setIModel(mSub);
		this.setGrid(grid);
		this.init();
	}
	

	@Override
	protected void attachBtnExportOps() {
		
		if( !permSet.contains( EnumPermission.REPORT_EXPORT_SUBSCRIBER_REG.val )){
			this.btnExportOps.setVisible( false );
			this.btnExportOps.setEnabled( false );
			return;
			
		} else {
			this.btnExportOps.setVisible( true );
			this.btnExportOps.setEnabled( true );
		}

		this.btnExportOps.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				log.debug( "Initializing data export.", this );
				new DPgExportLimitUISubReg( pageC, model, in, new ArrayList<Item>(), moreDropDown );
			}

		});
	}


	





	@Override
	protected ShortcutListener getSearchShortcutListener(TextField tF,
			String itemId, BeanItemContainer<AbstractDataBean> container) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
