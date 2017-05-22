package com.pos.util;

import java.awt.Dimension;
import java.awt.Toolkit;



public class DimensionUtil
{
  public static Dimension getDimension()
  {
    Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
    return ds;
  }
}
