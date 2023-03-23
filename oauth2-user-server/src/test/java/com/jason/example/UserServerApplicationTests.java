package com.jason.example;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson2.JSON;
import com.alibaba.nacos.shaded.io.grpc.internal.JsonUtil;
import com.jason.example.controller.CustomerService;
import com.jason.example.dao.CustomerRepository;
import com.jason.example.domain.Customer;
import com.jason.example.domain.CustomerRegistrationRequest;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServerApplicationTests {
    public final Logger logger=Logger.getGlobal();

    @Autowired
    private CustomerRepository customerRepository;
    Calculator underTest =new Calculator();
    @Autowired
    private CustomerService customerService;

    @Test
    void itShouldCheckIfCustomerExistsEmail() {
        //given
        Customer build = Customer.builder().email("Jason@163.com").firstName("Z").lastName("Jason002").build();
        customerRepository.save(build);
        //when
        Boolean existsEmail = customerRepository.selectExistsEmail("Jason@163.com");
        logger.info("customerRepository.selectExistsEmail(\"Jason@163.com\")  :"+existsEmail);
        //then
        AssertionsForClassTypes.assertThat(existsEmail).isTrue();
    }
    @Test
    public void contextLoads(){

    }

    @Test
    @DisplayName("测试两数相加")
    public void itShouldAddTwoNumbers(){
        int numOne = 20;
        int numTwo = 30;
        int add = underTest.add(numOne, numTwo);
        int expect =50;
        assertThat(add).isEqualTo(expect);
    }

    @Test
    @DisplayName("测试获取全部顾客")
    public void test_getAllCustomer(){
        List<Customer> all = customerRepository.findAll();
        logger.info(JSON.toJSONString(all));
    }

    @Test
    @DisplayName("异常测试")
    public void exceptionTest(){
        //扔出断言异常
        ArithmeticException arithmeticException = Assertions.assertThrows(ArithmeticException.class, () -> System.out.println(1 % 0));
    }

    @Test
    @DisplayName("超时测试")
    public void timeoutTest(){
        //如果测试方法时间超过1s将会异常
        Assertions.assertTimeout(Duration.ofMillis(1000), () -> Thread.sleep(500));
    }


    @ParameterizedTest
    @MethodSource("method")    //指定方法名
    @DisplayName("方法来源参数")
    public void testWithExplicitLocalMethodSource(String name) {
        System.out.println(name);
        Assertions.assertNotNull(name);
    }

    static Stream<String> method() {
        return Stream.of("apple", "banana");
    }

    @ParameterizedTest
    @ValueSource(strings = {"one", "two", "three"})
    @DisplayName("参数化测试1")
    public void parameterizedTest1(String string) {
        System.out.println(string);
        Assertions.assertTrue(StringUtils.isNotBlank(string));
    }

    class Calculator{
        int add(int a,int b){
            return a+b;
        }
    }
    @Test
    public void test_CustomerService(){
        customerService.registerCustomer(new CustomerRegistrationRequest("ZZ","ZZ","00ZZ@163.com"));
    }
}
