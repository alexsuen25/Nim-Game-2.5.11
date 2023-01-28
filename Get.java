// import statements for establishing   
// connection with the API and reading data from it.  
import java.io.IOException;  
import java.io.OutputStreamWriter;  
import java.io.OutputStream;  
import java.net.URL;  
import java.io.InputStreamReader;  
import java.net.MalformedURLException;  
import java.net.HttpURLConnection;  
import java.net.ProtocolException;  
import java.io.BufferedReader; 

public class Get   
{  
  
public static void GETRequest() throws IOException   
{  
String urlName = "http://43.231.232.159";  
URL urlForGetReq = new URL(urlName);  
String read = null;  
HttpURLConnection connection = (HttpURLConnection) urlForGetReq.openConnection();  
connection.setRequestMethod("GET");  
connection.setRequestProperty("userId", "abcdf"); // set userId its a sample here  
int codeResponse = connection.getResponseCode();  
// checking whether the connection has been established or not  
if (codeResponse == HttpURLConnection.HTTP_OK)   
{  
// reading the response from the server  
InputStreamReader isrObj = new InputStreamReader(connection.getInputStream());  
BufferedReader bf = new BufferedReader(isrObj);  
// to store the response from the servers  
StringBuffer responseStr = new StringBuffer();  
while ((read = bf .readLine()) != null)  
{  
    responseStr.append(read);  
}   
// closing the BufferedReader  
bf.close();  
// disconnecting the connection  
connection.disconnect();  
// print the response  
String response_to_str = responseStr.toString();
response_to_str = response_to_str.replace("}", "");
response_to_str = response_to_str.replace("{", "");
// print the response  
String[] pairs = response_to_str.split(",");
for (int i=0;i<pairs.length;i++) {
    String pair = pairs[i];
    String[] keyValue = pair.split(":");
    if (i == 0){
        Board.pile_size = Integer.parseInt(keyValue[1]);
    }
    else if (i==1){
        Board.players = Integer.parseInt(keyValue[1]);
    } 
    else{
      Board.state = Integer.parseInt(keyValue[1]);
    }
}
//System.out.println("JSON String Result is: \n" + responseStr.toString());  

}   
else   
{  
    System.out.println("GET Request did not work");  
}     
}  
}  