package com.lonestarcell.mtn.controller.util;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.AbstractDataBean;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.model.admin.IModel;
import com.lonestarcell.mtn.model.util.EnumPermission;
import com.lonestarcell.mtn.spring.fundamo.repo.LedgerAccount001Repo;
import com.vaadin.data.Item;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid;

public class AllRowsActionsUILedger
		extends
		AbstractAllRowsActionsUI<LedgerAccount001Repo, AbstractDataBean, TextChangeListenerSub<AbstractDataBean>> {

	private static final long serialVersionUID = 1L;
	private Logger log = LogManager.getLogger(AllRowsActionsUILedger.class
			.getName());

	public AllRowsActionsUILedger(IModel< LedgerAccount001Repo > mLedger, Grid grid, In in,
			boolean allowDateFilters, boolean isHeader,
			PaginationUIController pageC) {
		super(in, allowDateFilters, isHeader, pageC);
		this.setIModel(mLedger);
		this.setGrid(grid);
		this.init();
	}
	

	@Override
	protected void attachBtnExportOps() {
		
		if( !permSet.contains( EnumPermission.REPORT_EXPORT_LEDGER.val )){
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
				new DPgExportLimitUILedger( pageC, model, in, new ArrayList<Item>(), moreDropDown );
			}

		});
	}

}
