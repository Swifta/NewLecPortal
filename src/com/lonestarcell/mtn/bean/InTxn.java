package com.lonestarcell.mtn.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class InTxn {
	
	private String fDefaultDate, tDefaultDate, searchReqCur, searchToken, searchItronId, searchTokenStatus, searchTxnType, searchEmail, searchUserStatus, searchProfile, searchOrg, searchUsername, username, fDate, tDate, searchSID, searchMoID, searchMSISDN, searchMeterNo, searchStatusDesc;
	private int page;
	private int pageExportLimit, exportPgLen, exportFPgNo;
	private float pageSize;
	private boolean isExportOp = false;
	private boolean isPgNav = false;
	private Map< String, Object > searchMap = new HashMap< >();
	private Set< Short > permSet;
	
	private OutTxnMeta meta;

	
	
	public Set<Short> getPermSet() {
		return permSet;
	}
	public void setPermSet(Set<Short> permSet) {
		this.permSet = permSet;
	}
	public boolean isPgNav() {
		return isPgNav;
	}
	public void setPgNav(boolean isPgNav) {
		this.isPgNav = isPgNav;
	}
	public int getExportFPgNo() {
		return exportFPgNo;
	}
	public void setExportFPgNo(int exportFPgNo) {
		this.exportFPgNo = exportFPgNo;
	}
	public String getfDefaultDate() {
		return fDefaultDate;
	}
	public void setfDefaultDate(String fDefaultDate) {
		this.fDefaultDate = fDefaultDate;
	}
	public String gettDefaultDate() {
		return tDefaultDate;
	}
	public void settDefaultDate(String tDefaultDate) {
		this.tDefaultDate = tDefaultDate;
	}
	public OutTxnMeta getMeta() {
		return meta;
	}
	public void setMeta(OutTxnMeta meta) {
		this.meta = meta;
	}
	public Map<String, Object> getSearchMap() {
		return searchMap;
	}
	public void setSearchMap(Map<String, Object> searchMap) {
		this.searchMap = searchMap;
	}
	public int getExportPgLen() {
		return exportPgLen;
	}
	public void setExportPgLen(int exportPgLen) {
		this.exportPgLen = exportPgLen;
	}
	public boolean isExportOp() {
		return isExportOp;
	}
	public void setExportOp(boolean isExportOp) {
		this.isExportOp = isExportOp;
	}
	public float getPageSize() {
		return pageSize;
	}
	public void setPageSize(float pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageExportLimit() {
		return pageExportLimit;
	}
	public void setPageExportLimit(int pageExportLimit) {
		this.pageExportLimit = pageExportLimit;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getfDate() {
		return fDate;
	}
	public void setfDate(String fDate) {
		this.fDate = fDate;
	}
	public String gettDate() {
		return tDate;
	}
	public void settDate(String tDate) {
		this.tDate = tDate;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getSearchSID() {
		return searchSID;
	}
	public void setSearchSID(String searchSID) {
		this.searchSID = searchSID;
	}
	public String getSearchMoID() {
		return searchMoID;
	}
	public void setSearchMoID(String searchMoID) {
		this.searchMoID = searchMoID;
	}
	public String getSearchMSISDN() {
		return searchMSISDN;
	}
	public void setSearchMSISDN(String searchMSISDN) {
		this.searchMSISDN = searchMSISDN;
	}
	public String getSearchMeterNo() {
		return searchMeterNo;
	}
	public void setSearchMeterNo(String searchMeterNo) {
		this.searchMeterNo = searchMeterNo;
	}
	public String getSearchStatusDesc() {
		return searchStatusDesc;
	}
	public void setSearchStatusDesc(String searchStatusDesc) {
		this.searchStatusDesc = searchStatusDesc;
	}
	public String getSearchEmail() {
		return searchEmail;
	}
	public void setSearchEmail(String searchEmail) {
		this.searchEmail = searchEmail;
	}
	public String getSearchUserStatus() {
		return searchUserStatus;
	}
	public void setSearchUserStatus(String searchUserStatus) {
		this.searchUserStatus = searchUserStatus;
	}
	public String getSearchProfile() {
		return searchProfile;
	}
	public void setSearchProfile(String searchProfile) {
		this.searchProfile = searchProfile;
	}
	public String getSearchOrg() {
		return searchOrg;
	}
	public void setSearchOrg(String searchOrg) {
		this.searchOrg = searchOrg;
	}
	public String getSearchUsername() {
		return searchUsername;
	}
	public void setSearchUsername(String searchUsername) {
		this.searchUsername = searchUsername;
	}
	public String getSearchItronId() {
		return searchItronId;
	}
	public void setSearchItronId(String searchItronId) {
		this.searchItronId = searchItronId;
	}
	public String getSearchTokenStatus() {
		return searchTokenStatus;
	}
	public void setSearchTokenStatus(String searchTokenStatus) {
		this.searchTokenStatus = searchTokenStatus;
	}
	public String getSearchTxnType() {
		return searchTxnType;
	}
	public void setSearchTxnType(String searchTxnType) {
		this.searchTxnType = searchTxnType;
	}
	public String getSearchReqCur() {
		return searchReqCur;
	}
	public void setSearchReqCur(String searchReqCur) {
		this.searchReqCur = searchReqCur;
	}
	public String getSearchToken() {
		return searchToken;
	}
	public void setSearchToken(String searchToken) {
		this.searchToken = searchToken;
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
