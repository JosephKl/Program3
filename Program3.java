import java.util.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * @author Joseph Klaips, Prof Bill 
 * GUI implementation of the wheel of decision from Program1. 
 * March 2020 
 */
public class Program3 extends Application {
  private VBox topBox;
  private VBox innerBox;

  String wheelName;
  static MyList list;

  /**
   * Estblishes a stage for the program  
   */
  @Override
  public void start(Stage primaryStage) {
    topBox = new VBox();

    MenuBar mb = createMenuBar();
    topBox.getChildren().add( mb);

    // inner VBox
    innerBox = new VBox();
    topBox.getChildren().add( innerBox);
    Label lab1 = new Label( "Welcome to Wheel of Decision!");
    innerBox.getChildren().add( lab1);

    Scene scene = new Scene(topBox, 960, 600);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * @return The "Wheel Options" menu bar 
   */
  private MenuBar createMenuBar() {
    MenuBar mb = new MenuBar();

    Menu menu1 = new Menu("Wheel Options");
    mb.getMenus().add(menu1);

    //Add 
    MenuItem add = new MenuItem("Add");
    menu1.getItems().add(add);
    add.setOnAction(new ItemInsert());

    //Name 
    MenuItem name = new MenuItem("Name");
    menu1.getItems().add( name);
    name.setOnAction(new NameChange());  

    //https://youtu.be/AP4e6Lxncp4 - SeparatorMenuItem() source 
    menu1.getItems().add(new SeparatorMenuItem());

    //Print 
    MenuItem print = new MenuItem("Print");
    menu1.getItems().add( print);
    print.setOnAction(new PrintWheelItems());

    //Size  
    MenuItem size = new MenuItem("Size");
    menu1.getItems().add(size);
    size.setOnAction(new PrintSize());

    //First 
    MenuItem first = new MenuItem("Print First Item");
    menu1.getItems().add(first);
    first.setOnAction(new PrintFirst());

    //Last   
    MenuItem last = new MenuItem("Print Last Item");
    menu1.getItems().add(last);
    last.setOnAction(new PrintLast());

    //Random 
    MenuItem random = new MenuItem("Print Random Item");
    menu1.getItems().add(random);
    random.setOnAction(new PrintRandom());

    menu1.getItems().add(new SeparatorMenuItem());

    //Spin   
    MenuItem spin = new MenuItem("Spin");
    menu1.getItems().add(spin);
    spin.setOnAction(new SpinWheel());

    //Clear 
    MenuItem clear = new MenuItem("Clear");
    menu1.getItems().add(clear);
    clear.setOnAction(new ClearWheel());

    //Exit 
    MenuItem exit = new MenuItem("Exit");
    menu1.getItems().add(exit);
    exit.setOnAction(e -> System.exit(0));

    return mb;
  }

  /**
   * Changes the name of the wheel 
   */
  class NameChange implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent arg0) {
      innerBox.getChildren().clear();
      Label labTest = new Label("Change Wheel Name");
      Button confirm = new Button("Ok");
      //https://youtu.be/cwJK_WpseKQ?list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG "TextField" source
      TextField nameChange = new TextField();
      confirm.setOnAction(e -> wheelName = nameChange.getText());
      innerBox.getChildren().addAll(labTest, nameChange, confirm);
    }
  }

  /**
   * The user may enter items into the wheel 
   */
  class ItemInsert implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent arg0) {
      innerBox.getChildren().clear();
      Button confirm = new Button("Add");
      TextField addItem = new TextField();
      confirm.setOnAction(e -> {
        list.addToEnd(addItem.getText());
        Label items = new Label(list.getLast());
        innerBox.getChildren().add(items);  
      });
      innerBox.getChildren().addAll(addItem, confirm);
    }
  }

  /**
   * The items in the wheel are displayed 
   */
  class PrintWheelItems implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent arg0) {
      innerBox.getChildren().clear();
      if(list.numItems() == 0){
        Label warning = new Label("Add something to the wheel!");
        innerBox.getChildren().add(warning);
      }
      else{
      Label title = new Label(wheelName + ": ");
      Label wheelItems = new Label(list.toString());
      innerBox.getChildren().addAll(title, wheelItems);
      }
    }
  }

  /**
   * The size of the list is displayed 
   */
  class PrintSize implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent arg0) {
      innerBox.getChildren().clear();
      Label wheelSize = new Label("Size: " + Integer.toString(list.numItems()));
      innerBox.getChildren().add(wheelSize);
    } 
  }

  /**
   * The first item in the wheel is displayed 
   */
  class PrintFirst implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent arg0) {
      innerBox.getChildren().clear();
      //A warning message is displayed if there are no items in the wheel 
      if(list.numItems() == 0){
        Label warning = new Label("Add something to the wheel!");
        innerBox.getChildren().add(warning);
      }
      Label firstItem = new Label(list.getFirst());
      innerBox.getChildren().add(firstItem);
    }
  }

  /**
   * The last item of the wheel is displayed 
   */
  class PrintLast implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent arg0) {
      innerBox.getChildren().clear();
      //A warning message is displayed if there are no items in the wheel 
      if(list.numItems() == 0){
        Label warning = new Label("Add something to the wheel!");
        innerBox.getChildren().add(warning);
      }
      Label lastItem = new Label(list.getLast());
      innerBox.getChildren().add(lastItem);
    }
  }

  /**
   * A random item is selected and displayed 
   */
  class PrintRandom implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent arg0) {
      innerBox.getChildren().clear();
      //A warning message is displayed if there are no items in the wheel 
      if(list.numItems() == 0){
        Label warning = new Label("Add something to the wheel!");
        innerBox.getChildren().add(warning);
      }
      Label randomItem = new Label(list.get(randomNum()));
      innerBox.getChildren().add(randomItem);
    }
  }

  /**
   * Spins the wheel - a random item is selected, displayed, and then removed 
   */
  class SpinWheel implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent arg0) {
      innerBox.getChildren().clear();
      //A warning message is displayed if there are no items in the wheel 
      if(list.numItems() == 0){
        Label warning = new Label("Add something to the wheel!");
        innerBox.getChildren().add(warning);
      }
      else{
        int randNum = randomNum();
        Label chosenItem = new Label(list.get(randNum));
        innerBox.getChildren().add(chosenItem);
        list.remove(randNum);
      }
    }
  }

  /**
   *Clears the wheel
   */ 
  class ClearWheel implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent arg0) {
      innerBox.getChildren().clear();
      Label items = new Label("Wheel cleared!");
      list.clear();
      innerBox.getChildren().add(items);
    }
  }

  /**
   * @return A random number for the "Print Random Item" and "Spin" commands 
   */
  public int randomNum(){
    Random num = new Random();
    int index = num.nextInt(list.numItems());
    return index;
  } 
  
  public static void main(String[] args) {
    list = new MyList();
    launch(args);
  }

}

