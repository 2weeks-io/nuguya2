package io.weeks.nuguya.Controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
public class RestfulController {

    private Logger LOGGER = LoggerFactory.getLogger(RestfulController.class);


    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/restfulTest")
    public String home() {

        String json = restTemplate.getForObject("http://localhost:5000/crawling/image/nuguya/cat/4", String.class);
        System.out.println(json);
        try {

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(json);

            String keyValue = jsonNode.get("cat").asText();
            System.out.println("cat value : " + keyValue);



        } catch(Exception e){
            System.out.println(e);
        }

        return json;
    }



}