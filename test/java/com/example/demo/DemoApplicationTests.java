package com.example.demo;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    Logger log = Logger.getLogger("UploadController");
    @Test
    public void contextLoads(HttpServletRequest request) {
        String contextPath = request.getServletContext().getContextPath();

        log.info(contextPath);
    }

}

