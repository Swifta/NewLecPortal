package com.lonestarcell.mtn.controller.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.vaadin.haijian.CSVExporter;
import org.vaadin.haijian.ExcelExporter;
import org.vaadin.haijian.Exporter;

import com.lonestarcell.mtn.bean.AbstractDataBean;
import com.lonestarcell.mtn.bean.ExportSubReg;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.model.admin.IModel;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.UserError;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class DPgExportLimitUISubReg extends
		AbstractDPgExportLimitUI< ExportSubReg > {

	private static final long serialVersionUID = 1L;

	public DPgExportLimitUISubReg(Collection<Item> records) {
		super(records);
	}

	public DPgExportLimitUISubReg(PaginationUIController pageC, IModel mSub,
			In in, Collection<Item> records, VerticalLayout cMoreOps) {
		super(pageC, mSub, in, records, cMoreOps);
	}

	@Override
	public void attachBtnXLS() {
		this.btnXLS
				.addClickListener(e -> {

					exportHandler( xlsExporter, btnXLS );
				});
	}

	@Override
	public void attachBtnCSV() {
		this.btnCSV
				.addClickListener(e -> {
					exportHandler(cSVExporter, btnCSV);
				});
	}
	
	@Override
	public void attachBtnPDF() {
		this.btnPDF
				.addClickListener(e -> {
					exportHandler(pdfExporter, btnPDF);
				});
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public BeanItemContainer< ExportSubReg > getExportData() {
		BeanItemContainer< ExportSubReg > c = new BeanItemContainer<>(
				ExportSubReg.class);
		if (isMulti()) {
			Iterator<Item> itr = records.iterator();
			while (itr.hasNext()) {
				Item record = itr.next();			
				ExportSubReg t = new ExportSubReg();
				t.setColumn1( ( String ) record.getItemProperty("column1").getValue() );
				t.setColumn2( ( String ) record.getItemProperty("column2").getValue()  );
				t.setColumn3( ( String ) record.getItemProperty("column3").getValue()  );
				t.setColumn4( ( String ) record.getItemProperty("column4").getValue()  );
				t.setColumn5(  ( String ) record.getItemProperty("column5").getValue()  );
				t.setColumn6(  ( String ) record.getItemProperty("column6").getValue()  );
				t.setRegDate(  ( String ) record.getItemProperty("date").getValue()  );
				c.addBean(t);
			}

		} else {

			Out out = model.setExportData(in,
					new BeanItemContainer<AbstractDataBean>(
							AbstractDataBean.class));
			if (out.getStatusCode() != 1)
				return null;
			c = (BeanItemContainer< ExportSubReg >) out.getData().getData();
		}

		return c;
	}
	
	@Override
	protected void renameColumns( Exporter exporter ){
		// private String name, msisdn,  idNo, idType, dob, status, regDate;
		exporter.setColumnHeader( "column1", "NAME" );
		exporter.setColumnHeader( "column2", "MSISDN" );
		exporter.setColumnHeader( "column3", "ID Number" );
		exporter.setColumnHeader( "column4", "ID Type" );
		exporter.setColumnHeader( "column5", "Date of Birth" );
		exporter.setColumnHeader( "column6", "Status" );
		exporter.setColumnHeader( "regDate", "Registration Timestamp" );
	}
	

	@Override
	protected BeanItemContainer<ExportSubReg> getExportBean() {
		return new BeanItemContainer< ExportSubReg >( ExportSubReg.class );
	}
}
