package model;

import Types.Category;
import Types.InOrOut;

import java.sql.Timestamp;

public class Money {


    Category category;
    float amount;
    InOrOut inOrOut;
    int userId;
    Timestamp createdAt;
    Timestamp updatedAt;


    public String getInOrOut() {
        return String.valueOf(inOrOut);
    }

    public String getCategory() {
        return String.valueOf(category);
    }

    public float getAmount() {
        return amount;
    }


    public Money(InOrOut inOrOut, Category category, float amount, int UserId, Timestamp createdAt, Timestamp updatedAt) {
        this.inOrOut = inOrOut;
        this.category = category;
        this.amount = amount;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
