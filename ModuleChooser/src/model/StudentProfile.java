package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;



public class StudentProfile implements Serializable {

	private String pNumber;
	private Name studentName;
	private String email;
	private LocalDate date;
	private Course course;
	private Set<Module> selectedModules;
	private Set<Module> reserveModules;
	
	public StudentProfile() {
		pNumber = "";
		studentName = new Name();
		email = "";
		date = null;
		course = null;
		selectedModules = new TreeSet<Module>();
		reserveModules = new TreeSet<Module>();
	}
	
	public String getpNumber() {
		return pNumber;
	}
	
	public void setpNumber(String pNumber) {
		this.pNumber = pNumber;
	}
	
	public Name getStudentName() {
		return studentName;
	}
	
	public void setStudentName(Name studentName) {
		this.studentName = studentName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public Course getCourse() {
		return course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public boolean addSelectedModule(Module m) {
		return selectedModules.add(m);
	}
	
	public Set<Module> getSelectedModules() {
		return selectedModules;
	}
	
	public void clearSelectedModules() {
		selectedModules.clear();
	}
	public void clearReserveSelectedModules() {
		reserveModules.clear();
	}
	public boolean addReserveModule(Module r) {
		return reserveModules.add(r);
	}
	public Set<Module> getReserveModules() {
		return reserveModules;
	}
	
	@Override
	public String toString() {
		return "StudentProfile:[pNumber=" + pNumber + ", studentName="
				+ studentName + ", email=" + email + ", date="
				+ date + ", course=" + course.actualToString() 
				+ ", selectedModules=" + selectedModules + "]";
	}

	
}
