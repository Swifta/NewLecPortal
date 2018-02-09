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
import com.vaadin.ui.VerticalLayout;

public class DPgExportLimitUIMerchant extends
		AbstractDPgExportLimitUI< ExportMerchant > {

	private static final long serialVersionUID = 1L;
	private String reportTitle = "Merchant Transactions Report";

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
		this.btnCSV
				.addClickListener(e -> {
					exportHandler( pdfExporter, btnPDF, reportTitle );
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
	
	
	@Override
	protected void renameColumns( Exporter exporter ){
		
		exporter.setColumnHeader( "column1", "Name" );
		exporter.setColumnHeader( "column2", "MSISDN" );
		exporter.setColumnHeader( "column3", "Transaction No." );
		exporter.setColumnHeader( "column4", "Type" );
		exporter.setColumnHeader( "column5", "Amount" );
		exporter.setColumnHeader( "column6", "Status" );
		exporter.setColumnHeader( "column7", "Channel" );
		exporter.setColumnHeader( "column8", "Payee" );
		exporter.setColumnHeader( "column9", "Payer" );
		exporter.setColumnHeader( "desc", "Description" );
		exporter.setColumnHeader( "entryDate", "Timestamp" );
		
	}

	@Override
	protected BeanItemContainer<ExportMerchant> getExportBean() {
		return new BeanItemContainer<ExportMerchant>(ExportMerchant.class);
	}
}
