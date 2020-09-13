package sample.controllers;

public class companytable {
    String CompanyName;
    String CompanyCountry;
    String CompanyEmail;
    String CompanyContact;
    String Address;

    public companytable(String companyName, String companyCountry, String companyEmail, String companyContact, String address) {
        this.CompanyName =companyName;
        this.CompanyCountry =companyCountry;
        this.CompanyName = companyName;
        this.CompanyEmail = companyEmail;
        this.CompanyContact = companyContact;
        this.Address = address;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getCompanyCountry() {
        return CompanyCountry;
    }

    public void setCompanyCountry(String companyCountry) {
        CompanyCountry = companyCountry;
    }

    public String getCompanyEmail() {
        return CompanyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        CompanyEmail = companyEmail;
    }

    public String getCompanyContact() {
        return CompanyContact;
    }

    public void setCompanyContact(String companyContact) {
        CompanyContact = companyContact;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}


