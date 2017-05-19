/*     */ package com.pos.db;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.sql.Connection;
/*     */ import java.sql.DriverManager;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ 
/*     */ public class BaseDao
/*     */ {
/*  18 */   private Connection conn = null;
/*  19 */   private Statement s = null;
/*  20 */   private Properties pro = null;
/*     */   private String driver;
/*     */   private String url;
/*     */   private String user;
/*     */   private String password;
/*     */ 
/*     */   public BaseDao()
/*     */   {
/*  24 */     InputStream is = null;
/*     */     try {
/*  26 */       this.pro = new Properties();
/*  27 */       is = BaseDao.class.getClass().getResourceAsStream(
/*  28 */         "/property/db.properties");
/*  29 */       this.pro.load(is);
/*  30 */       this.driver = this.pro.getProperty("driver").toString();
/*  31 */       this.url = this.pro.getProperty("url").toString();
/*  32 */       this.user = this.pro.getProperty("user").toString();
/*  33 */       this.password = this.pro.getProperty("password").toString();
/*  34 */       Class.forName(this.driver);
/*  35 */       this.conn = DriverManager.getConnection(this.url, this.user, this.password);
/*  36 */       is.close();
/*     */     } catch (IOException e) {
/*  38 */       e.printStackTrace();
/*     */     } catch (ClassNotFoundException e) {
/*  40 */       e.printStackTrace();
/*     */     } catch (SQLException e) {
/*  42 */       e.printStackTrace();
/*     */     } finally {
/*  44 */       if (is != null)
/*     */         try {
/*  46 */           is.close();
/*     */         } catch (IOException e) {
/*  48 */           e.printStackTrace();
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   public Connection getConnection()
/*     */   {
/*  56 */     return this.conn;
/*     */   }
/*     */ 
/*     */   public List<Map> queryDbBySql(String sql) throws SQLException {
/*  61 */     if (!"SELECT".equals(sql.toUpperCase().trim().substring(0, 6)));
/*     */     List list;
/*     */     try {
/*  63 */       throw new Exception("sql错误，此方法为insert方法");
/*     */     } catch (Exception list) {
/*  65 */       e.printStackTrace();
/*     */ 
/*  68 */       list = new ArrayList();
/*  69 */       this.conn = getConnection();
/*  70 */       this.s = this.conn.createStatement();
/*  71 */       ResultSet rs = this.s.getResultSet();
/*  72 */       rs = this.s.executeQuery(sql);
/*  73 */       ResultSetMetaData rsmd = rs.getMetaData();
/*  74 */       String[] columns = new String[rsmd.getColumnCount()];
/*  75 */       for (int i = 0; i < rsmd.getColumnCount(); ++i) {
/*  76 */         columns[i] = rsmd.getColumnName(i + 1);
/*     */       }
/*  78 */       while (rs.next()) {
/*  79 */         Map t = new HashMap();
/*  80 */         for (String str : columns) {
/*  81 */           t.put(str.toUpperCase(), rs.getString(str));
/*     */         }
/*  83 */         list.add(t);
/*     */       }
/*  85 */       connectionColse(this.conn, rs, this.s);
/*  86 */     }return list;
/*     */   }
/*     */ 
/*     */   public Integer insertDbBySql(String sql)
/*     */   {
/*  91 */     if (!"INSERT".equals(sql.toUpperCase().trim().substring(0, 6))) {
/*     */       try {
/*  93 */         throw new Exception("sql错误，此方法为insert方法");
/*     */       } catch (Exception e) {
/*  95 */         e.printStackTrace();
/*     */       }
/*     */     }
/*  98 */     return operationDbBySql(sql);
/*     */   }
/*     */ 
/*     */   public Integer deleteDbBySql(String sql)
/*     */   {
/* 103 */     if (!"DELETE".equals(sql.toUpperCase().trim().substring(0, 6))) {
/*     */       try {
/* 105 */         throw new Exception("sql错误，此方法为delete方法");
/*     */       } catch (Exception e) {
/* 107 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 110 */     return operationDbBySql(sql);
/*     */   }
/*     */ 
/*     */   public Integer updateDbBySql(String sql)
/*     */   {
/* 115 */     if (!"UPDATE".equals(sql.toUpperCase().trim().substring(0, 6))) {
/*     */       try {
/* 117 */         throw new Exception("sql错误，此方法为delete方法");
/*     */       } catch (Exception e) {
/* 119 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 122 */     return operationDbBySql(sql);
/*     */   }
/*     */ 
/*     */   public Integer operationDbBySql(String sql)
/*     */   {
/* 127 */     Integer i = Integer.valueOf(0);
/*     */     try {
/* 129 */       this.conn = getConnection();
/* 130 */       this.s = this.conn.createStatement();
/* 131 */       i = Integer.valueOf(this.s.executeUpdate(sql));
/*     */     } catch (SQLException e) {
/* 133 */       e.printStackTrace();
/*     */     }
/* 135 */     connectionColse(this.conn, null, this.s);
/* 136 */     return i;
/*     */   }
/*     */ 
/*     */   public static void connectionColse(Connection con, ResultSet rs, Statement s)
/*     */   {
/*     */     try
/*     */     {
/* 144 */       if (rs != null) {
/* 145 */         rs.close();
/*     */       }
/* 147 */       if (s != null) {
/* 148 */         s.close();
/*     */       }
/* 150 */       if (con != null)
/* 151 */         con.close();
/*     */     }
/*     */     catch (SQLException e) {
/* 154 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg) throws SQLException {
/* 159 */     for (int i = 80; i < 180; ++i)
/* 160 */       new BaseDao()
/* 161 */         .insertDbBySql("insert into user(user_name,card_no,valide_date,card_self,tuo_guan) values('" + 
/* 162 */         i + 
/* 163 */         "','" + 
/* 164 */         i + 
/* 165 */         "','" + 
/* 166 */         i + 
/* 167 */         "','" + 
/* 168 */         i + 
/* 169 */         "','" + 
/* 170 */         i + 
/* 171 */         "')");
/*     */   }
/*     */ }

/* Location:           F:\otec\pos软件\原始文件\20170517-1.jar
 * Qualified Name:     com.pos.db.BaseDao
 * JD-Core Version:    0.5.4
 */