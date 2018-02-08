package com.lonestarcell.mtn.controller.util;

import java.util.Collection;
import java.util.Iterator;

import org.vaadin.haijian.Exporter;

import com.lonestarcell.mtn.bean.AbstractDataBean;
import com.lonestarcell.mtn.bean.ExportUser;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.model.admin.IModel;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.UserError;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class DPgExportLimitUIUser extends AbstractDPgExportLimitUI<ExportUser> {

	private static final long serialVersionUID = 1L;
	private String reportTitle = "Portal Users Report";

	public DPgExportLimitUIUser(Collection<Item> records) {
		super(records);
	}

	public DPgExportLimitUIUser(PaginationUIController pageC, IModel mSub,
			In in, Collection<Item> records, VerticalLayout cMoreOps) {
		super(pageC, mSub, in, records, cMoreOps);
	}

	@Override
	public void attachBtnXLS() {
		this.btnXLS
				.addClickListener(e -> {

					exportHandler( xlsExporter, btnXLS, reportTitle );
					
				});
	}

	@Override
	public void attachBtnCSV() {
		this.btnCSV
				.addClickListener(e -> {
					exportHandler( cSVExporter, btnCSV, reportTitle );

				});
	}
	
	@Override
	public void attachBtnPDF() {
		this.btnPDF
				.addClickListener(e -> {
					exportHandler( pdfExporter, btnPDF, reportTitle );

				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public BeanItemContainer<ExportUser> getExportData() {
		BeanItemContainer<ExportUser> c = new BeanItemContainer<>(ExportUser.class);
		if (isMulti()) {
			Iterator<Item> itr = records.iterator();
			while (itr.hasNext()) {
				Item record = itr.next();
				
				ExportUser u = new ExportUser();
				u.setColumn1( ( String ) record.getItemProperty("column1").getValue() );
				u.setColumn2(  ( String ) record.getItemProperty("column2").getValue()  );
				u.setColumn3(  ( String ) record.getItemProperty("column3").getValue()  );
				u.setColumn4(  ( String ) record.getItemProperty("column4").getValue()  );
				u.setColumn5(  ( String ) record.getItemProperty("column5").getValue()  );
				u.setColumn6(  ( String ) record.getItemProperty("column6").getValue()  );
				u.setDate( ( String ) record.getItemProperty("date").getValue()  );
				
				c.addBean(u);
			}

		} else {

			Out out = model.setExportData(in,
					new BeanItemContainer<AbstractDataBean>(
							AbstractDataBean.class));
			if (out.getStatusCode() != 1)
				return null;
			c = (BeanItemContainer<ExportUser>) out.getData().getData();
		}

		return c;
	}
	
	// username, email, lastLogin, org, userStatus, profile, date;
	@Override
	protected void renameColumns( Exporter exporter ){
		exporter.setColumnHeader( "column1", "Username" );
		exporter.setColumnHeader( "column2", "email" );
		exporter.setColumnHeader( "column3", "Organization" );
		exporter.setColumnHeader( "column4", "Status" );
		exporter.setColumnHeader( "column5", "Profile" );
		exporter.setColumnHeader( "column6", "Latest login Timestamp" );
		exporter.setColumnHeader( "date", "Creation Timestamp" );
	}

	@Override
	protected BeanItemContainer<ExportUser> getExportBean() {
		return new BeanItemContainer<ExportUser>(ExportUser.class);
	}
}
