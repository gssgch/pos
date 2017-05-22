package com.pos.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class CheckUtil
{
  // 校验 用户名和密码
  public static boolean checkValide(String paramString1, String paramString2)
    throws Exception
  {
    int i = 0;
    boolean flag = false;
    InetAddress localInetAddress = InetAddress.getLocalHost();
    byte[] arrayOfByte = NetworkInterface.getByInetAddress(localInetAddress).getHardwareAddress();
    StringBuffer localStringBuffer = new StringBuffer("");
    for (int j = 0; j < arrayOfByte.length; j++) {
      if (j != 0) {
        localStringBuffer.append("-");
      }
      
      int k = arrayOfByte[j] & 0xFF;
      String str3 = Integer.toHexString(k);
      if (str3.length() == 1) {
        localStringBuffer.append("0" + str3);
      } else {
        localStringBuffer.append(str3);
      }
    }
    String str1 = localStringBuffer.toString().toUpperCase().replaceAll("-", "");
    String str2 = compressString(str1 + paramString1).replace("=", "");
    System.out.println(str2);
    if (!str2.equals(paramString2)) {
      i = 1;
      flag=true;
    }
//    return flag;
    return false;
  }
  
  public static String compressString(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      return paramString;
    }
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    GZIPOutputStream localGZIPOutputStream = null;
    try {
      localGZIPOutputStream = new GZIPOutputStream(localByteArrayOutputStream);
      localGZIPOutputStream.write(paramString.getBytes());

    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    } finally {
      if (localGZIPOutputStream != null) {
        try {
          localGZIPOutputStream.close();
        } catch (IOException localIOException3) {
          localIOException3.printStackTrace();
        }
      }
    }
      return new BASE64Encoder().encode(localByteArrayOutputStream.toByteArray());
  }
  

  public static String uncompressString(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      return null;
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    ByteArrayInputStream localByteArrayInputStream = null;
    GZIPInputStream localGZIPInputStream = null;
    byte[] arrayOfByte1 = null;
    String str = null;
    try {
      arrayOfByte1 = new BASE64Decoder().decodeBuffer(paramString);
      localByteArrayInputStream = new ByteArrayInputStream(arrayOfByte1);
      localGZIPInputStream = new GZIPInputStream(localByteArrayInputStream);
      byte[] arrayOfByte2 = new byte[1024];
      int i = -1;
      while ((i = localGZIPInputStream.read(arrayOfByte2)) != -1) {
        localByteArrayOutputStream.write(arrayOfByte2, 0, i);
      }
      str= localByteArrayOutputStream.toString();
    } catch (IOException localIOException2) {
      localIOException2.printStackTrace();
    } finally {
      try {
        if (localGZIPInputStream != null)
          localGZIPInputStream.close();
        if (localByteArrayInputStream != null)
          localByteArrayInputStream.close();
        if (localByteArrayOutputStream != null)
          localByteArrayOutputStream.close();
      } catch (IOException localIOException4) {
        localIOException4.printStackTrace();
      }
    }
    return str;
  }
  
  public static void main(String[] paramArrayOfString)
    throws Exception
  {
    checkValide("wll", "");
  }
}
