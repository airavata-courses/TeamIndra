package edu.iu.indra.scigw.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
    
    @RequestMapping(value="/upload", method= RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }
    
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file){
//    	System.out.println("File type : "+file.getContentType());
        if (!file.isEmpty()	) {
            try {
            	System.out.println("Coming here");
            	File fname = new File("C:\\Users\\Pratish\\Documents\\Assignments\\SG\\WebService\\Test Files\\newinputfile.txt");
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = 
                        new BufferedOutputStream(new FileOutputStream(fname));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded ConfigFile !";
            } catch (Exception e) {
                return "You failed to upload ConfigFile => " + e.getMessage();
            }
        } else {
            return "You failed to upload Config because the file was empty.";
        }
    }

}
