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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Carta;
import models.Pedidos;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class PrimaryController implements Initializable {

    FuncionPedido funcionP = new FuncionPedido();
    FuncionCarta funcionC = new FuncionCarta();

    private TableColumn<Pedidos, Integer> id;
    @FXML
    private TableColumn<Pedidos, String> cliente;
    @FXML
    private TableColumn<Pedidos, String> curso;
    private TableColumn<Pedidos, Date> fecha;
    @FXML
    private TableColumn<Pedidos, String> estado;
    private TableColumn<Pedidos, Integer> cartaId;

    @FXML
    private TableView<Pedidos> tab;
    @FXML
    private Label pedidosHoy;
    
    private static SessionFactory sf = new Configuration().configure().buildSessionFactory();
    private static Session s = sf.openSession();

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
    public void count(){
        pedidosHoy.setText("PENDIENTES: "+String.valueOf(funcionP.listaComandasPendientes).size());
    
    }

    @FXML
    private void pulsar(MouseEvent event) {
        Pedidos ped = tab.getSelectionModel().getSelectedItem();
        Transaction t = s.beginTransaction();
        ped.setEstado("ENTREGADO");
        s.update(ped);
        t.commit();
        funcionP.listaComandas();
    }
}
