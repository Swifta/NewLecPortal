package com.lonestarcell.mtn.controller.util;

import java.util.Collection;
import java.util.Iterator;

import com.lonestarcell.mtn.bean.AbstractDataBean;
import com.lonestarcell.mtn.bean.ExportMerchant;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.model.admin.IModel;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.UserError;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class DPgExportLimitUIMerchant extends
		AbstractDPgExportLimitUI< ExportMerchant > {

	private static final long serialVersionUID = 1L;

	public DPgExportLimitUIMerchant(Collection<Item> records) {
		super(records);
	}

	public DPgExportLimitUIMerchant(PaginationUIController pageC, IModel mSub,
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

						BeanItemContainer< ExportMerchant > c = this
								.getExportData();

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
						BeanItemContainer<ExportMerchant> c = this
								.getExportData();
						btnCSV.setIcon(FontAwesome.FILE_TEXT);
						btnCSV.setEnabled(true);

						if (c == null) {
							showWarn("Failed to load export data. Please try again/contact support.");
							return;
						}

						Table table = new Table( "Merchant Transaction Report" );
						table.setContainerDataSource( c );
						cSVExporter.setTableToBeExported( table );
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
	public BeanItemContainer<ExportMerchant> getExportData() {
		BeanItemContainer<ExportMerchant> c = new BeanItemContainer<>(
				ExportMerchant.class);
		if (isMulti()) {
			Iterator<Item> itr = records.iterator();
			while (itr.hasNext()) {
				Item record = itr.next();
				ExportMerchant t = new ExportMerchant();
				t.setName( ( String ) record.getItemProperty( "column1" ).getValue() );
				t.setMsisdn( ( String ) record.getItemProperty( "column2" ).getValue() );
				t.setTno( ( String ) record.getItemProperty( "column3" ).getValue() );
				t.setType( ( String ) record.getItemProperty( "column4" ).getValue() );
				t.setAmount( ( String ) record.getItemProperty( "column5" ).getValue() );
				t.setStatus( ( String ) record.getItemProperty( "column6" ).getValue() );
				t.setChannel( ( String ) record.getItemProperty( "column7" ).getValue() );
				t.setDesc( ( String ) record.getItemProperty( "column8" ).getValue() );
				t.setPayee( ( String ) record.getItemProperty( "column9" ).getValue() );
				t.setPayer( ( String ) record.getItemProperty( "column10" ).getValue() );
				t.setEntryDate( ( String ) record.getItemProperty( "date" ).getValue() );
				
				c.addBean(t);
			}

		} else {

			Out out = model.setExportData(in,
					new BeanItemContainer<AbstractDataBean>(
							AbstractDataBean.class));
			if (out.getStatusCode() != 1)
				return null;
			c = (BeanItemContainer<ExportMerchant>) out.getData().getData();
		}

		return c;
	}
}
