package com.lonestarcell.mtn.controller.util;

import java.util.Collection;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.bean.AbstractDataBean;
import com.lonestarcell.mtn.bean.ExportSubscriber;
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

public class DPgExportLimitUISub extends
		AbstractDPgExportLimitUI< ExportSubscriber > {

	private static final long serialVersionUID = 1L;
	
	private Logger log = LogManager.getLogger();
	public DPgExportLimitUISub(Collection<Item> records) {
		super(records);
	}

	public DPgExportLimitUISub(PaginationUIController pageC, IModel mSub,
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

						BeanItemContainer<ExportSubscriber> c = this
								.getExportData();

						btnXLS.setIcon(FontAwesome.FILE_EXCEL_O);
						btnXLS.setEnabled(true);

						if (c == null) {
							showWarn("Failed to load export data. Please try again/contact support.");
							return;
						}

						Table table = new Table( "Subscriber Transaction Report" );
						table.setContainerDataSource( c );
						xlsExporter.setTableToBeExported( table );
						//xlsExporter.setContainerToBeExported(c);
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
						BeanItemContainer<ExportSubscriber> c = this
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
	public BeanItemContainer< ExportSubscriber > getExportData() {
		BeanItemContainer<ExportSubscriber> c = new BeanItemContainer<>(
				ExportSubscriber.class);
		if (isMulti()) {
			Iterator<Item> itr = records.iterator();
			while (itr.hasNext()) {
				Item record = itr.next();
				ExportSubscriber t = new ExportSubscriber();
				t.setTransactionNumber( ( String ) record.getItemProperty( "column1" ).getValue() );
				t.setType( ( String ) record.getItemProperty( "column2" ).getValue() );
				t.setAmount( ( String ) record.getItemProperty( "column3" ).getValue() );
				t.setStatus( ( String ) record.getItemProperty( "column4" ).getValue() );
				t.setPayer( ( String ) record.getItemProperty( "column5" ).getValue() );
				t.setPayee( ( String ) record.getItemProperty( "column6" ).getValue() );
				t.setDate( ( String ) record.getItemProperty( "date" ).getValue() );

				c.addBean(t);
			}

		} else {

			Out out = model.setExportData(in,
					new BeanItemContainer<AbstractDataBean>(
							AbstractDataBean.class));
			if (out.getStatusCode() != 1)
				return null;
			
			c = (BeanItemContainer<ExportSubscriber>) out.getData().getData();
			log.debug( "Export data set with size: "+c.size() );
		}

		return c;
	}
}
