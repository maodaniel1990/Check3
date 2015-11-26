package lingntao.check3;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

public class DBweb {

//    private static final String URL = "http://www.dyfood.com.tw/TW/mobile/StockManageWebservice/StockManageWebservice/Service1.asmx";
//    private static final String soap_insert = "http://tempuri.org/insertCargoInfo2";
//    private static final String meth_insert = "insertCargoInfo2";
//    private static final String soap_strd = "http://tempuri.org/selectAllCargoInfor2";
//    private static final String meth_strd = "selectAllCargoInfor2";
//    private static final String soap_hello = "http://tempuri.org/Encode";
//    private static final String meth_hello = "Encode";
//    private static final String URL5 = "http://140.121.150.56/a/Service1.asmx";
//    private static final String meth_hello = "loadimage";
//    private static final String soap_hello = "http://tempuri.org/loadimage";
//    private static final String URL3 = "http://140.121.150.237/taonling/mysql/WebApplication10/WebService1.asmx";

    private static String data = "";
    static String strd[][];
    public static String photo="";
    private static final String NAMESPACE = "http://tempuri.org/" ;
    private static final String URL3 = "http://140.121.150.237/foreal/WebService1.asmx";

    private static final String meth_insert = "MySqlQueryInsert";
    private static final String soap_insert = "http://tempuri.org/MySqlQueryInsert";
    private static final String meth_strd = "getSomething";
    private static final String soap_strd = "http://tempuri.org/getSomething";
    private static final String meth_hello = "HelloWorld";
    private static final String soap_hello = "http://tempuri.org/HelloWorld";

    private static final String METHOD_NAME4 = "MySqlQuerySelect";
    private static final String SOAP_ACTION4 = "http://tempuri.org/MySqlQuerySelect";
    private static final String SOAP_ACTION6 = "http://tempuri.org/imageupload";
    private static final String METHOD_NAME6 = "imageupload";

    private static String haha = "";

