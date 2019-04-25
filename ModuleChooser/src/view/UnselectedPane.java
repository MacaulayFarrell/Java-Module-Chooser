package view;

import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Module;

public class UnselectedPane extends HBox {
	//labels
	
	private Label lblTerm1Desc, lblTerm2Desc, lblReserveTerm1, lblCompulsoryModule, lblTerm1Selected, lblTerm2Selected, lblReserveTerm2, lblTerm1, lblTerm2;
	private TextField tfDescTerm1, tfDescTerm2;
	//list views
	
	private ListView<Module> lvTerm1Unselected, lvTerm2Unselected, lvTerm1Selected, lvTerm2Selected, lvCompulsoryModule;
	
	//observable lists for list views
	
	private ObservableList<Module> OListTerm1 = FXCollections.observableArrayList();
	private ObservableList<Module> OListTerm2 = FXCollections.observableArrayList();
	private ObservableList<Module> OListTerm1Selected = FXCollections.observableArrayList();
	private ObservableList<Module> OListTerm2Selected = FXCollections.observableArrayList();
	private ObservableList<Module> OListCompulsoryModule = FXCollections.observableArrayList();
	
	//buttons 
	
	private Button btnReset, btnSubmit;
	private Button btnAdd1, btnAdd2, btnRemove1, btnRemove2;
	
