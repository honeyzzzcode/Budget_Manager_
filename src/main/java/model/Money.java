package model;

import Types.Category;
import Types.InOrOut;

public class Money {


    Category category;
    float amount;
    InOrOut inOrOut;

    public String getInOrOut() {
        return String.valueOf(inOrOut);
    }

    public String getCategory() {
        return String.valueOf(category);
    }

    public float getAmount() {
        return amount;
    }


    public Money(InOrOut inOrOut, Category category, float amount) {
        this.inOrOut = inOrOut;
        this.category = category;
        this.amount = amount;
    }
}
