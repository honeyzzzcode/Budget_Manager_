package model;

import Types.Category;
import Types.InOrOut;

import java.sql.Timestamp;
import java.text.DecimalFormat;

public class Money {
    DecimalFormat df = new DecimalFormat("#.##");
    String formatted = df.format(2.456345);

    int ID;
    Category category;
    float amount;
    InOrOut inOrOut;
    int userId;
    Timestamp createdAt;
    Timestamp updatedAt;
    Float total;

    public Money(int id, Timestamp createdAt, String inOrOut, float amount, String category) {
        this.ID = id;
        this.createdAt = createdAt;
        this.inOrOut = InOrOut.valueOf(inOrOut);
        this.amount = amount;
        this.category = Category.valueOf(category);
    }


    public int getID() {
        return ID;
    }
    public Money(String inOrOut, Float total) {
        this.inOrOut = InOrOut.valueOf(inOrOut);
        this.total = total;
    }

    public Money(float amount, String inOrOut) {
        this.amount = amount;
        this.inOrOut = InOrOut.valueOf(inOrOut);
    }

    public Money(Timestamp createdAt, String inOrOut, float amount, String category) {
        this.createdAt = createdAt;
        this.inOrOut = InOrOut.valueOf(inOrOut);
        this.amount = amount;
        this.category = Category.valueOf(category);

    }



    public Float getTotal() {
        return total;
    }

    public String getInOrOut() {
        return String.valueOf(inOrOut);
    }

    public String getCategory() {
        return String.valueOf(category);
    }

    public float getAmount() {
        return amount;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }


    public Money(InOrOut inOrOut, Category category, float amount) {
        this.inOrOut = inOrOut;
        this.category = category;
        this.amount = amount;

    }

    public Money(int ID,String inOrOut, String category, float amount, int UserId, Timestamp createdAt, Timestamp updatedAt) {
        this.ID = ID;
        this.inOrOut = InOrOut.valueOf(inOrOut);
        this.category  = Category.valueOf(category);
        this.amount = amount;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public Money(Timestamp createdAt, String inOrOut, float amount) {
        this.createdAt = createdAt;
        this.inOrOut = InOrOut.valueOf(inOrOut);
        this.amount = amount;
    }
}