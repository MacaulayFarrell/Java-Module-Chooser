package view;


import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;



public class OverviewPane extends VBox{
		
	private TextArea main;
	private Button save;
		
	public OverviewPane() {
			
	//formatting of grid pane
			
	this.setStyle("-fx-background-color:linear-gradient(#335EFF, #33C7FF)");
	this.setBorder(new Border(new BorderStroke(Color.web("#000000"), BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
	this.setPadding(new Insets(40, 40, 40, 40));

	this.setAlignment(Pos.CENTER);
	
	//initialise button/text area
	
	main = new TextArea("Student Profile overview will show here.");
	main.setEditable(false);


	save = new Button("Save Overview");
	this.setSpacing(30);
	this.getChildren().addAll(main, save);
	VBox.setVgrow(main, Priority.ALWAYS);

//=========================================
//METHODS/EVENT HANDLERS
//=========================================
	
}
public void setResults(String results) {
main.setText(results);
}
public void SaveOverviewHandler(EventHandler<ActionEvent> handler) {
	save.setOnAction(handler);
}
public String getOverviewText() {
	return main.getText();
}
public void SaveOverviewBind(BooleanBinding bs) {
	save.disableProperty().bind(bs);
}
public BooleanBinding TextChecker() {
	return main.lengthProperty().lessThan(50);
}

}
