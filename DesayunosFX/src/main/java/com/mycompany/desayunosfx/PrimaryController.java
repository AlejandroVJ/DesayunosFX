package com.mycompany.desayunosfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Pedidos;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryController implements Initializable {
    FuncionPedido funcionP = new FuncionPedido();

    @FXML
    private TableColumn<Pedidos, Integer> id;
    @FXML
    private TableColumn<Pedidos, String> cliente;
    @FXML
    private TableColumn<Pedidos, String> curso;
    @FXML
    private TableColumn<Pedidos, Date> fecha;
    @FXML
    private TableColumn<Pedidos, String> estado;
    @FXML
    private TableColumn<Pedidos, Integer> cartaId;

    private static SessionFactory sf = new Configuration().configure().buildSessionFactory();
    private static Session s = sf.openSession();

    @FXML
    private TableView<Pedidos> tab;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Pedidos> obl = FXCollections.observableArrayList();
        tab.setItems(obl);
        Timer t = new Timer();
        TimerTask tTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        obl.clear();
                        obl.addAll(funcionP.listaComandas());
                        count();

                    }

                });

            }

        };
        t.scheduleAtFixedRate(tTask, 0, 1000);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        curso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        cartaId.setCellValueFactory(new PropertyValueFactory<>("carta_Id"));
        
        obl.addAll(funcionP.listaComandas());

    }

}
