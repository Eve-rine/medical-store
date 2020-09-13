package sample.controllers;

public class receivedtable {
    //String SalesID;
    String ProductName;
    String CompanyName;
    String Date;
    String Quantity;
    //String UnitPrice;
    //String Total;

    public receivedtable( String productName, String companyName, String date ,String quantity) {
    //    SalesID = salesID;
        ProductName = productName;
        CompanyName = companyName;
        Date = date;
        Quantity = quantity;
      //  UnitPrice = unitPrice;
      //  Total = total;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }
}
