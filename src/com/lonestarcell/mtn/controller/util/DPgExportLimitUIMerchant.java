package com.lonestarcell.mtn.controller.util;

import java.util.Collection;
import java.util.Iterator;

import org.vaadin.haijian.Exporter;

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

						Table table = new Table( "Merchant Transaction Report" );
						table.setContainerDataSource( c );
						xlsExporter.setTableToBeExported( table );
						//xlsExporter.setContainerToBeExported(c);
						renameColumns( xlsExporter );
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
						renameColumns( cSVExporter );
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
				t.setColumn1( ( String ) record.getItemProperty( "column1" ).getValue() );
				t.setColumn2( ( String ) record.getItemProperty( "column2" ).getValue() );
				t.setColumn3( ( String ) record.getItemProperty( "column3" ).getValue() );
				t.setColumn4( ( String ) record.getItemProperty( "column4" ).getValue() );
				t.setColumn5( ( String ) record.getItemProperty( "column5" ).getValue() );
				t.setColumn6( ( String ) record.getItemProperty( "column6" ).getValue() );
				t.setColumn7( ( String ) record.getItemProperty( "column7" ).getValue() );
				t.setColumn9( ( String ) record.getItemProperty( "column9" ).getValue() );
				t.setColumn8( ( String ) record.getItemProperty( "column10" ).getValue() );
				t.setDesc( ( String ) record.getItemProperty( "column8" ).getValue() );
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
	
	// name, msisdn,  tno, type, amount,  status, channel, desc, payee, payer, entryDate ;
	// desc switched for column10
	
	
	private void renameColumns( Exporter exporter ){
		
		exporter.setColumnHeader( "column1", "Name" );
		exporter.setColumnHeader( "column2", "MSISDN" );
		exporter.setColumnHeader( "column3", "Transaction No." );
		exporter.setColumnHeader( "column4", "Type" );
		exporter.setColumnHeader( "column5", "Amount" );
		exporter.setColumnHeader( "column6", "Status" );
		exporter.setColumnHeader( "column7", "Channel" );
		exporter.setColumnHeader( "column8", "Payer" );
		exporter.setColumnHeader( "column9", "Payeee" );
		exporter.setColumnHeader( "desc", "Description" );
		exporter.setColumnHeader( "entryDate", "Timestamp" );
		
	}

	@Override
	protected BeanItemContainer<ExportMerchant> getExportBean() {
		return new BeanItemContainer<ExportMerchant>(ExportMerchant.class);
	}
}
