package test;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;

public class ApiExplorer {
	
	public static void main(String[] args) throws InterruptedException {
		
		int totalCount = getTotalCount();
		int start = 1;
		int end = 1000;
		
		while(end <= totalCount) {
			
			getWifiList(String.valueOf(start), String.valueOf(end));
			
			start += 1000;
			end += 1000;
		}
		
		getWifiList(String.valueOf(end-1000+1), String.valueOf(totalCount));
		
	}
	
	public static void getWifiList(String start, String end) {
		
		try {
			
		
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/507a7344666b696d34394c6d4c4f68/json/TbPublicWifiInfo");
		urlBuilder.append("/" + URLEncoder.encode(start, "UTF-8"));
		urlBuilder.append("/" + URLEncoder.encode(end,"UTF-8"));
		
		/*
		 * url을 여러개 만들어서 하는게 아닌가보다
		int totalCallNum = totalCount / 500; // 총 api 호출 횟수 구함	
		for(int i = 0; i < totalCallNum - 2; i++) {
			urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/507a7344666b696d34394c6d4c4f68/json/TbPublicWifiInfo");
			
			urlBuilder.append("/" + URLEncoder.encode(start + 500,"UTF-8"));
			urlBuilder.append("/" + URLEncoder.encode(end + 500,"UTF-8"));
		}
		
		// urlBuilder => url
		// 500 개씩 DB에 데이터 INSERT하기
		for(int i = 0; i < 1; i++) {
			urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/507a7344666b696d34394c6d4c4f68/json/TbPublicWifiInfo");
			
			urlBuilder.append("/" + URLEncoder.encode(start + 500,"UTF-8"));
			urlBuilder.append("/" + URLEncoder.encode(String.valueOf(totalCount),"UTF-8"));
		}
		*/
		
		// OkHttp 클라이언트 객체 생성  
		OkHttpClient client = new OkHttpClient();

		// REST API 서버로 전송할 GET 요청 객체 생성, Request.Builder의 get() 메소드를 이용해서 get요청을 위한 빌드 작업임
		Request.Builder builder = new Request.Builder().url(urlBuilder.toString()).get();
		Request request = builder.build(); // build() 메소드를 통해 Request 객체 생성
		
		// OkHttp 클라이언트로 GET 요청 객체 전송, 만들어진 객체를 newCall() 메소드를 이용해서 REST API 서버로 전송함,
		// execute()를 꼮 호출해서 서버로 객체를 전송시켜야 한다, 이 메소드는 Response 객체를 반환한다,
		// Response 객체에는 사용자가 던진 get 요청의 처리 결과에 대한 정보가 들어있다
        Response response = client.newCall(request).execute();
        
        if (response.isSuccessful()) {
        	//응답 받아서 처리
            ResponseBody body = response.body();
            
        	// map처럼 꺼내 쓸 수 있또록 
        	//String body = response.body().toString();
        	
            if (body != null) {
            	String result = body.string(); // string()은 한번만 호출 가능함 => 변수로 쓰면 괜찮
//                System.out.println(result); // 변수를 사용하면 에러 안 뜸
                
            	JSONParser parser = new JSONParser();
        		JSONObject obj = (JSONObject)parser.parse(result);  // 변수를 사용하면 에러 안 뜸
        		JSONObject TbPublicWifiInfo = (JSONObject)obj.get("TbPublicWifiInfo");
        		
        		// 숫자는 JSONObject로 출력이 안돼나.. => 형 변환 해줘야 함!! parseInt 대신 valueOf도 가능!!! => 데이터 전체 개수
        		// 전체 갯수
//        		int count = Integer.parseInt(TbPublicWifiInfo.get("list_total_count").toString());
        		
//        		System.out.println(count);
        		
        		// row 배열의 데이터
        		JSONArray row = (JSONArray)TbPublicWifiInfo.get("row");
        		
        		DBcon dbCon = new DBcon();
        		Wifi wifi = new Wifi();
        		
        		for(int i = 0; i <= Integer.parseInt(end)-Integer.parseInt(start); i++){
            		
        			JSONObject tmp = (JSONObject)row.get(i);
        			String X_SWIFI_MGR_NO = (String)tmp.get("X_SWIFI_MGR_NO");
        			String X_SWIFI_WRDOFC = (String)tmp.get("X_SWIFI_WRDOFC");
        			String X_SWIFI_MAIN_NM = (String)tmp.get("X_SWIFI_MAIN_NM");
        			String X_SWIFI_ADRES1 = (String)tmp.get("X_SWIFI_ADRES1");
        			String X_SWIFI_ADRES2 = (String)tmp.get("X_SWIFI_ADRES2");
        			String X_SWIFI_INSTL_FLOOR = (String)tmp.get("X_SWIFI_INSTL_FLOOR");
        			String X_SWIFI_INSTL_TY = (String)tmp.get("X_SWIFI_INSTL_TY");
        			String X_SWIFI_INSTL_MBY = (String)tmp.get("X_SWIFI_INSTL_MBY");
        			String X_SWIFI_SVC_SE = (String)tmp.get("X_SWIFI_SVC_SE");
        			String X_SWIFI_CMCWR = (String)tmp.get("X_SWIFI_CMCWR");
        			String X_SWIFI_CNSTC_YEAR = (String)tmp.get("X_SWIFI_CNSTC_YEAR");
        			String X_SWIFI_INOUT_DOOR = (String)tmp.get("X_SWIFI_INOUT_DOOR");
        			String X_SWIFI_REMARS3 = (String)tmp.get("X_SWIFI_REMARS3");
        			String LAT = (String)tmp.get("LAT");
        			String LNT = (String)tmp.get("LNT");
        			String WORK_DTTM = (String)tmp.get("WORK_DTTM");
        			//String distance = (String)tmp.get("distance");
					
					 wifi.setX_SWIFI_MGR_NO(X_SWIFI_MGR_NO);
					 wifi.setX_SWIFI_WRDOFC(X_SWIFI_WRDOFC);
					 wifi.setX_SWIFI_MAIN_NM(X_SWIFI_MAIN_NM);
					 wifi.setX_SWIFI_ADRES1(X_SWIFI_ADRES1);
					 wifi.setX_SWIFI_ADRES2(X_SWIFI_ADRES2);
					 wifi.setX_SWIFI_INSTL_FLOOR(X_SWIFI_INSTL_FLOOR);
					 wifi.setX_SWIFI_INSTL_TY(X_SWIFI_INSTL_TY);
					 wifi.setX_SWIFI_INSTL_MBY(X_SWIFI_INSTL_MBY);
					 wifi.setX_SWIFI_SVC_SE(X_SWIFI_SVC_SE); wifi.setX_SWIFI_CMCWR(X_SWIFI_CMCWR);
					 wifi.setX_SWIFI_CNSTC_YEAR(X_SWIFI_CNSTC_YEAR);
					 wifi.setX_SWIFI_INOUT_DOOR(X_SWIFI_INOUT_DOOR);
					 wifi.setX_SWIFI_REMARS3(X_SWIFI_REMARS3); 
					 wifi.setLAT(LAT); 
					 wifi.setLNT(LNT);
					 wifi.setWORK_DTTM(WORK_DTTM); 
					 //wifi.setDistance(distance);
					 
					 //System.out.println(wifi.toString());
					 
					 dbCon.register(wifi);	// 데이터를 insert하는 부분
            	
					/* 다 제대로 나오는 것 확인했음!
					 * System.out.println("----- "+(i+1)+"번째 검색 결과 -----");
					 * System.out.println(X_SWIFI_MGR_NO); System.out.println(X_SWIFI_WRDOFC);
					 * System.out.println(X_SWIFI_MAIN_NM); System.out.println(X_SWIFI_ADRES1);
					 * System.out.println(X_SWIFI_ADRES2); System.out.println(X_SWIFI_INSTL_FLOOR);
					 * System.out.println(X_SWIFI_INSTL_TY); System.out.println(X_SWIFI_INSTL_MBY);
					 * System.out.println(X_SWIFI_SVC_SE); System.out.println(X_SWIFI_CMCWR);
					 * System.out.println(X_SWIFI_CNSTC_YEAR);
					 * System.out.println(X_SWIFI_INOUT_DOOR); System.out.println(X_SWIFI_REMARS3);
					 * System.out.println(LAT); System.out.println(LNT);
					 * System.out.println(WORK_DTTM);
					 */
					 	 
        		}
        		
        		/*
        		 * 재귀함수 식으로 부르는 건 안되나보네
        		
        		for(int i = 0; i < totalCallNum - 1; i++) {
        			getWifiList(String.valueOf(start) + 500, String.valueOf(end) + 500, getTotalCount());
        		}
        		for(int i = 0; i < 1; i++) {
        			getWifiList(String.valueOf(start) + 500, String.valueOf(getTotalCount()), getTotalCount());
        		}
        		*/
    			
        		/*
        		if(Integer.parseInt(start) + 500 < totalCount) {
        			getWifiList(String.valueOf(start) + 500, String.valueOf(end) + 500, getTotalCount());
        			
        		} else if(Integer.parseInt(start) + 500 >= totalCount){
        			getWifiList(String.valueOf(start), String.valueOf(getTotalCount()), getTotalCount());
    			}
    			*/
        		
            }
            }
        
        else
            System.err.println("Error Occurred");
 
        return;
        
    	} catch(Exception e) {
        e.printStackTrace();

    	}
	}
	
