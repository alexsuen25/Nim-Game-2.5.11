// import statements for establishing   
// connection with the API and reading data from it.  
import java.io.BufferedReader;  
import java.net.HttpURLConnection;  
import java.io.IOException;  
import java.io.OutputStream;  
import java.io.InputStreamReader;  
import java.net.URL;  
import java.net.ProtocolException;  
import java.net.MalformedURLException;  
import java.io.OutputStreamWriter;  
public class Post   
{  
public static void POSTReq() throws IOException   
{  
// The message that is going to be sent to the server  
// using the POST request  
String messageContent = "{\"Pen\":" + Board.pile_size + ", \"state\":" + Board.state + ", \"players\":" + Board.players + "}";  
// Printing the message  
//System.out.println(messageContent);  
// URL of the API or Server  
String url = "http://43.231.232.159/post";  
//String url = "http://127.0.0.1:5000/post";  

URL urlObj = new URL(url);  
HttpURLConnection postCon = (HttpURLConnection) urlObj.openConnection();  
postCon.setRequestMethod("POST");  
postCon.setRequestProperty("userId", "abcdef");  
// Setting the message content type as JSON  
postCon.setRequestProperty("Content-Type", "application/json");  
postCon.setDoOutput(true);  
// for writing the message content to the server  
OutputStream osObj = postCon.getOutputStream();  
osObj.write(messageContent.getBytes());  
// closing the output stream  
osObj.flush();  
osObj.close();  
int respCode = postCon.getResponseCode();  
//System.out.println("Response from the server is: \n");  
//System.out.println("The POST Request Response Code :  " + respCode);  
//System.out.println("The POST Request Response Message : " + postCon.getResponseMessage());  
if (respCode == HttpURLConnection.HTTP_OK)   
{   
// reaching here means the connection has been established  
// By default, InputStream is attached to a keyboard.  
// Therefore, we have to direct the InputStream explicitly  
// towards the response of the server  
InputStreamReader irObj = new InputStreamReader(postCon.getInputStream());   
BufferedReader br = new BufferedReader(irObj);  
String input = null;  
StringBuffer sb = new StringBuffer();  
while ((input = br .readLine()) != null)   
{  
    sb.append(input);  
}   
br.close();  
postCon.disconnect();  
// printing the response  
//System.out.println(sb.toString());  
}   
else   
{  
// connection was not successful  
System.out.println("POST Request did not work.");  
}  
}  
}  