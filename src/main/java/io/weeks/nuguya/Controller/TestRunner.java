package io.weeks.nuguya.Controller;

import io.weeks.FileService.FileService;
import io.weeks.config.configuration.WebConfig;
import io.weeks.config.dto.FileConfigDto;
import io.weeks.config.dto.RestfulConfigDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Component
public class TestRunner implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    FileConfigDto fileConfigDto;

    @Autowired
    FileService fileService;

    @Autowired
    WebConfig webConfig;

    @Autowired
    RestfulConfigDto restfulConfigDto;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(fileConfigDto.getFileUploadPath());
        System.out.println(fileConfigDto.getApplicationName());
        System.out.println(fileService.toString());
        System.out.println(restfulConfigDto.toString());
    }
}
