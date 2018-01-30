package com.lonestarcell.mtn.controller.admin;

import java.util.Collection;
import java.util.Iterator;

import com.lonestarcell.mtn.bean.AbstractDataBean;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutUser;
import com.lonestarcell.mtn.controller.util.PaginationUIController;
import com.lonestarcell.mtn.model.admin.IModel;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.VerticalLayout;

public class DPgExportLimitUIUser extends AbstractDPgExportLimitUI< OutUser >{

	private static final long serialVersionUID = 1L;

	public DPgExportLimitUIUser(Collection<Item> records) {
		super(records);
	}
	
	public DPgExportLimitUIUser(PaginationUIController pageC, IModel mSub, In in,
			Collection<Item> records, VerticalLayout cMoreOps) {
		super( pageC, mSub, in, records, cMoreOps );
	}
	
	

	@Override
	public void attachBtnXLS() {
		this.btnXLS.addClickListener(e -> {
			if( !isMulti() )
				if (!combosSet())
					return;
			
				BeanItemContainer< OutUser > c = this.getExportData();
				if ( c == null ) {
					showWarn("Failed to load export data. Please try again/contact support.");
					return;
				}
				
				xlsExporter.setContainerToBeExported(c);
				xlsExporter.removeStyleName("sn-display-none");
				btnXLS.setVisible(false);
				showSuccess("File ready. Click download icon");

			});
	}

	@Override
	public void attachBtnCSV() {
		this.btnCSV
				.addClickListener(e -> {
					if( !isMulti() )
						if ( !combosSet() )
							return;
					// Load data
					BeanItemContainer< OutUser > c = this.getExportData();
					if ( c == null ) {
						showWarn("Failed to load export data. Please try again/contact support.");
						return;
					}

					cSVExporter.setContainerToBeExported(c);
					cSVExporter.removeStyleName("sn-display-none");
					btnCSV.setVisible(false);
					showSuccess("File ready. Click download icon");

				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public BeanItemContainer< OutUser > getExportData() {
		BeanItemContainer< OutUser > c = new BeanItemContainer<>(OutUser.class);
		if (isMulti()) {
			Iterator<Item> itr = records.iterator();
			while (itr.hasNext()) {
				Item record = itr.next();
				Property<?> column = record.getItemProperty("username");
				String val = column.getValue().toString();
				OutUser u = new OutUser();
				u.setUsername( val );;
				c.addBean( u );
			}

		} else {

			Out out = model.setExportData(in,
					new BeanItemContainer<AbstractDataBean>(
							AbstractDataBean.class));
			if (out.getStatusCode() != 1)
				return null;
			c = (BeanItemContainer< OutUser >) out.getData().getData();
		}
		
		return c;
	}
}
