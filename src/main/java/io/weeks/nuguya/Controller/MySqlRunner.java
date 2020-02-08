package io.weeks.nuguya.Controller;

import io.weeks.config.dto.FileConfigDto;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class MySqlRunner implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    FileConfigDto fileConfigDto;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(fileConfigDto.getFileUploadPath());
        System.out.println(fileConfigDto.getApplicationName());
    }
}
