package org.rahul.demo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.rahul.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Disabled
    @Test
    void testSendMail() {
        emailService.sendEmail("21bme063@iiitdmj.ac.in",
                "Testing Java mail sender",
                "Hi, aap kaise hain ?");
    }
}

