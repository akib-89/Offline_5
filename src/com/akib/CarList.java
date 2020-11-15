package com.akib;

import java.util.ArrayList;
import java.util.List;

public class CarList {
    private final List<Car> cars;

    public CarList(){
        this.cars = new ArrayList<>();
    }

    public boolean addCar(Car car){
        Car newCar = this.searchCar(car.getRegistrationNumber());
        if (newCar != null){
            //no need to proceed the car is already in the list
            return false;
        }
        this.cars.add(car);
        return true;
    }

    public boolean deleteCar(String registrationNumber){
        Car newCar = this.searchCar(registrationNumber);
        if (newCar != null){
            this.cars.remove(newCar);
            return true;
        }
        return false;
    }

    public Car searchCar(String registrationNumber){
        for (Car c: this.cars){
            if (c.getRegistrationNumber().equals(registrationNumber)){
                return c;
            }
        }
        return null;
    }

    public List<Car> searchCar(String manufacture, String model){
        List<Car> cars = new ArrayList<>();
        if (model.equalsIgnoreCase("any")){
             for (Car c : this.cars) {
                 if (c.getManufacturer().equalsIgnoreCase(manufacture)){
                     cars.add(c);
                 }
             }
        }else {
            for (Car c : this.cars) {
                if (c.getManufacturer().equalsIgnoreCase(manufacture) && c.getModel().equalsIgnoreCase(model)) {
                    cars.add(c);
                }
            }
        }
        return cars;
    }

    public List<Car> getCars() {
        return cars;
    }
}
