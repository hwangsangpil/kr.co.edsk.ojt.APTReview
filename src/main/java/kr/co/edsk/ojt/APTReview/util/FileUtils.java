package kr.co.edsk.ojt.APTReview.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component("fileUtils")
public class FileUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);
	
	private static final String filePath = "C:\\test\\file\\";
    
    public List<Map<String,Object>> parseInsertFileInfo(int aptReviewNo, HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
         
        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
         
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String, Object> listMap = null; 
         
        String boardIdx = String.valueOf(aptReviewNo);
        
        File file = new File(filePath);
        if(file.exists() == false){
            file.mkdirs();
        }
        
        while(iterator.hasNext()){
        	multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            if(multipartFile.isEmpty() == false){
            	LOGGER.info("------------- multipartFile.isEmpty() == false -------------");
                originalFileName = multipartFile.getOriginalFilename();
                originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                storedFileName = CommonUtils.getRandomString() + originalFileExtension;
                
                file = new File(filePath + storedFileName);
                multipartFile.transferTo(file);
                
                listMap = new HashMap<String,Object>();
                listMap.put("APT_REVIEW_NO", boardIdx);
                listMap.put("APT_REVIEW_ORIGINAL_FILE_NAME", originalFileName);
                listMap.put("APT_REVIEW_STORED_FILE_NAME", storedFileName);
                listMap.put("APT_REVIEW_SIZE", multipartFile.getSize());
                list.add(listMap);
            }
            if(multipartFile.isEmpty() == true){
            	if(originalFileName==null){
            	LOGGER.info("------------- multipartFile.isEmpty() == true -------------");
                originalFileName = multipartFile.getOriginalFilename();
                storedFileName = "No Data";
                
                listMap = new HashMap<String,Object>();
                listMap.put("APT_REVIEW_NO", boardIdx);
                listMap.put("APT_REVIEW_ORIGINAL_FILE_NAME", originalFileName);
                listMap.put("APT_REVIEW_STORED_FILE_NAME", storedFileName);
                listMap.put("APT_REVIEW_SIZE", multipartFile.getSize());
                list.add(listMap);
                break;
            	}
            }
        }
        
        return list;
    }
}
