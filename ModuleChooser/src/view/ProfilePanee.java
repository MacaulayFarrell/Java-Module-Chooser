package view;

import java.time.LocalDate;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Course;
import model.StudentProfile;



public class ProfilePanee extends GridPane {
	private ComboBox<Course> cboCourses;
	private Label lblcourse, lblpNumber, lblfirstName, lbllastName, lblemail, lbldate;
	private TextField tfpNumber, tffirstName, tflastName, tfemail;
	private Button btnSubmit;
	private DatePicker date;
	
	public ProfilePanee() {
		//formatting
		
		this.setStyle("-fx-background-color:linear-gradient(#335EFF, #33C7FF)");
		this.setBorder(new Border(new BorderStroke(Color.web("#000000"), BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		this.setPadding(new Insets(80, 80, 80, 80));
		
		this.setVgap(15);
		this.setHgap(15);
		this.setAlignment(Pos.CENTER);
		
		//labels 
		
		lblcourse = new Label("Select Course: ");
		lblcourse.setFont(Font.font ("Arial", 13));
		
		lblpNumber = new Label("P Number (Numbers only): ");
		lblpNumber.setFont(Font.font ("Arial", 13));
		
		lblfirstName = new Label("First Name: ");
		lblfirstName.setFont(Font.font ("Arial", 13));
		
		lbllastName = new Label("Surname: ");
		lbllastName.setFont(Font.font ("Arial", 13));
		
		lblemail = new Label("Email Address: ");
		lblemail.setFont(Font.font ("Arial", 13));
		
		lbldate = new Label("Current Date: "); 
		lbldate.setFont(Font.font ("Arial", 13));
	
		//combo box for course choice
		
		cboCourses = new ComboBox<Course>();
		
		//text fields set up
		
		tfpNumber = new TextField();
		tffirstName = new TextField();
		
		tflastName = new TextField();
		tfemail = new TextField();
		
		//create date picker
		
		date = new DatePicker();
		date.setEditable(false);
	
		// button set up
		
		btnSubmit = new Button("Create Profile"); 
		
	
	
		
		//adding elements to gridpane 
		
		this.add(lblcourse, 0, 0);
		//tffirstName.setStyle(" -fx-background-radius: 5;");
		this.add(cboCourses, 1, 0);
		
		this.add(lblpNumber, 0, 1);
		this.add(tfpNumber, 1, 1);
		
		this.add(lblfirstName, 0, 2);
		this.add(tffirstName, 1, 2);
		
		this.add(lbllastName, 0, 3);
		this.add(tflastName, 1, 3);
		
		this.add(lblemail, 0, 4);
		this.add(tfemail, 1, 4);
		
		this.add(lbldate, 0, 5);
		this.add(date, 1, 5);
		
		this.add(btnSubmit, 1, 6);
	}
	
	//=========================================
	//METHODS/EVENT HANDLERS
	//=========================================
	public void populateComboBox(Course[] courses) {
		cboCourses.getItems().addAll(courses);
		cboCourses.getSelectionModel().select(0);
		
		}
	
	//---Get/set methods----

	public Course getcboCourses() {
		return cboCourses.getSelectionModel().getSelectedItem();
	}
	public String gettfpNumber() {
		return tfpNumber.getText();
	}
	public void setpNumber(String s) {
		tfpNumber.setText(s);
	}
	public String gettffirstName() {
		return tffirstName.getText();
	}
	public void setFirstName(String s) {
	tffirstName.setText(s);
	}
	public void setLasttName(String s) {
	tflastName.setText(s);
	}
	public TextField getpNumber() {
		return tfpNumber;
	}
	public String gettflastName() {
		return tflastName.getText();
	}
	public String gettfemail() {
		return tfemail.getText();
	}
	
	//----BUTTON HANDLERS----
	
	public void CreateProfileHandler(EventHandler<ActionEvent> handler) {
		btnSubmit.setOnAction(handler);
	}
	
	//----LISTENERS----
	public void pNumberCheckListener(ChangeListener<String> j) {
		tfpNumber.textProperty().addListener(j);
	}
	
	public void CheckListener(ChangeListener<String> j) {
		tffirstName.textProperty().addListener(j);
		
	} 
	public void Check1Listener(ChangeListener<String> j) {
	
		tflastName.textProperty().addListener(j);
	}
	
	

	public LocalDate getdate() {
	return date.getValue();
	}
	
	//---Method for setting student details (Load)---
	
	public void setStudentDetails(StudentProfile sp) {
		if (sp.getCourse().equals("Computer Science")) {
			cboCourses.getSelectionModel().select(0);
		}
		else if(sp.getCourse().equals("Software Engineering")) {
			cboCourses.getSelectionModel().select(1);
		}
		tfpNumber.setText(sp.getpNumber());
		tffirstName.setText(sp.getStudentName().getFirstName());
		tflastName.setText(sp.getStudentName().getFamilyName());
		tfemail.setText(sp.getEmail());
		date.setValue(sp.getDate());
	}
	
	//----BINDING----
	
	public void TextFieldsEmptyBind(BooleanBinding bs) { 
		btnSubmit.disableProperty().bind(bs);
	}
	

	public BooleanBinding isTextEmpty() {
		return tfpNumber.textProperty().isEmpty().or(tffirstName.textProperty().isEmpty()).or(tflastName.textProperty().isEmpty().or
				(tfemail.textProperty().isEmpty()).or(date.valueProperty().isNull()));
	}
	
}

