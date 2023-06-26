package fr.azures.mod.greenportals.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) {
    	List<String> list = new ArrayList<String>();  

    	TextField xCoord = new TextField();
        TextField yCoord = new TextField();
        TextField zCoord = new TextField();

		Set<RegistryKey<World>> dimensions = ServerLifecycleHooks.getCurrentServer().levelKeys();
        dimensions.forEach(dimension -> {
        	list.add(dimension.location().toString());
        });    
        ChoiceBox <String> choiceBox = new ChoiceBox<>(FXCollections.observableList(list));
        xCoord.setPromptText("X :");
        yCoord.setPromptText("Y :");
        zCoord.setPromptText("Z :");
        
        choiceBox.setValue("Selectionnez une dimension");
        
        Button button = new Button("Valider");
        button.setOnAction(event -> {
            String text1 = xCoord.getText();
            String text2 = yCoord.getText();
            String text3 = zCoord.getText();
            String selectedOption = choiceBox.getValue();
            System.out.println("Champ 1 : " + text1);
            System.out.println("Champ 2 : " + text2);
            System.out.println("Champ 3 : " + text3);
            System.out.println("Option sélectionnée : " + selectedOption);
        });

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(xCoord, yCoord, zCoord, choiceBox, button);

        Scene scene = new Scene(root, 300, 200);
        
        primaryStage.setTitle("Portal Gun Config");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}