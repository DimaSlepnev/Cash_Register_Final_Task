package org.example.model;

public class Bill {
    private int id;
    private int productId;
    private String body;
    private int amount;
    private int price;
    private boolean confirmation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isConfirmation() {
        return confirmation;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", productId=" + productId +
                ", body='" + body + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", confirmation=" + confirmation +
                '}';
    }

    public static class Builder {
        private Bill newBill;

        public Builder() {
            newBill = new Bill();
        }

        public Builder withId(int id) {
            newBill.id = id;
            return this;
        }

        public Builder withProductId(int productId) {
            newBill.productId = productId;
            return this;
        }

        public Builder withBody(String body) {
            newBill.body = body;
            return this;
        }

        public Builder withAmount(int amount) {
            newBill.amount = amount;
            return this;
        }

        public Builder withPrice(int price) {
            newBill.price = price;
            return this;
        }

        public Builder withConfirmation(boolean confirmation) {
            newBill.confirmation = confirmation;
            return this;
        }

        public Bill build() {
            return newBill;
        }


    }
}
