package com.mycompany.app;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

public class App
{
public static int sumOfValues = 0;

public static String sumOfString = "(";

    public static boolean search(ArrayList<Integer> array, int e) {
      System.out.println("inside search");
      if (array == null) return false;

      for (int i=0; i<array.size(); i++) {
         sumOfString += array.get(i) + "+";
         sumOfValues += array.get(i);
      }
       sumOfString = sumOfString.substring(0,sumOfString.length()-1);

      if (sumOfValues == e){
        sumOfString += ") = " + e;
        return true;

      }else{
        sumOfString += ") != " + e;
      return false;
      }
    }
public static void main(String[] args) {
        port(getHerokuAssignedPort());

        get("/", (req, res) -> "If the sum of integers which are in the first, second and third box, is equal to the value which is in the right (the fourth) box, then the program will show ... = ... (equal), otherwise program will show ... != ... (not equal)");

        post("/compute", (req, res) -> {

          //System.out.println(req.queryParams("input1"));
          //System.out.println(req.queryParams("input2"));
          //System.out.println(req.queryParams("input3"));
          //System.out.println(req.queryParams("input4"));

          String input1 = req.queryParams("input1");
          String input2 = req.queryParams("input2");
          String input3 = req.queryParams("input3");

          java.util.Scanner sc1 = new java.util.Scanner(input1);
          java.util.Scanner sc2 = new java.util.Scanner(input2);
          java.util.Scanner sc3 = new java.util.Scanner(input3);

          sc1.useDelimiter("[;\r\n]+");
          sc2.useDelimiter("[;\r\n]+");
          sc3.useDelimiter("[;\r\n]+");

          java.util.ArrayList<Integer> inputList = new java.util.ArrayList<>();
          while (sc1.hasNext())
          {
            int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
            inputList.add(value);
          }
           while (sc2.hasNext())
          {
            int value2 = Integer.parseInt(sc2.next().replaceAll("\\s",""));
            inputList.add(value2);
          }
           while (sc3.hasNext())
          {
            int value3 = Integer.parseInt(sc3.next().replaceAll("\\s",""));
            inputList.add(value3);
          }

          System.out.println(inputList);


          String input4 = req.queryParams("input4").replaceAll("\\s","");
          int input4AsInt = Integer.parseInt(input4);

          boolean result = App.search(inputList, input4AsInt);

         Map map = new HashMap();
          map.put("result", sumOfString);
          sumOfValues = 0;
          sumOfString = "(";
          return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());


        get("/compute",
            (rq, rs) -> {
              Map map = new HashMap();
              map.put("result", "not computed yet!");
              return new ModelAndView(map, "compute.mustache");
            },
            new MustacheTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
    return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
