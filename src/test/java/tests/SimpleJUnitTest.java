package tests;

import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SimpleJUnitTest {

    int result = getResult();

    @AfterEach
    void AfterEach() {
        System.out.println("###      AfterEach\n");
        result = 0;
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("\n###      BeforeAll\n");
    }

    @BeforeEach
            void beforeEach() {
        System.out.println("###      BeforeEach");
        result = getResult();
    }


    private int getResult() {
        return  3;
    }

    @Test
    void firstTest() {
        System.out.println("###      firstTest");
        Assertions.assertTrue(result > 2);
    }

    @Test
    void secondTest() {
        System.out.println("###      secondTest");
        Assertions.assertTrue(result > 2);
    }

    @Test
    void thirdTest() {
        System.out.println("###      thirdTest");
        Assertions.assertTrue( result > 2);
    }


}
