package com.lonestarcell.mtn.controller.util;

import java.util.Collection;
import java.util.Iterator;

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

						xlsExporter.setContainerToBeExported(c);
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

						cSVExporter.setContainerToBeExported(c);
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
				u.setUsername( ( String ) record.getItemProperty("username").getValue() );
				u.setEmail(  ( String ) record.getItemProperty("email").getValue()  );
				u.setOrg(  ( String ) record.getItemProperty("org").getValue()  );
				u.setUserStatus(  ( String ) record.getItemProperty("userStatus").getValue()  );
				u.setProfile(  ( String ) record.getItemProperty("profile").getValue()  );
				u.setLastLogin(  ( String ) record.getItemProperty("lastLogin").getValue()  );
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
}
