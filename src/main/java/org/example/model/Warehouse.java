package org.example.model;

public class Warehouse {
    private int id;
    private String product;
    private int amount;
    private int expertId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getExpertId() {
        return expertId;
    }

    public void setExpertId(int expertId) {
        this.expertId = expertId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", amount=" + amount +
                ", expertId=" + expertId +
                '}';
    }

    public static class Builder{
        private Warehouse newWarehouse;
        public Builder(){
            newWarehouse = new Warehouse();
        }

        public Builder withId(int id){
            newWarehouse.id = id;
            return this;
        }

        public Builder withProduct(String product){
            newWarehouse.product = product;
            return this;
        }

        public Builder withAmount(int amount){
            newWarehouse.amount = amount;
            return this;
        }

        public Builder withExpertId(int expertId){
            newWarehouse.expertId = expertId;
            return this;
        }

        public Warehouse build(){
            return newWarehouse;
        }
    }
}
