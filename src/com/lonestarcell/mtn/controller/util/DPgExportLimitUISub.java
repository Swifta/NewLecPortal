package com.lonestarcell.mtn.controller.util;

import java.util.Collection;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.vaadin.haijian.Exporter;

import com.lonestarcell.mtn.bean.AbstractDataBean;
import com.lonestarcell.mtn.bean.ExportSubscriber;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.model.admin.IModel;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class DPgExportLimitUISub extends
		AbstractDPgExportLimitUI<ExportSubscriber> {

	private static final long serialVersionUID = 1L;

	private Logger log = LogManager.getLogger();
	
	private String reportTitle = "Subscriber Transactions Report";

	public DPgExportLimitUISub(Collection<Item> records) {
		super(records);
	}

	public DPgExportLimitUISub(PaginationUIController pageC, IModel mSub,
			In in, Collection<Item> records, VerticalLayout cMoreOps) {
		super(pageC, mSub, in, records, cMoreOps);
	}

	@Override
	public void attachBtnXLS() {
		this.btnXLS.addClickListener(e -> {
			exportHandler(xlsExporter, btnXLS, reportTitle );
		});
	}

	@Override
	public void attachBtnCSV() {
		this.btnCSV.addClickListener(e -> {
			exportHandler(cSVExporter, btnCSV, reportTitle );

		});
	}

	@Override
	public void attachBtnPDF() {
		this.btnPDF.addClickListener(e -> {
			exportHandler(pdfExporter, btnPDF, reportTitle );

		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public BeanItemContainer<ExportSubscriber> getExportData() {
		BeanItemContainer<ExportSubscriber> c = new BeanItemContainer<>(
				ExportSubscriber.class);
		if (isMulti()) {
			Iterator<Item> itr = records.iterator();
			while (itr.hasNext()) {
				Item record = itr.next();
				ExportSubscriber t = new ExportSubscriber();
				t.setColumn1((String) record.getItemProperty("column1")
						.getValue());
				t.setColumn2((String) record.getItemProperty("column2")
						.getValue());
				t.setColumn3((String) record.getItemProperty("column3")
						.getValue());
				t.setColumn4((String) record.getItemProperty("column4")
						.getValue());
				t.setColumn5((String) record.getItemProperty("column5")
						.getValue());
				t.setColumn6((String) record.getItemProperty("column6")
						.getValue());
				t.setDate((String) record.getItemProperty("date").getValue());

				c.addBean(t);
			}

		} else {

			Out out = model.setExportData(in,
					new BeanItemContainer<AbstractDataBean>(
							AbstractDataBean.class));
			if (out.getStatusCode() != 1)
				return null;

			c = (BeanItemContainer<ExportSubscriber>) out.getData().getData();
			log.debug("Export data set with size: " + c.size());
		}

		return c;
	}

	@Override
	protected void renameColumns(Exporter exporter) {
		// transactionNumber, type, amount, status, payer, payee, date;
		exporter.setColumnHeader("column1", "Transaction No.");
		exporter.setColumnHeader("column2", "Type");
		exporter.setColumnHeader("column3", "Amount");
		exporter.setColumnHeader("column4", "Status");
		exporter.setColumnHeader("column5", "Payer");
		exporter.setColumnHeader("column6", "Payee");
		exporter.setColumnHeader("date", "Timestamp");
	}

	@Override
	protected BeanItemContainer<ExportSubscriber> getExportBean() {
		return new BeanItemContainer<ExportSubscriber>(ExportSubscriber.class);
	}

}
