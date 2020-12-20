package com.example.demo;

import com.example.demo.crawler.Crawler;
import com.example.demo.crawler.Parse;
import com.example.demo.crawler.Tools;
import com.example.demo.pojo.AreaStat;
import com.example.demo.pojo.Statistics;
import com.example.demo.pojo.TimeLine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void TimeTest(){
        Tools.getPageByJSoup(Crawler.URL);
        String staticInformation = null;
        List<TimeLine> statisticsInformation = null;
        try {
            staticInformation = Tools.getInformation(Crawler.TIME_LINE_REGEX_TEMPLATE, "id", Crawler.TIME_LINE_ATTRIBUTE);
            //去掉static数据正则1匹配最后的 }catch(e){}串， 剩下的就是一个格式正确的json串了
            staticInformation = staticInformation.replace("}catch(e){}","");
            System.out.println(staticInformation);
            //statisticsInformation = Parse.parseStatisticsInformation(staticInformation);
            statisticsInformation = Parse.parseTimeLineInformation(staticInformation);
            System.out.println("1: "+statisticsInformation);
        } catch (Exception e1) {
            System.out.println("static信息正则1匹配失败，切换正则2");
        }
    }
    @Test
    void test(){
        Tools.getPageByJSoup(Crawler.URL);
        String staticInformation = null;
        List<AreaStat> statisticsInformation = null;
        try {
            staticInformation = Tools.getInformation(Crawler.AREA_INFORMATION_REGEX_TEMPLATE, "id", Crawler.AREA_INFORMATION_ATTRIBUTE);
            //去掉static数据正则1匹配最后的 }catch(e){}串， 剩下的就是一个格式正确的json串了
            staticInformation = staticInformation.replace("}catch(e){}","");
            System.out.println(staticInformation);
            //statisticsInformation = Parse.parseStatisticsInformation(staticInformation);
            statisticsInformation = Parse.parseAreaInformation(staticInformation);
            System.out.println("1: "+statisticsInformation);
        } catch (Exception e1) {
            System.out.println("static信息正则1匹配失败，切换正则2");
        }
    }
    @Test
    void contextLoads() {
        //获取HTML数据
        Tools.getPageByJSoup(Crawler.URL);

        //提取static信息的json数据

        String staticInformation = null;
        //解析static信息的json数据
        Statistics statisticsInformation = null;
        try {
            staticInformation = Tools.getInformation(Crawler.STATIC_INFORMATION_REGEX_TEMPLATE_1, "id", Crawler.STATIC_INFORMATION_ATTRIBUTE);
            //去掉static数据正则1匹配最后的 }catch(e){}串， 剩下的就是一个格式正确的json串了
            staticInformation = staticInformation.replace("}catch(e){}","");
            System.out.println(staticInformation);
            statisticsInformation = Parse.parseStatisticsInformation(staticInformation);
            System.out.println("1: "+statisticsInformation);
        } catch (Exception e1) {
            //logger.error("static信息正则1匹配失败，切换正则2");
            System.out.println("static信息正则1匹配失败，切换正则2");
            try {
                staticInformation = Tools.getInformation(Crawler.STATIC_INFORMATION_REGEX_TEMPLATE_2, "id", Crawler.STATIC_INFORMATION_ATTRIBUTE);
                statisticsInformation = Parse.parseStatisticsInformation(staticInformation);
                System.out.println("2: "+statisticsInformation);
            } catch (Exception e2) {
                //logger.error("static信息正则2匹配失败，切换正则3");
                System.out.println("static信息正则2匹配失败，切换正则3");
                staticInformation = Tools.getInformation(Crawler.STATIC_INFORMATION_REGEX_TEMPLATE_3, "id", Crawler.STATIC_INFORMATION_ATTRIBUTE);
                statisticsInformation = Parse.parseStatisticsInformation(staticInformation);
                System.out.println("3: "+statisticsInformation);
            }

        }

    }

}
