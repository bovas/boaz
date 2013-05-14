/*
/*
 * CRYPTOCard Java API Sample Project
 * Before running this project, add BSIDJavaAPI.jar to your project
 * BSIDJavaAPI use libJCryptoWrapper.so on Linux and JCryptoWrapperWin.dll
 * on Windows to communicate with BSID. Management is via JCryptoWrapperWin.ini on Windows and
 * JCryptoWrapper.ini on Linux.
 * INI file must be in the same folder where respective dynamic library is
 */
package com.mycare.actions.test;

import CRYPTOCard.API.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.*;

/**
 *
 * @author kmushtaq
 */
public class CryptoMain
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

    public static void main(String[] args)
    {
        try
        {
            /*
             * If the library is not in the PATH environment variable, please correct these paths.
             * On windows, the environment variable is set by the API Manager.
             */
            String LinuxLibPath = "/usr/local/cryptocard/javaapi/bin/x64/libJCryptoWrapper.so";
            String WindowsLibPath = "C:/Program Files/CRYPTOCard/BlackShield ID/JavaAPI/bin/x86/JCryptoWrapperWin.dll";
            String osLoadLib;
            System.out.println(System.getProperty("os.arch"));
            String osname = System.getProperty("os.name");
            if (osname.toLowerCase().contains("windows"))
            {
                osLoadLib = WindowsLibPath;
            }
            else
            {
                osLoadLib = LinuxLibPath;
            }

            CRYPTOCardAPI cryptocardApi = CRYPTOCardAPI.getInstance();

            System.out.println("Instance Creation OK");

            //Load libraries from Path set by API Manager
            //cryptocardApi.LoadJNILibrary();

            // Use this line if the path is not set by API Manager
            // This must be only used if the line above fails.
            cryptocardApi.LoadJNILibrary(osLoadLib);

            //no error thrown means initialization is OK
            System.out.println("Loading and Initialization OK");

            /*
            jstring UserName;
            jstring Organization;
            jstring OTP;
            jstring Challenge;
            jstring State;
            jstring ChallengeData;
            jstring ChallengeMessage;
            jstring ReturndResult;
            jstring BothServersDown;
            jstring ErrorMessage;
            jstring IP;
             */


            String[] arrData = new String[11];

            arrData[0] = "Administrator";               //username input parameter
            arrData[1] = "";                            //organization input parameter
            arrData[2] = "";                            //Pin + OTP input parameter
            arrData[3] = "";                            //Challenge output parameter
            arrData[4] = "";                            //State in / output parameter
            arrData[5] = "";                            //ChallengeData output parameter
            arrData[6] = "";                            //ChallengeMessage output parameter
            arrData[7] = "";                            //ReturndResult output parameter - Normal BSID return results 0 to 8
            arrData[8] = "";                            //BothServersDown output parameter - 1 or 0 (1 if down)
            arrData[9] = "";                            //ErrorMessage output parameter - Error Message for Log or client
            arrData[10] = "127.0.0.1";                  //Client IP Address input parameter

            cryptocardApi.Authenticate(arrData);

            if (Integer.parseInt(arrData[7]) == AUTH_SUCCESS)
            {
                System.out.println(String.format("Authentication Test OK"));
            }
            else if (Integer.parseInt(arrData[7]) == AUTH_FAILURE)
            {
                System.out.println(String.format("Authentication FAILED"));
            }
            else if (Integer.parseInt(arrData[7]) == CHALLENGE)
            {
                System.out.println("Authentication Challenge");
                System.out.println("Returned Challenge:");
                System.out.println(arrData[3]);

                System.out.println("Returned State:");
                System.out.println(arrData[4]);

                System.out.println("Returned Challenge Data:");
                System.out.println(arrData[5]);

                // if GrIDsure Support is Available to BSID users for Java API arrData[5] will return String "GrIDsure"
                // along with minimum 25 characters long Challenge
                // Pass this challenge to get back a Grid Image (BufferedImage) and GrIDsure Logo Image
                if(arrData[5].toLowerCase().equals("gridsure") && arrData[3].length()>24)
                {
                    try
                    {
                        //Its a GrIDsure user and BSID has returned a challenge
                        //Lets create a grid from Challenge
                        BufferedImage gridImage = cryptocardApi.getGridSureGrid(arrData[3]);

                        //this call will return GrIDsure Logo which must be displayed under the Grid
                        BufferedImage gridsureLogo = cryptocardApi.getGridSureLogo();

                        // Now we have both Images as Buffered Data we can use them as we want.
                        // Please remember, you have to maintain the state and it should be returned along with
                        // the characters entered by user corresponding to their pattern.
                    }
                    catch(Exception ex)
                    {
                        System.out.println(ex);
                    }

                }








                //challenge thrown, now next call must accompany returned state from last call
                arrData[0] = "Administrator";               //username input parameter
                arrData[1] = "";                            //organization input parameter
                arrData[2] = "SamplePassword";                      //new PIN, New Static Password or Next Pin+OTP - input parameter
                arrData[3] = "";                            //Challenge output parameter
                //arrData[4] = "Returned State";              //State in / output parameter
                arrData[5] = "";                            //ChallengeData output parameter
                arrData[6] = "";                            //ChallengeMessage output parameter
                arrData[7] = "";                            //ReturndResult output parameter - Normal BSID return results 0 to 8
                arrData[8] = "";                            //BothServersDown output parameter - 1 or 0 (1 if down)
                arrData[9] = "";                            //ErrorMessage output parameter - Error Message for Log or client
                arrData[10] = "127.0.0.1";                  //Client IP Address input parameter

                //call again to get authentication request response
                cryptocardApi.Authenticate(arrData);

                if (Integer.parseInt(arrData[7]) == AUTH_SUCCESS)
                {
                    System.out.println(String.format("Challenge / Response Authentication Test OK"));
                }
                else if (Integer.parseInt(arrData[7]) == AUTH_FAILURE)
                {
                    System.out.println(String.format("Challenge / Response Authentication FAILED"));
                }

            }
            else
            {
                //Must handle following cases the way challenge response is handled above:
                    /*
                static int USER_PIN_CHANGE = 4;
                static int OUTER_WINDOW_AUTH = 5;
                static int CHANGE_STATIC_PASSWORD = 6;
                static int STATIC_CHANGE_FAILED = 7;
                static int PIN_CHANGE_FAILED = 8;
                 */

                System.out.println("Returned Code:");
                System.out.println(arrData[7]);

            }














            // Testing Signature Verification
            /*
            arrData (Strings Array) Details:
            These should be passed only to Verify Signatures
            SerialNumber        = 0                 In Value
            Hash                = 1                 In Value
            Signature           = 2                 In Value
            ReturndResult       = 3                 Return Value -> Result (1 Success 0 Failure)
            */

            arrData = new String[4];
            arrData[0] = "121212121212";               //SerialNumber input parameter
            arrData[1] = "xsxsxsxsxsxsxsxsx";          //Hash input parameter
            arrData[2] = "dsfcdsfdfsdfsf";             //Signature input parameter
            arrData[3] = "";                           //ReturndResult output parameter

            //lets verify signatures
            cryptocardApi.VerifySignature(arrData);

            if (Integer.parseInt(arrData[3]) == AUTH_SUCCESS)
            {
                System.out.println(String.format("Signature Verification OK"));
            }
            else if (Integer.parseInt(arrData[3]) == AUTH_FAILURE)
            {
                System.out.println(String.format("Signature Verification FAILED"));
            }

        }
        catch (UnsatisfiedLinkError ex1)
        {
            System.out.println(ex1.getMessage());
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}