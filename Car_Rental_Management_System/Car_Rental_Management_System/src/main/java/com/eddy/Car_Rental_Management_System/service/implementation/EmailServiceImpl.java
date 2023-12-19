package com.eddy.Car_Rental_Management_System.service.implementation;


import com.eddy.Car_Rental_Management_System.model.Car;
import com.eddy.Car_Rental_Management_System.model.Customer;
import com.eddy.Car_Rental_Management_System.model.Rental;
import com.eddy.Car_Rental_Management_System.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void sendEmail(Car car, Rental rental, Customer customer){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(rental.getEmail());
        message.setSubject("Booking Information");
        message.setText("Hello" + customer.getUsername() + " " + customer.getId() + ",\n\n"
                + "You have booked this car:\n"
                + "Car Id: " + car.getId() + "\n"
                + "Car Plate number: " +car.getPlateNumber()+ "\n"
                + "Car Model: " +car.getModel()+ "\n"
                + "Please, when you come to take this car, come with this email in case."+ "\n\n"
                + "Best regards,"+ "\n"
                + "Kizigenza car rental");
        javaMailSender.send(message);
    }
}
