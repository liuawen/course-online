package com.course.generator.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FreemarkerUtil {

    static String ftlPath = "generator\\src\\main\\java\\com\\course\\generator\\ftl\\";

    static Template temp;

    public static void initConfig(String ftlName) throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setDirectoryForTemplateLoading(new File(ftlPath));
        cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_29));
        temp = cfg.getTemplate(ftlName);
    }

    public static void generator(String fileName, Map<String, Object> map) throws IOException, TemplateException {
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);
        temp.process(map, bw);
        bw.flush();
        fw.close();
    }
}
