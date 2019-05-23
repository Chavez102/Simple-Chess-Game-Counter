package Main_Package;
import java.awt.Toolkit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
 
public class GUI extends Application
{

	static int minutes=5;
	static int seconds=0;
	public static int testing=0;
	public static Scene scene1,scene2, scene3;
	static int time_choice=5;
	static Timeline timeline;
	
	static double this_height=400;
			static double this_width=400;

 
	public static void main(String[] args) 
	{
	launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		Scene1(primaryStage);
	}

	public static void timer1(Text text, GridPane Grid2, Stage primaryStage)
	{
		minutes=time_choice;
		seconds=1;
		
		 timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() 
		{
		    @Override
		    public void handle(ActionEvent event) 
		    {
		    	
		     	seconds--;
			    if (seconds<0){minutes-=1; seconds=59;}
			    if (minutes<0){minutes=59;seconds=59;}
			    if(minutes==0&&seconds<11)
			    	{
			    	Grid2.setStyle("-fx-background-color: #FF0000;");
			    	Toolkit.getDefaultToolkit().beep();
			    	};

			    text.setText("");
				//String hoursformatted = String.format("%02d", hours);
				String minutesformatted = String.format("%02d", minutes);			
				String secondsformatted = String.format("%02d", seconds);
				text.setText(minutesformatted+":"+secondsformatted);
				
				
				if(minutes==0&&seconds==0)
			    {
					timeline.stop();
					play_sound();
					 try {
						primaryStage.setScene(Scene3(primaryStage));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
		    }
		    
			 private void play_sound()
				{
					AudioClip note=new AudioClip(getClass().getResource("Sad_Trombone.wav").toString());
					note.play();
				}
			 
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		
	}

	private static DoubleProperty fontSize = new SimpleDoubleProperty(50);
	    private static IntegerProperty blues = new SimpleIntegerProperty(50);
	    
	    
	    
	static public Scene Scene1(Stage primaryStage) throws Exception
	{
//////////////Scene 1\

	
	Button Go_btn = new Button("Go!");
	Go_btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);


	
	
	ChoiceBox<String> choice_box = new ChoiceBox<String>(FXCollections.observableArrayList("1","2","3","4","5", "10"));
	 choice_box.getSelectionModel().select(4);
	choice_box.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() 
	 {
		
	      public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) 
	      {
	      time_choice=Integer.parseInt(choice_box.getItems().get((Integer) number2));
	      minutes=time_choice;
	      }
	 });
	

	
	choice_box.setMaxHeight(Double.MAX_VALUE);
	
	Go_btn.setOnAction(new EventHandler<ActionEvent>() { 
	@Override
	public void handle(ActionEvent event) 
	{
		this_height=scene1.getHeight();
		 this_width=scene1.getWidth();
	
			try {
				primaryStage.setScene(Scene2(primaryStage));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	});
	Text text=new Text("how long?");
	

	GridPane Grid1=new GridPane();
	GridPane.setHalignment(text, HPos.CENTER);
	GridPane.setHgrow(text, Priority.ALWAYS);
	GridPane.setVgrow(text, Priority.ALWAYS);
	Grid1.setAlignment(Pos.CENTER);
	Grid1.setHgap(10);
	Grid1.add(text, 1, 2);
	Grid1.add(choice_box, 2, 2);
	Grid1.add(Go_btn, 3, 2);
	primaryStage.setTitle("Chess counter");
	scene1= new Scene(Grid1, this_width, this_height);
	primaryStage.setScene(scene1);
	primaryStage.show();
	return scene1;
	
	}
	
	
	static public Scene Scene2(Stage primaryStage) throws Exception 
	{
	//////////////Scene 2
		Button Reset_btn = new Button("Reset");
		Button Cancel_btn=new Button("Cancel");
		Button Home_btn=new Button("Home");
		
		Reset_btn.setOnAction(new EventHandler<ActionEvent>() 
		{ 
			
		@Override
			public void handle(ActionEvent event) 
			{
			this_height=scene2.getHeight();
			 this_width=scene2.getWidth();
			minutes=time_choice;
			timeline.stop();
				try {
					primaryStage.setScene(Scene2(primaryStage));
				} catch (Exception e) {e.printStackTrace();}
			}
		});	
		
		
		
		Cancel_btn.setOnAction(e->System.exit(0));
		Text text=new Text("Loading...");
		text.setFont(Font.font ("Verdana"));
		Home_btn.setOnAction(e->
		{
			timeline.stop();
			this_height=scene2.getHeight();
			 this_width=scene2.getWidth();
			try {
				primaryStage.setScene(Scene1(primaryStage));
			}catch (Exception e1) {e1.printStackTrace();}
		});
		GridPane.setHalignment(Home_btn, HPos.RIGHT);
		GridPane.setValignment(Home_btn, VPos.TOP);
		
		GridPane Grid2=new GridPane();
		GridPane.setHalignment(text, HPos.CENTER);
		GridPane.setHgrow(text, Priority.ALWAYS);
		GridPane.setVgrow(text, Priority.ALWAYS);
		GridPane.setHgrow(Cancel_btn, Priority.ALWAYS);
		GridPane.setVgrow(Cancel_btn, Priority.ALWAYS);
		GridPane.setVgrow(Reset_btn, Priority.ALWAYS);
		GridPane.setHgrow(Reset_btn, Priority.ALWAYS);
		GridPane.setVgrow(Home_btn, Priority.ALWAYS);
		GridPane.setHgrow(Home_btn, Priority.ALWAYS);
		
		Reset_btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		Cancel_btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		Home_btn.setMinSize(50, 50);
		
		Grid2.setHgap(20);
		Grid2.setVgap(5);
		Grid2.setPadding(new Insets(10,10,10,10));
	
		Grid2.add(text, 0,1,4,1);
		Grid2.add(Reset_btn, 0,4,1,1);
		Grid2.add(Cancel_btn, 3,4,1,1);
		Grid2.add(Home_btn, 3,0);
		Grid2.isResizable();
		Grid2.setMinHeight(500);
		primaryStage.setTitle("Chess Counter");
		
		 timer1(text,Grid2,primaryStage);
		
		scene2= new Scene(Grid2,this_width,this_height);
		
		  fontSize.bind(scene2.widthProperty().add(scene2.heightProperty()).divide(20));
	        text.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.subtract(20).asString(), ";"
	        		  ,"-fx-base: rgb(100,100,",blues.asString(),");"));
	     //   Reset_btn.setStyle("-fx-text-fill: green");
	        Reset_btn.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.subtract(20).asString(), ";"
	        		  ,"-fx-base: rgb(123,104,238,",blues.asString(),");"));
	   
	        Cancel_btn.styleProperty().bind(Bindings.concat("-fx-font-size: ",  fontSize.subtract(20).asString(), ";"
	        		  ,"-fx-base: rgb(255,0,0,",blues.asString(),");"));
	        
        Home_btn.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.subtract(20).asString(), ";"
	        		, "-fx-base: rgb(255,0,0,",blues.asString(),");"));

	        
		return scene2;
		
	}
	

	
	static public Scene Scene3(Stage primaryStage) 
	{
	//////////////Scene3


		Text text=new Text("You loose");
		text.setFont(Font.font ("Verdana", 80));

		Button Home_btn=new Button("Home");
		
		Home_btn.setOnAction(e->{
			this_height=scene3.getHeight();
			 this_width=scene3.getWidth();
			try {
				primaryStage.setScene(Scene1(primaryStage));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		
		GridPane Grid3=new GridPane();
		 
		GridPane.setHalignment(text, HPos.CENTER);
		Grid3.setAlignment(Pos.CENTER);
		Grid3.setVgap(50);
		Grid3.setHgap(30);
		Grid3.add(text, 0,0, 3,2);
		Grid3.add(Home_btn, 1, 2);
		
		Grid3.setStyle("-fx-background-color: #FF0000;");
		
		GridPane.setHalignment(Home_btn, HPos.CENTER);
	//	Home_btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		primaryStage.setTitle("Chess Counter");
		Grid3.setGridLinesVisible(false);
	 scene3= new Scene(Grid3,this_width,this_height);
	 Home_btn.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";"
     		, "-fx-base: rgb(255,0,0,",blues.asString(),");"));
		GridPane.setVgrow(Home_btn, Priority.ALWAYS);
		GridPane.setHgrow(Home_btn, Priority.ALWAYS);
    
		
			
		return scene3;
		
	}
	

	
	
	
	
	
	
	
	

}