	//combo boxes for reserve modules
	private ComboBox<Module> reserveModuleTerm1, reserveModuleTerm2;
	public UnselectedPane() {
		
		//styling 
		this.setStyle("-fx-background-color:linear-gradient(#335EFF, #33C7FF)");
		
		//Labels
		
		lblTerm1Desc = new Label("Term 1 Modules");
		lblTerm1Desc.setFont(Font.font ("Arial", 13));
		
		lblTerm2Desc = new Label("Term 2 Modules");
		lblTerm2Desc.setFont(Font.font ("Arial", 13));
		
		lblReserveTerm1 = new Label("Reserve Term 1 Module");
		lblReserveTerm1.setFont(Font.font ("Arial", 13));
		
		lblCompulsoryModule = new Label("Compulsory Modules: ");
		lblCompulsoryModule.setFont(Font.font ("Arial", 13));
		
		lblTerm1Selected = new Label("Term 1 Selected Modules");
		lblTerm1Selected.setFont(Font.font ("Arial", 13));
		
		lblTerm2Selected = new Label("Term 2 Selected Modules");
		lblTerm2Selected.setFont(Font.font ("Arial", 13));
		
		lblReserveTerm2 = new Label("Reserve Term 2 Module ");
		lblReserveTerm2.setFont(Font.font ("Arial", 13));
		
		lblTerm1 = new Label("Term 1 Credits: ");
		lblTerm1.setFont(Font.font ("Arial", 11));
		
		lblTerm2 = new Label("Term 2 Credits: ");
		lblTerm2.setFont(Font.font ("Arial", 11));
		
		//text boxes
		
		tfDescTerm1 = new TextField();
		tfDescTerm1.setMaxSize(40, 40);
		tfDescTerm1.setEditable(false);
		
		tfDescTerm2 = new TextField();
		tfDescTerm2.setMaxSize(40, 40);
		tfDescTerm2.setEditable(false);
		
		//List views
		
		lvTerm1Unselected = new ListView<>(OListTerm1);
		lvTerm1Unselected.setPrefSize(150, 50);
		
		lvTerm2Unselected = new ListView<>(OListTerm2);
		lvTerm2Unselected.setPrefSize(150, 50);
		
		lvCompulsoryModule = new ListView<>(OListCompulsoryModule);
		lvCompulsoryModule.setPrefSize(150, 50);
		lvCompulsoryModule.setMaxHeight(50);
		
		lvTerm1Selected = new ListView<>(OListTerm1Selected);
		lvTerm1Selected.setPrefSize(150, 50);
		
		lvTerm2Selected = new ListView<>(OListTerm2Selected);
		lvTerm2Selected.setPrefSize(150, 50);
		
		//Buttons
		btnReset = new Button("Reset ");
		btnSubmit = new Button("Submit");
		
		btnAdd1 = new Button("Add ");
		btnAdd2 = new Button("Add ");
		
		btnRemove1 = new Button("Remove ");
		btnRemove2 = new Button("Remove");
		
		//ComboBox
		
		reserveModuleTerm2 = new ComboBox<Module>();
		reserveModuleTerm1 = new ComboBox<Module>();
		
		//HBox for credits 
	
		//HBox for buttons
		
		HBox buttonPane1 = new HBox();
		buttonPane1.setSpacing(30);
		buttonPane1.getChildren().addAll(btnAdd1, btnRemove1, lblTerm1, tfDescTerm1);
		
		HBox buttonPane2 = new HBox();
		buttonPane2.setSpacing(30);
		buttonPane2.getChildren().addAll(btnAdd2, btnRemove2, lblTerm2, tfDescTerm2);
		
		//VBox for layout of elements for unselected 
		
		VBox UnselectedpaneStuff = new VBox();
		UnselectedpaneStuff.getChildren().addAll(lblTerm1Desc, lvTerm1Unselected, buttonPane1, lblTerm2Desc, lvTerm2Unselected, buttonPane2, lblReserveTerm1, reserveModuleTerm1, btnReset);
		
		
		//vBox for layout of selected elements side 
		
		VBox SelectedpaneStuff = new VBox();
		SelectedpaneStuff.getChildren().addAll(lblCompulsoryModule, lvCompulsoryModule, lblTerm1Selected, lvTerm1Selected, lblTerm2Selected, lvTerm2Selected, lblReserveTerm2, reserveModuleTerm2, btnSubmit);
		
		//Adding panes to HBox view
		
		this.getChildren().addAll(UnselectedpaneStuff, SelectedpaneStuff);
		
		//Padding & spacing 
		
		this.setPadding(new Insets(10, 30, 30, 30));
		this.setSpacing(30);
		UnselectedpaneStuff.setSpacing(12);
		SelectedpaneStuff.setSpacing(12);
		
		//Resizing 
		
		this.setBorder(new Border(new BorderStroke(Color.web("#000000"), BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		HBox.setHgrow(UnselectedpaneStuff, Priority.ALWAYS);
		HBox.setHgrow(SelectedpaneStuff, Priority.ALWAYS);
		VBox.setVgrow(lvTerm1Unselected, Priority.ALWAYS);
		VBox.setVgrow(lvTerm2Unselected, Priority.ALWAYS);
		VBox.setVgrow(lvTerm1Selected, Priority.ALWAYS);
		VBox.setVgrow(lvTerm2Selected, Priority.ALWAYS);

		
		
	}
	//---- BUTTON HANDLERS----
	
	public void resetHandler(EventHandler<ActionEvent> handler) {
		btnReset.setOnAction(handler);
	}
	
	public void submitHandler(EventHandler<ActionEvent> handler) {
		btnSubmit.setOnAction(handler);
	}
	
	public void addAdd1Handler(EventHandler<ActionEvent> handler) {
		btnAdd1.setOnAction(handler);
	}
	
	public void addRemove1Handler(EventHandler<ActionEvent> handler) {
		btnRemove1.setOnAction(handler);
	}
	
	public void addAdd2Handler(EventHandler<ActionEvent> handler) {
		btnAdd2.setOnAction(handler);
	}
	
	public void addRemove2Handler(EventHandler<ActionEvent> handler) {
		btnRemove2.setOnAction(handler);
	}

	
	
	//----ADDING MODULES TO LISTVIEWS---
	
	public void addTerm1UnselectedModule(Module m) {
		OListTerm1.add(m);
	}
	
	public void addTerm2UnselectedModule(Module m) {
		OListTerm2.add(m);
		
	}
	
	public void addCompulsoryModule(Module m) {
		OListCompulsoryModule.add(m);
	}
	
	public void addTerm1SelectedModule(Module m) {
		OListTerm1Selected.add(m);
	}
	
	public void addTerm2SelectedModule(Module m) {
		OListTerm2Selected.add(m);
	}
	
	//----ADDING MODULES TO COMBO BOXES----
	
	public void populateComboBoxTerm1(Module m) {
		reserveModuleTerm1.getItems().add(m);
		reserveModuleTerm1.getSelectionModel().select(0);
		
		}
	
	public void populateComboBoxTerm2(Module m) {
		reserveModuleTerm2.getItems().add(m);
		reserveModuleTerm2.getSelectionModel().select(0);
		
		
		}
	
	//----GET SELECTED MODULE FROM COMBO BOXES----
	
	public Module getTerm1ReserveModuleSelection() {
		return reserveModuleTerm1.getSelectionModel().getSelectedItem();
	}
	
	public Module getTerm2ReserveModuleSelection() {
		return reserveModuleTerm2.getSelectionModel().getSelectedItem();
	}
	
	//----GETTING SELECTED MODULES FROM LISTVIEW----
	
	public Module getTerm1selecteditem() {
		return lvTerm1Unselected.getSelectionModel().getSelectedItem();
	}
	
	public Module getTerm2selecteditem() {
		return lvTerm2Unselected.getSelectionModel().getSelectedItem();
	}
	
	public Module getTerm1Selectedselecteditem() {
		return lvTerm1Selected.getSelectionModel().getSelectedItem();
	}
	
	public Module getTerm2SelectedselectedItem() {
		return lvTerm2Selected.getSelectionModel().getSelectedItem();
	}
	
	//----OBSERVABLE LISTS----
	
	public Module getCompulsoryModuleSelected() {
		return OListCompulsoryModule.get(0);
	}
	
	public ObservableList<Module> getOListTerm1() {
		return OListTerm1;
	}
	
	public ObservableList<Module> getOListTerm2() {
		return OListTerm2;
	}
	
	public ObservableList<Module> getOListTerm2Selected() {
		return OListTerm2Selected;
	}
	
	public ObservableList<Module> getOListTerm1Selected() {
		return OListTerm1Selected;
	}
	
	public ObservableList<Module> getOListCompulsoryModules() {
		return OListCompulsoryModule;
	}
	
	
	
	//----CLEAR LISTVIEWS AND COMBOBOXES----
	
	public void clearListViews() {
		OListCompulsoryModule.clear();
		OListTerm1.clear();
		OListTerm2.clear();
		OListTerm1Selected.clear();
		OListTerm2Selected.clear();
	}
	
	public void clearComboBoxes() {
		reserveModuleTerm1.getItems().clear();
		reserveModuleTerm2.getItems().clear();
		
	}
	
	//----METHODS TO ADD AND REMOVE CREDITS----
	
	public void getTerm1Credits() {
		//get text from text field
		String text = tfDescTerm1.getText();
		//covert string to integer
		int t1 = Integer.parseInt(text);
		t1 =  t1 + 15;
		//convert updated t1 integer to string
		text = Integer.toString(t1);
		//set text as new value 
		tfDescTerm1.setText(text);
		
	}
	
	public void getTerm2Credits() {
		String text1 = tfDescTerm2.getText();
		int t2 = Integer.parseInt(text1);
		t2 =  t2 + 15;
		text1 = Integer.toString(t2);
		tfDescTerm2.setText(text1);
		
	}
	
	public void RemoveTerm1Credits() {
		String text = tfDescTerm1.getText();
		int t1 = Integer.parseInt(text);
		t1 =  t1 - 15;
		text = Integer.toString(t1);
		tfDescTerm1.setText(text);
		
	}
	public void RemovetTerm2Credits() {
		String text1 = tfDescTerm2.getText();
		int t2 = Integer.parseInt(text1);
		t2 =  t2 - 15;
		text1 = Integer.toString(t2);
		tfDescTerm2.setText(text1);
		
	}
	
	//----SORTING COMBO BOXES TO REMOVE SELECTED MODULES----
	//add
	public void SortComboBoxTerm1() {
		for(Module m : OListTerm1Selected) {
			if(reserveModuleTerm1.getItems() != OListTerm1Selected) {
				reserveModuleTerm1.getItems().clear();
			}
			
		}
		for(Module m : OListTerm1) {
			reserveModuleTerm1.getItems().add(m);
		}
		reserveModuleTerm1.getSelectionModel().select(0);
	}
	
	public void SortComboBoxTerm2() {
		for(Module m : OListTerm2Selected) {
			if(reserveModuleTerm2.getItems() != OListTerm2Selected) {
				reserveModuleTerm2.getItems().clear();
			}
			
		}
		for(Module m : OListTerm2) {
			reserveModuleTerm2.getItems().add(m);
		}
		reserveModuleTerm2.getSelectionModel().select(0);
	}
	
	//remove
	
	public void SortComboBoxTerm1Selected() {
		for(Module m : OListTerm1) {
			if(OListTerm1 != reserveModuleTerm1.getItems()) {
				reserveModuleTerm1.getItems().clear();
	}
	for(Module s : OListTerm1) {
		reserveModuleTerm1.getItems().add(s);
	}
	reserveModuleTerm1.getSelectionModel().select(0);
	
		}
	}
	public void SortComboBoxTerm2Selected() {
		for(Module m : OListTerm2) {
			if(OListTerm2 != reserveModuleTerm2.getItems()) {
				reserveModuleTerm2.getItems().clear();
			}
	for(Module s : OListTerm2) {
		reserveModuleTerm2.getItems().add(s);
	}
	reserveModuleTerm2.getSelectionModel().select(0);
	
		}
	}
	



	//----RETURNS TEXT FIELDS FOR USE----
	
	public TextField getT2() {
		return tfDescTerm2;
	}
	
	public TextField getT1() {
		return tfDescTerm1;
	}
	
	public ComboBox<Module> getCBTerm1() {
		return reserveModuleTerm1;
	}
	public ComboBox<Module> getCBTerm2() {
		return reserveModuleTerm2;
	}
	
	//----BINDING---
	
	public void BindButtons(BooleanBinding property) {
		btnSubmit.disableProperty().bind(property);
		btnReset.disableProperty().bind(property);
		btnAdd1.disableProperty().bind(property);
		btnAdd2.disableProperty().bind(property);
		btnRemove1.disableProperty().bind(property);
		btnRemove2.disableProperty().bind(property);
	}
	public BooleanBinding isAnyEmpty() {
		return tfDescTerm1.textProperty().isEmpty();
	}
	

}