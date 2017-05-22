package com.pos.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreateWordByMap {
    private Configuration configuration = null;
    private Map<String, Template> allTemplates = null;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    public CreateWordByMap() {
        this.configuration = new Configuration();
        this.configuration.setDefaultEncoding("GBK");
    }

    public File createWord1(Map dataMap) {
        System.out.println("=======inter func createWord1=========");
        boolean flag = ((Boolean) dataMap.get("flag")).booleanValue();
        if (flag) {
            createWord(dataMap, "pos");
            createWord(dataMap, "details");
        } else {
            createWord(dataMap, "pos");
        }
        return null;
    }

    public File createWord(Map dataMap, String fileStr) {
        String cardStr = (String) dataMap.get("cardStr");
        String ushop = (String) dataMap.get("ushop");
        String userName = (String) dataMap.get("userName");
        String shopDataId = (String) dataMap.get("ushopDataId");
        String shopCardId = (String) dataMap.get("ushopCardId");
        System.out.println("======cardStr=" + cardStr + "---ushop=" + ushop);
        cardStr = cardString(shopCardId, ushop, cardStr);
//        cardStr = cardString(ushop, cardStr);
        /*if (("银滕珠宝".equals(ushop)) && ("details".equals(fileStr))) {
            String detailsCardStr = (String)dataMap.get("detailsCardStr");
            detailsCardStr = cardString(ushop + "_details", detailsCardStr);
            dataMap.put("detailsCardStr", detailsCardStr);
        }*/

//        if ("中国石油(关河东路店)".equals(ushop)) {
        if ("中国石油(关河东路店)".equals(ushop)) {
            List list = new ArrayList();
            list = (List) dataMap.get("cardList");

            Random random = new Random();
            int index = random.nextInt(list.size());

            Map map = (Map) list.get(index);
            dataMap.put("name", map.get("name"));
            double money = Double.parseDouble(dataMap.get(map.get("name")).toString());
            dataMap.put("unitPrice", dataMap.get(map.get("name")));

            double amount = Double.parseDouble(dataMap.get("amount").toString());
            double countStr = amount / money;
            DecimalFormat df = new DecimalFormat("######0.00");
            dataMap.put("countStr", df.format(countStr));
        }

        dataMap.put("cardStr", cardStr);
        String dateStr = this.sdf.format(new Date());

        this.configuration.setClassForTemplateLoading(super.getClass(), "/model/" +
                fileStr);
        this.allTemplates = new HashMap();
        try {
            this.allTemplates.put(
                    "test",
                    this.configuration.getTemplate("test-" + ushop + "_" + fileStr +
                            ".ftl"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String tempStr = "";
        if ("pos".equals(fileStr))
            tempStr = "pos";
        else {
            tempStr = "xd";
        }
        // 修改路径为项目相对路径
        File outFile = new File("../POS/" + userName + "/" + ushop + "/" +
                tempStr + "_" + dateStr + "_" + ushop + ".doc");
        File outDir = new File("../POS/" + userName + "/" + ushop + "/");

        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        Template t = (Template) this.allTemplates.get("test");
        Writer out = null;
        try {
            out = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(outFile), "GBK"));
            t.process(dataMap, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return outFile;
    }

    public String cardString(String shopCardId, String ushops, String unumber) {
        String number = "";
//        if ("常州明都酒店".equals(ushops)) {
        if ("1".equals(shopCardId)) {
            String str = "*********";

            if (unumber != null) {
                String a = unumber;
                String bf = a.substring(0, 6);
                String ft = a.substring(a.length() - 4, a.length());
                number = bf + str + ft;
            }
        } else
//            if (("常州新城希尔顿酒店".equals(ushops)) ||
//                ("大润发（常州店）".equals(ushops)) || ("传奇人生音乐酒吧".equals(ushops)) || ("六福珠宝(常州新世纪商城)".equals(ushops))) {
            if ("2".equals(shopCardId)) {

                String str = "******";

                if (unumber != null) {
                    String a = unumber;
                    String bf = a.substring(0, 6);
                    String ft = a.substring(a.length() - 4, a.length());
                    number = bf + str + ft;
                }
            }
//        else if (("海澜之家（关河东路店）".equals(ushops)) || ("环洲绿岛咖啡延陵店".equals(ushops)) || ("常州泰富百货集团有限公司".equals(ushops))) {
            else if ("3".equals(shopCardId)) {
                String str = "******";

                if (unumber != null) {
                    String a = unumber;
                    String bf = a.substring(0, 4);
                    String bf2 = a.substring(4, 6);
                    String ft = a.substring(a.length() - 4, a.length());
                    number = bf + " " + bf2 + str + ft;
                }
//            } else if ("家乐福(乐家邹区店)".equals(ushops)) {
            } else if ("4".equals(shopCardId)) {
                String str = " ";
                if (unumber != null) {
                    String a = unumber;
                    String bf = a.substring(0, 4);
                    String bf2 = a.substring(4, 6);
                    String ft = "******";
                    String ft2 = a.substring(a.length() - 4, a.length());
                    number = bf + str + bf2 + str + ft + str + ft2;
                }
//            } else if ("中国石油(关河东路店)".equals(ushops)) {
            } else if ("5".equals(shopCardId)) {
                String str = " ";
                String str1 = "**";
                String str2 = "****";
                if (unumber != null) {
                    String a = unumber;
                    String bf = a.substring(0, 4);
                    String bf2 = a.substring(4, 6);
                    String ft2 = a.substring(a.length() - 4, a.length());
                    number = bf + str + bf2 + str1 + str + str2 + ft2;
                }
            }

        return number;
    }

    public String cardString(String ushops, String unumber) {
        String number = "";
        if ("常州明都酒店".equals(ushops)) {
            String str = "*********";

            if (unumber != null) {
                String a = unumber;
                String bf = a.substring(0, 6);
                String ft = a.substring(a.length() - 4, a.length());
                number = bf + str + ft;
            }
        } else if (("常州新城希尔顿酒店".equals(ushops)) ||
                ("大润发（常州店）".equals(ushops)) || ("传奇人生音乐酒吧".equals(ushops)) || ("六福珠宝(常州新世纪商城)".equals(ushops))) {
            String str = "******";

            if (unumber != null) {
                String a = unumber;
                String bf = a.substring(0, 6);
                String ft = a.substring(a.length() - 4, a.length());
                number = bf + str + ft;
            }
        } else if (("海澜之家（关河东路店）".equals(ushops)) || ("环洲绿岛咖啡延陵店".equals(ushops)) || ("常州泰富百货集团有限公司".equals(ushops))) {
            String str = "******";

            if (unumber != null) {
                String a = unumber;
                String bf = a.substring(0, 4);
                String bf2 = a.substring(4, 6);
                String ft = a.substring(a.length() - 4, a.length());
                number = bf + " " + bf2 + str + ft;
            }
        } else if ("家乐福(乐家邹区店)".equals(ushops)) {
            String str = " ";
            if (unumber != null) {
                String a = unumber;
                String bf = a.substring(0, 4);
                String bf2 = a.substring(4, 6);
                String ft = "******";
                String ft2 = a.substring(a.length() - 4, a.length());
                number = bf + str + bf2 + str + ft + str + ft2;
            }
        } else if ("中国石油(关河东路店)".equals(ushops)) {
            String str = " ";
            String str1 = "**";
            String str2 = "****";
            if (unumber != null) {
                String a = unumber;
                String bf = a.substring(0, 4);
                String bf2 = a.substring(4, 6);
                String ft2 = a.substring(a.length() - 4, a.length());
                number = bf + str + bf2 + str1 + str + str2 + ft2;
            }
        }

        return number;
    }
}
