package com.mycare.actions.test;

import com.mycare.actions.test.CRYPTOCardAPI;
import java.io.PrintStream;

public class TestCryptoCard
{
  static int AUTH_FAILURE = 0;
  static int AUTH_SUCCESS = 1;
  static int CHALLENGE = 2;
  static int SERVER_PIN_PROVIDED = 3;
  static int USER_PIN_CHANGE = 4;
  static int OUTER_WINDOW_AUTH = 5;
  static int CHANGE_STATIC_PASSWORD = 6;
  static int STATIC_CHANGE_FAILED = 7;
  static int PIN_CHANGE_FAILED = 8;

  public static void main(String[] paramArrayOfString)
  {
    String str1 = "";
    /*String str2 = "/home/bovas/Downloads/usr/local/cryptocard/javaapi/bin/x86/JCryptoWrapperWin.dll";
    String str3 = "/home/bovas/Downloads/usr/local/cryptocard/javaapi/x64/JCryptoWrapperWin.dll";
*/
    String str4 = System.getProperty("os.arch");
    System.out.println(str4);
    if (str4.toLowerCase().contains("64"))
    {
      str1 = "/usr/local/cryptocard/javaapi/bin/x64/libJCryptoWrapper.so";
    }
    else
    {
      str1 = "/usr/local/cryptocard/javaapi/bin/x86/libJCryptoWrapper.so";
    }

    String str5 = "";

    String str6 = System.getProperty("os.name");
    /*if (str6.toLowerCase().contains("windows"))
    {
      if (str4.toLowerCase().contains("32"))
      {
        str5 = str2;
      }
      else
      {
        str5 = str3;
      }
    }
    else
    {*/
      str5 = str1;
    //}      
      System.out.println(str1);
    CRYPTOCardAPI localCRYPTOCardAPI = CRYPTOCardAPI.getInstance();
    int i = 0;
    try
    {
      localCRYPTOCardAPI.LoadJNILibrary();
      i = 1;
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError1)    
    {
    	localUnsatisfiedLinkError1.printStackTrace();
    }
    catch (Exception localException1)
    {
    	localException1.printStackTrace();
    }
    
    

    if (i == 0)
    {
      System.out.println(String.format("WARNING: Loading failed using environment paths. Will try now installation path[%s]", new Object[] { str5 }));
      try
      {
        localCRYPTOCardAPI.LoadJNILibrary(str5);
        i = 1;
      }
      catch (UnsatisfiedLinkError localUnsatisfiedLinkError2)
      {
    	  localUnsatisfiedLinkError2.printStackTrace();
      }
      catch (Exception localException2)
      {
    	  localException2.printStackTrace();
      }

      if (i != 0)
      {
        System.out.println(String.format("INFORMATION: Loading OK using installation path[%s]", new Object[] { str5 }));
      }
    }

    if (i == 0)
    {
      System.out.println(String.format("ERROR: Loading failed using installation path[%s] and environment path", new Object[] { str5 }));
      return;
    }

    String[] arrayOfString = new String[11];
    arrayOfString[0] = "Administrator";
    arrayOfString[1] = "";
    arrayOfString[2] = "";
    arrayOfString[3] = "";
    arrayOfString[4] = "";
    arrayOfString[5] = "";
    arrayOfString[6] = "";
    arrayOfString[7] = "";
    arrayOfString[8] = "";
    arrayOfString[9] = "";
    arrayOfString[10] = "";

    if (paramArrayOfString.length == 2)
    {
      String str7 = paramArrayOfString[0];
      String str8 = paramArrayOfString[1];
      arrayOfString[0] = str7;
      arrayOfString[2] = str8;
      localCRYPTOCardAPI.Authenticate(arrayOfString);

      if (Integer.parseInt(arrayOfString[7]) == AUTH_SUCCESS)
      {
        System.out.println(String.format("Authentication Test OK", new Object[0]));
      }
      else if (Integer.parseInt(arrayOfString[7]) == AUTH_FAILURE)
      {
        System.out.println(String.format("Authentication FAILED", new Object[0]));
      }
      else
      {
        System.out.println("Returned Challenge:");
        System.out.print(arrayOfString[3]);

        System.out.println("Returned State:");
        System.out.print(arrayOfString[4]);

        System.out.println("Returned Challenge Data:");
        System.out.print(arrayOfString[5]);

        System.out.println("Returned User Message appended with Challenge:");
        System.out.print(arrayOfString[6]);

        System.out.println("Error:");
        System.out.print(arrayOfString[9]);
      }
    }
    else if (paramArrayOfString.length == 0)
    {
      localCRYPTOCardAPI.Authenticate(arrayOfString);
      if (arrayOfString[8].equals("1"))
      {
        System.out.println("Primary / Secondary BSID Servers down or could not be contacted. Please check configuration.");
      }
      else if ((Integer.parseInt(arrayOfString[7]) == CHALLENGE) || (Integer.parseInt(arrayOfString[7]) == AUTH_FAILURE) || (Integer.parseInt(arrayOfString[7]) == AUTH_SUCCESS) || ((Integer.parseInt(arrayOfString[7]) > 1) && (Integer.parseInt(arrayOfString[7]) < 9)))
      {
        System.out.println("Primary OR Secondary BSID Servers are OK");
      }
      else
      {
        System.out.println("FAILED to contact Primary and Secondary BSID Servers");
      }
    }
    else
    {
      System.out.println(String.format("ERROR: Invalid arguments [%i]", new Object[] { Integer.valueOf(paramArrayOfString.length) }));
      System.out.println(String.format("Authentication Usage: java - jar TestAPI.jar UserName OTP", new Object[0]));
      System.out.println(String.format("Status Check: java - jar TestAPI.jar", new Object[0]));
    }
  }
}