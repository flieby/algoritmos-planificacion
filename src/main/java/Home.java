import bo.FIFO;
import bo.RR;
import bo.SJF;
import com.google.gson.Gson;
import commons.CommonUtils;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import objects.Work;

import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Home extends Application {

    public Button generar;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //Creating a GridPane container
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        //Defining the Name text field
        final TextField name = new TextField();
        name.setMinWidth(400);
        name.setPromptText("C:\\Users\\flieby\\Projects\\aso-planificacion\\src\\main\\resources\\scenarios.json");
        name.setText("C:\\Users\\flieby\\Projects\\aso-planificacion\\src\\main\\resources\\scenarios.json");
        name.setPrefColumnCount(75);
        name.getText();
        GridPane.setConstraints(name, 0, 1);
        grid.getChildren().add(name);


        //Defining the Name text field
        StringBuffer sb = new StringBuffer();
        sb.append("FACULTAD: Tecnología Informática\n");
        sb.append("CARRERA: Lic. en gestión de Tecnología informática\n");
        sb.append("Sede: Castelar\n");
        sb.append("Asignatura: Arquitectura de Sistemas Operativos\n");
        sb.append("PROFESOR: ING. DAMIAN BERRUTTI\n");
        sb.append("ALUMNO: Facundo Lieby\n");
        final ScrollPane scroll2 = new ScrollPane();
        GridPane.setConstraints(scroll2, 0, 0);
        GridPane.setColumnSpan(scroll2, 2);
        grid.getChildren().add(scroll2);
        StackPane pane1 = new StackPane();
        final TextFlow textFlow1 = new TextFlow();
        Text text1 = new Text(sb.toString().toUpperCase());
        textFlow1.getChildren().add(text1);
        pane1.getChildren().add(textFlow1);
        scroll2.setContent(pane1);

        //Adding a Label
        final ScrollPane scroll = new ScrollPane();
        GridPane.setConstraints(scroll, 0, 3);
        GridPane.setColumnSpan(scroll, 2);
        grid.getChildren().add(scroll);

        //Defining the Submit button
        Button submit = new Button("Calcular");
        GridPane.setConstraints(submit, 1, 1);

        //Setting an action for the Submit button
        submit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                StackPane pane = new StackPane();
                final TextFlow textFlow = new TextFlow();
                String output = generarOutput(name.getText());
                System.out.println(output);
                Text text = new Text(output);
                textFlow.getChildren().add(text);
                pane.getChildren().add(textFlow);
                scroll.setContent(pane);
            }
        });
        grid.getChildren().add(submit);


        primaryStage.setScene(new Scene(grid, 800, 300));
        primaryStage.setTitle("TP PLANIFICACIÓN - FACUNDO LIEBY");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public String generarOutput(String path) {
        Gson gson = new Gson();

        try {
            FileReader file = new FileReader(path);

            Work wl = gson.fromJson(file, Work.class);
            StringBuffer sb = new StringBuffer();
            sb.append(CommonUtils.informAlgString("FIFO"));
            sb.append(FIFO.findavgTime(wl));
            sb.append(CommonUtils.informAlgString("SJF/SPN (Sin interrupciones)"));
            sb.append(SJF.printNonPreemptive(wl));
            sb.append(CommonUtils.informAlgString("SRT/SPN (Con interrupciones) sin penalizacion"));
            sb.append(SJF.printPreemptive(wl,false));
            sb.append(CommonUtils.informAlgString("SRT/SPN (Con interrupciones) con penalizacion"));
            sb.append(SJF.printPreemptive(wl,true));
//            sb.append(CommonUtils.informAlgString("Round Robin q=1 sin penalizacion"));
//            sb.append(RR.printRR(wl, 1, false));
//            sb.append(CommonUtils.informAlgString("Round Robin q=2 sin penalizacion"));
//            sb.append(RR.printRR(wl, 2, false));
//            sb.append(CommonUtils.informAlgString("Round Robin q=3 sin penalizacion"));
//            sb.append(RR.printRR(wl, 3, false));
//            sb.append(CommonUtils.informAlgString("Round Robin q=4 sin penalizacion"));
//            sb.append(RR.printRR(wl, 4, false));
            sb.append(CommonUtils.informAlgString("Round Robin q=1 con penalizacion"));
            sb.append(RR.printRR(wl, 1, true));
            sb.append(CommonUtils.informAlgString("Round Robin q=2 con penalizacion"));
            sb.append(RR.printRR(wl, 2, true));
            sb.append(CommonUtils.informAlgString("Round Robin q=3 con penalizacion"));
            sb.append(RR.printRR(wl, 3, true));
            sb.append(CommonUtils.informAlgString("Round Robin q=4 con penalizacion"));
            sb.append(RR.printRR(wl, 4, true));
            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
