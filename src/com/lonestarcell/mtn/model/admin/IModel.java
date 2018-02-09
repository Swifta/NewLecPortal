package com.lonestarcell.mtn.model.admin;

import java.util.Collection;
import java.util.Date;

import com.lonestarcell.mtn.bean.AbstractDataBean;
import com.lonestarcell.mtn.bean.In;
import com.lonestarcell.mtn.bean.InTxn;
import com.lonestarcell.mtn.bean.Out;
import com.lonestarcell.mtn.bean.OutTxnMeta;
import com.lonestarcell.mtn.spring.fundamo.repo.Transaction001Repo;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;

public interface IModel< R > {
	Out search( In in, BeanItemContainer<AbstractDataBean> container );
	Out set(In in, BeanItemContainer<AbstractDataBean> container);
	Out setMeta(In in, OutTxnMeta outSubscriber);
	Out searchMeta(In in, OutTxnMeta outSubscriber);
	Out setExportData(In in, BeanItemContainer<AbstractDataBean> container);
	Out setExportDataMulti(In in,
			BeanItemContainer<AbstractDataBean> container, Collection<Item> records);
	Date getExportFDate( InTxn inTxn, R repo );
}
