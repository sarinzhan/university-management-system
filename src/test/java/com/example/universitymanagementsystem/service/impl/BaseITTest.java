package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.configuration.SecurityConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//для интеграционного теста поднимаем весь контекст
// контекст поднимется на свободном рандомном порту
@TestPropertySource(locations = "classpath:application-test.properties")
// указываем
@Import(value = SecurityConfiguration.class)
@AutoConfigureMockMvc
public class BaseITTest {
}
