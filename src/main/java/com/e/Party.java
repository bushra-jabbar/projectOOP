package com.e;

import java.util.List;

public class Party {
    private String name;
    private String partySlogan;
    private String password;
    private int voteCount;
    private  List<PartyData> partyData;
    public List<PartyData> getPartyData() {
        return partyData;
    }

    public Party(String name, List<PartyData> partyData) {
        this.name = name;
        this.partyData = partyData;
    }
    public Party(String name, String partySlogan, String password) {
        this.name = name;
        this.partySlogan = partySlogan;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Party(String name, String partySlogan) {
        this.name = name;
        this.partySlogan=partySlogan;
    }
    public String getPartySlogan() {
        return partySlogan;
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