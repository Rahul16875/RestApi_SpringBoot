package org.rahul.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.rahul.demo.entity.User;
import org.rahul.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserRepository userRepository;

    // @Disabled
    // write this if want to do not test
    // @Test
    @ParameterizedTest
    @CsvSource({
        "ram",
        "shyam",
        "rahul"
    })
    public void testFindByUserName(String name){
        User user = userRepository.findByUserName(name);
        assertTrue(!user.getJournalEntries().isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
        "1,1,2",
        "2,10,12",
        "2,3,5"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a+b);
    }
}
