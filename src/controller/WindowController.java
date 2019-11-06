package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import modelo.*;


public class WindowController implements Initializable  {
	
	
	
	@FXML private GridPane matrixGP;
		  private Buscaminas program;
	@FXML private ComboBox<String> nivel;
	@FXML private BorderPane matrixBP;


		  
	@FXML
	public void generar(ActionEvent event) {
	
		
	  	String level= (String) nivel.getValue();

		matrixGP = new GridPane();
		VBox matrixVB = new VBox();
		matrixBP.setCenter(matrixVB);
		matrixVB.setAlignment(Pos.CENTER);
		matrixGP.setAlignment(Pos.CENTER);
		matrixVB.getChildren().add(matrixGP);
	
		HBox options = new HBox();
		Button resolverB = new Button("Resolver.");
		Button darPistaB = new Button("Dar pista.");
		Button reiniciarB = new Button("Reiniciar Juego.");
		options.getChildren().add(resolverB);
		options.getChildren().add(darPistaB);
		options.getChildren().add(reiniciarB);
		
		matrixBP.setBottom(options);

		if (level.equals("Principiante")) {
		program = new Buscaminas(1);
		}
		
		else if(level.equals("Intermedio")) {
			
		program = new Buscaminas(2);
		
		}
		
		else if(level.equals("Experto")) {
		program = new Buscaminas(3);
		
		}
		
		for(int i = 0 ; i<program.darCasillas().length ; i++) {
			for(int j = 0 ; j<program.darCasillas()[i].length;j++) {
				Button minas = new Button();
				
				
				final int ik = i;
				final int jk = j;

				minas.setOnMouseClicked(new EventHandler<Event>() {
					
					@Override
					public void handle(Event evt) {
						destapar(ik,jk);	
						minas.setText(program.darCasillas()[ik][jk].mostrarValorCasilla());
					}
				});
		
				matrixGP.add(minas,i,j);
			}
		}
	
		resolverB.setOnMouseClicked(new EventHandler<Event>() {
			
			@Override
			public void handle(Event evt) {
				matrixGP = new GridPane();
				VBox matrixVB = new VBox();
				matrixBP.setCenter(matrixVB);
				matrixVB.setAlignment(Pos.CENTER);
				matrixGP.setAlignment(Pos.CENTER);
				matrixVB.getChildren().add(matrixGP);
				
				for(int i = 0 ; i<program.darCasillas().length ; i++) {
					for(int j = 0 ; j<program.darCasillas()[i].length;j++) {
						Button minas = new Button();
						
						final int ik = i;
						final int jk = j;
						
						
						program.darCasillas()[ik][jk].destapar();
						minas.setText(program.darCasillas()[ik][jk].mostrarValorCasilla());
					
						matrixGP.add(minas,i,j);
					}
				}
			}
			
		});
	darPistaB.setOnMouseClicked(new EventHandler<Event>() {
			
			@Override
			public void handle(Event evt) {
				
				try {
					String des = program.darPista();
					matrixGP.getChildren().clear();
					for (int i = 0; i < program.darCasillas().length; i++) {
						for (int j = 0; j < program.darCasillas()[0].length; j++) {
							Button b1 = new Button(program.darCasillas()[i][j].mostrarValorCasilla());
							b1.setId(i+","+j);
							if(!program.darCasillas()[i][j].darSeleccionada()) {
								b1.setOnAction(e->{
									destaparButton(b1);});
								}
							matrixGP.add(b1,i,j);
								}
						}
				
				} catch (ExcepcionPista e) {
					
					e.printStackTrace();
				}
			
			}
			
		});
	reiniciarB.setOnMouseClicked(new EventHandler<Event>() {
		
		@Override
		public void handle(Event evt) {
			
			
			generar(event);
		}
		
	});

		
}
	
	
	
	public void destapar(int i, int j) {
		program.darCasillas()[i][j].destapar();
		if (program.darCasillas()[i][j].esMina()) {
			
			program.darCasillas()[i][j].destapar();
			//JOptionPane.showConfirmDialog(null, "Abriste una mina PERDISTE..!!");
			Alert mina = new Alert(AlertType.ERROR);
        	mina.setTitle("Fin del juego");
        	mina.setHeaderText(null);
        	mina.initStyle(StageStyle.UTILITY);
        	mina.setContentText("Perdiste abriste una mina");
        	mina.show();
        	matrixGP.getChildren().clear();
   
		}
	}


@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	nivel.getItems().addAll("Principiante","Intermedio","Experto");
}
		
public void destaparButton(Button b1) {
	
		String text = b1.getId();
		String parts[] = text.split(",");
		int i = Integer.parseInt(parts[0]);
		int j = Integer.parseInt(parts[1]);
		program.abrirCasilla(i, j);
		b1.setText(program.darCasillas()[i][j].mostrarValorCasilla());
		b1.setOnAction(e -> {
		});
		if (program.darPerdio()) {
			
			Alert mina = new Alert(AlertType.ERROR);
        	mina.setTitle("Fin del juego");
        	mina.setHeaderText(null);
        	mina.initStyle(StageStyle.UTILITY);
        	mina.setContentText("Perdiste abriste una mina");
        	mina.show();
        	matrixGP.getChildren().clear();
   
			//JOptionPane.showMessageDialog(null, "Perdiste");
			solucionarBuscaminas();
		} else if (program.gano()) {
			Alert mina = new Alert(AlertType.ERROR);
        	mina.setTitle("Fin del juego");
        	mina.setHeaderText(null);
        	mina.initStyle(StageStyle.UTILITY);
        	mina.setContentText("Ganaste..!!");
        	mina.show();
			//JOptionPane.showMessageDialog(null, "ganaste");
			solucionarBuscaminas();
		

	}

}

public void solucionarBuscaminas() {
	program.resolver();
	matrixGP.getChildren().clear();
	for (int i = 0; i < program.darCasillas().length; i++) {
		for (int j = 0; j < program.darCasillas()[0].length; j++) {
			Button b1 = new Button(program.darCasillas()[i][j].mostrarValorCasilla());
			
			matrixGP.add(b1, j, i);
		}
	}
}



}

