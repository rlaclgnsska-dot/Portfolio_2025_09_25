package com.kim.demo.util;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class Util {
	public static boolean empty(String str) {
		
		if (str == null) {
			return true;
		}
		
		return str.trim().length() == 0;
	}
	
	public static String f(String format, Object... args) {
		return String.format(format, args);
	}
	
	public static String jsHistoryBack(String msg) {
		
		if (msg == null) {
			msg = "";
		}
		
		return Util.f("""
					<script>
						const msg = '%s'.trim();
						
						if (msg.length > 0) {
							alert(msg);
						}
						
						history.back();
					</script>
				""", msg);
	}

	public static String jsReplace(String msg, String uri) {
		
		if (msg == null) {
			msg = "";
		}
		
		if (uri == null) {
			uri = "";
		}
		
		return Util.f("""
							<script>
								const msg = '%s'.trim();
								
								if (msg.length > 0) {
									alert(msg);
								}
								
								location.replace('%s');
							</script>
						""", msg, uri);
	}
	
	public static String convertDateTimeFormat(String datetimeLocal) {
	    if (datetimeLocal == null || datetimeLocal.isEmpty()) {
	        return null;
	    }
	    try {
	        LocalDateTime dateTime = LocalDateTime.parse(datetimeLocal);
	        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	    } catch (Exception e) {
	        System.out.println("날짜 형식 변환 오류: " + datetimeLocal);
	        return null;
	    }
	}



}
