package com.ezeservice.customer;

import com.ezeservices.clients.fraud.FraudCheckResponse;
import com.ezeservices.clients.fraud.FraudClient;
import com.ezeservices.clients.notification.NotificationClient;
import com.ezeservices.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        customerRepository.saveAndFlush(customer);
        // todo: check if email valid
        // todo: check if email not taken
        //todo: check if customer is fradulent
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("is fraudster");
        }
        notificationClient.sendNotification(
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        String.format("Hi %s, welcome to ezeservices...", customer.getFirstName())
                )
        );
    }
}
