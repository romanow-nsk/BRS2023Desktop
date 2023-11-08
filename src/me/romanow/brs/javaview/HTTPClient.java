
package me.romanow.brs.javaview;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class HTTPClient {
    public static void main(String argv[]){
        sendMedia("http://romanow-eugene.narod.ru", "f:\\xxx.jpg", "fhoto" );
        }
    public static void sendMedia(String urlAddr, String filePath, String description ){
          final String serverAddress = urlAddr;
          final String photoDescr = description;
          final File file = new File(filePath);
          Runnable r = new Runnable(){
               public void run(){
                       String requestString = serverAddress;
                       String resultString = new String("");
                       String descrString = photoDescr;
                       StringBuffer requestBody = new StringBuffer();
                       try {

                               URLConnection connection = null;
                               URL url = new URL(requestString);

                               connection = url.openConnection( );

                             /////////////////////////////

                             String BOUNDRY = "ccf8111910сс";

                             HttpURLConnection httpConnection = (HttpURLConnection)connection;
                             httpConnection.setRequestMethod("POST");

                             httpConnection.setUseCaches(false);

                             httpConnection.setDoOutput(true);
                             httpConnection.setDoInput(true);

                             httpConnection.setRequestProperty("User-Agent", "MyAndroid/1.6");
                             httpConnection.setRequestProperty("Content-Language", "ru-RU");
                             httpConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDRY);
                             httpConnection.setRequestProperty("Connection", "Keep-Alive");

                             
                             // собрать буфер для отправки запроса
                            String contentDisposition = "Content-Disposition: form-data; name=\"mediafile\"; filename=\"" + file.getName() + "\"";
                            String contentType = "Content-Type: application/octet-stream\nContent-Transfer-Encoding: binary";
                            
                            requestBody.append("--");
                            requestBody.append(BOUNDRY);
                            requestBody.append('\n');

                            requestBody.append("Content-Disposition: form-data; name='description'\n\n");
                            requestBody.append( descrString + "\n" );
                            requestBody.append("--");
                            requestBody.append(BOUNDRY);
                            requestBody.append('\n');

                     
                            requestBody.append(contentDisposition);
                            requestBody.append('\n');
                            requestBody.append(contentType);
                            requestBody.append('\n');
                            requestBody.append('\n');
                            
                            
                            int bytesAvailable, bufferSize, bytesRead,endBlockSize;
                            byte[] buffer;
                            int maxBufferSize = 1 * 1024 * 1024;
                            FileInputStream fileInputStream = new FileInputStream(file);
                            
                            bytesAvailable = fileInputStream.available();  

                            bufferSize = Math.min(bytesAvailable, maxBufferSize);
                            buffer = new byte[bufferSize];

                             // установка Content-Length

                            endBlockSize = BOUNDRY.length()+ 6;
  
                            httpConnection.setRequestProperty("Content-Length",
                                String.valueOf(requestBody.toString().length() +
                                bufferSize +
                                endBlockSize
                            ));
                          
                             
                             // соединение
                             httpConnection.connect();

                             // отправка первой части запроса
                            DataOutputStream dataOS = new DataOutputStream(httpConnection.getOutputStream());
                            dataOS.writeBytes(requestBody.toString());

                            // отправка файла
                            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                            dataOS.write(buffer, 0, bytesRead);

                            //////////////////////////////////////////////

                            dataOS.writeBytes("\n");
                            dataOS.writeBytes("--");
                            dataOS.writeBytes(BOUNDRY);
                            dataOS.writeBytes("--");
                            dataOS.writeBytes("\n");

                            fileInputStream.close();

                            dataOS.flush();
                            dataOS.close();

                            int responseCode = httpConnection.getResponseCode();
                              if (responseCode == 200 ) { 
                                     // если все прошло нормально, получаем результат
                                     // может быть другой код, см. http://developer.android.com/reference/java/net/HttpURLConnection.html

                                     

                                     InputStream in = httpConnection.getInputStream();

                                     InputStreamReader isr = new InputStreamReader(in, "UTF-8");

                                     StringBuffer data = new StringBuffer();
                                     int c;
                                     while ((c = isr.read()) != -1){
                                         data.append((char) c);
                                     }


                                     resultString = new String (data.toString());

                               }
                               else
                               {
                                     resultString = "сервер не ответил"; 
                               }

                           
                            }
                            catch (MalformedURLException e) {
  
                                       resultString = "MalformedURLException:" + e.getMessage();
                              }
                            catch (IOException e) {
 
                                      resultString = "IOException:" + e.getMessage();
                             }
                            catch (Exception e) {
 
                                      resultString = "Exception:" + e.getMessage();
                             }

                             requestBody = null;
                             System.gc();

                           }
          };
          new Thread(r).start();
    }



}