    public void linkinsert (String number){
        data = number;
        Thread networkThread = new Thread() {
            public void run() {
                SoapObject rq = new SoapObject(NAMESPACE, meth_insert);
                SoapSerializationEnvelope ev = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                ev.setOutputSoapObject(rq);
                ev.dotNet = true;
                try {
                    /* request.addProperty("table2","insert into TB_Order values('4','test','test','test','0000000000','M140430184600','2014/5/1 00:00:00','2','1','0',"
                     		+ "'ntoutest','1','1','0','0','0','0','2014/5/2 00:00:00','2014/5/2 00:00:00','�W��','0','0','0','0','�q�ʤ�','0','7',"
                     		+ "'2014/5/1 00:00:00','7','2014/5/1 00:00:00');");  */
                    rq.addProperty("sql", data);
                    Log.e("debug_log",data);
                    HttpTransportSE ht = new HttpTransportSE(URL3);
                    ht.call(soap_insert, ev);
//                    SoapObject result = (SoapObject) ev.bodyIn;
//                    System.out.println(result.getProperty(0).toString());
//                    check=result.getProperty(0).toString();
                }
                catch (Exception e) {
                    e.printStackTrace();
                    SoapFault error = (SoapFault) ev.bodyIn;
                    System.out.println("Error message : "+error.toString());
                }
            }
        };
        networkThread.start();
        try {
            networkThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void linkselectdouble(String number) {
        data = number;
        Thread networkThread = new Thread() {
            public void run() {
                try {
                    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME4);
                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.setOutputSoapObject(request);
                    envelope.dotNet = true;
                    request.addProperty("sql", data);
                    HttpTransportSE ht = new HttpTransportSE(URL3);
                    ht.call(SOAP_ACTION4, envelope);
                    SoapObject result = (SoapObject) envelope.bodyIn;
                    SoapObject soapresults = (SoapObject) result.getProperty(0);
                    SoapObject diffgram = (SoapObject) soapresults.getProperty("diffgram");
                    SoapObject DocumentElement = (SoapObject) diffgram.getProperty("DocumentElement");
                    SoapObject Tov = (SoapObject) DocumentElement.getProperty(0);
                    strd = new String[DocumentElement.getPropertyCount()][Tov.getPropertyCount()];
                    for (int i = 0; i < DocumentElement.getPropertyCount(); i++) {
                        Tov = (SoapObject) DocumentElement.getProperty(i);
                        for (int j = 0; j < Tov.getPropertyCount(); j++) {
                            strd[i][j] = String.valueOf(Tov.getProperty(j));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        networkThread.start();
        try {
            networkThread.join();
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
    }



    public String wtf() {
        Thread networkThread = new Thread() {
            public void run() {
                SoapObject request = new SoapObject(NAMESPACE, meth_hello);
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.setOutputSoapObject(request);
                envelope.dotNet = true;
                try {
//                    request.addProperty("sql", haha);
                    HttpTransportSE ht = new HttpTransportSE(URL3);
                    ht.call(soap_hello, envelope);
                    SoapObject result = (SoapObject) envelope.bodyIn;
                    SoapPrimitive result2 = (SoapPrimitive)envelope.getResponse();
                    haha = result.toString() +", " + result2.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        networkThread.start();
        try {
            networkThread.join();
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
        return haha;
    }

    public void getImage (String number){
//        data = number;
//        Thread networkThread = new Thread() {
//            public void run() {
//                try {
//                    SoapObject request = new SoapObject(NAMESPACE, meth_strd);
//                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//                    envelope.setOutputSoapObject(request);
//                    envelope.dotNet = true;
//                    request.addProperty("sql",data);
//                    HttpTransportSE ht = new HttpTransportSE(URL3);
//                    ht.call(soap_strd, envelope);
//                    SoapObject result = (SoapObject) envelope.bodyIn;
//                    SoapObject soapresults = (SoapObject)result.getProperty(0);
//                    str2=new String [soapresults.getPropertyCount()];
//                    for(int i=0;i<soapresults.getPropertyCount();i++){
//                        str2[i] =soapresults.getProperty(i).toString();
//                    }
//                }
//                catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        };


        data = number;
        Thread networkThread = new Thread() {
            public void run() {
                try {
                    SoapObject request = new SoapObject(NAMESPACE, meth_strd);
                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.setOutputSoapObject(request);
                    envelope.dotNet = true;
                    request.addProperty("sql", data);
                    HttpTransportSE ht = new HttpTransportSE(URL3);
                    ht.call(soap_strd, envelope);
                    SoapObject result = (SoapObject) envelope.bodyIn;
                    SoapObject soapresults = (SoapObject) result.getProperty(0);
                    SoapObject diffgram = (SoapObject) soapresults.getProperty("diffgram");
                    SoapObject DocumentElement = (SoapObject) diffgram.getProperty("DocumentElement");
                    SoapObject Tov = (SoapObject) DocumentElement.getProperty(0);
                    strd = new String[DocumentElement.getPropertyCount()][Tov.getPropertyCount()];
                    for (int i = 0; i < DocumentElement.getPropertyCount(); i++) {
                        Tov = (SoapObject) DocumentElement.getProperty(i);
                        for (int j = 0; j < Tov.getPropertyCount(); j++) {
                            strd[i][j] = String.valueOf(Tov.getProperty(j));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        networkThread.start();
        try {
            networkThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    public void insertImage() {
//        Thread networkThread = new Thread() {
//            public void run() {
//                try {
//                    Bitmap myBitmap= BitmapFactory.decodeFile(Form.sdPath + "/" + "photo1.png");
//                    ByteArrayOutputStream baos=new ByteArrayOutputStream();
//                    myBitmap.compress(CompressFormat.JPEG,100, baos);
//                    byte [] b=baos.toByteArray();
//                    String imagebyte=Base64.encodeToString(b, Base64.DEFAULT);
//                    SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME6);
//                    SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//                    soapEnvelope.dotNet=true;
//                    soapEnvelope.setOutputSoapObject(Request);
//                    Request.addProperty("in_image",imagebyte);
//                    Request.addProperty("photoname",photo);
//                    String abc="update note set picture = @img where idnote = " + (Form.id_note -1);
////                    String abc="update photo set picture = @img where id = 3";
//                    Log.e("debug_log",abc);
//                    Request.addProperty("Sql",abc);
//                    HttpTransportSE aht=new HttpTransportSE(URL3);
//                    aht.call(SOAP_ACTION6, soapEnvelope);
//                    SoapPrimitive response = (SoapPrimitive)soapEnvelope.getResponse();
//                    Log.e("debug_log",response.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        networkThread.start();
//        try {
//            networkThread.join();
//        } catch (InterruptedException e) {
//            System.out.println(e.toString());
//        }
//    }

}


//    public static void updateImage2(final String orderid,final String code,final String picture) {
//        Thread networkThread = new Thread() {
//            public void run() {
//                try {
//                    Bitmap myBitmap= BitmapFactory.decodeFile("mnt/sdcard/test321/"+picture+".jpeg");
//                    ByteArrayOutputStream baos=new ByteArrayOutputStream();
//                    myBitmap.compress(CompressFormat.JPEG,100, baos);
//                    byte [] b=baos.toByteArray();
//                    String imagebyte=Base64.encodeToString(b, Base64.DEFAULT);
//                    SoapObject Request = new SoapObject(NAMESPACE5, meth_hello);
//                    SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//                    soapEnvelope.dotNet=true;
//                    soapEnvelope.setOutputSoapObject(Request);
//                    Request.addProperty("in_image",imagebyte);
//                    Request.addProperty("photoname",photo);
//                    Request.addProperty("Sql","update TB_OrderProduct set Cancel = '1' where OrderID = '"+  orderid +"' and ProductCode = '"+code+"';"
//                            + "update TB_OrderProduct set image = @image where OrderID = '"+ orderid +"' and ProductCode = '"+code+"'");
//                    HttpTransportSE aht=new HttpTransportSE(URL5);
//                    try{
//                        Log.e("debug_log","3");
//                        aht.call(soap_hello, soapEnvelope);
//                        SoapPrimitive response = (SoapPrimitive)soapEnvelope.getResponse();
//                        System.out.println("Message:"+response.toString());
//                        TakeActivity.checkupload=response.toString();
//                        Log.e("debug_log",response.toString());
//                    }
//                    catch(Exception e)
//                    {
//                        e.printStackTrace();
//                        System.out.println(e.toString());
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    TakeActivity.checkupload="fail";
//                }
//            }
//        };
//        networkThread.start();
//        try {
//         } catch (Exception e) {
//        }
//    }


//    public static  String linkencode (String number,final Integer method)
//    {
//        data = number;
//        Thread networkThread = new Thread() {
//            public void run() {
//                try {
//                    SoapObject request = new SoapObject(NAMESPACE, meth_hello);
//                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//                    envelope.setOutputSoapObject(request);
//                    envelope.dotNet = true;
//                    request.addProperty("data",data);
//                    HttpTransportSE ht = new HttpTransportSE(URL);
//                    ht.call(soap_hello, envelope);
//                    SoapObject result = (SoapObject) envelope.bodyIn;
//                    System.out.println(data);
//                    System.out.println(result.getProperty(0).toString());
//                    switch (method) {
//                        case 0:
//                            str2=result.getProperty(0).toString();
//                            break;
//                    }
//                    System.out.println(str2);
//                }
//                catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        networkThread.start();
//        try {
//            networkThread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return str2;
//    }


//==========================================test hello//==========================================
//private static String haha = "";
//private static final String meth_hello = "HelloWorld";
//private static final String soap_hello = "http://tempuri.org/HelloWorld";

//    public String wtf() {
//        Thread networkThread = new Thread() {
//            public void run() {
//                try {
//                    SoapObject request = new SoapObject(NAMESPACE, meth_hello);
//                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//                    envelope.setOutputSoapObject(request);
//                    envelope.dotNet = true;
//                    request.addProperty("sql", haha);
//                    HttpTransportSE ht = new HttpTransportSE(URL3);
//                    ht.call(soap_hello, envelope);
//                    SoapObject result = (SoapObject) envelope.bodyIn;
//                    SoapPrimitive result2 = (SoapPrimitive)envelope.getResponse();
//                    haha = result.toString() +", " + result2.toString();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        networkThread.start();
//        try {
//            networkThread.join();
//        } catch (InterruptedException e) {
//            System.out.println(e.toString());
//        }
//        return haha;
//    }


//    public void linkselect (String number){
//        data = number;
//        Thread networkThread = new Thread() {
//            public void run() {
//                try {
//                    SoapObject request = new SoapObject(NAMESPACE, meth_strd);
//                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//                    envelope.setOutputSoapObject(request);
//                    envelope.dotNet = true;
//                    request.addProperty("sql",data);
//                    HttpTransportSE ht = new HttpTransportSE(URL3);
//                    ht.call(soap_strd, envelope);
//                    SoapObject result = (SoapObject) envelope.bodyIn;
//                    SoapObject soapresults = (SoapObject)result.getProperty(0);
//                    str=new String [soapresults.getPropertyCount()];
//                    for(int i=0;i<soapresults.getPropertyCount();i++){
//                        str[i] =((String) soapresults.getProperty(i));
//                    }
//                }
//                catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        networkThread.start();
//        try {
//            networkThread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }