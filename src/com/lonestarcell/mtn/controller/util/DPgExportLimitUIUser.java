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

					try {
						if (!isMulti())
							if (!combosSet())
								return;

						btnXLS.setIcon(FontAwesome.SPINNER);
						btnXLS.setImmediate(true);
						btnXLS.setComponentError(null);

						BeanItemContainer<ExportUser> c = this.getExportData();

						btnXLS.setIcon(FontAwesome.FILE_EXCEL_O);
						btnXLS.setEnabled(true);

						if (c == null) {
							showWarn("Failed to load export data. Please try again/contact support.");
							return;
						}

						Table table = new Table( "Portal Users Report" );
						table.setContainerDataSource( c );
						xlsExporter.setTableToBeExported( table );
						renameColumns( xlsExporter );
						
						// xlsExporter.setContainerToBeExported(c);
						xlsExporter.removeStyleName("sn-display-none");
						btnXLS.setVisible(false);
						showSuccess("File ready. Click download icon");

					} catch (Exception ex) {
						ex.printStackTrace();
						btnXLS.setComponentError(new UserError(
								"Data export failed. Please try again/contact support."));
						btnXLS.setIcon(FontAwesome.FILE_TEXT);
						btnXLS.setEnabled(true);
					}

				});
	}

	@Override
	public void attachBtnCSV() {
		this.btnCSV
				.addClickListener(e -> {

					try {
						if (!isMulti())
							if (!combosSet())
								return;

						btnCSV.setIcon(FontAwesome.SPINNER);
						btnCSV.setImmediate(true);
						btnCSV.setComponentError(null);
						// Load data
						BeanItemContainer<ExportUser> c = this.getExportData();
						btnCSV.setIcon(FontAwesome.FILE_TEXT);
						btnCSV.setEnabled(true);

						if (c == null) {
							showWarn("Failed to load export data. Please try again/contact support.");
							return;
						}

						Table table = new Table( "Portal Users Report" );
						table.setContainerDataSource( c );
						cSVExporter.setTableToBeExported( table );
						renameColumns( cSVExporter );
						// cSVExporter.setContainerToBeExported(c);
						cSVExporter.removeStyleName("sn-display-none");
						btnCSV.setVisible(false);
						showSuccess("File ready. Click download icon");
					} catch (Exception ex) {
						ex.printStackTrace();
						btnCSV.setComponentError(new UserError(
								"Data export failed. Please try again/contact support."));
						btnCSV.setIcon(FontAwesome.FILE_TEXT);
						btnCSV.setEnabled(true);

					}

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
				u.setColumn1( ( String ) record.getItemProperty("username").getValue() );
				u.setColumn2(  ( String ) record.getItemProperty("email").getValue()  );
				u.setColumn3(  ( String ) record.getItemProperty("org").getValue()  );
				u.setColumn4(  ( String ) record.getItemProperty("userStatus").getValue()  );
				u.setColumn5(  ( String ) record.getItemProperty("profile").getValue()  );
				u.setColumn6(  ( String ) record.getItemProperty("lastLogin").getValue()  );
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
	private void renameColumns( Exporter exporter ){
		exporter.setColumnHeader( "column1", "Username" );
		exporter.setColumnHeader( "column2", "email" );
		exporter.setColumnHeader( "column3", "Organization" );
		exporter.setColumnHeader( "column4", "Status" );
		exporter.setColumnHeader( "column5", "Profile" );
		exporter.setColumnHeader( "column6", "Lastest login Timestamp" );
		exporter.setColumnHeader( "date", "Creation Timestamp" );
	}

	@Override
	protected BeanItemContainer<ExportUser> getExportBean() {
		return new BeanItemContainer<ExportUser>(ExportUser.class);
	}
}
