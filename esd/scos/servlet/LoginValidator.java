package esd.scos.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class LoginValidator extends HttpServlet {

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
                String jsonStr = "{\"RESULTCODE\":\"1\"}";
                PrintWriter printWriter = resp.getWriter();
                printWriter.write(jsonStr);
                printWriter.close();
            } else {
                System.out.println("GetJsonNULL");
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        System.out.println("=====================Send Get Response===============");
        doPost(req,resp);
    }
}
