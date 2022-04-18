package com.bhandal.springmicroservicebeerclient.web.client;

import com.bhandal.springmicroservicebeerclient.web.model.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "aj.customer", ignoreUnknownFields = false)
public class CustomerClient {
    private       String       apiHost;

    private final String       CUSTOMER_API_PATH = "/api/v1/customer/";
    private final RestTemplate restTemplate;
    public CustomerClient(RestTemplateBuilder restTemplate) {
        this.restTemplate = restTemplate.build();
    }

    public CustomerDto getCustomerById(UUID uuid) {
        return restTemplate.getForObject(apiHost + CUSTOMER_API_PATH + uuid.toString(), CustomerDto.class);
    }



    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public URI saveNewCustomerDto(CustomerDto customerDto) {
        final URI uri = restTemplate.postForLocation(apiHost + CUSTOMER_API_PATH, customerDto);
        return uri;
    }

    public void updateCustomer(UUID randomUUID, CustomerDto customerDto) {
        restTemplate.put(apiHost + CUSTOMER_API_PATH + randomUUID, customerDto);
    }

    public void deleteCustomer(UUID randomUUID) {
        restTemplate.delete(apiHost + CUSTOMER_API_PATH + randomUUID);
    }
}
