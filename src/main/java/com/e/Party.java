package com.e;
public class Party {
        private String name;
        private int voteCount;



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
