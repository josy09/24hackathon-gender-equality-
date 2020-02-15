package com.example.gqbulk;

import java.util.ArrayList;

public class bulkItem {
    public String category;
    public String discounted_Price;
    public String original_Price;
    public String target;
    public String image;
    public ArrayList<String> id;
    public String product;

    public ArrayList<String> getId() {
        return id;
    }

    public void setId(ArrayList<String> id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDiscounted_Price() {
        return discounted_Price;
    }

    public void setDiscounted_Price(String discounted_Price) {
        this.discounted_Price = discounted_Price;
    }

    public String getOriginal_Price() {
        return original_Price;
    }

    public void setOriginal_Price(String original_Price) {
        this.original_Price = original_Price;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
