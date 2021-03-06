package application;

import javafx.application.Application;

import java.io.FileNotFoundException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainFX extends Application {
	
/**	public static Fraeskopf fraeskopf = new Fraeskopf();
	public static Kuehlmittel kuehlmittel = new Kuehlmittel();
	public static Spindel spindel = new Spindel();
	public static ErrorHandling errorHandler = new ErrorHandling();
	public static BefehlHandler befehlHandler = new BefehlHandler();
	**/
	
	static BorderPane root = new BorderPane();
	
	public static Circle bohrer = new Circle(100, 100, 7.5, Color.RED);
	
	Label position = new Label("Position: " + Fraeskopf._getPosition());
	
	Label spindelStatus = new Label(Spindel.SpindelAusgabe());
	
	Label kuehlmittelStatus = new Label("Kuehlmittelstatus: " + Kuehlmittel._getStatus());
	
	Label geschwindigkeit = new Label("Geschwindigkeit: " + Fraeskopf._getGeschwindigkeit() + "m/min");
	
	public static void moveLine(int x, int y) {
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20),
	            new EventHandler<ActionEvent>() {

	    	double dx = 1; //Step on x or velocity
	    	double dy = 1; //Step on y

	        public void handle(ActionEvent t) {
	        	//move the ball
	        	if (bohrer.getLayoutX() == x) {
	        		dx = 0;
	        	}
	        	else if (bohrer.getLayoutY() == y) {
	        		dy = 0;
	        	}
	        	else {
	        		bohrer.setLayoutX(bohrer.getLayoutX() + dx);
		        	bohrer.setLayoutY(bohrer.getLayoutY() + dy);
	        	}
	        	
	        }
		}));
		
		timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
	}
	
	public static void fraesenLine(int x, int y, double dx, int dy) {
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20),
	            new EventHandler<ActionEvent>() {
			
	        public void handle(ActionEvent t) {
	        	//move the ball
		        Circle circle2 = new Circle(bohrer.getLayoutX() + 100, bohrer.getLayoutY() + 100, 3.75, Color.BLACK);
		        root.getChildren().add(circle2);
	        	if (bohrer.getLayoutX() < x || bohrer.getLayoutY() < y) {
	        		if (bohrer.getLayoutX() >= x) {
		        		final int dx = 0;
		        	}
	        		else if (bohrer.getLayoutY() >= y) {
		        		final int dy = 0;
		        	}
			        Circle circle = new Circle(bohrer.getLayoutX() + 100, bohrer.getLayoutY() + 100, 3.75, Color.BLACK);
			        root.getChildren().add(circle);
		        	bohrer.setLayoutX(bohrer.getLayoutX() + dx);
		        	bohrer.setLayoutY(bohrer.getLayoutY() + dy);
	        	}
	        }
		}));
		
		timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
	}

	public static void main(String[] args) throws FileNotFoundException {
		BefehlHandler.befehlAufrufen(3);
		launch(args);

		Thread t1 = new Thread (new Fraeskopf());
		Thread t2 = new Thread (new Kuehlmittel());
		t1.start();
		t2.start();
		run();
	}
	
	public static void run() {
		
	}
	
	@Override
//				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

	public void start(Stage primaryStage) {
			primaryStage.setTitle("Fraesmaschine");
			primaryStage.setResizable(false);
			//kein Resizen m�glich um Fehler zu vermeiden
			primaryStage.centerOnScreen();
			
			Scene scene = new Scene(root, 1100, 725);
			
			Rectangle arbeitsflaeche = new Rectangle(1100, 725, Color.GREY);
			root.getChildren().add(arbeitsflaeche);
			
			Rectangle info = new Rectangle(200, 725, Color.WHITE);
			info.setX(800);
			root.getChildren().add(info);
			
			Rectangle borderO = new Rectangle(1100, 100, Color.WHITE);
			root.getChildren().add(borderO);
			
			Rectangle borderR = new Rectangle(100, 725, Color.WHITE);
			borderR.setX(1000);
			root.getChildren().add(borderR);
			
			Rectangle borderU = new Rectangle(1100, 100, Color.WHITE);
			borderU.setY(625);
			root.getChildren().add(borderU);
			
			Rectangle borderL = new Rectangle(100, 725, Color.WHITE);
			root.getChildren().add(borderL);
			
			root.getChildren().add(bohrer);
			
			Circle home = new Circle(100,100,5,Color.GREEN);
			root.getChildren().add(home);
			 
			
			VBox infos = new VBox();
			
			HBox suchen = new HBox();
			
			Label befehl = new Label ("Befehl: ");
			TextField textField = new TextField ();
			Button go = new Button("Go");
			
			suchen.getChildren().addAll(befehl, textField, go);
			suchen.setSpacing(5);
			
			infos.getChildren().addAll(position, spindelStatus, kuehlmittelStatus, geschwindigkeit, suchen);
			root.setRight(infos);
			infos.setSpacing(10);
			
			HBox buttons = new HBox();
			
			Button weiter = new Button("Weiter");
			weiter.setDisable(true);
			
			Button stop = new Button("Stop");
			stop.setDisable(true);
			
			Button abbrechen = new Button("Abbrechen");
			abbrechen.setDisable(true);
			
			stop.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					stop.setDisable(true);
					weiter.setDisable(false);
					Fraeskopf.stoppFraese();
				}
			});
			
			abbrechen.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					stop.setDisable(true);
					abbrechen.setDisable(true);
					Fraeskopf.stoppFraese();
					Fraeskopf._setPositionX(0);
					Fraeskopf._setPositionY(0);
				}
			});
			
			weiter.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					stop.setDisable(false);
					abbrechen.setDisable(false);
				}
			});
			
			buttons.getChildren().addAll(stop, weiter, abbrechen);
			root.setBottom(buttons);		
			buttons.setSpacing(5);
			
		/**	Text arbeitsflaeche = new Text(25, 25, "Arbeitsflaeche");
			arbeitsflaeche.setFill(Color.CHOCOLATE);
			arbeitsflaeche.setFont(Font.font(java.awt.Font.SERIF, 25));
			root.getChildren().add(arbeitsflaeche); **/
        
			primaryStage.setScene(scene);
			primaryStage.show();
			
	}

}
//Lisa
