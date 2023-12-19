package com.eddy.Car_Rental_Management_System.controller;

import com.eddy.Car_Rental_Management_System.model.Car;
import com.eddy.Car_Rental_Management_System.model.Customer;
import com.eddy.Car_Rental_Management_System.model.Rental;
import com.eddy.Car_Rental_Management_System.service.CarService;
import com.eddy.Car_Rental_Management_System.service.CustomerService1;
import com.eddy.Car_Rental_Management_System.service.CustomerService1;
import com.eddy.Car_Rental_Management_System.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RentalController {

    private final RentalService rentalService;
    private final CarService carService;
    private final CustomerService1 customerService1;

    @Autowired
    public RentalController(RentalService rentalService, CarService carService, CustomerService1 customerService1) {
        this.rentalService = rentalService;
        this.carService = carService;
        this.customerService1 = customerService1;
    }



    @GetMapping("/rental")
    public String showRentalForm(Model model) {
        model.addAttribute("rental", new Rental());
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("customers", customerService1.getCustomers());
        return "rental-form";
    }



    @PostMapping("/rent")
    public String rentCar(@ModelAttribute Rental rental, @RequestParam("carId") Integer carId) {

        // Retrieve the selected Car entity using the provided carId
        Car foundCar = carService.getCarById(carId);
//        Customer customer = customerService1.getAllCustomers();

        if (foundCar != null) {
            // Set the selected Car entity in the Rental object
            rental.setCar(foundCar);
            carService.bookCar(carId);

            // Save rental information to the database
            rentalService.saveRental(rental);

            // Redirect to a success page or any other appropriate page
            return "redirect:/customer-dashboard";
        } else {
            // Handle the case where the selected car does not exist or the ID is invalid
            // You can redirect back to customer-dashboard with an error message
            return "redirect:/error-page2";
        }
    }

    @PostMapping("/rent")
    public String rentCar(@ModelAttribute Rental rental, @RequestParam("carId") Integer carId, @RequestParam("customerId") Integer customerId) {
        // Retrieve the selected Car entity using the provided carId
        Car selectedCar = carService.getCarById(carId);

        // Retrieve the Customer entity using the provided customerId
        Customer customer = customerService1.findCustomerById(customerId);

        if (selectedCar != null && customer != null) {
            // Set the selected Car entity in the Rental object
            rental.setCar(selectedCar);

            // Set the retrieved Customer entity in the Rental object
            rental.setCustomer(customer);

            // Update the car status to 'Booked'
            carService.bookCar(carId);

            // Save rental information to the database
            rentalService.saveRental(rental);

            // Redirect to a success page or any other appropriate page
            return "redirect:/customer-dashboard";
        } else {
            // Handle the case where the selected car or customer does not exist or the IDs are invalid
            // You can redirect back to customer-dashboard with an error message
            return "redirect:/customer-dashboard?error=invalid_car_or_customer_id";
        }
    }





    @GetMapping("/view-rentals")
    public String viewUsers(Model model) {
        List<Rental> rentals = rentalService.getAllRentals();
        model.addAttribute("rentals", rentals);
        return "rental";
    }

    @GetMapping("/rental/{rentalId}/delete")
    public String deleteRental(@PathVariable("rentalId")Integer Id) {
        rentalService.deleteRental(Id);
        return "redirect:/view-rentals";
    }

}
