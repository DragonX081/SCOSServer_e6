package esd.scos.servlet;

import esd.scos.servlet.json.JSONException;
import esd.scos.servlet.json.JSONObject;
import esd.scos.servlet.json.JSONStringer;
import esd.scos.servlet.models.FoodInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FoodUpdateService extends HttpServlet {

    ArrayList<FoodInfo> foodInfos;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        String acceptJson = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream(), "utf-8"));
            StringBuffer stringBuffer = new StringBuffer("");
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                stringBuffer.append(temp);
            }
            bufferedReader.close();
            acceptJson = stringBuffer.toString();

            System.out.println("====================" + acceptJson);

            if (acceptJson != "") {
                System.out.println("GetJsonNotNull");
                String jsonStr = getFoodInfosJson().toString();
                PrintWriter printWriter = resp.getWriter();
                printWriter.write(jsonStr);
                printWriter.close();
            } else {
                System.out.println("GetNULL");
                String jsonStr = "{\"RESULTCODE\":\"0\"}";
                PrintWriter printWriter = resp.getWriter();
                printWriter.write(jsonStr);
                printWriter.close();
            }
        }catch (Exception e){
            //todo handle exception
            e.printStackTrace();
        }
    }
    private JSONObject getFoodInfosJson(){
        JSONObject foodInfosJsonObject = new JSONObject();
        try {
            foodInfosJsonObject.put("RESULTCODE","1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(foodInfos==null) foodInfos = FoodInfo.getTestFoodInfo(100);
        int i =0;
        for(FoodInfo foodInfo: foodInfos){
            JSONObject foodInfoJson = new JSONObject();
            try {
                foodInfoJson.put("foodName",foodInfo.getFoodName());
                foodInfoJson.put("foodDescription",foodInfo.getFoodDescription());
                foodInfoJson.put("foodID",foodInfo.getFoodID()+"");
                foodInfoJson.put("foodType",foodInfo.getFoodtype());
                foodInfoJson.put("foodPrice",foodInfo.getFoodPrice());
                foodInfoJson.put("orderedNum",foodInfo.getOrderedNum()+"");
                foodInfoJson.put("submitNum",foodInfo.getSubmitNum());
                foodInfoJson.put("foodPos",foodInfo.getFoodPos()+"");
                foodInfoJson.put("foodStock",foodInfo.getStock());
                foodInfoJson.put("isNew",foodInfo.isNew()?"true":"false");
                foodInfosJsonObject.put(i+"",foodInfoJson);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            i++;
        }
        return foodInfosJsonObject;
    }
}
