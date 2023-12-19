package com.eddy.Car_Rental_Management_System.service;


import com.eddy.Car_Rental_Management_System.model.Car;
import com.eddy.Car_Rental_Management_System.model.Customer;
import com.eddy.Car_Rental_Management_System.model.Rental;


public interface EmailService {
    void sendEmail(Car car, Rental rental, Customer customer);
}
