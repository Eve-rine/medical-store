package sample.controllers;

import javafx.beans.value.ObservableNumberValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.TransformationList;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

import javax.swing.table.TableColumn;
import java.util.function.Function;

public class billtable {
    String bill;
    String Sale_quantity;
    String Unit_Price;
    String total;


    public billtable(String bill, String Sale_quantity, String Unit_Price, String total) {
        this.bill = bill;
        this.Sale_quantity = Sale_quantity;
        this.Unit_Price = Unit_Price;
        this.total = total;


}


    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getSale_quantity() {
        return Sale_quantity;
    }

    public void setSale_quantity(String sale_quantity) {
        Sale_quantity = sale_quantity;
    }

    public String getUnit_Price() {
        return Unit_Price;
    }

    public void setUnit_Price(String unit_Price) {
        Unit_Price = unit_Price;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
//    private static <S,T>TableColumn<S,T>column(String title, Function<S, ObservableNumberValue<T>>property, boolean editable, StringConverter<T>converter){
//        TableColumn<S, T>col= new TableColumn<>(title);
//        col.setCellValueFactory(cellData->property.apply(cellData.getValue()));
//        col.setEditable(editable);
//        if (editable){
//            col.setCellFactoty(TextFieldTableCell.forTableColumn(converter));
//        }
//        return col;
//    }
//
//    public static class LineItemLitWithTotal extends TransformationList<LineItem,LineItem>{
//        private final TotalLine totalLine;
//        protected LineItemLitWithTotal(
//                ObservableList<?extends LineItem> source
//        ){
//            super(source);
//            totalLine=new TotalLine(source);
//        }
//        @Override
//        protected void sourceChanged(Change<? extends LineItem>c ){
//            fireChange(c);
//        }
//
//    }

}
