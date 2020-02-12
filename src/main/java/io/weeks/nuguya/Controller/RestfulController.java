package io.weeks.nuguya.Controller;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
public class RestfulController {

    private Logger LOGGER = LoggerFactory.getLogger(RestfulController.class);


    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/restfulTest")
    public String home() {

        String url = "http://localhost:5000/crawling/image/";
        String appName = "nuguya";
        String keywords = "아이린,수지,로제";
        String num = "4";

        String requestUrl = url + "/" + appName + "/" + keywords + "/" + num;

        String json = restTemplate.getForObject(requestUrl, String.class);
        System.out.println(json);
        try {

            JSONParser jsonParser = new JSONParser();

            //JSON데이터를 넣어 JSON Object 로 만들어 준다.
            JSONObject jsonObject = (JSONObject) jsonParser.parse(json);

            String[] keywordArray = keywords.split(",", 0);
            int keywordLen = keywordArray.length;

            for(int j=0;j<keywordLen;j++) {

                //키워드별로 저장 위치 출력
                JSONArray imageList = (JSONArray) jsonObject.get(keywordArray[j]);

                for (int i = 0; i < imageList.size(); i++) {

                    //배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
                    Object fileInfoObj = (Object) imageList.get(i);

                    //JSON name으로 추출
                    System.out.println(fileInfoObj.toString());

                }
            }

        } catch(Exception e){
            System.out.println(e);
        }

        return json;
    }



}