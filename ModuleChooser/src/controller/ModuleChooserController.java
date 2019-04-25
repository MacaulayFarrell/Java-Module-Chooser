package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Course;
import model.Delivery;
import model.Module;
import model.Name;
import model.StudentProfile;
import view.ModuleChooserRootPane;
import view.OverviewPane;
import view.ProfilePanee;
import view.TheMenu;
import view.UnselectedPane;



public class ModuleChooserController {

	//fields to be used throughout the class
	private ModuleChooserRootPane view;
	private ProfilePanee pp;
	private UnselectedPane up;
	private TheMenu th;
	private OverviewPane op;


	private StudentProfile model;

	public ModuleChooserController(ModuleChooserRootPane view, StudentProfile model) {
		//initialise model and view fields
		this.model = model;
		this.view = view;
		//instantiate views
				pp = view.getProfilePanee();
				up = view.getUnselectedPane();
				op = view.getOverviewPane();
				th = view.getTheMenu();
				


		//populate combo box in create profile pane, e.g. if cpp represented your create profile pane you could invoke the line below
		pp.populateComboBox(setupAndGetCourses());

		//attach event handlers + bindings to view using private helper method
		this.attachEventHandlers();	
		this.attachBindings();

	}

	private void attachEventHandlers() {
		pp.CreateProfileHandler(new CreateProfileHandler()); //attaches button handler
		up.addAdd1Handler(new addAdd1Handler());
		up.addAdd2Handler(new addAdd2Handler());
		up.addRemove1Handler(new addRemove1Handler());
		up.addRemove2Handler(new addRemove2Handler());
		th.addExitHandler(e -> System.exit(0));
		up.submitHandler(new submitHandler());
		up.resetHandler(new resetHandler());
		th.addAboutHandler(e -> this.alertDialogBuilder("Information Dialog", null, "Final Year Module Chooser v1.0\nFinal year module chooser for Computer Science and Software Engineering students.")); 
		th.addLoadHandler(new LoadHandler());
		th.addSaveHandler(new SaveHandler());
		op.SaveOverviewHandler(new SaveOverviewHandler());
		pp.pNumberCheckListener(new pNumberValidator());
		pp.CheckListener(new IntegerCheckListener());
		pp.Check1Listener(new IntegerCheck1Listener());
	} 
	
	private void attachBindings() {
		op.SaveOverviewBind(op.TextChecker());
		pp.TextFieldsEmptyBind(pp.isTextEmpty());
		up.BindButtons(up.isAnyEmpty());
		
	}


	private class CreateProfileHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
	
			if(!pp.gettfemail().contains("@") || !pp.gettfemail().contains(".")) {
				alertDialogBuilder1("Warning Dialog", "Email issue", "Please enter a valid email, e.g. Name@domain.com!");
			}
		
