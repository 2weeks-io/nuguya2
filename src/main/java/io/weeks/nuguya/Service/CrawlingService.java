package io.weeks.nuguya.Service;


import io.weeks.config.dto.FileConfigDto;
import io.weeks.nuguya.dto.CrawlingDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CrawlingService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    FileConfigDto fileConfigDto;

    /*
     ** 이미지 크롤링
     */
    public JSONObject getCrawlingImg(CrawlingDto crawlingDto) {

        String requestUrl = crawlingDto.getRequestUrl();

        String json = "";

        JSONObject jsonObject = new JSONObject();

        System.out.println("결과 데이터 : " + json);

        try {

            System.out.println("requestUrl : " + requestUrl);

            json = restTemplate.getForObject(requestUrl, String.class);

            JSONParser jsonParser = new JSONParser();

            //JSON데이터를 넣어 JSON Object 로 만들어 준다.
            jsonObject = (JSONObject) jsonParser.parse(json);

        } catch(Exception e){
            System.out.println(e);
        }

        return jsonObject;
    }
}
