/*     */ package com.pos.db;
/*     */ 
/*     */ import java.sql.Connection;
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
/*     */   private Connection conn;
/*     */   private Statement s;
/*     */   private Properties pro;
/*     */   private String driver;
/*     */   private String url;
/*     */   private String user;
/*     */   private String password;
/*     */   
/*     */   /* Error */
/*     */   public BaseDao()
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokespecial 19	java/lang/Object:<init>	()V
/*     */     //   4: aload_0
/*     */     //   5: aconst_null
/*     */     //   6: putfield 21	com/pos/db/BaseDao:conn	Ljava/sql/Connection;
/*     */     //   9: aload_0
/*     */     //   10: aconst_null
/*     */     //   11: putfield 23	com/pos/db/BaseDao:s	Ljava/sql/Statement;
/*     */     //   14: aload_0
/*     */     //   15: aconst_null
/*     */     //   16: putfield 25	com/pos/db/BaseDao:pro	Ljava/util/Properties;
/*     */     //   19: aconst_null
/*     */     //   20: astore_1
/*     */     //   21: aload_0
/*     */     //   22: new 27	java/util/Properties
/*     */     //   25: dup
/*     */     //   26: invokespecial 29	java/util/Properties:<init>	()V
/*     */     //   29: putfield 25	com/pos/db/BaseDao:pro	Ljava/util/Properties;
/*     */     //   32: ldc 1
/*     */     //   34: invokevirtual 30	java/lang/Object:getClass	()Ljava/lang/Class;
/*     */     //   37: ldc 34
/*     */     //   39: invokevirtual 36	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
/*     */     //   42: astore_1
/*     */     //   43: aload_0
/*     */     //   44: getfield 25	com/pos/db/BaseDao:pro	Ljava/util/Properties;
/*     */     //   47: aload_1
/*     */     //   48: invokevirtual 42	java/util/Properties:load	(Ljava/io/InputStream;)V
/*     */     //   51: aload_0
/*     */     //   52: aload_0
/*     */     //   53: getfield 25	com/pos/db/BaseDao:pro	Ljava/util/Properties;
/*     */     //   56: ldc 46
/*     */     //   58: invokevirtual 47	java/util/Properties:getProperty	(Ljava/lang/String;)Ljava/lang/String;
/*     */     //   61: invokevirtual 51	java/lang/String:toString	()Ljava/lang/String;
/*     */     //   64: putfield 57	com/pos/db/BaseDao:driver	Ljava/lang/String;
/*     */     //   67: aload_0
/*     */     //   68: aload_0
/*     */     //   69: getfield 25	com/pos/db/BaseDao:pro	Ljava/util/Properties;
/*     */     //   72: ldc 59
/*     */     //   74: invokevirtual 47	java/util/Properties:getProperty	(Ljava/lang/String;)Ljava/lang/String;
/*     */     //   77: invokevirtual 51	java/lang/String:toString	()Ljava/lang/String;
/*     */     //   80: putfield 60	com/pos/db/BaseDao:url	Ljava/lang/String;
/*     */     //   83: aload_0
/*     */     //   84: aload_0
/*     */     //   85: getfield 25	com/pos/db/BaseDao:pro	Ljava/util/Properties;
/*     */     //   88: ldc 62
/*     */     //   90: invokevirtual 47	java/util/Properties:getProperty	(Ljava/lang/String;)Ljava/lang/String;
/*     */     //   93: invokevirtual 51	java/lang/String:toString	()Ljava/lang/String;
/*     */     //   96: putfield 63	com/pos/db/BaseDao:user	Ljava/lang/String;
/*     */     //   99: aload_0
/*     */     //   100: aload_0
/*     */     //   101: getfield 25	com/pos/db/BaseDao:pro	Ljava/util/Properties;
/*     */     //   104: ldc 65
/*     */     //   106: invokevirtual 47	java/util/Properties:getProperty	(Ljava/lang/String;)Ljava/lang/String;
/*     */     //   109: invokevirtual 51	java/lang/String:toString	()Ljava/lang/String;
/*     */     //   112: putfield 66	com/pos/db/BaseDao:password	Ljava/lang/String;
/*     */     //   115: aload_0
/*     */     //   116: getfield 57	com/pos/db/BaseDao:driver	Ljava/lang/String;
/*     */     //   119: invokestatic 68	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
/*     */     //   122: pop
/*     */     //   123: aload_0
/*     */     //   124: aload_0
/*     */     //   125: getfield 60	com/pos/db/BaseDao:url	Ljava/lang/String;
/*     */     //   128: aload_0
/*     */     //   129: getfield 63	com/pos/db/BaseDao:user	Ljava/lang/String;
/*     */     //   132: aload_0
/*     */     //   133: getfield 66	com/pos/db/BaseDao:password	Ljava/lang/String;
/*     */     //   136: invokestatic 72	java/sql/DriverManager:getConnection	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/sql/Connection;
/*     */     //   139: putfield 21	com/pos/db/BaseDao:conn	Ljava/sql/Connection;
/*     */     //   142: aload_1
/*     */     //   143: invokevirtual 78	java/io/InputStream:close	()V
/*     */     //   146: goto +102 -> 248
/*     */     //   149: astore_2
/*     */     //   150: aload_2
/*     */     //   151: invokevirtual 83	java/io/IOException:printStackTrace	()V
/*     */     //   154: aload_1
/*     */     //   155: ifnull +111 -> 266
/*     */     //   158: aload_1
/*     */     //   159: invokevirtual 78	java/io/InputStream:close	()V
/*     */     //   162: goto +104 -> 266
/*     */     //   165: astore 4
/*     */     //   167: aload 4
/*     */     //   169: invokevirtual 83	java/io/IOException:printStackTrace	()V
/*     */     //   172: goto +94 -> 266
/*     */     //   175: astore_2
/*     */     //   176: aload_2
/*     */     //   177: invokevirtual 88	java/lang/ClassNotFoundException:printStackTrace	()V
/*     */     //   180: aload_1
/*     */     //   181: ifnull +85 -> 266
/*     */     //   184: aload_1
/*     */     //   185: invokevirtual 78	java/io/InputStream:close	()V
/*     */     //   188: goto +78 -> 266
/*     */     //   191: astore 4
/*     */     //   193: aload 4
/*     */     //   195: invokevirtual 83	java/io/IOException:printStackTrace	()V
/*     */     //   198: goto +68 -> 266
/*     */     //   201: astore_2
/*     */     //   202: aload_2
/*     */     //   203: invokevirtual 91	java/sql/SQLException:printStackTrace	()V
/*     */     //   206: aload_1
/*     */     //   207: ifnull +59 -> 266
/*     */     //   210: aload_1
/*     */     //   211: invokevirtual 78	java/io/InputStream:close	()V
/*     */     //   214: goto +52 -> 266
/*     */     //   217: astore 4
/*     */     //   219: aload 4
/*     */     //   221: invokevirtual 83	java/io/IOException:printStackTrace	()V
/*     */     //   224: goto +42 -> 266
/*     */     //   227: astore_3
/*     */     //   228: aload_1
/*     */     //   229: ifnull +17 -> 246
/*     */     //   232: aload_1
/*     */     //   233: invokevirtual 78	java/io/InputStream:close	()V
/*     */     //   236: goto +10 -> 246
/*     */     //   239: astore 4
/*     */     //   241: aload 4
/*     */     //   243: invokevirtual 83	java/io/IOException:printStackTrace	()V
/*     */     //   246: aload_3
/*     */     //   247: athrow
/*     */     //   248: aload_1
/*     */     //   249: ifnull +17 -> 266
/*     */     //   252: aload_1
/*     */     //   253: invokevirtual 78	java/io/InputStream:close	()V
/*     */     //   256: goto +10 -> 266
/*     */     //   259: astore 4
/*     */     //   261: aload 4
/*     */     //   263: invokevirtual 83	java/io/IOException:printStackTrace	()V
/*     */     //   266: return
/*     */     // Line number table:
/*     */     //   Java source line #23	-> byte code offset #0
/*     */     //   Java source line #18	-> byte code offset #4
/*     */     //   Java source line #19	-> byte code offset #9
/*     */     //   Java source line #20	-> byte code offset #14
/*     */     //   Java source line #24	-> byte code offset #19
/*     */     //   Java source line #26	-> byte code offset #21
/*     */     //   Java source line #27	-> byte code offset #32
/*     */     //   Java source line #28	-> byte code offset #37
/*     */     //   Java source line #27	-> byte code offset #39
/*     */     //   Java source line #29	-> byte code offset #43
/*     */     //   Java source line #30	-> byte code offset #51
/*     */     //   Java source line #31	-> byte code offset #67
/*     */     //   Java source line #32	-> byte code offset #83
/*     */     //   Java source line #33	-> byte code offset #99
/*     */     //   Java source line #34	-> byte code offset #115
/*     */     //   Java source line #35	-> byte code offset #123
/*     */     //   Java source line #36	-> byte code offset #142
/*     */     //   Java source line #37	-> byte code offset #146
/*     */     //   Java source line #38	-> byte code offset #150
/*     */     //   Java source line #44	-> byte code offset #154
/*     */     //   Java source line #46	-> byte code offset #158
/*     */     //   Java source line #47	-> byte code offset #162
/*     */     //   Java source line #48	-> byte code offset #167
/*     */     //   Java source line #39	-> byte code offset #175
/*     */     //   Java source line #40	-> byte code offset #176
/*     */     //   Java source line #44	-> byte code offset #180
/*     */     //   Java source line #46	-> byte code offset #184
/*     */     //   Java source line #47	-> byte code offset #188
/*     */     //   Java source line #48	-> byte code offset #193
/*     */     //   Java source line #41	-> byte code offset #201
/*     */     //   Java source line #42	-> byte code offset #202
/*     */     //   Java source line #44	-> byte code offset #206
/*     */     //   Java source line #46	-> byte code offset #210
/*     */     //   Java source line #47	-> byte code offset #214
/*     */     //   Java source line #48	-> byte code offset #219
/*     */     //   Java source line #43	-> byte code offset #227
/*     */     //   Java source line #44	-> byte code offset #228
/*     */     //   Java source line #46	-> byte code offset #232
/*     */     //   Java source line #47	-> byte code offset #236
/*     */     //   Java source line #48	-> byte code offset #241
/*     */     //   Java source line #51	-> byte code offset #246
/*     */     //   Java source line #44	-> byte code offset #248
/*     */     //   Java source line #46	-> byte code offset #252
/*     */     //   Java source line #47	-> byte code offset #256
/*     */     //   Java source line #48	-> byte code offset #261
/*     */     //   Java source line #52	-> byte code offset #266
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	267	0	this	BaseDao
/*     */     //   20	233	1	is	java.io.InputStream
/*     */     //   149	2	2	e	java.io.IOException
/*     */     //   175	2	2	e	ClassNotFoundException
/*     */     //   201	2	2	e	SQLException
/*     */     //   227	20	3	localObject	Object
/*     */     //   165	3	4	e	java.io.IOException
/*     */     //   191	3	4	e	java.io.IOException
/*     */     //   217	3	4	e	java.io.IOException
/*     */     //   239	3	4	e	java.io.IOException
/*     */     //   259	3	4	e	java.io.IOException
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   21	146	149	java/io/IOException
/*     */     //   158	162	165	java/io/IOException
/*     */     //   21	146	175	java/lang/ClassNotFoundException
/*     */     //   184	188	191	java/io/IOException
/*     */     //   21	146	201	java/sql/SQLException
/*     */     //   210	214	217	java/io/IOException
/*     */     //   21	154	227	finally
/*     */     //   175	180	227	finally
/*     */     //   201	206	227	finally
/*     */     //   232	236	239	java/io/IOException
/*     */     //   252	256	259	java/io/IOException
/*     */   }
/*     */   
/*     */   public Connection getConnection()
/*     */   {
/*  56 */     return this.conn;
/*     */   }
/*     */   
/*     */   public List<Map> queryDbBySql(String sql) throws SQLException
/*     */   {
/*  61 */     if (!"SELECT".equals(sql.toUpperCase().trim().substring(0, 6))) {
/*     */       try {
/*  63 */         throw new Exception("sql错误，此方法为insert方法");
/*     */       } catch (Exception e) {
/*  65 */         e.printStackTrace();
/*     */       }
/*     */     }
/*  68 */     List<Map> list = new ArrayList();
/*  69 */     this.conn = getConnection();
/*  70 */     this.s = this.conn.createStatement();
/*  71 */     ResultSet rs = this.s.getResultSet();
/*  72 */     rs = this.s.executeQuery(sql);
/*  73 */     ResultSetMetaData rsmd = rs.getMetaData();
/*  74 */     String[] columns = new String[rsmd.getColumnCount()];
/*  75 */     for (int i = 0; i < rsmd.getColumnCount(); i++) {
/*  76 */       columns[i] = rsmd.getColumnName(i + 1);
/*     */     }
/*  78 */     while (rs.next()) {
/*  79 */       Map t = new HashMap();
/*  80 */       String[] arrayOfString1; int j = (arrayOfString1 = columns).length; for (int i = 0; i < j; i++) { String str = arrayOfString1[i];
/*  81 */         t.put(str.toUpperCase(), rs.getString(str));
/*     */       }
/*  83 */       list.add(t);
/*     */     }
/*  85 */     connectionColse(this.conn, rs, this.s);
/*  86 */     return list;
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
/* 150 */       if (con != null) {
/* 151 */         con.close();
/*     */       }
/*     */     } catch (SQLException e) {
/* 154 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void main(String[] arg) throws SQLException {
/* 159 */     for (int i = 80; i < 180; i++)
/*     */     {
/* 161 */       new BaseDao().insertDbBySql("insert into user(user_name,card_no,valide_date,card_self,tuo_guan) values('" + 
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
/*     */     }
/*     */   }
/*     */ }


/* Location:              F:\otec\pos软件\20170517\1.jar!\com\pos\db\BaseDao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */