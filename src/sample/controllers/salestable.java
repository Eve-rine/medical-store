package sample.controllers;

public class salestable {
    String SalesID;
    String ProductName;
    String CompanyName;
    String DateOfSale;
    String Quantity;
    String UnitPrice;
    String Total;

    public salestable(String salesID, String productName, String companyName, String dateOfSale, String quantity, String unitPrice, String total) {
        SalesID = salesID;
        ProductName = productName;
        CompanyName = companyName;
        DateOfSale = dateOfSale;
        Quantity = quantity;
        UnitPrice = unitPrice;
        Total = total;
    }

    public String getSalesID() {
        return SalesID;
    }

    public void setSalesID(String salesID) {
        SalesID = salesID;
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

    public String getDateOfSale() {
        return DateOfSale;
    }

    public void setDateOfSale(String dateOfSale) {
        DateOfSale = dateOfSale;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        UnitPrice = unitPrice;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }
}
