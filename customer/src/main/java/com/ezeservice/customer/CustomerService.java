package com.ezeservice.customer;

//import com.ezeservices.clients.fraud.FraudCheckResponse;
//import com.ezeservices.clients.fraud.FraudClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
//    private final FraudClient fraudClient;

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
//        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        

//        if(fraudCheckResponse.isFraudster()){
//            throw new IllegalStateException("is fraudster");
//        }
    }
}
