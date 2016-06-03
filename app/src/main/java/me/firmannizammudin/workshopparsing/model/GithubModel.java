package me.firmannizammudin.workshopparsing.model;

/**
 * Created by Firman on 03-Jun-16.
 */
public class GithubModel {
    private int id;
    private String name;
    private String full_name;

    public GithubModel(int id, String name, String full_name) {
        this.id = id;
        this.name = name;
        this.full_name = full_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
}
