package com.e;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PartyData {
    private final StringProperty years;
    private final StringProperty slogan;

    public PartyData(String years, String slogan) {
        this.years = new SimpleStringProperty(years);
        this.slogan = new SimpleStringProperty(slogan);
    }

    public String getYears() {
        return years.get();
    }

    public StringProperty yearsProperty() {
        return years;
    }

    public String getSlogan() {
        return slogan.get();
    }

    public StringProperty sloganProperty() {
        return slogan;
    }
}