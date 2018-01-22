package com.lonestarcell.mtn.controller.admin;

public class DSubRegUI extends DTxnUI implements DUserUIInitializable<DMainUI,ISubUI >, DUIControllable {

	private static final long serialVersionUID = 1L;
	DSubRegUI(DMainUI a) {
		super(a);
	}
	
	@Override
	public void setContent() {
		setHeader();
		setFooter();
		// swap( new DTxnStateUI( getParentUI() ) ); 
		swap( new DTxnStateSubRegArchiveUI( getParentUI() ) );
		attachCommandListeners();
	}

	
	

}
