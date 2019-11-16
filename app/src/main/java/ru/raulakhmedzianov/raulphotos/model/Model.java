package ru.raulakhmedzianov.raulphotos.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private int count;
    private List<String> list;

    public Model() {
        list = new ArrayList<>();
        this.count = 0;
    }

    public List<String> getList() {
        return list;
    }

    public void setItem(String itemName) {
        this.list.add(itemName);
    }

    public int getCount() {
        return count;
    }

    public void setCountIncrement() {
        this.count = this.count + 1;
    }
}
