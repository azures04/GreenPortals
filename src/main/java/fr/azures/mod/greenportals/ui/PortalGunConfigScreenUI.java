package fr.azures.mod.greenportals.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import fr.azures.mod.greenportals.utils.Temp;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.World;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class PortalGunConfigScreenUI extends Application {
	
	public static boolean isOpened = false;
	
    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) {
    	isOpened = true;
    	List<String> list = new ArrayList<String>();  

    	TextField xCoord = new TextField();
        TextField yCoord = new TextField();
        TextField zCoord = new TextField();

		Set<RegistryKey<World>> dimensions = ServerLifecycleHooks.getCurrentServer().levelKeys();
        dimensions.forEach(dimension -> {
        	list.add(dimension.location().toString());
        });    
        ChoiceBox <String> dimChoiceBox = new ChoiceBox<>(FXCollections.observableList(list));
        xCoord.setPromptText("X :");
        yCoord.setPromptText("Y :");
        zCoord.setPromptText("Z :");
        
        Button button = new Button("Valider");
        button.setOnAction(event -> {
        	Temp.xCoord = Integer.valueOf(xCoord.getText());
        	Temp.yCoord = Integer.valueOf(yCoord.getText());
        	Temp.zCoord = Integer.valueOf(zCoord.getText());
        	Temp.dimCoord = dimChoiceBox.getValue();
        	System.out.println(Temp.xCoord);
        	System.out.println(Temp.yCoord);
        	System.out.println(Temp.zCoord);
        	System.out.println(Temp.dimCoord);
        	isOpened = false;
        	primaryStage.hide();
        	try {
				stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
        });

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(xCoord, yCoord, zCoord, dimChoiceBox, button);

        Scene scene = new Scene(root, 300, 200);
        
        primaryStage.setTitle("Portal Gun Config");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}