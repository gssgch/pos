/*     */ package com.pos.util;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.net.InetAddress;
/*     */ import java.net.NetworkInterface;
/*     */ import java.util.zip.GZIPInputStream;
/*     */ import java.util.zip.GZIPOutputStream;
/*     */ import sun.misc.BASE64Decoder;
/*     */ import sun.misc.BASE64Encoder;
/*     */ 
/*     */ public class CheckUtil
/*     */ {
/*     */   public static boolean checkValide(String paramString1, String paramString2)
/*     */     throws Exception
/*     */   {
/*  19 */     int i = 0;
/*  20 */     InetAddress localInetAddress = InetAddress.getLocalHost();
/*  21 */     byte[] arrayOfByte = NetworkInterface.getByInetAddress(localInetAddress).getHardwareAddress();
/*  22 */     StringBuffer localStringBuffer = new StringBuffer("");
/*  23 */     for (int j = 0; j < arrayOfByte.length; ++j) {
/*  24 */       if (j != 0) {
/*  25 */         localStringBuffer.append("-");
/*     */       }
/*     */ 
/*  28 */       int k = arrayOfByte[j] & 0xFF;
/*  29 */       String str3 = Integer.toHexString(k);
/*  30 */       if (str3.length() == 1)
/*  31 */         localStringBuffer.append("0" + str3);
/*     */       else {
/*  33 */         localStringBuffer.append(str3);
/*     */       }
/*     */     }
/*  36 */     String str1 = localStringBuffer.toString().toUpperCase().replaceAll("-", "");
/*  37 */     String str2 = compressString(str1 + paramString1).replace("=", "");
/*  38 */     System.out.println(str2);
/*  39 */     if (!str2.equals(paramString2)) {
/*  40 */       i = 1;
/*     */     }
/*  42 */     return false;
/*     */   }
/*     */ 
/*     */   public static String compressString(String paramString)
/*     */   {
/*  47 */     if ((paramString == null) || (paramString.length() == 0)) {
/*  48 */       return paramString;
/*     */     }
/*  50 */     ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
/*  51 */     GZIPOutputStream localGZIPOutputStream = null;
/*     */     try {
/*  53 */       localGZIPOutputStream = new GZIPOutputStream(localByteArrayOutputStream);
/*  54 */       localGZIPOutputStream.write(paramString.getBytes());
/*     */     } catch (Exception localIOException2) {
/*  56 */       localException.printStackTrace();
/*     */     } finally {
/*  58 */       if (localGZIPOutputStream != null) {
/*     */         try {
/*  60 */           localGZIPOutputStream.close();
/*     */         } catch (IOException localIOException3) {
/*  62 */           localIOException3.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*  66 */     return new BASE64Encoder().encode(localByteArrayOutputStream.toByteArray());
/*     */   }
/*     */ 
/*     */   public static String uncompressString(String paramString)
/*     */   {
/*  71 */     if ((paramString == null) || (paramString.length() == 0))
/*  72 */       return null;
/*  73 */     ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
/*  74 */     ByteArrayInputStream localByteArrayInputStream = null;
/*  75 */     GZIPInputStream localGZIPInputStream = null;
/*  76 */     byte[] arrayOfByte1 = null;
/*  77 */     String str = null;
/*     */     try {
/*  79 */       arrayOfByte1 = new BASE64Decoder().decodeBuffer(paramString);
/*  80 */       localByteArrayInputStream = new ByteArrayInputStream(arrayOfByte1);
/*  81 */       localGZIPInputStream = new GZIPInputStream(localByteArrayInputStream);
/*  82 */       byte[] arrayOfByte2 = new byte[1024];
/*  83 */       int i = -1;
/*  84 */       while ((i = localGZIPInputStream.read(arrayOfByte2)) != -1) {
/*  85 */         localByteArrayOutputStream.write(arrayOfByte2, 0, i);
/*     */       }
/*  87 */       str = localByteArrayOutputStream.toString();
/*     */     } catch (IOException localIOException3) {
/*  89 */       localIOException2.printStackTrace();
/*     */     } finally {
/*     */       try {
/*  92 */         if (localGZIPInputStream != null)
/*  93 */           localGZIPInputStream.close();
/*  94 */         if (localByteArrayInputStream != null)
/*  95 */           localByteArrayInputStream.close();
/*  96 */         if (localByteArrayOutputStream != null)
/*  97 */           localByteArrayOutputStream.close();
/*     */       } catch (IOException localIOException4) {
/*  99 */         localIOException4.printStackTrace();
/*     */       }
/*     */     }
/* 102 */     return str;
/*     */   }
/*     */ 
/*     */   public static void main(String[] paramArrayOfString) throws Exception
/*     */   {
/* 107 */     checkValide("wll", "");
/*     */   }
/*     */ }

/* Location:           F:\otec\pos软件\原始文件\20170517-1.jar
 * Qualified Name:     com.pos.util.CheckUtil
 * JD-Core Version:    0.5.4
 */