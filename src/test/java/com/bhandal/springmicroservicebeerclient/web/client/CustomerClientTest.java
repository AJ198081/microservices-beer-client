package com.bhandal.springmicroservicebeerclient.web.client;

import com.bhandal.springmicroservicebeerclient.web.model.BeerDto;
import com.bhandal.springmicroservicebeerclient.web.model.CustomerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerClientTest {

    @Autowired
    CustomerClient client;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getCustomerById() {
        final UUID uuid = UUID.randomUUID();
        final CustomerDto customerDto = client.getCustomerById(uuid);
        assertEquals("Aj", customerDto.getFirstName());
        assertEquals("Bhandal", customerDto.getLastName());
        assertEquals(uuid, customerDto.getId());
    }

    @Test
    void testSaveNewCustomer() {
        final CustomerDto customerDto = CustomerDto.builder()
                .id(UUID.randomUUID())
                .firstName("Reet")
                .lastName("Bhandal")
                .build();
        final URI uri = client.saveNewCustomerDto(customerDto);
        assertNotNull(uri);
        System.out.println(uri);
    }

    @Test
    void updateCustomer() {
        final CustomerDto customerDto = CustomerDto.builder()
                .id(UUID.randomUUID())
                .firstName("Reet")
                .lastName("Bhandal")
                .build();
        client.updateCustomer(UUID.randomUUID(), customerDto);
    }

    @Test
    void deleteCustomer() {
        client.deleteCustomer(UUID.randomUUID());
    }
}