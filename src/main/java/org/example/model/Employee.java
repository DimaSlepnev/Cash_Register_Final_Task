package org.example.model;

public class Employee {
    private int id;
    private String position;
    private String login;
    private String pass;
    private String firstName;
    private String secondName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public static class Builder{
        private Employee newEmployee;
        public Builder(){
            newEmployee = new Employee();
        }

        public Builder withId(int id){
            newEmployee.id = id;
            return this;
        }

        public Builder withPosition(String position){
            newEmployee.position = position;
            return this;
        }

        public Builder withLogin(String login){
            newEmployee.login = login;
            return this;
        }

        public Builder withPass(String pass){
            newEmployee.pass = pass;
            return this;
        }

        public Builder withFirstName(String firstName){
            newEmployee.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName){
            newEmployee.secondName = lastName;
            return this;
        }

        public Employee built(){
            return newEmployee;
        }
    }
}