	public static int getTotalCount() {
		try {
			// urlBuilder => url
			// 500 개씩 DB에 데이터 INSERT하기
			String urlBuilder = new String("http://openapi.seoul.go.kr:8088/507a7344666b696d34394c6d4c4f68/json/TbPublicWifiInfo/1/5");
			
			// OkHttp 클라이언트 객체 생성  
			OkHttpClient client = new OkHttpClient();

			// REST API 서버로 전송할 GET 요청 객체 생성, Request.Builder의 get() 메소드를 이용해서 get요청을 위한 빌드 작업임
			Request.Builder builder = new Request.Builder().url(urlBuilder).get();
			Request request = builder.build(); // build() 메소드를 통해 Request 객체 생성
			
			// OkHttp 클라이언트로 GET 요청 객체 전송, 만들어진 객체를 newCall() 메소드를 이용해서 REST API 서버로 전송함,
			// execute()를 꼮 호출해서 서버로 객체를 전송시켜야 한다, 이 메소드는 Response 객체를 반환한다,
			// Response 객체에는 사용자가 던진 get 요청의 처리 결과에 대한 정보가 들어있다
	        Response response = client.newCall(request).execute();
	        
	        if (response.isSuccessful()) {
	        	//응답 받아서 처리
	            ResponseBody body = response.body();
	            
	        	// map처럼 꺼내 쓸 수 있또록 
	        	//String body = response.body().toString();
	        	
	            if (body != null) {
	            	String result = body.string(); // string()은 한번만 호출 가능함 => 변수로 쓰면 괜찮
	                
	            	JSONParser parser = new JSONParser();
	        		JSONObject obj = (JSONObject)parser.parse(result);  // 변수를 사용하면 에러 안 뜸
	        		JSONObject TbPublicWifiInfo = (JSONObject)obj.get("TbPublicWifiInfo");
	        		
	        		// 숫자는 JSONObject로 출력이 안돼나.. => 형 변환 해줘야 함!! parseInt 대신 valueOf도 가능!!! => 데이터 전체 개수
	        		// 전체 갯수
	        		int count = Integer.parseInt(TbPublicWifiInfo.get("list_total_count").toString());
	        		return count;
	        	}
	        } else {
	        	System.err.println("Error Occurred");
	        }
	        
	    } catch(Exception e) {    
	    	e.printStackTrace();
    	}
		return 0 ;
	}
}
	