package hibAssignment;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ToDoApp extends Application {
	
	static List<Item> items;
	
	public static void main (String[] args)
	{
		ItemDao prevSession = new ItemDao();
		items = prevSession.getItems();
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {	
		// Create ArrayLists to track to-do and done items
		ObservableList<String> toDo = FXCollections.observableArrayList();
		ObservableList<String> done = FXCollections.observableArrayList();
		for (Item i : items)
		{
			if (i.isDone)
			{
				done.add(i.task);
			}
			else
			{
				toDo.add(i.task);
			}
		}
		// TextField to input new items to lists
		TextField txt = new TextField();
			
		// Create item entry label
		Label entry = new Label("Add item to to-do list: ");
			
		// Create list labels
		Label toDoLabel = new Label("To-Do List: ");
		Label doneLabel = new Label("Done List: ");
			
		// Create ListView of to-do list
		final ListView<String> isToDo = new ListView<>();
		// Add the items to the list
		isToDo.getItems().addAll(toDo);
		// Set ListView size
		isToDo.setPrefSize(120,  120);
		// Only one may be selected at a time
		isToDo.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			
		// Create ListView of done list
		final ListView<String> isDone = new ListView<>();
		// Add the items to the list
		isDone.getItems().addAll(done);
		// Set ListView size
		isDone.setPrefSize(120,  120);
		// Only one may be selected at a time
		isDone.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			
		// Create HBox for to-do
		HBox toDoSelect = new HBox();
		// Set spacing to 10 pixels
		toDoSelect.setSpacing(10);
		// Add to-do label and list to HBox
		toDoSelect.getChildren().addAll(toDoLabel, isToDo);
			
		// Create HBox for done
		HBox doneSelect = new HBox();
		// Set spacing to 10 pixels
		doneSelect.setSpacing(10);
		// Add done label and list to HBox
		doneSelect.getChildren().addAll(doneLabel, isDone);
			
		HBox textEntry = new HBox();
		textEntry.setSpacing(10);
		textEntry.getChildren().addAll(entry, txt);
			
		// Buttons to help with to-do list selection
		Button btnSelectAllTD = new Button("Select All");
		Button btnClearAllTD = new Button("Clear All");
		Button btnSelectFirstTD = new Button("Select First");
		Button btnSelectLastTD = new Button("Select Last");
		Button btnSelectNextTD = new Button("Select Next");
		Button btnSelectPreviousTD = new Button("Select Previous");
		Button btnSelectDone = new Button("Move to Done");
			
		// Buttons to help with done list selection
		Button btnSelectAllD = new Button("Select All");
		Button btnClearAllD = new Button("Clear All");
		Button btnSelectFirstD = new Button("Select First");
		Button btnSelectLastD = new Button("Select Last");
		Button btnSelectNextD = new Button("Select Next");
		Button btnSelectPreviousD = new Button("Select Previous");
		Button btnRemoveDone = new Button("Remove");
			
		// Button that adds items to the to-do list
		Button btnAddToDo = new Button("Add to To-Do List");
			
		// Let buttons expand to fit text
		btnSelectAllTD.setMaxWidth(Double.MAX_VALUE);
		btnClearAllTD.setMaxWidth(Double.MAX_VALUE);
		btnSelectFirstTD.setMaxWidth(Double.MAX_VALUE);
		btnSelectLastTD.setMaxWidth(Double.MAX_VALUE);
		btnSelectNextTD.setMaxWidth(Double.MAX_VALUE);
		btnSelectPreviousTD.setMaxWidth(Double.MAX_VALUE);
		btnSelectAllD.setMaxWidth(Double.MAX_VALUE);
		btnClearAllD.setMaxWidth(Double.MAX_VALUE);
		btnSelectFirstD.setMaxWidth(Double.MAX_VALUE);
		btnSelectLastD.setMaxWidth(Double.MAX_VALUE);
		btnSelectNextD.setMaxWidth(Double.MAX_VALUE);
		btnSelectPreviousD.setMaxWidth(Double.MAX_VALUE);
		btnAddToDo.setMaxWidth(Double.MAX_VALUE);
		btnSelectDone.setMaxWidth(Double.MAX_VALUE);
		btnRemoveDone.setMaxWidth(Double.MAX_VALUE);
			
		VBox buttonsToDo = new VBox();
		buttonsToDo.getChildren().addAll(btnSelectFirstTD, btnSelectLastTD, btnSelectNextTD,
				btnSelectPreviousTD, btnSelectDone, btnSelectAllTD, btnClearAllTD);
			
		VBox buttonsDone = new VBox();
		buttonsDone.getChildren().addAll(btnSelectFirstD, btnSelectLastD, btnSelectNextD,
				btnSelectPreviousD, btnRemoveDone, btnSelectAllD, btnClearAllD);	
			
		HBox selectionToDo = new HBox();
		selectionToDo.setSpacing(10);
		selectionToDo.getChildren().addAll(toDoSelect, buttonsToDo);
			
		HBox selectionDone = new HBox();
		selectionDone.setSpacing(10);
		selectionDone.getChildren().addAll(doneSelect, buttonsDone);
		
		HBox selectionInput = new HBox();
		selectionInput.setSpacing(10);
		selectionInput.getChildren().addAll(textEntry, btnAddToDo);
			
		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(5);
		pane.addColumn(0, selectionToDo);
		pane.addColumn(1, selectionDone);
		pane.addColumn(2, selectionInput);
			
		VBox root = new VBox();
		root.setSpacing(10);
		root.getChildren().addAll(pane, txt);
			
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("To-Do List Application");
		stage.show();	
		
		////////////////////////
		// TO-DO BUTTON LOGIC //
		////////////////////////
			
		btnSelectAllTD.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override public void handle(ActionEvent e)
			{
				isToDo.getSelectionModel().selectAll();
			}
		});
			
		btnClearAllTD.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override public void handle(ActionEvent e)
			{
				isToDo.getSelectionModel().clearSelection();
			}
		});
			
		btnSelectFirstTD.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override public void handle(ActionEvent e)
			{
				isToDo.getSelectionModel().selectFirst();
			}
		});
			
		btnSelectLastTD.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override public void handle(ActionEvent e)
			{
				isToDo.getSelectionModel().selectLast();
			}
		});
			
		btnSelectNextTD.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override public void handle(ActionEvent e)
			{
				isToDo.getSelectionModel().selectNext();
			}
		});
			
		btnSelectPreviousTD.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override public void handle(ActionEvent e)
			{
				isToDo.getSelectionModel().selectPrevious();
			}
		});
		
		btnSelectDone.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override public void handle(ActionEvent e)
			{
				String selectedItem = isToDo.getSelectionModel().getSelectedItem();
				toDo.remove(selectedItem);
				done.add(selectedItem);
				isToDo.setItems(toDo);
				isDone.setItems(done);
				
				for (Item i : items)
				{
					if (i.FindItem(selectedItem) != null)
					{
						try {i.SetDoneness(i, true);
						ItemDao itemdao = new ItemDao();
						itemdao.makeDone(i);}
						catch (Exception e1) {System.out.println("Couldn't change doneness of item.");}
					}
				}
				
				for (Item i : items)
				{
					System.out.println(i.task); // Used to check that items is updating
				}
			}
		});
			
		////////////////////////
		//  DONE BUTTON LOGIC //
		////////////////////////
			
		btnSelectAllD.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override public void handle(ActionEvent e)
			{
				isDone.getSelectionModel().selectAll();
			}
		});
			
		btnClearAllD.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override public void handle(ActionEvent e)
			{
				isDone.getSelectionModel().clearSelection();
			}
		});
			
		btnSelectFirstD.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override public void handle(ActionEvent e)
			{
				isDone.getSelectionModel().selectFirst();
			}
		});
			
		btnSelectLastD.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override public void handle(ActionEvent e)
			{
				isDone.getSelectionModel().selectLast();
			}
		});
			
		btnSelectNextD.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override public void handle(ActionEvent e)
			{
				isDone.getSelectionModel().selectNext();
			}
		});
			
		btnSelectPreviousD.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override public void handle(ActionEvent e)
			{
				isDone.getSelectionModel().selectPrevious();
			}
		});
		
		btnRemoveDone.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override public void handle(ActionEvent e)
			{
				String selectedItem = isDone.getSelectionModel().getSelectedItem();
				done.remove(selectedItem);
				isDone.setItems(done);
				
				// Remove the item from the list and from the items ArrayList
				// Use toRemove list to avoid ConcurrentModificationException
				List<Item> toRemove = new ArrayList<Item>();
				for (Item i : items)
				{
					Item q = i.FindItem(selectedItem);
					if (q != null)
					{
						try {toRemove.add(q);}
						catch (Exception e2) {System.out.println("Could not remove that item from the list.");}
						
						ItemDao itemdao = new ItemDao();
						itemdao.removeItem(i);
					}
				}
				items.removeAll(toRemove);
				for (Item i : items)
				{
					System.out.println(i.task); // Used to check that items is updating
				}
			}
		});
		
		////////////////////////
		//   ADD TO-DO LIST   //
		////////////////////////
		
		btnAddToDo.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override public void handle(ActionEvent e)
			{
				Item input = new Item(txt.getText(), false);
				items.add(input);
				toDo.add(input.task);
				isToDo.setItems(toDo);
				System.out.println("Added " + input.task + " to To-Do list.");
				
				ItemDao itemdao = new ItemDao();
				itemdao.saveItem(input);
				
				for (Item i : items)
				{
					System.out.println(i.task); // Used to check that items is updating
				}
			}
		});
	}
}
