package com.lab7zhakin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import java.text.ParseException;
import java.util.ArrayList;

public class MainController {
    @FXML
    private TextField name;
    @FXML
    private TextField price;
    @FXML
    private TextField manufacturer;
    @FXML
    private TextField size;

    @FXML
    private TextField find;
    @FXML
    private Label info;
    @FXML
    private Button add;
    @FXML
    private Button remove;
    @FXML
    private Button edit;
    @FXML
    private Button sort;
    @FXML
    private Button search;
    @FXML
    private Button reset;
    private ObservableList<Market> Collectssize = FXCollections.observableArrayList();
    @FXML
    private TableView<Market> table;
    @FXML
    private TableColumn<Market, String> Cname;
    @FXML
    private TableColumn<Market, String> Cprice;
    @FXML
    private TableColumn<Market, String> Cmanufacturer;
    @FXML
    private TableColumn<Market, String> Csize;

    protected void initialize() {
        Cname.setCellValueFactory(new PropertyValueFactory<>("name"));
        Cprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        Cmanufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        Csize.setCellValueFactory(new PropertyValueFactory<>("size"));
        table.setEditable(true);
        Cname.setCellFactory(TextFieldTableCell.forTableColumn());
        Cprice.setCellFactory(TextFieldTableCell.forTableColumn());
        Cmanufacturer.setCellFactory(TextFieldTableCell.forTableColumn());
        Csize.setCellFactory(TextFieldTableCell.forTableColumn());
        table.setItems(Collectssize);
        Cname.setSortType(TableColumn.SortType.ASCENDING);
    }
    @FXML
    protected void addItem() throws ParseException {
        try {
            if (name.getText().length()!=0 && price.getText().length()!=0
                    && manufacturer.getText().length()!=0 && size.getText().length()!=0) {
                Market col = new Market(name.getText(), price.getText(), manufacturer.getText(), size.getText());
                Collectssize.add(col);
                info.setText("Данные успешно добавлены в таблицу");
                initialize();
            } else info.setText("Все поля должны быть заполнены");
        } catch (IllegalStateException | NumberFormatException exc) {
            info.setText("Неверный формат ввода");
        }
        name.setText(null);
        price.setText(null);
        manufacturer.setText(null);
        size.setText(null);
    }

    @FXML
    protected void remove() {
        Collectssize=table.getSelectionModel().getSelectedItems();
        ObservableList<Market> allCollects = table.getItems();

        if (Collectssize!=null) {
            ArrayList<Market> rows = new ArrayList<>(Collectssize);
            rows.forEach(row->table.getItems().remove(row));
            info.setText("Строка успешна удалена");
            Collectssize=allCollects;
        }
    }

    @FXML
    protected void edit() {
        table.setEditable(true);
        Cname.setCellFactory(TextFieldTableCell.forTableColumn());
        Cprice.setCellFactory(TextFieldTableCell.forTableColumn());
        Cmanufacturer.setCellFactory(TextFieldTableCell.forTableColumn());
        Csize.setCellFactory(TextFieldTableCell.forTableColumn());

        Cname.setOnEditCommit((TableColumn.CellEditEvent<Market, String> e)->{
            TablePosition<Market, String> pos=e.getTablePosition();
            String newName=e.getNewValue();
            int row = pos.getRow();
            Market col=e.getTableView().getItems().get(row);
            col.setName(newName);
        });

        Cprice.setOnEditCommit((TableColumn.CellEditEvent<Market, String> e)->{
            TablePosition<Market, String> pos=e.getTablePosition();
            String newPrice=e.getNewValue();
            int row = pos.getRow();
            Market col=e.getTableView().getItems().get(row);
            col.setPrice(newPrice);
        });

        Cmanufacturer.setOnEditCommit((TableColumn.CellEditEvent<Market, String> e)->{
            TablePosition<Market, String> pos=e.getTablePosition();
            String newmanufacturer=e.getNewValue();
            int row = pos.getRow();
            Market col=e.getTableView().getItems().get(row);
            col.setManufacturer(newmanufacturer);
        });

        Csize.setOnEditCommit((TableColumn.CellEditEvent<Market, String> e)->{
            TablePosition<Market, String> pos=e.getTablePosition();
            String newSize=e.getNewValue();
            int row = pos.getRow();
            Market col=e.getTableView().getItems().get(row);
            col.setSize(newSize);
        });
    }

    @FXML
    protected void sort() {
        if(!Collectssize.isEmpty()) {
            table.getSortOrder().addAll(Cname);
            info.setText("Продукты отсортированы");
        }
        else info.setText("Таблица пустая");
    }

    @FXML
    protected void search() {
        table.setItems(Collectssize);
        ObservableList<Market> name=FXCollections.observableArrayList();
        if (!Collectssize.isEmpty()) {
            for (int i = 0; i<Collectssize.size();i++) {
                if (Cname.getCellData(i).equals(find.getText())) {
                    name.add(Collectssize.get(i));
                }
            }
        } else info.setText("Таблица пустая");
        if (!name.isEmpty()) {
            table.setItems(name);
            reset.setDisable(false);
        }
        else info.setText("Такое наименование не найдено");
    }

    @FXML
    protected void reset() {
        table.setItems(Collectssize);
        find.setText(null);
        reset.setDisable(true);
    }
}
