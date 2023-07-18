package com.vscg.itemmanagerapiv3;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Item manager API", version = "2.0", description = "Crud API for management of IT company items"))
public class ItemManagerApiV3Application {

    public static void main(String[] args) {
        SpringApplication.run(ItemManagerApiV3Application.class, args);
    }

}
