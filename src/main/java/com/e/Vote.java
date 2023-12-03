package com.e;
public class Vote {

    private String voterUsername;
    private String party;

    public Vote(String voterUsername, String party) {
        this.voterUsername = voterUsername;
        this.party = party;
    }

    public String getVoterUsername() {
        return voterUsername;
    }

    public String getParty() {
        return party;
    }
}

