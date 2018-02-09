package com.lonestarcell.mtn.controller.admin;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.lonestarcell.mtn.bean.BData;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.controller.main.DLoginUIController;
import com.lonestarcell.mtn.design.admin.DDashTxnUIDesign;
import com.lonestarcell.mtn.model.admin.MDash;
import com.lonestarcell.mtn.model.util.EnumPermission;
import com.lonestarcell.mtn.model.util.NumberFormatFac;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class DDashTxnUI extends DDashTxnUIDesign implements
		DUserUIInitializable<DDashUI, DDashTxnUI>, DUIControllable {

	private static final long serialVersionUID = 1L;

	private DDashUI ancestor;
	private Logger log = LogManager.getLogger();
	private Item record;
	private OutTxnMeta data;
	private ApplicationContext springAppContext;
	private Set< Integer > permSet;

	private MDash mDash;

	DDashTxnUI(DDashUI a) {
		this.setSpringAppContext(a.getSpringAppContext());
		this.setPermSet( null );
		init(a);
	}
	
	

	public Set<Integer> getPermSet() {
		return permSet;
	}



	@SuppressWarnings("unchecked")
	public void setPermSet(Set<Integer> permSet) {
		 this.permSet = UI.getCurrent().getSession().getAttribute( Set.class );
	}



	public ApplicationContext getSpringAppContext() {
		return springAppContext;
	}

	public void setSpringAppContext(ApplicationContext springAppContext) {
		this.springAppContext = springAppContext;
	}

	public OutTxnMeta getData() {
		return data;
	}

	public void setData(OutTxnMeta data) {
		this.data = data;
	}

	public MDash getmDash() {
		return mDash;
	}

	public void setmDash(MDash mDash) {
		this.mDash = mDash;
	}

	public Item getRecord() {
		return record;
	}

	public void setRecord(Item record) {
		this.record = record;
	}

	@Override
	public void attachCommandListeners() {
		this.attachBtnRefresh();

	}

	private void attachBtnRefresh() {

		this.btnRefreshSub.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				loadDataHandlerSub();

			}

		});

		this.btnRefreshMer.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				loadDataHandlerMer();

			}

		});

		this.btnRefreshTxn.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				loadDataHandlerTxn();

			}

		});

		this.btnRefreshUser.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				loadDataHandlerUser();

			}

		});
	}

	@Override
	public void setHeader() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setContent() {
		this.setData(new OutTxnMeta());
		this.setRecord(new BeanItem<>(this.getData()));
		this.setmDash(new MDash(getCurrentUserId(), getCurrentUserSession(),
				springAppContext));
		setHeader();
		setFooter();
		swap(this);
		attachCommandListeners();

		this.setDashData();
	}

	@Override
	public void swap(Component cuid) {
		// ancestor.setHeight("100%");
		// cuid.setHeight("100%");

		// ancestor.addStyleName("sn-p");
		// cuid.addStyleName("sn-c");

		cuid.setHeight("100%");
		ancestor.getAncestorUI().getcMainContent().setHeight("100%");
		// ancestor.getAncestorUI().getcMainContent().setWidth( "100%" );
		ancestor.setHeight("100%");

		log.debug("Users height: " + cuid.getHeight());

		ancestor.swap(cuid);

	}

	@Override
	public void init(DDashUI a) {
		setAncestorUI(a);
		setContent();

	}

	@Override
	public void setFooter() {
		// TODO Auto-generated method stub

	}

	@Override
	public DDashUI getAncestorUI() {
		return ancestor;
	}

	@Override
	public void setAncestorUI(DDashUI a) {
		this.ancestor = a;

	}

	@Override
	public DDashTxnUI getParentUI() {
		return this;
	}

	@Override
	public void setParentUI(DDashTxnUI p) {
		// TODO Auto-generated method stub

	}

	private void setDashData() {

		VerticalLayout v = new VerticalLayout();
		// Sub
		if( permSet.contains( EnumPermission.DASH_SUBSCRIBER_STAT.val )){
			this.loadDataHandlerSub();
			this.cCardSub.setVisible( true );
			v = null;
		} else {
			this.cCardSub.setVisible( false );
		}
		
		// Mer
		if( permSet.contains( EnumPermission.DASH_MERCHANT_STAT.val )){
			this.loadDataHandlerMer();
			this.cCardMer.setVisible( true );
			v = null;
		} else {
			this.cCardMer.setVisible( false );
		}
		
		// Tran
		if( permSet.contains( EnumPermission.DASH_TRANSACTION_STAT.val )){
			this.loadDataHandlerTxn();
			this.cCardTxn.setVisible( true );
			v = null;
		} else {
			this.cCardTxn.setVisible( false );
		}
		
		
		// User
		if( permSet.contains( EnumPermission.DASH_SYS_USER_STAT.val )){
			this.loadDataHandlerUser();
			this.cCardUser.setVisible( true );
			
			v = null;
		} else {
			this.cCardUser.setVisible( false );
		}
		
		if( v != null ){
			v.setWidth( "100%" );
			v.setHeight( "100%" );
			this.swap( v );
		}
		

	}

	private Out loadDataSub() {
		In in = new In();
		BData<InTxn> inBData = new BData<>();
		InTxn inTxn = new InTxn();
		inBData.setData(inTxn);
		in.setData(inBData);
		return mDash.setDashMetaSub(in, this.getRecord());
	}

	private Out loadDataMer() {
		In in = new In();
		BData<InTxn> inBData = new BData<>();
		InTxn inTxn = new InTxn();
		inBData.setData(inTxn);
		in.setData(inBData);
		return mDash.setDashMetaMer(in, this.getRecord());
	}

	private Out loadDataTxn() {
		In in = new In();
		BData<InTxn> inBData = new BData<>();
		InTxn inTxn = new InTxn();
		inBData.setData(inTxn);
		in.setData(inBData);
		return mDash.setDashMetaTxn(in, this.getRecord());
	}

	private Out loadDataUser() {
		In in = new In();
		BData<InTxn> inBData = new BData<>();
		InTxn inTxn = new InTxn();
		inBData.setData(inTxn);
		in.setData(inBData);
		return mDash.setDashMetaUser(in, this.getRecord());
	}

	private void loadDataHandlerSub() {

		Out out = this.loadDataSub();
		if (out.getStatusCode() != 1) {
			Notification.show("Subscriber stats loading failed. ",
					Notification.Type.WARNING_MESSAGE);
			return;
		} else {
			try {
				formatSub();
				Notification.show("Data loaded successfully.",
						Notification.Type.HUMANIZED_MESSAGE);
			} catch (Exception ex) {
				Notification.show("Some data formating failed.",
						Notification.Type.WARNING_MESSAGE);
				ex.printStackTrace();
			}
		}

	}

	private void loadDataHandlerMer() {

		Out out = this.loadDataMer();
		if (out.getStatusCode() != 1) {
			Notification.show("Merchant stats loading failed. ",
					Notification.Type.WARNING_MESSAGE);
			return;
		} else {
			try {
				formatMer();
				Notification.show("Data loaded successfully.",
						Notification.Type.HUMANIZED_MESSAGE);
			} catch (Exception ex) {
				Notification.show("Some data formating failed.",
						Notification.Type.WARNING_MESSAGE);
				ex.printStackTrace();
			}
		}

	}

	private void loadDataHandlerTxn() {

		Out out = this.loadDataTxn();
		if (out.getStatusCode() != 1) {
			Notification.show("Transaction stats loading failed. ",
					Notification.Type.WARNING_MESSAGE);
			return;
		} else {
			try {
				formatTxn();
				Notification.show("Data loaded successfully.",
						Notification.Type.HUMANIZED_MESSAGE);
			} catch (Exception ex) {
				Notification.show("Some data formating failed.",
						Notification.Type.WARNING_MESSAGE);
				ex.printStackTrace();
			}
		}

	}

	private void loadDataHandlerUser() {

		Out out = this.loadDataUser();
		if (out.getStatusCode() != 1) {
			Notification.show("User stats loading failed. ",
					Notification.Type.WARNING_MESSAGE);
			return;
		} else {
			try {
				formatUser();
				Notification.show("Data loaded successfully.",
						Notification.Type.HUMANIZED_MESSAGE);
			} catch (Exception ex) {
				Notification.show("Some data formating failed.",
						Notification.Type.WARNING_MESSAGE);
				ex.printStackTrace();
			}
		}

	}

	private void formatSub() {

		Long other = this.getData().getTotalSubOther();
		String sOther = "N/A";
		if (other != null)
			sOther = NumberFormatFac.toThousands(other.toString());

		Long success = this.getData().getTotalSubSuccess();
		String sSuccess = "N/A";
		if (success != null)
			sSuccess = NumberFormatFac.toThousands(success.toString());

		Long total = this.getData().getTotalSub();
		String sTotal = "N/A";
		if (total != null)
			sTotal = NumberFormatFac.toThousands(total.toString());

		this.getData().setsTotalSubOther(sOther);
		this.getData().setsTotalSubSuccess(sSuccess);
		this.getData().setsTotalSub(sTotal);

		this.btnSubOther.setCaption(this.getData().getsTotalSubOther());
		this.btnSubSuccess.setCaption(this.getData().getsTotalSubSuccess());
		this.btnSubTotal.setCaption(this.getData().getsTotalSub());

		Long per = this.getData().getPerSub();
		this.lbSub.setValue(per + "");
		if (per >= 80) {
			this.lbSubPer.setStyleName("sn-percentage-sign sn-green");
		} else if (per >= 50) {
			this.lbSubPer.addStyleName("sn-percentage-sign sn-orange");
		} else {
			this.lbSubPer.addStyleName("sn-percentage-sign sn-red");
		}

	}

	private void formatMer() {

		// Totals

		Long other = this.getData().getTotalMerOther();
		String sOther = "N/A";
		if (other != null)
			sOther = NumberFormatFac.toThousands(other.toString());

		Long success = this.getData().getTotalMerSuccess();
		String sSuccess = "N/A";
		if (success != null)
			sSuccess = NumberFormatFac.toThousands(success.toString());

		Long total = this.getData().getTotalMer();
		String sTotal = "N/A";
		if (total != null)
			sTotal = NumberFormatFac.toThousands(total.toString());

		this.getData().setsTotalMerOther(sOther);
		this.getData().setsTotalMerSuccess(sSuccess);
		this.getData().setsTotalMer(sTotal);

		this.btnMerOther.setCaption(this.getData().getsTotalMerOther());
		this.btnMerSuccess.setCaption(this.getData().getsTotalMerSuccess());
		this.btnMerTotal.setCaption(this.getData().getsTotalMer());

		Long per = this.getData().getPerMer();
		this.lbMer.setValue(per + "");
		if (per >= 80) {
			this.lbMerPer.setStyleName("sn-percentage-sign sn-green");
		} else if (per >= 50) {
			this.lbMerPer.addStyleName("sn-percentage-sign sn-orange");
		} else {
			this.lbMerPer.addStyleName("sn-percentage-sign sn-red");
		}

	}

	private void formatTxn() {

		Long other = this.getData().getTotalTxnOther();
		String sOther = "N/A";
		if (other != null)
			sOther = NumberFormatFac.toThousands(other.toString());

		Long success = this.getData().getTotalTxnSuccess();
		String sSuccess = "N/A";
		if (success != null)
			sSuccess = NumberFormatFac.toThousands(success.toString());

		Long total = this.getData().getTotalTxn();
		String sTotal = "N/A";
		if (total != null)
			sTotal = NumberFormatFac.toThousands(total.toString());

		this.getData().setsTotalTxnOther(sOther);
		this.getData().setsTotalTxnSuccess(sSuccess);
		this.getData().setsTotalTxn(sTotal);

		this.btnTxnOther.setCaption(this.getData().getsTotalTxnOther());
		this.btnTxnSuccess.setCaption(this.getData().getsTotalTxnSuccess());
		this.btnTxnTotal.setCaption(this.getData().getsTotalTxn());

		Long per = this.getData().getPerTxn();
		this.lbTxn.setValue(per + "");
		if (per >= 80) {
			this.lbTxnPer.setStyleName("sn-percentage-sign sn-green");
		} else if (per >= 50) {
			this.lbTxnPer.addStyleName("sn-percentage-sign sn-orange");
		} else {
			this.lbTxnPer.addStyleName("sn-percentage-sign sn-red");
		}

	}

	private void formatUser() {

		Long other = this.getData().getTotalUserOther();
		String sOther = "N/A";
		if (other != null)
			sOther = NumberFormatFac.toThousands(other.toString());

		Long success = this.getData().getTotalUserSuccess();
		String sSuccess = "N/A";
		if (success != null)
			sSuccess = NumberFormatFac.toThousands(success.toString());

		Long total = this.getData().getTotalUser();
		String sTotal = "N/A";
		if (total != null)
			sTotal = NumberFormatFac.toThousands(total.toString());

		this.getData().setsTotalUserOther(sOther);
		this.getData().setsTotalUserSuccess(sSuccess);
		this.getData().setsTotalUser(sTotal);

		this.btnUserOther.setCaption(this.getData().getsTotalUserOther());
		this.btnUserActive.setCaption(this.getData().getsTotalUserSuccess());
		this.btnUserTotal.setCaption(this.getData().getsTotalUser());

		Long per = this.getData().getPerUser();
		this.lbUser.setValue(per + "");
		if (per >= 80) {
			this.lbUserPer.setStyleName("sn-percentage-sign sn-green");
		} else if (per >= 50) {
			this.lbUserPer.addStyleName("sn-percentage-sign sn-orange");
		} else {
			this.lbUserPer.addStyleName("sn-percentage-sign sn-red");
		}
	}

	private long getCurrentUserId() {
		return (long) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.USER_ID);
	}

	private String getCurrentUserSession() {
		return (String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.SESSION_VAR);
	}

	private String getCurrentTimeCorrection() {
		return (String) UI.getCurrent().getSession()
				.getAttribute(DLoginUIController.TIME_CORRECTION);
	}

}
