package com.e;
import javafx.beans.property.SimpleStringProperty;
import java.util.List;
import java.util.stream.Collectors;
public class Party {
        private String name;
        private int voteCount;

    private List<PartyData> partyDataList;

    public Party(String name, List<PartyData> partyDataList) {
        this.name = name;
        this.partyDataList = partyDataList;
    }



    public List<PartyData> getPartyDataList() {
        return partyDataList;
    }

    public void setPartyDataList(List<PartyData> partyDataList) {
        this.partyDataList = partyDataList;
    }

    public String getYearsString() {
        return partyDataList.stream()
                .map(PartyData::getYear)
                .collect(Collectors.joining(", "));
    }

    public SimpleStringProperty yearsProperty() {
        return new SimpleStringProperty(getYearsString());
    }



    public Party(String name) {
        this.name = name;
        this.voteCount = 0;
    }

    public String getName() {
        return name;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void incrementVoteCount() {
        voteCount++;
    }
}
