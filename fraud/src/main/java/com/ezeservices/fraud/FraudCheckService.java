package com.ezeservices.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {
    final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudulent(Integer customerId){
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudulent(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;

    }
}