			else {
				model.setCourse(pp.getcboCourses());
				model.setDate(pp.getdate());
				model.setEmail(pp.gettfemail());
				model.setpNumber(pp.gettfpNumber());
				model.setStudentName(new Name(pp.gettffirstName(), pp.gettflastName()));
				if(pp.getcboCourses().getCourseName() == "Software Engineering") {
					up.getT2().setText("30");
					up.getT1().setText("30");
				}
				else {
					up.getT2().setText("15");
					up.getT1().setText("30");
				}
				up.clearListViews();
				model.clearSelectedModules();
				model.clearReserveSelectedModules();
				up.clearComboBoxes();
				
					for(Module m : pp.getcboCourses().getModulesOnCourse()) {
					
						if(m.isMandatory() && m.getRunPlan() == Delivery.YEAR_LONG) {
							up.addCompulsoryModule(m);
					}
					
							if(m.isMandatory() && m.getRunPlan() == Delivery.TERM_1) {
								up.addTerm1SelectedModule(m);
					
						
					}
			if(m.isMandatory() && m.getRunPlan() == Delivery.TERM_2) {
				up.addTerm2SelectedModule(m);
			}
					if(m.getRunPlan() == Delivery.TERM_1 && !m.isMandatory()) {
						up.addTerm1UnselectedModule(m);
						up.populateComboBoxTerm1(m);
					}
					if(m.getRunPlan() == Delivery.TERM_2 & !m.isMandatory()) {
						up.addTerm2UnselectedModule(m);
						up.populateComboBoxTerm2(m);
					}
				
				
				view.changeTab(1);
				}
			}
			
				} 
				
				
			}
	private class submitHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e ) {
			model.clearSelectedModules();
			model.clearReserveSelectedModules();
			
		if(up.getOListTerm1Selected().size() < 3 || up.getOListTerm2Selected().size() < 3) {
			alertDialogBuilder1("Error", null, "Insufficient  Modules have been chosen, please select more modules to emsure you have 120 credits to proceed.");
		}
	
		else {
			
		
			//add selected modules to model
				for(Module m : up.getOListTerm1Selected()) {
					model.addSelectedModule(m);
				}
				for(Module m : up.getOListTerm2Selected()) {
					model.addSelectedModule(m);
				}
				for(Module m : up.getOListCompulsoryModules()) {
					model.addSelectedModule(m);
					
				} 
		
			
				
				//add reserve modules 
				
				model.addReserveModule(up.getTerm1ReserveModuleSelection());
				model.addReserveModule(up.getTerm2ReserveModuleSelection());
				
				//String to hold data for populating last tab
				
				String s = "================\n Your Details: \n================\n";
				s += "P Number: " + "P" + model.getpNumber() + "\n";
				s += "Name: " + model.getStudentName().getFirstName() + " " + model.getStudentName().getFamilyName() + "\n";
				s += "Email: " + model.getEmail()  + "\n";
				s += "Date of Submission: " + model.getDate()  + "\n";
				s += "Course: " + model.getCourse()  + "\n";
				s += "\n================\n";
				s += "Selected Modules\n================\n";
				s += "\n";
				for(Module m : model.getSelectedModules()) {
					
					s += "Module code: " + m.getModuleCode() + ", " + "Module Name: " + m.getModuleName() + ", \n" + "Credits: " + m.getCredits() + ", " + "Mandatory on your course? " + m.isMandatory() + ", " + "Delivery: " + m.getRunPlan();
					s += "\n\n";
 				}
				s += "\n================\n";
				s += "Reserve Modules\n================\n";
				s += "\n";
				
				for(Module m : model.getReserveModules()) {
				
					s += "Module code: " + m.getModuleCode() + ", " + "Module Name: " + m.getModuleName() + ", \n" + "Credits: " + m.getCredits() + ", " + "Mandatory on your course? " + m.isMandatory() + ", " + "Delivery: " + m.getRunPlan();
					s += "\n\n";
				}
			
				op.setResults(s);
				
				view.changeTab(2);
				
			}
		}
	}

	private class resetHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			if(up.getOListCompulsoryModules().size() == 0 || up.getOListTerm1().size() == 0) {
				alertDialogBuilder1("Error", null, "Error! Error! Error!");
				return;
			}
		
			
			else {
			up.clearComboBoxes();
			up.clearListViews();
			for(Module m : pp.getcboCourses().getModulesOnCourse()) {
				
				if(m.isMandatory() && m.getRunPlan() == Delivery.YEAR_LONG) {
					//sp.compulsoryModule(m);
					up.addCompulsoryModule(m);
				}
		if(m.isMandatory() && m.getRunPlan() == Delivery.TERM_1) {
					//sp.term1selectedModules(m);
				up.addTerm1SelectedModule(m);
					
				}
		if(m.isMandatory() && m.getRunPlan() == Delivery.TERM_2) {
			//sp.term2selectedModules(m);
			//up.populateComboBoxTerm2(m);
			up.addTerm2SelectedModule(m);
		}
				if(m.getRunPlan() == Delivery.TERM_1 && !m.isMandatory()) {
					up.addTerm1UnselectedModule(m);
					up.populateComboBoxTerm1(m);
				}
				if(m.getRunPlan() == Delivery.TERM_2 & !m.isMandatory()) {
					up.addTerm2UnselectedModule(m);
					up.populateComboBoxTerm2(m);
				}
				if(pp.getcboCourses().getCourseName() == "Software Engineering") {
					up.getT2().setText("30");
					up.getT1().setText("30");
				}
				else {
					up.getT2().setText("15");
					up.getT1().setText("30");
				}
				
		}
	}
	}
	}
	
	
	private class addAdd1Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Module selected = up.getTerm1selecteditem();
			if(selected == null) {
				alertDialogBuilder1("Error", null, "Please select a module");
			}
			else if(up.getOListTerm1Selected().size() > 2) {
				alertDialogBuilder("Information Dialog", null, "You can't select another module!");
			}
			
			else if(!up.getOListTerm1Selected().contains(selected) && up.getOListTerm1Selected().size() < 3) {
				up.getOListTerm1().remove(selected);
				up.getOListTerm1Selected().add(selected);
				up.getTerm1Credits();
				up.SortComboBoxTerm1();
			}
			
		}
	}
	
	
	private class addAdd2Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Module selected = up.getTerm2selecteditem();
			if(selected == null) {
				alertDialogBuilder1("Error", null, "Please select a module");
			}
			else  if(up.getOListTerm2Selected().size() > 2) {
				alertDialogBuilder("Information Dialog", null, "You can't select another module!");
			}
			
			else if(!up.getOListTerm2Selected().contains(selected) && up.getOListTerm2Selected().size() < 3) {
				up.getOListTerm2().remove(selected);
				up.getOListTerm2Selected().add(selected);
				up.getTerm2Credits();
				up.SortComboBoxTerm2();
			}
			
		}
	}
	
	
	private class addRemove1Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Module selected = up.getTerm1Selectedselecteditem();
			if(selected == null) {
				alertDialogBuilder1("Error", null, "Please select a module");
			}
			if(selected != null && selected != up.getOListTerm1Selected().get(0)) {
				up.getOListTerm1Selected().remove(selected);
				up.getOListTerm1().add(selected);
				up.SortComboBoxTerm1Selected();
				up.RemoveTerm1Credits();
				
				
				}	
		}
	}
	
	
	private class addRemove2Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Module selected = up.getTerm2SelectedselectedItem();
			if(selected == null) {
				alertDialogBuilder1("Error", null, "Please select a module");
			}
			if(pp.getcboCourses().getCourseName().equals("Software Engineering") && selected != null && selected != up.getOListTerm2Selected().get(0)) {
				up.getOListTerm2Selected().remove(selected);
				up.getOListTerm2().add(selected);
				up.SortComboBoxTerm2Selected();
				up.RemovetTerm2Credits();
				}
				else if (pp.getcboCourses().getCourseName().equals("Computer Science") && selected != null){
				up.getOListTerm2Selected().remove(selected);
				up.getOListTerm2().add(selected);
				up.SortComboBoxTerm2Selected();
				up.RemovetTerm2Credits();
			}
		}	
	}
	
	
	private class SaveHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {          
			//save the data model
			if(model.getSelectedModules().isEmpty() || model.getpNumber().isEmpty()) {
				alertDialogBuilder1("Error", null, "Please fill out your student profile!");
				return;
			}
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("StudentObj.dat"));) {
				
				oos.writeObject(model);
				alertDialogBuilder("Information Dialog", "Successfully Saved Data", "Information saved to model!");
				
			}
			catch (IOException ioExcep){
				System.out.println("Error saving");
			}
		}
	}

	
	private class LoadHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			//load in the data model
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("StudentObj.dat"));) {
				
				model = (StudentProfile) ois.readObject(); //reads the model object back from a file	
				
				alertDialogBuilder("Information Dialog", "Success!", "Data Loaded");
				
	        }
	        catch (IOException ioEx){
	            System.out.println("Error loading");
	            alertDialogBuilder1("Error Dialog", "IO Exception", "An error has occurred. Sorry for any inconvenience caused!");
	        }
			catch (ClassNotFoundException c) {
	            System.out.println("Class Not found");
	            alertDialogBuilder1("Error Dialog", "Class Not Found", "Class not found, sorry!");
	        }	
			//returning correct reserve module using iterator
			Iterator<Module> reserve = model.getReserveModules().iterator();
			Module first = reserve.next();
			Module second = reserve.next(); 
			//----SET STUDENT DETAILS FROM LOADED DETAILS----
			pp.setStudentDetails(model);
			
			//--SET MODULES BASED ON USER LOADED MODULES----
			
			up.clearListViews();
			up.clearComboBoxes();
			for(Module m : pp.getcboCourses().getModulesOnCourse()) {
				if(m.getRunPlan().equals(Delivery.TERM_1) && !m.isMandatory()) {
					up.addTerm1UnselectedModule(m);
					up.populateComboBoxTerm1(m);
			}
				else if(m.getRunPlan().equals(Delivery.TERM_2) && !m.isMandatory()) {
					up.addTerm2UnselectedModule(m);
					up.populateComboBoxTerm2(m);
				}
			}
			for(Module m : model.getSelectedModules()) {
				if(m.getRunPlan() == Delivery.TERM_1) {
					up.addTerm1SelectedModule(m);
					up.getOListTerm1().remove(m);
					up.SortComboBoxTerm1Selected();
					up.getCBTerm1().getSelectionModel().select(first);
					
				}
				if(m.getRunPlan() == Delivery.TERM_2) {
					up.addTerm2SelectedModule(m);
					up.getOListTerm2().remove(m);
					up.SortComboBoxTerm2Selected();
					up.getCBTerm2().getSelectionModel().select(second);
				}
				if(m.getRunPlan() == Delivery.YEAR_LONG) {
					up.addCompulsoryModule(m);
				}
				up.getT1().setText("60");
				up.getT2().setText("60");
			} 
		
			String s = "================\n Your Details: \n================\n";
			s += "P Number: " + "P" +  model.getpNumber() + "\n";
			s += "Name: " + model.getStudentName().getFirstName() + " " + model.getStudentName().getFamilyName() + "\n";
			s += "Email: " + model.getEmail()  + "\n";
			s += "Date of Submission: " + model.getDate()  + "\n";
			s += "Course: " + model.getCourse()  + "\n";
			s += "\n================\n";
			s += "Selected Modules\n================\n";
			s += "\n";
			for(Module m : model.getSelectedModules()) {
				
				s += "Module code: " + m.getModuleCode() + ", " + "Module Name: " + m.getModuleName() + ", \n" + "Credits: " + m.getCredits() + ", " + "Mandatory on your course? " + m.isMandatory() + ", " + "Delivery: " + m.getRunPlan();
				s += "\n\n";
				
				
				}
			s += "\n================\n";
			s += "Reserve Modules\n================\n";
			s += "\n";
			
			for(Module m : model.getReserveModules()) {
			
				s += "Module code: " + m.getModuleCode() + ", " + "Module Name: " + m.getModuleName() + ", \n" + "Credits: " + m.getCredits() + ", " + "Mandatory on your course? " + m.isMandatory() + ", " + "Delivery: " + m.getRunPlan();
				s += "\n\n";
			}
		
			op.setResults(s);
		
			
			

		}
	}

	private class SaveOverviewHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {          
			
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("StudentDetailV1.dat"));) {

				oos.writeObject(op.getOverviewText()); 
				oos.flush();
				//oos.close();
				
				alertDialogBuilder("Information Dialog", "Save success", "Details saved to StudentDetailV1.dat"
						+ "");
			
			}
			catch (IOException ioExcep){
				System.out.println("Error saving");
			} 
		}
	}
	private class IntegerCheckListener implements ChangeListener<String> {
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			if(!newValue.matches("[a-zA-Z]")) {
				pp.setFirstName(newValue.replaceAll("[\\d || \\p{Punct}]", ""));
				
			}
			
		}
	}
	private class IntegerCheck1Listener implements ChangeListener<String> {
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			if(!newValue.matches("[a-zA-Z]")) {
				
				pp.setLasttName(newValue.replaceAll("[\\d || \\p{Punct}]", ""));
			}
			
		}
	}
	private class pNumberValidator implements ChangeListener<String> {
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			if(!newValue.matches("\\d*")) {
				pp.setpNumber(newValue.replaceAll("[^\\d]", ""));
				
			}
			else if(pp.gettfpNumber().length() > 8) {
				
				pp.setpNumber(pp.getpNumber().getText(0, 8).replaceAll("", ""));
			}
		}
	}
	



	private Course[] setupAndGetCourses() {
		Module imat3423 = new Module("IMAT3423", "Systems Building: Methods", 15, true, Delivery.TERM_1);
		Module imat3451 = new Module("IMAT3451", "Computing Project", 30, true, Delivery.YEAR_LONG);
		Module ctec3902_SoftEng = new Module("CTEC3902", "Rigerous Systems", 15, true, Delivery.TERM_2);	
		Module ctec3902_CompSci = new Module("CTEC3902", "Rigerous Systems", 15, false, Delivery.TERM_2);
		Module ctec3110 = new Module("CTEC3110", "Secure Web Application Development", 15, false, Delivery.TERM_1);
		Module ctec3426 = new Module("CTEC3426", "Telematics", 15, false, Delivery.TERM_1);
		Module ctec3605 = new Module("CTEC3605", "Multi-service Networks 1", 15, false, Delivery.TERM_1);	
		Module ctec3606 = new Module("CTEC3606", "Multi-service Networks 2", 15, false, Delivery.TERM_2);	
		Module ctec3410 = new Module("CTEC3410", "Web Application Penetration Testing", 15, false, Delivery.TERM_2);
		Module ctec3904 = new Module("CTEC3904", "Functional Software Development", 15, false, Delivery.TERM_2);
		Module ctec3905 = new Module("CTEC3905", "Front-End Web Development", 15, false, Delivery.TERM_1);
		Module ctec3906 = new Module("CTEC3906", "Interaction Design", 15, false, Delivery.TERM_2);
		Module imat3410 = new Module("IMAT3104", "Database Management and Programming", 15, false, Delivery.TERM_2);
		Module imat3406 = new Module("IMAT3406", "Fuzzy Logic and Knowledge Based Systems", 15, false, Delivery.TERM_1);
		Module imat3611 = new Module("IMAT3611", "Computing Ethics and Privacy", 15, false, Delivery.TERM_1);
		Module imat3613 = new Module("IMAT3613", "Data Mining", 15, false, Delivery.TERM_1);
		Module imat3614 = new Module("IMAT3614", "Big Data", 15, false, Delivery.TERM_2);
		Module imat3428_CompSci = new Module("IMAT3428", "Information Technology Services Practice", 15, false, Delivery.TERM_2);

		Course compSci = new Course("Computer Science");
		compSci.addModule(imat3423);
		compSci.addModule(imat3451);
		compSci.addModule(ctec3902_CompSci);
		compSci.addModule(ctec3110);
		compSci.addModule(ctec3426);
		compSci.addModule(ctec3605);
		compSci.addModule(ctec3606);
		compSci.addModule(ctec3410);
		compSci.addModule(ctec3904);
		compSci.addModule(ctec3905);
		compSci.addModule(ctec3906);
		compSci.addModule(imat3410);
		compSci.addModule(imat3406);
		compSci.addModule(imat3611);
		compSci.addModule(imat3613);
		compSci.addModule(imat3614);
		compSci.addModule(imat3428_CompSci);

		Course softEng = new Course("Software Engineering");
		softEng.addModule(imat3423);
		softEng.addModule(imat3451);
		softEng.addModule(ctec3902_SoftEng);
		softEng.addModule(ctec3110);
		softEng.addModule(ctec3426);
		softEng.addModule(ctec3605);
		softEng.addModule(ctec3606);
		softEng.addModule(ctec3410);
		softEng.addModule(ctec3904);
		softEng.addModule(ctec3905);
		softEng.addModule(ctec3906);
		softEng.addModule(imat3410);
		softEng.addModule(imat3406);
		softEng.addModule(imat3611);
		softEng.addModule(imat3613);
		softEng.addModule(imat3614);

		Course[] courses = new Course[2];
		courses[0] = compSci;
		courses[1] = softEng;

		return courses;
	}
	private void alertDialogBuilder(String alerttitle, String alertheader, String alertcontent) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(alerttitle);
		alert.setHeaderText(alertheader);
		alert.setContentText(alertcontent);
		alert.showAndWait();
	}
	private void alertDialogBuilder1(String alerttitle, String alertheader, String alertcontent) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(alerttitle);
		alert.setHeaderText(alertheader);
		alert.setContentText(alertcontent);
		alert.showAndWait();
	}


}

