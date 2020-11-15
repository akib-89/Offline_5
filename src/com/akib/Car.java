package com.akib;

public class Car {
    private final String registrationNumber;
    private final int yearMade;
    private final String manufacturer;
    private final String model;
    private String[] colors =new String[3];
    private int price;

    public Car(String registrationNumber, int yearMade, String[] colours, String manufacturer, String model, int price) {
        this.registrationNumber = registrationNumber;
        this.yearMade = yearMade;
        this.colors = colours;
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return registrationNumber+ "," + yearMade + "," + colors[0] + "," +
                colors[1] + "," + colors[2] + "," + manufacturer + "," +
                model + "," + price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this ){
            return true;
        }
        if (obj instanceof Car){
            Car carObj = (Car)obj;
            return this.registrationNumber.equalsIgnoreCase(carObj.registrationNumber);
        }
        return false;
    }
}
