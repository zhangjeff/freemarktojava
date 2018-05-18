package com.jeff;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;


public class GeneratorCode {

    public static void main(String[] args) {
//        try {
//            File file = new File("src/main/resources/templates/person.ftl");
//            FileReader reader = new FileReader(file);
//            BufferedReader bufferedReader = new BufferedReader(reader);
//            String readLine = "";
//            while ((readLine = bufferedReader.readLine())!= null) {
//                System.out.println(readLine);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            new GeneratorCode().gen();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    public void gen() throws IOException, TemplateException {

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Template temp = cfg.getTemplate("person.ftl");
        Map<String, Object> root = new HashMap<String, Object>();

        root.put("packageName", "com.jeff.java");
        root.put("className", "Person");
        root.put("author", "jeff Zhang");

        File dir = new File("src/main/java/com/jeff/java");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        OutputStream fos = new FileOutputStream(new File(dir, "Person.java")); //java文件的生成目录
        Writer out = new OutputStreamWriter(fos);
        temp.process(root, out);
        fos.flush();
        fos.close();
        System.out.println("gen code success!");
    }


}
