package sample.controllers;

public class medictable {
    String productId;
    String ProductName;
    String CompanyName;
    String ProductCategory;
    String Quantity;
    String UnitPrice;

    public medictable(String productId, String productName, String companyName, String productCategory, String quantity, String unitPrice) {
        this.productId = productId;
        this.ProductName = productName;
        this.CompanyName = companyName;
        this.ProductCategory = productCategory;
        this.Quantity = quantity;
        this.UnitPrice = unitPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public String getProductCategory() {
        return ProductCategory;
    }

    public void setProductCategory(String productCategory) {
        ProductCategory = productCategory;
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

}