package com.lonestarcell.mtn.controller.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lonestarcell.mtn.controller.admin.DUIControllable;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;

public class PaginationUIController implements DUIControllable, Serializable {

	private static final long serialVersionUID = 1L;
	private Logger log = LogManager.getLogger();

	private Map<String, Button> mapPageBtns;

	private Button btnNextH;
	private Button btnNextF;

	private Button btnPrevH;
	private Button btnPrevF;

	private Button btnAfterPrevH;
	private Button btnAfterPrevF;

	private Button btnBeforeNextH;
	private Button btnBeforeNextF;

	private int currentPage = 2;
	private int newPage = 1;
	private int pages = 0;
	private Label lbTotalRecords;
	private float pageLength;
	private long rowCount;

	public PaginationUIController() {
		mapPageBtns = new HashMap<>(8);
	}

	public Map<String, Button> getListPageBtns() {
		return mapPageBtns;
	}

	public long getRowCount() {
		return rowCount;
	}

	public void setRowCount(long rowCount) {
		this.rowCount = rowCount;
	}

	public float getPageLength() {
		return pageLength;
	}

	public void setPageLength(float pageLength) {
		this.pageLength = pageLength;
	}

	public int getNewPage() {
		return newPage;
	}

	public void setNewPage(int newPage) {
		this.newPage = newPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public Label getLbTotalRecords() {
		return lbTotalRecords;
	}

	public void setLbTotalRecords(Label lbTotalRecords) {
		this.lbTotalRecords = lbTotalRecords;
		if( this.lbTotalRecords == null )
			log.info( "lbTotalRecords is null" );
		else
			log.info( "lbTotalRecords is set" );
	}

	@Override
	public void attachCommandListeners() {
		this.attachOPTotalRecords();
	}

	public void next() {

		if (currentPage < pages) {
			// currentPage++;
			newPage = currentPage + 1;
		}

		navigation();

	}

	public void prev() {

		if (currentPage > 1) {
			// currentPage--;
			newPage = currentPage - 1;
		}
		navigation();

	}

	public void beforeNext() {

		newPage = Integer.valueOf(btnBeforeNextH.getData().toString());
		if (newPage == currentPage)
			return;
		navigation();

	}

	public void afterPrev() {
		newPage = Integer.valueOf(btnAfterPrevH.getData().toString());
		if (newPage == currentPage)
			return;
		navigation();
	}

	public void init() {
		this.initBtns();
		this.attachCommandListeners();
	}

	public int getPages() {
		return pages;
	}

	private int getTotalPages(Long total) {

		int pages = 0;
		Float pageLength = 15F;
		this.setPageLength(pageLength);
		pages = (int) Math.ceil(total / pageLength);

		return pages;

	}

	private void attachOPTotalRecords() {

		Long total = Long.valueOf(this.lbTotalRecords.getValue().toString()
				.replaceAll(",", ""));
		setRowCount(total);
		initNavigation(total);

		this.lbTotalRecords.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {

				Long total = Long.valueOf(event.getProperty().getValue()
						.toString().replaceAll(",", ""));
				setRowCount(total);
				currentPage = 2;
				newPage = 1;
				initNavigation(total);

			}

		});
	}

	private void initBtns() {

		btnNextH = mapPageBtns.get("nextH");
		btnNextF = mapPageBtns.get("nextF");

		btnPrevH = mapPageBtns.get("prevH");
		btnPrevF = mapPageBtns.get("prevF");

		btnAfterPrevH = mapPageBtns.get("afterPrevH");
		btnAfterPrevF = mapPageBtns.get("afterPrevF");

		if( btnAfterPrevH == null ){
			log.info( "btnAfterPrevH is null" );
			return;
		} 
		
		btnAfterPrevH.setCaption(currentPage + "");
		btnAfterPrevF.setCaption(currentPage + "");

		btnAfterPrevH.setData(currentPage);
		btnAfterPrevF.setData(currentPage);

		btnBeforeNextH = mapPageBtns.get("beforeNextH");
		btnBeforeNextF = mapPageBtns.get("beforeNextF");

		btnBeforeNextH.setCaption((currentPage + 1) + "");
		btnBeforeNextF.setCaption((currentPage + 1) + "");

		btnBeforeNextH.setData((currentPage + 1));
		btnBeforeNextF.setData((currentPage + 1));
	}

	private void initNavigation(Long total) {
		log.debug("Pagination initialization with total: " + total);
		pages = getTotalPages(total);
		if (pages <= 1) {

			btnPrevH.addStyleName("sn-invisible");
			btnPrevF.addStyleName("sn-invisible");
			btnPrevH.setEnabled(false);
			btnPrevF.setEnabled(false);

			btnNextH.addStyleName("sn-invisible");
			btnNextF.addStyleName("sn-invisible");
			btnNextH.setEnabled(false);
			btnNextF.setEnabled(false);

			btnAfterPrevH.setVisible(false);
			btnAfterPrevF.setVisible(false);
			btnBeforeNextH.setVisible(false);
			btnBeforeNextF.setVisible(false);

		} else if (pages == 2) {

			btnPrevH.addStyleName("sn-invisible");
			btnPrevF.addStyleName("sn-invisible");
			btnPrevH.setEnabled(false);
			btnPrevF.setEnabled(false);

			btnNextH.addStyleName("sn-invisible");
			btnNextF.addStyleName("sn-invisible");
			btnNextH.setEnabled(false);
			btnNextF.setEnabled(false);

			btnAfterPrevH.setVisible(true);
			btnAfterPrevF.setVisible(true);
			btnBeforeNextH.setVisible(true);
			btnBeforeNextF.setVisible(true);

			this.navigation();

		} else if (pages >= 3) {

			btnPrevH.addStyleName("sn-invisible");
			btnPrevF.addStyleName("sn-invisible");
			btnPrevH.setEnabled(false);
			btnPrevF.setEnabled(false);

			btnNextH.removeStyleName("sn-invisible");
			btnNextF.removeStyleName("sn-invisible");
			btnNextH.setEnabled(true);
			btnNextF.setEnabled(true);

			btnAfterPrevH.setVisible(true);
			btnAfterPrevF.setVisible(true);
			btnBeforeNextH.setVisible(true);
			btnBeforeNextF.setVisible(true);

			pages = getTotalPages(total);
			this.navigation();

		}

	}

	private void navigation() {


		if (pages == 2) {
			setActivePage();

		} else if (pages >= 3) {

			if (newPage > 1) {

				btnPrevH.removeStyleName("sn-invisible");
				btnPrevF.removeStyleName("sn-invisible");
				btnPrevH.setEnabled(true);
				btnPrevF.setEnabled(true);

			} else {

				btnPrevH.addStyleName("sn-invisible");
				btnPrevF.addStyleName("sn-invisible");
				btnPrevH.setEnabled(false);
				btnPrevF.setEnabled(false);
			}

			log.debug("Current page: " + newPage + " Total pages: " + pages);

			if (newPage < pages) {

				btnNextH.removeStyleName("sn-invisible");
				btnNextF.removeStyleName("sn-invisible");
				btnNextH.setEnabled(true);
				btnNextF.setEnabled(true);

				log.debug("btnNextH set to visible.");

			} else {

				btnNextH.addStyleName("sn-invisible");
				btnNextF.addStyleName("sn-invisible");
				btnNextH.setEnabled(false);
				btnNextF.setEnabled(false);

				log.debug("btnNextH set to INvisible.");
			}

			setActivePage();

		}

	}

	private void setActivePage() {
		if (newPage >= 1 && newPage <= pages) {

			if (currentPage < newPage) {

				btnBeforeNextH.setCaption(newPage + "");
				btnBeforeNextF.setCaption(newPage + "");

				btnBeforeNextH.setDescription(newPage + "/" + pages);
				btnBeforeNextF.setDescription(newPage + "/" + pages);

				btnBeforeNextH.setData(newPage);
				btnBeforeNextF.setData(newPage);

				btnBeforeNextH.addStyleName("sn-cur-page");
				btnBeforeNextF.addStyleName("sn-cur-page");

				btnAfterPrevH.setCaption((currentPage) + "");
				btnAfterPrevF.setCaption((currentPage) + "");

				btnBeforeNextH.setDescription((currentPage) + "/" + pages);
				btnBeforeNextF.setDescription((currentPage) + "/" + pages);

				btnAfterPrevH.setData((currentPage));
				btnAfterPrevF.setData((currentPage));

				btnAfterPrevH.removeStyleName("sn-cur-page");
				btnAfterPrevF.removeStyleName("sn-cur-page");

				currentPage = newPage;

			} else {

				btnAfterPrevH.setCaption(newPage + "");
				btnAfterPrevF.setCaption(newPage + "");

				btnAfterPrevH.setDescription(newPage + "/" + pages);
				btnAfterPrevF.setDescription(newPage + "/" + pages);

				btnAfterPrevH.setData(newPage);
				btnAfterPrevF.setData(newPage);

				btnAfterPrevH.addStyleName("sn-cur-page");
				btnAfterPrevF.addStyleName("sn-cur-page");

				btnBeforeNextH.setCaption((currentPage) + "");
				btnBeforeNextF.setCaption((currentPage) + "");

				btnBeforeNextH.setDescription((currentPage) + "/" + pages);
				btnBeforeNextF.setDescription((currentPage) + "/" + pages);

				btnBeforeNextH.setData((currentPage));
				btnBeforeNextF.setData((currentPage));

				btnBeforeNextH.removeStyleName("sn-cur-page");
				btnBeforeNextF.removeStyleName("sn-cur-page");
				currentPage = newPage;
			}
		}
		
	}

}
