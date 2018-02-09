package com.lonestarcell.mtn.controller.util;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.AbstractDataBean;
import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.model.admin.IModel;
import com.lonestarcell.mtn.model.util.DateFormatFac;
import com.lonestarcell.mtn.model.util.EnumPermission;
import com.lonestarcell.mtn.spring.fundamo.repo.Transaction001Repo;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
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

public class AllRowsActionsUISub
		extends
		AbstractAllRowsActionsUI<Transaction001Repo, AbstractDataBean, TextChangeListenerSub<AbstractDataBean>> {

	private static final long serialVersionUID = 1L;

	private Logger log = LogManager.getLogger(AllRowsActionsUISub.class
			.getName());

	public AllRowsActionsUISub(IModel<Transaction001Repo> mSub, Grid grid, In in,
			boolean allowDateFilters, boolean isHeader,
			PaginationUIController pageC) {
		super(in, allowDateFilters, isHeader, pageC);
		this.setIModel(mSub);
		this.setGrid(grid);
		this.init();
	}

	@Override
	protected void setIModel(IModel<Transaction001Repo> model) {
		this.model = model;
	}

	@Override
	protected void setOutTxnMeta() {
		outTxnMeta = inTxn.getMeta();
		
		/*
		outTxnMeta = new OutTxnMeta();
		outTxnMeta
				.setTotalRevenue(new ObjectProperty<String>("0", String.class));
		outTxnMeta
				.setTotalRecord(new ObjectProperty<String>("0", String.class));

		inTxn.setMeta( outTxnMeta ); */
		
		// Out out = model.searchMeta(in, outTxnMeta);
		// if (out.getStatusCode() != 1)
			// Notification.show(out.getMsg(), Notification.Type.ERROR_MESSAGE);

	}

	@Override
	protected void setNewPage(int page) {
		super.setNewPage(page);
		container.removeAllItems();
		inTxn.setPage( page );
		log.info( "New item: "+newPage );
		model.search(in, container);
		// format();

	}

	@Override
	protected void refreshGridData() {

		container.removeAllContainerFilters();
		container.removeAllItems();

		try {
			
			inTxn.setfDate((this.dFStartDate.getValue() == null) ? null
					: DateFormatFac.toString(this.dFStartDate.getValue()));
			
			inTxn.settDate((this.dFLastDate.getValue() == null) ? null
					: DateFormatFac.toString(this.dFLastDate.getValue()));
			
		} catch (Exception e) {
			Notification.show("Error parsing date object",
					Notification.Type.ERROR_MESSAGE);
			
			return;
		}

		// TODO validate response
		Out out = model.search(in, container);
		//model.searchMeta(in, outTxnMeta);
		if (out.getStatusCode() != 1) {
			Notification.show(out.getMsg(), Notification.Type.ERROR_MESSAGE);
			return;
		}

		format();

	}

	@Override
	protected void addFilterField(BeanItemContainer<AbstractDataBean> container,
			HeaderRow filterHeader, String itemId) {
		TextField tF = new TextField();
		tF.setStyleName("sn-tf-filter");
		tF.setDescription("Search");
		tF.setInputPrompt("Filter/Search");
		tF.setWidth("100%");
		HeaderCell cFilter = filterHeader.getCell(itemId);
		cFilter.setComponent(tF);

		TextChangeListenerSub<AbstractDataBean> tChangeListener = getTextChangeListner(
				container, itemId, tF);
		tF.addTextChangeListener(tChangeListener);
		tFSearchFields.add(tF);

		ShortcutListener enterListener = getSearchShortcutListener(tF, itemId,
				container);
		tF.addFocusListener(getSearchFocusListener(tF, enterListener));
		tF.addBlurListener(getSearchBlurListener(tF, enterListener));
		tF.addValueChangeListener( e->{
			
			/*
			Map< String, Object > searchMap = inTxn.getSearchMap();
			TextField f =  ( TextField )searchMap.get( "prevTF" );
			
			if( !tF.equals( f ) )
				tF.clear();
			
			searchMap.put( "prevTF", tF );
			
			*/
		});

		tF.setDescription("Type to filter / Press Enter to search");

	}

	@Override
	protected ShortcutListener getSearchShortcutListener(TextField tF,
			String itemId, BeanItemContainer<AbstractDataBean> container) {

		return new ShortcutListener("", KeyCode.ENTER, null) {
			private static final long serialVersionUID = 1L;

			@Override
			public void handleAction(Object sender, Object target) {
				log.debug("Enter search shortcut clicked.");
				
				UserError uError = new UserError( "Enter at least 4 characters to search." );;
				Object obj = tF.getValue();
				String searchStr = ( String ) obj;
				tF.setComponentError( null );
				
				if( searchStr == null  ) {
					tF.setComponentError( uError );
					return;
				}
				
				searchStr = searchStr.trim();
				if( searchStr.isEmpty() || searchStr.length() < 4 ) {
					tF.setComponentError( uError );
					return;
				}

				container.removeAllItems();
				log.debug("Proceeding with search.");

				In in = new In();

				BData<InTxn> inBData = new BData<>();
				inTxn.setPage(1);
				inBData.setData(inTxn);
				in.setData(inBData);
				
				// Set OutTxnMeta
				
				// Set OutTxnMeta
				
				/*
				OutTxnMeta outTxnMeta = new OutTxnMeta();
				outTxnMeta
						.setTotalRevenue(new ObjectProperty<String>("0", String.class));
				outTxnMeta
						.setTotalRecord(new ObjectProperty<String>("0", String.class));
				inTxn.setMeta( outTxnMeta ); */
				
				

				Out out = model.search(in, container);

				// model.searchMeta(in, outTxnMeta);

				if (out.getStatusCode() != 1) {
					Notification.show(out.getMsg(),
							Notification.Type.WARNING_MESSAGE);
					return;
				}
				
				format();

			}

		};

	}

	@Override
	protected void initDataExportUI() {

		/*
		 * PopupView popupView = new PopupView( "Export", new DataExportUITxn(
		 * container ) ); // popupView.setIcon( FontAwesome.SHARE_SQUARE_O );
		 * popupView.addStyleName(
		 * "sn-data-export-popup borderless link icon-align-left" );
		 * popupView.setDescription( "Export data" ); cDataExport.addComponent(
		 * popupView ); this.btnExport.setVisible( false );
		 */

	}

	@SuppressWarnings("unchecked")
	@Override
	protected void setBeanItemContainer() {
		container = ((BeanItemContainer<AbstractDataBean>) ((GeneratedPropertyContainer) grid
				.getContainerDataSource()).getWrappedContainer());

	}

	@Override
	protected void setGrid(Grid grid) {
		this.grid = grid;

	}

	@Override
	protected TextChangeListenerSub<AbstractDataBean> getTextChangeListner(
			BeanItemContainer<AbstractDataBean> container, String itemId,
			TextField tF) {
		return new TextChangeListenerSub<AbstractDataBean>(container, inTxn,
				itemId, tF);
	}

	@Override
	protected void attachBtnExportOps() {
		
		if( !permSet.contains( EnumPermission.REPORT_EXPORT_TRANSACTION.val )){
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
				new DPgExportLimitUISub( pageC, model, in, new ArrayList<Item>(), moreDropDown );
			}

		});

		this.btnExportOps.addBlurListener(new BlurListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void blur(BlurEvent event) {

				// btnExportOps.removeStyleName( "sn-btn-export-ops-active" );
				moreDropDown.removeStyleName("sn-data-export-active");
				log.debug(" Export menu blurred.");

			}

		});

		this.btnExportOps.addFocusListener(new FocusListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void focus(FocusEvent event) {

				// btnExportOps.removeStyleName( "sn-btn-export-ops-active" );
				// / moreDropDown.addStyleName( "sn-data-export-active" );
				log.debug(" Export menu focus.");

			}

		});

	}

}
