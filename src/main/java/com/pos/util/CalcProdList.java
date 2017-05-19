package com.pos.util;

import com.pos.db.BaseDao;
import com.pos.vo.GetString;
import com.pos.vo.ShopDetailVO;
import com.pos.vo.UserDB;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javax.swing.JOptionPane;

public class CalcProdList
{
    private static Random r = new Random();
    private static DecimalFormat myformat = new DecimalFormat("0.00");

    public static ArrayList<ShopDetailVO> queryShopMap(String regionStr, String id, String amount)
    {
        String[] ids = id.split("-id-");

        String typeId = ids[0].split(":")[1];
        String shopId = ids[1].split(":")[1];
        String sql = "select t.details_name, t.details_price,t.details_until from shop s inner join details t on s.shop_type_id = '" +
                typeId +
                "' and s.shop_id='" +
                shopId +
                "' and s.shop_type_id = t.shop_type_id   and t.details_price < " +
                amount + " order by t.details_price asc";
        List lis = null;
        try {
            lis = new BaseDao().queryDbBySql(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList shopList = new ArrayList();
        if (lis.size() > 0) {
            ShopDetailVO vo1 = null;
            Map tempMap = null;
            for (int i = 0; i < lis.size(); ++i) {
                tempMap = (Map)lis.get(i);
                vo1 = new ShopDetailVO();
                vo1.setUnitPrice(
                        new BigDecimal((String)tempMap
                                .get("DETAILS_PRICE")));
                vo1.setUnitPriceSrc(vo1.getUnitPrice());
                vo1.setName((String)tempMap.get("DETAILS_NAME"));
                vo1.setDetailsUnit((String)tempMap.get("DETAILS_UNTIL"));
                shopList.add(vo1);
            }
        }
        return shopList;
    }

    public static void createWordByMap(Map map1, String regionStr)
            throws Exception
    {
        if (map1.size() > 1)
        {
            String jf2Str = (map1.get("tuoguan") == null) ? "" : map1.get(
                    "tuoguan").toString();

            String userName = (map1.get("userName") == null) ? "" : map1.get(
                    "userName").toString();

            String cardSelJfStr = (map1.get("cardSelJfStr") == null) ? "" : map1
                    .get("cardSelJfStr").toString();

            String cardValidJfStr = (map1.get("cardValidJfStr") == null) ? "" :
                    map1.get("cardValidJfStr").toString();

            String cardJfStr = (map1.get("cardJfStr") == null) ? "" : map1.get(
                    "cardJfStr").toString();
            Map dateMap = new HashMap();
            dateMap = (Map)map1.get("data");
            ArrayList lis = null;
            Map shopMap = null;
            Set ss = dateMap.keySet();
            Map cardMap = new HashMap();

            int t = 0;
            for (String s : ss) {
                shopMap = (Map)dateMap.get(s);
                String shopName = (String)shopMap.get("shopName");
                String typeName = (String)shopMap.get("typeName");
                BigDecimal amount = new BigDecimal(shopMap.get("amount")
                        .toString());
                BigDecimal minusCount = new BigDecimal(shopMap.get("amount")
                        .toString());
                lis = queryShopMap("", s, amount.toString());
                String printDate = (String)shopMap.get("printDate");
                if (!"".equals(shopName))
                {
                    int productCount = 0;
                    if ((lis == null) || (lis.size() == 0)) {
                        JOptionPane.showMessageDialog(null,
                                "商铺类型:" + shopMap.get("typeName") + ",商铺名称:" +
                                        (String)shopMap.get("shopName") +
                                        "，无符合商品，请重新输入价格！");
                        continue;
                    }
                    cardMap = createProductList(lis, amount, minusCount,
                            productCount, s);

                    productCount = ((Integer)cardMap.get("productCount")).intValue();
                    minusCount = (BigDecimal)cardMap.get("minusCount");
                    cardMap.remove("productCount");
                    cardMap.remove("minusCount");

                    Set ts1 = cardMap.keySet();
                    ShopDetailVO dd = null;

                    minusCount = getProductCalc(cardMap, minusCount,
                            Integer.valueOf(productCount), ts1, dd);

                    List cardList = new ArrayList();
                    for (String tt : ts1) {
                        dd = (ShopDetailVO)cardMap.get(tt);
                        dd.setUintPirceSum(dd.getUnitPrice().multiply(
                                new BigDecimal(dd.getCount())));
                        Map tempMap = new HashMap();

                        String shopDetailsName = dd.getName();
                        int countStr = dd.getCount();
                        BigDecimal unitPrice = dd.getUnitPrice();
                        BigDecimal unitPriceSum = dd.getUintPirceSum();
                        String danwei = dd.getDetailsUnit();
                        cardMap.put(shopDetailsName, unitPrice);
                        tempMap.put("txNo", GetString.getTXNo(""));
                        tempMap.put("name", shopDetailsName);
                        tempMap.put(
                                "details",
                                getStr(shopName, shopDetailsName, countStr,
                                        unitPrice, unitPriceSum, danwei));
                        cardList.add(tempMap);
                    }
                    cardMap.put("cardList", cardList);
                    cardMap.put("listCount", Integer.valueOf(cardList.size() - 1));
                    cardMap.put("listCount2", Integer.valueOf(cardList.size()));
                }

                String detailsPrintDate = DateUtil.getDate(
                        printDate.replaceAll("-", "/"), typeName);

                String posPrintDate = DateUtil.getDate1(detailsPrintDate)
                        .substring(0, 10);
                String posPrintTime = DateUtil.getDate1(detailsPrintDate)
                        .substring(11, 19);

                cardMap.put("ushop", shopName);
                cardMap.put("typeName", typeName);
                cardMap.put("cardShop", cardSelJfStr);
                cardMap.put("cardStr", cardJfStr);
                cardMap.put("detailsCardStr", cardJfStr);
                cardMap.put("cardValid", cardValidJfStr);
                cardMap.put("amount", myformat.format(amount));
                cardMap.put("userName", userName);
                cardMap.put("posPrintDate", posPrintDate);
                cardMap.put("posTime", posPrintTime);
                cardMap.put("detailsPrintDate",
                        DateUtil.getDateStr(detailsPrintDate, "1", shopName));

                cardMap.put("batchNo", GetString.getBatchNo(shopName));
                cardMap.put("voucher", GetString.getVoucherNo(shopName));
                cardMap.put("authNo", GetString.getAuthNo(shopName));
                cardMap.put("txNo", GetString.getTXNo(shopName));
                cardMap.put(
                        "refNo",
                        GetString.getRefNo(shopName, 12, posPrintDate + " " +
                                posPrintTime));
                cardMap.put("detailsNum",
                        GetString.getDetailsNum(shopName, detailsPrintDate));
                cardMap.put("detailsPrintDate2",
                        DateUtil.getDateStr(detailsPrintDate, "2", shopName));
                cardMap.put("memberShipCard",
                        GetString.getMemberShipCard(shopName));

                cardMap.put("userNo", UserDB.getShoppingUser(typeName));
                cardMap.put("flag", Boolean.valueOf(getFlagStr(typeName)));
                new CreateWordByMap().createWord1(cardMap);
            }
        }
        else {
            throw new Exception("获取商品列表信息为空！");
        }
    }

    public static String getStr(String shopName, String shopDetailsName, int count, BigDecimal unitPrice, BigDecimal unitPriceSum, String danwei)
    {
        StringBuffer sb = new StringBuffer();
        if (("常州明都酒店".equals(shopName)) || ("常州新城希尔顿酒店".equals(shopName)) || ("环洲绿岛咖啡延陵店".equals(shopName)) || ("六福珠宝(常州新世纪商城)".equals(shopName))) {
            sb.append(shopDetailsName);
            if (shopDetailsName.getBytes().length < 16)
                sb.append(getStr(16 - shopDetailsName.getBytes().length));
            else {
                sb.append(" ");
            }
            if (count > 10)
                sb.append(count);
            else {
                sb.append(" " + count);
            }
            if (unitPrice.intValue() < 10)
                sb.append("        ").append(myformat.format(unitPrice));
            else if (unitPrice.intValue() < 100)
                sb.append("       ").append(myformat.format(unitPrice));
            else if (unitPrice.intValue() < 1000)
                sb.append("      ").append(myformat.format(unitPrice));
            else if (unitPrice.intValue() < 10000)
                sb.append("     ").append(myformat.format(unitPrice));
            else
                sb.append("    ").append(myformat.format(unitPrice));
        }
        else if ("传奇人生音乐酒吧".equals(shopName)) {
            sb.append(shopDetailsName);
            if (shopDetailsName.getBytes().length < 12)
                sb.append(getStr(12 - shopDetailsName.getBytes().length));
            else {
                sb.append(" ");
            }
            if (count > 10)
                sb.append(count);
            else {
                sb.append(" " + count);
            }
            if (unitPrice.intValue() < 10)
                sb.append("    ").append(myformat.format(unitPrice));
            else if (unitPrice.intValue() < 100)
                sb.append("   ").append(myformat.format(unitPrice));
            else if (unitPrice.intValue() < 1000)
                sb.append("  ").append(myformat.format(unitPrice));
            else {
                sb.append("  ").append(myformat.format(unitPrice));
            }

            if (unitPriceSum.intValue() < 10)
                sb.append("   ").append(myformat.format(unitPriceSum));
            else if (unitPriceSum.intValue() < 100)
                sb.append("  ").append(myformat.format(unitPriceSum));
            else if (unitPriceSum.intValue() < 1000)
                sb.append(" ").append(myformat.format(unitPriceSum));
            else
                sb.append(" ").append(myformat.format(unitPriceSum));
        }
        else if ("大润发（常州店）".equals(shopName)) {
            sb.append("31575  ").append(shopDetailsName);
            if (shopDetailsName.getBytes().length < 16)
                sb.append(getStr(16 - shopDetailsName.getBytes().length));
            else {
                sb.append(" ");
            }
            if (count > 10)
                sb.append(count);
            else {
                sb.append(" " + count);
            }
            if (unitPrice.intValue() < 10)
                sb.append("     ").append(myformat.format(unitPrice));
            else if (unitPrice.intValue() < 100)
                sb.append("    ").append(myformat.format(unitPrice));
            else if (unitPrice.intValue() < 1000)
                sb.append("   ").append(myformat.format(unitPrice));
            else
                sb.append("   ").append(myformat.format(unitPrice));
        }
        else if ("海澜之家（关河东路店）".equals(shopName)) {
            sb.append(shopDetailsName);
            if (shopDetailsName.getBytes().length < 16)
                sb.append(getStr(13 - shopDetailsName.getBytes().length));
            else {
                sb.append(" ");
            }
            if (count > 10)
                sb.append(count);
            else {
                sb.append(" " + count);
            }
            if ((danwei != null) && (!"".equals(danwei))) {
                sb.append("    " + danwei);
            }
            if (unitPrice.intValue() < 10)
                sb.append("     ").append(myformat.format(unitPrice));
            else if (unitPrice.intValue() < 100)
                sb.append("    ").append(myformat.format(unitPrice));
            else if (unitPrice.intValue() < 1000)
                sb.append("   ").append(myformat.format(unitPrice));
            else {
                sb.append("   ").append(myformat.format(unitPrice));
            }
        }
        else if ("常州泰富百货集团有限公司".equals(shopName)) {
            sb.append(shopDetailsName);
            if (shopDetailsName.getBytes().length < 12)
                sb.append(getStr(10 - shopDetailsName.getBytes().length));
            else {
                sb.append(" ");
            }

            if (unitPrice.intValue() < 10)
                sb.append("    ").append(myformat.format(unitPrice));
            else if (unitPrice.intValue() < 100)
                sb.append("   ").append(myformat.format(unitPrice));
            else if (unitPrice.intValue() < 1000)
                sb.append("  ").append(myformat.format(unitPrice));
            else {
                sb.append("  ").append(myformat.format(unitPrice));
            }
            if (count > 10)
                sb.append(count);
            else {
                sb.append("  " + count);
            }
            if (unitPriceSum.intValue() < 10)
                sb.append("   ").append(myformat.format(unitPriceSum));
            else if (unitPriceSum.intValue() < 100)
                sb.append("  ").append(myformat.format(unitPriceSum));
            else if (unitPriceSum.intValue() < 1000)
                sb.append(" ").append(myformat.format(unitPriceSum));
            else {
                sb.append(" ").append(myformat.format(unitPriceSum));
            }
        }
        else if ("家乐福(乐家邹区店)".equals(shopName)) {
            if (shopDetailsName.getBytes().length < 12)
                sb.append(getStr(10 - shopDetailsName.getBytes().length));
            else {
                sb.append(" ");
            }

            if (count > 10)
                sb.append(count);
            else {
                sb.append("  " + count);
            }

            if (unitPrice.intValue() < 10)
                sb.append("    ").append(myformat.format(unitPrice));
            else if (unitPrice.intValue() < 100)
                sb.append("   ").append(myformat.format(unitPrice));
            else if (unitPrice.intValue() < 1000)
                sb.append("  ").append(myformat.format(unitPrice));
            else {
                sb.append("  ").append(myformat.format(unitPrice));
            }
            if (unitPriceSum.intValue() < 10)
                sb.append("   ").append(myformat.format(unitPriceSum));
            else if (unitPriceSum.intValue() < 100)
                sb.append("  ").append(myformat.format(unitPriceSum));
            else if (unitPriceSum.intValue() < 1000)
                sb.append(" ").append(myformat.format(unitPriceSum));
            else {
                sb.append(" ").append(myformat.format(unitPriceSum));
            }

        }

        return sb.toString();
    }

    public static String getStr(int j) {
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < j; ++i) {
            sf.append(" ");
        }
        return sf.toString();
    }

    public static boolean getFlagStr(String typeName)
    {
        boolean flag = true;
        if ("油站".equals(typeName)) {
            flag = false;
        }
        return flag;
    }

    public static BigDecimal getProductCalc(Map cardMap, BigDecimal minusCount, Integer productCount, Set<String> ts1, ShopDetailVO dd)
    {
        if (minusCount.compareTo(new BigDecimal("0")) == 1) {
            BigDecimal tempBig = minusCount.divide(
                    new BigDecimal(productCount.intValue()), 3, 4)
                    .setScale(0, 5);
            if (tempBig.multiply(new BigDecimal(productCount.intValue())).compareTo(
                    minusCount) == 1) {
                tempBig = tempBig.subtract(new BigDecimal("1"));
            }

            BigDecimal yushu = minusCount.subtract(tempBig
                    .multiply(new BigDecimal(productCount.intValue())));
            boolean flag;
            do
            {
                flag = false;
                for (String tt : ts1) {
                    dd = (ShopDetailVO)cardMap.get(tt);
                    if (tempBig.compareTo(new BigDecimal("0")) > 0)
                    {
                        if ((dd.getUnitPriceSrc().divide(new BigDecimal(4))
                                .compareTo(tempBig) >= 0) &&
                                (tempBig.multiply(
                                        new BigDecimal(dd.getCount()))
                                        .compareTo(minusCount) <= 0))
                        {
                            dd.setUnitPrice(tempBig.add(dd.getUnitPrice()));

                            minusCount = minusCount.subtract(tempBig
                                    .multiply(new BigDecimal(dd.getCount())));
                            flag = true;
                            continue; } if ((minusCount.compareTo(yushu) == 0) &&
                            (minusCount.compareTo(new BigDecimal("0")) == 1) &&
                            (dd.getCount() == 1)) {
                        minusCount = minusCount.subtract(yushu);
                        dd.setUnitPrice(dd.getUnitPrice().add(yushu));
                        break;
                    }if ((minusCount.compareTo(yushu) != 0) ||
                            (minusCount.compareTo(new BigDecimal("0")) != 1) ||
                            (dd.getCount() != 2)) continue;
                        minusCount = minusCount.subtract(yushu);
                        dd.setUnitPrice(dd.getUnitPrice().add(
                                yushu.divide(new BigDecimal("2"))));
                        break;
                    }
                    if (yushu.compareTo(new BigDecimal("0")) <= 0) break;
                    if (dd.getCount() == 1) {
                        minusCount = minusCount.subtract(yushu);
                        dd.setUnitPrice(dd.getUnitPrice().add(yushu));
                        break;
                    }
                    minusCount = minusCount.subtract(yushu);
                    dd.setUnitPrice(dd.getUnitPrice().add(
                            yushu.divide(new BigDecimal("2"))));
                    break;
                }

            }

            while (flag);
        }

        return minusCount;
    }

    public static Map createProductList(ArrayList<ShopDetailVO> lis, BigDecimal amount, BigDecimal minusCount, int productCount, String keyStr)
    {
        ArrayList newList = null;
        ShopDetailVO vt = null;
        Map details = new HashMap();
        Map cardMap = new HashMap();
        int t = 0;
        while (true) {
            newList = checkProductList(lis, minusCount, details);
            if ((newList.size() == 0) && (cardMap.size() == 0)) {
                JOptionPane.showMessageDialog(null, keyStr + "，无符合商品，请重新输入价格！");
                break;
            }if ((newList.size() == 0) && (cardMap.size() > 0))
                break;
            if (newList.size() == 0) {
                JOptionPane.showMessageDialog(null, keyStr + "，无符合商品，请重新输入价格！");
                break;
            }
            t = r.nextInt(newList.size());
            vt = (ShopDetailVO)newList.get(t);
            if (cardMap.keySet().contains(vt.getName())) {
                if (vt.getCount() < 2);
                minusCount = minusCount.subtract(vt.getUnitPrice());
                vt.setCount(vt.getCount() + 1);
                cardMap.put(vt.getName(), vt);
                ++productCount;
                details.put(vt.getName(), vt);
            }

            minusCount = minusCount.subtract(vt.getUnitPrice());
            cardMap.put(vt.getName(), vt);
            ++productCount;
        }

        cardMap.put("productCount", Integer.valueOf(productCount));
        cardMap.put("minusCount", minusCount);
        return cardMap;
    }

    public static ArrayList checkProductList(ArrayList<ShopDetailVO> list, BigDecimal amountflag, Map details)
    {
        ArrayList newLis = new ArrayList();
        ShopDetailVO vo = null;
        if ((list != null) && (list.size() > 0)) {
            for (int i = 0; i < list.size(); ++i) {
                vo = (ShopDetailVO)list.get(i);
                if ((vo.getUnitPrice().compareTo(amountflag) >= 1) ||
                        (details.keySet().contains(vo.getName()))) continue;
                newLis.add(vo);
            }
        }

        return newLis;
    }
}