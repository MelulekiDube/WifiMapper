package com.example.bugbusters.wifimapper;

public class User {
    private String macAdress;
    private Record record;

    User(String ma, Record r) {
        macAdress = ma;
        record = r;
    }

    public void setMacAdress(String macAdress) {
        this.macAdress = macAdress;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public Record getRecord() {
        return record;
    }

    public String getMacAdress() {
        return macAdress;
    }
}
