package model.pojo;

import java.util.UUID;

public class Item {

    private UUID itemID;
    private String name;

    public Item(UUID itemID, String name) {
        this.itemID = itemID;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemID=" + itemID +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
