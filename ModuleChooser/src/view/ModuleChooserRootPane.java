package view;



import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;



//You may change this class to extend another type if you wish
public class ModuleChooserRootPane extends BorderPane {
	private TabPane tp;
	private TheMenu mb;
	private ProfilePanee pb;
	private UnselectedPane up;
	private OverviewPane op;


	public ModuleChooserRootPane() {
tp = new TabPane();


	tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE); //don't allow tabs to be closed
	//These initiate instances of separate views therefore showing them on the stage
	mb = new TheMenu();
	pb = new ProfilePanee();
	up = new UnselectedPane();
	op = new OverviewPane();




	//create tabs with panes added
	Tab t1 = new Tab("Create Profile", pb);
	Tab t2 = new Tab("Select Modules", up);
	Tab t3 = new Tab("Overview", op );
	tp.setStyle("-fx-background-color: #EBF6FF;");
	
	//add tabs to tab pane
	tp.getTabs().addAll(t1, t2, t3);
	this.setCenter(tp);
	this.setTop(mb);

	

}
	public ProfilePanee getProfilePanee() {
		return pb;
	}
	public UnselectedPane getUnselectedPane() {
		return up;
	}
	public OverviewPane getOverviewPane() {
		return op;
	}
	public TheMenu getTheMenu() {
		return mb;
	}

	public void changeTab(int index) {
		tp.getSelectionModel().select(index);
	}

}
