package com.lonestarcell.mtn.controller.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.vaadin.haijian.CSVExporter;
import org.vaadin.haijian.ExcelExporter;

import com.lonestarcell.mtn.controller.admin.DUIInitializable;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractDDataExportUI<M, O, I> implements DUIInitializable {

	private Logger log = LogManager.getLogger();
	
	protected BeanItemContainer<O> container;
	
	private VerticalLayout cMoreOps;
	protected M model;
	protected I in;
	private Collection<Item> records;
	
	

	
	
	
	public AbstractDDataExportUI(  M model, I in, Collection<Item> records, VerticalLayout cMoreOps ){
		this.model = model;
		this.in = in;
		this.records = records;
		this.cMoreOps = cMoreOps;
		
	}
	
	

	
	
	
	protected  abstract boolean setAllExportRecords( M model, I in, Collection<Item>records );


	@Override
	public void setHeader() {
		// TODO Auto-generated method stub
		
	}
	
	


	public VerticalLayout getcMoreOps() {
		return cMoreOps;
	}


	public void setcMoreOps(VerticalLayout cMoreOps) {
		this.cMoreOps = cMoreOps;
	}


	@Override
	public void setContent() {
		
		if( container == null ){
			Notification.show( "Container is null.", Notification.Type.ERROR_MESSAGE );
			return;
		} else {
			log.debug( "Data container size: "+container.size(), this );
		}
		
		
		cMoreOps.removeAllComponents();
		
		DateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		
		String fileName = sdf.format( new Date() )+"_"+ "_by_" + this.getCurrentUsername();
		
		Table table = new Table( fileName );
		
		
		table.setContainerDataSource( container );
		
		ExcelExporter excelExporter = new ExcelExporter();
		excelExporter.setDateFormat("yyyy-MM-dd");
		excelExporter.setContainerToBeExported( container );//.setTableToBeExported( table );
		excelExporter.setCaption( "" );
		excelExporter.setIcon( FontAwesome.FILE_EXCEL_O );
		excelExporter.addStyleName( "borderless icon-align-top sn-btn-export-ops" );
		excelExporter.setDescription( "Export all records in .xls" );
		excelExporter.setDownloadFileName( fileName );
		
		/*PdfExporter pdfExporter = new PdfExporter();
		
		pdfExporter.setDateFormat("yyyy-MM-dd");
		pdfExporter.setTableToBeExported( table );
		pdfExporter.setCaption( "" );
		pdfExporter.setIcon( FontAwesome.FILE_PDF_O );
		pdfExporter.addStyleName( "borderless icon-align-top sn-btn-export-ops" );
		pdfExporter.setDescription( "Export all records in .pdf" ); */
		
		CSVExporter cSVExporter = new CSVExporter();
		
		cSVExporter.setDateFormat("yyyy-MM-dd");
		cSVExporter.setTableToBeExported( table );
		cSVExporter.setCaption( "" );
		cSVExporter.setIcon( FontAwesome.FILE_TEXT_O );
		cSVExporter.addStyleName( "borderless icon-align-top sn-btn-export-ops" );
		cSVExporter.setDescription( "Export all records in .csv" );
		cSVExporter.setDownloadFileName( fileName );
		
		
		cMoreOps.addComponent( excelExporter );
		// cMoreOps.addComponent( pdfExporter );
		cMoreOps.addComponent( cSVExporter );
		cMoreOps.addStyleName( "sn-data-export-active" );
		cMoreOps.setWidth( "60px" );
		
		
		
		
	}
	
	


	@Override
	public void swap(Component cuid) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void init() {
		if( !setAllExportRecords( model, in, records ) )
			return;
		
		this.setContent();
		
	}


	@Override
	public void setFooter() {
		// TODO Auto-generated method stub
		
	}
	
	
	private String getCurrentUsername(){
		return ( String ) UI.getCurrent().getSession().getAttribute( DLoginUIController.USERNAME );
	}
}
