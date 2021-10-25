package com.trustsoft.route.service;

import static org.mockito.quality.Strictness.LENIENT;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.context.annotation.ComponentScan;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
@ComponentScan({"com.trustsoft.*"})
@SuppressWarnings("squid:S2187")
public class BaseServiceTest {

}
