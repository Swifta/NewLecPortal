package com.lonestarcell.mtn.controller.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.lonestarcell.mtn.bean.AbstractDataBean;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.controller.util.PaginationUIController;
import com.lonestarcell.mtn.design.admin.DTxnStateUIDesign;
import com.lonestarcell.mtn.model.admin.IModel;
import com.lonestarcell.mtn.model.admin.MSub;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractDTxnStateUI< T > extends DTxnStateUIDesign implements
		DUserUIInitializable<ISubUI, AbstractDTxnStateUI< T > >, DUIControllable {

	private static final long serialVersionUID = 1L;
	
	
	protected ISubUI ancestor;
	protected Logger log = LogManager.getLogger(AbstractDTxnStateUI.class.getName());
	protected Set< Short > permSet;

	protected IModel mSub;
	protected InTxn inTxn;

	private ApplicationContext springAppContext;

	AbstractDTxnStateUI( ISubUI a) {
		this(a.getSpringAppContext());
		this.setPermSet( a.getPermSet() );
		mSub = new MSub(getCurrentUserId(), getCurrentUserSession(),
				getCurrentTimeCorrection(), springAppContext );
		init(a);
	}

	/*
	 * Shared constructor by both DTxnStateUI [ Parent class ] &
	 * DTxnStateUIArchive [ Child class ]. Note init() is not called in this. It
	 * only set's up data objects
	 */
	protected AbstractDTxnStateUI(ApplicationContext cxt) {
		this.setSpringAppContext(cxt);
		this.setPermSet( null );
		inTxn = new InTxn();
		this.setInDate(inTxn, 1);
	}
	
	

	

	public Set<Short> getPermSet() {
		return permSet;
	}

	@SuppressWarnings("unchecked")
	public void setPermSet(Set<Short> permSet) {
		if( permSet == null )
			this.permSet = UI.getCurrent().getSession().getAttribute( Set.class );
		else
			this.permSet = permSet;
		
	}

	public ApplicationContext getSpringAppContext() {
		return springAppContext;
	}

	public void setSpringAppContext(ApplicationContext springAppContext) {
		this.springAppContext = springAppContext;
	}

	@Override
	public void attachCommandListeners() {

	}

	@Override
	public void setHeader() {
		this.lbDataTitle.setValue(" Transaction Records Today");
	}

	@Override
	public void setContent() {
		setHeader();
		setFooter();

		swap(this);
		attachCommandListeners();
		this.vlTrxnTable.addComponent(loadGridData(new BeanItemContainer<>(
				AbstractDataBean.class)));
		this.vlTrxnTable.setHeightUndefined();
		// this.vlTrxnTable.setWidth("1200px");
		this.vlTrxnTable.setWidth("100%");

	}

	@Override
	public void swap(Component cuid) {
		// ancestor.setHeight("100%");
		// cuid.setHeight("100%");

		// ancestor.addStyleName("sn-p");
		// cuid.addStyleName("sn-c");

		cuid.setHeight("100%");
		
		// TODO testing max content width
		cuid.setWidth( "100%" );
		((VerticalLayout)( (  Panel )cuid).getContent()).setWidth( "100%" );
		// VerticalLayout v = null;
		
		
		ancestor.getAncestorUI().getcMainContent().setHeight("100%");
		// ancestor.getAncestorUI().getcMainContent().setWidth( "100%" );
		ancestor.setHeight("100%");

		log.debug("Users height: " + cuid.getHeight());

		ancestor.swap(cuid);

	}

	@Override
	public void init(ISubUI a) {

		setAncestorUI(a);
		setContent();

	}

	@Override
	public void setFooter() {
		// TODO Auto-generated method stub

	}

	@Override
	public ISubUI getAncestorUI() {
		return ancestor;
	}

	@Override
	public void setAncestorUI(ISubUI a) {
		this.ancestor = a;

	}

	@Override
	public AbstractDTxnStateUI<T> getParentUI() {
		return this;
	}

	@Override
	public void setParentUI(AbstractDTxnStateUI<T> p) {
		// TODO Auto-generated method stub

	}

	protected abstract Grid loadGridData(
			BeanItemContainer<AbstractDataBean> beanItemContainer);

	protected  abstract T getHeaderController(IModel mSub, Grid grid,
			In in, PaginationUIController pageC);

	protected abstract T getFooterController(IModel mSub, Grid grid,
			In in, PaginationUIController pageC);

	protected long getCurrentUserId() {
		return (long) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USER_ID);
	}

	protected String getCurrentUserSession() {
		return (String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR);
	}

	protected String getCurrentTimeCorrection() {
		return (String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.TIME_CORRECTION);
	}

	protected void setInDate(InTxn inTxn, int dayOffSet) {

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();

		String tDate = sdf.format(cal.getTime());
		log.debug("To: " + tDate);

		inTxn.settDate(tDate);

		cal.add(Calendar.DAY_OF_MONTH, -1 * (dayOffSet));
		String fDate = sdf.format(cal.getTime());
		log.debug("From: " + fDate);

		inTxn.setfDate( "2010-02-01" );
		inTxn.settDate( "2010-02-03" );

	}

}
