package com.gwt.client;

//package guiA.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;




public class MainView extends Composite {
	
	private HorizontalPanel rootPanel = new HorizontalPanel();
	private VerticalPanel contentPanel;
    private SelectionView selView;
	
    /* main view is the homepage where the user can choose the options to create the graph. Or can open saved options
     */
	public MainView(){
		initWidget(this.rootPanel);
		
		MenuView menu= new MenuView(this);
		rootPanel.add(menu);
		
		selView=new SelectionView(this);
		
	    contentPanel= new VerticalPanel();
		contentPanel.add(selView);
		rootPanel.add(contentPanel);
	}
	/*clears the panel and draw the open view*/
	public void openOpenView(){
		rootPanel.clear();
		contentPanel.clear();

		ExtendedMenuView menu= new ExtendedMenuView(this);
		rootPanel.add(menu);
		
		/*create openView */
		
	}
	
	/*clears the panel and draw the home view*/
     public void openHomeView(){
		rootPanel.clear();
		contentPanel.clear();
	    MenuView menu= new MenuView(this);
		rootPanel.add(menu);
		
		selView=new SelectionView(this);
		
	    contentPanel= new VerticalPanel();
		contentPanel.add(selView);
		rootPanel.add(contentPanel);
		}
   
   public void openCreateView(){
	   rootPanel.clear();
		contentPanel.clear();
	    ExtendedMenuView menu= new ExtendedMenuView(this);
		rootPanel.add(menu);
		CreateView cView= new CreateView();
		contentPanel.add(cView);
		rootPanel.add(contentPanel);
   }

}
