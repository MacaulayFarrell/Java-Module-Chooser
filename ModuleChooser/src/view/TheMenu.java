package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;

public class TheMenu extends MenuBar {
	
	private MenuItem load, save, exit, about;

	public TheMenu() {      

		Menu menu;

		//Create file menu 
		
		menu = new Menu("_File");

		//Load button
		
		load = new MenuItem("_Load Student Data");
		load.setAccelerator(KeyCombination.keyCombination("SHORTCUT+L"));
		menu.getItems().add(load);

		//Save button
		
		save = new MenuItem("_Save Student Data");
		save.setAccelerator(KeyCombination.keyCombination("SHORTCUT+S"));
		menu.getItems().add(save);

		
		 menu.getItems().add( new SeparatorMenuItem());

		//exit button
		exit = new MenuItem("E_xit");
		exit.setAccelerator(KeyCombination.keyCombination("SHORTCUT+X"));
		menu.getItems().add(exit);
		
		this.getMenus().add(menu); 


		//Help bar
		
		menu = new Menu("_Help");

		//'About' menu item
		
		about = new MenuItem("_About");
		about.setAccelerator(KeyCombination.keyCombination("SHORTCUT+A"));
		menu.getItems().add(about);

		this.getMenus().add(menu); 
	}

	//=========================================
	//METHODS/EVENT HANDLERS
	//=========================================
	
	public void addLoadHandler(EventHandler<ActionEvent> handler) {
		load.setOnAction(handler);
	}
    
    public void addSaveHandler(EventHandler<ActionEvent> handler) {
  		save.setOnAction(handler);
  	}
    
    public void addExitHandler(EventHandler<ActionEvent> handler) {
  		exit.setOnAction(handler);
  	}
    
    public void addAboutHandler(EventHandler<ActionEvent> handler) {
  		about.setOnAction(handler);
  	}
}
