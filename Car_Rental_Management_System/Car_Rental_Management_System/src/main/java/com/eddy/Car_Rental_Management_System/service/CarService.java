package com.eddy.Car_Rental_Management_System.service;

import com.eddy.Car_Rental_Management_System.model.Available;
import com.eddy.Car_Rental_Management_System.model.Car;
import com.eddy.Car_Rental_Management_System.model.Customer;

import java.util.List;

public interface CarService {

    Car registerCar(String plateNumber, String model, String year, String image, Available available, Customer customer);
    Car updateCar(Car car);
    void deleteCar(Integer Id);
    List<Car> carList();


//    Car findCarByPlateNumber(Car car);
// Method to find a car by its plate number
    Car findCarByPlateNumber(int plateNumber);

    List<Car> getAllCars();

    void bookCar(Integer carId);

    void deleteCarsByUserId(Integer id);
    Car getOrCreateCar(String plateNumber);
   Car getCarById(Integer Id);
}
