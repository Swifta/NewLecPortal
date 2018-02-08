package com.lonestarcell.mtn.controller.util;

import java.util.Collection;
import java.util.Iterator;

import org.vaadin.haijian.Exporter;

import com.lonestarcell.mtn.bean.AbstractDataBean;
import com.lonestarcell.mtn.bean.ExportLedger;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.model.admin.IModel;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.UserError;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class DPgExportLimitUILedger extends
		AbstractDPgExportLimitUI<ExportLedger> {

	private static final long serialVersionUID = 1L;
	private String reportTitle = "Ledger Report";

	public DPgExportLimitUILedger(Collection<Item> records) {
		super(records);
	}

	public DPgExportLimitUILedger(PaginationUIController pageC, IModel mSub,
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
					exportHandler(cSVExporter, btnCSV, reportTitle );
				});
	}
	
	@Override
	public void attachBtnPDF() {
		this.btnPDF
				.addClickListener(e -> {
					exportHandler(pdfExporter, btnPDF, reportTitle);
				});
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public BeanItemContainer<ExportLedger> getExportData() {
		BeanItemContainer<ExportLedger> c = new BeanItemContainer<>(
				ExportLedger.class);
		if (isMulti()) {
			Iterator<Item> itr = records.iterator();
			while (itr.hasNext()) {
				Item record = itr.next();
				ExportLedger t = new ExportLedger();
				t.setColumn1( ( String ) record.getItemProperty( "column1" ).getValue() );
				t.setColumn2( ( String ) record.getItemProperty( "column2" ).getValue() );
				t.setColumn3( ( String ) record.getItemProperty( "column3" ).getValue() );
				t.setDate( ( String ) record.getItemProperty( "date" ).getValue() );
				c.addBean(t);
			}

		} else {

			Out out = model.setExportData(in,
					new BeanItemContainer<AbstractDataBean>(
							AbstractDataBean.class));
			if (out.getStatusCode() != 1)
				return null;
			c = (BeanItemContainer<ExportLedger>) out.getData().getData();
		}

		return c;
	}
	
	
	//accNo, name,  amount, date;
	
	@Override
	protected void renameColumns( Exporter exporter ){
		
		exporter.setColumnHeader( "column1", "Acc. No." );
		exporter.setColumnHeader( "column2", "Name" );
		exporter.setColumnHeader( "column3", "Amount" );
		exporter.setColumnHeader( "date", "Latest Timestamp" );
		
	}

	@Override
	protected BeanItemContainer<ExportLedger> getExportBean() {
		return new BeanItemContainer< ExportLedger >( ExportLedger.class );
	}
}
