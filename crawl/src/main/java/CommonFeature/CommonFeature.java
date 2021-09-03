package CommonFeature;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class CommonFeature {

    /**
     * The function converts Vietnamese with accents into unsigned forms
     * @param value value of String
     * @return string
     */
    public static String ConvertStringToUnsignedForms(String value) {
        try{
            String text = value.replace("-","").replaceAll("\\s+"," ");
            String tempVaue = text.replaceAll(" ", "-");
            String temp = Normalizer.normalize(tempVaue, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp).replaceAll("").replace("''", "").replace('đ', 'd').replace('Đ',
                    'D').replaceAll("[^A-Za-z0-9._-]", "").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Convert json array to Array
     * @param array Json array
     * @return array list
     */
    public static List<String> ConvertToStringArray(JsonArray array) {
        if(array==null) {
            return null;
        }
        List<String> arr=new ArrayList<>();
        for(int i=0; i<array.size(); i++) {
            System.out.println(array.get(i).toString());
            arr.add(array.get(i).getAsString());
        }
        return arr;
    }
    public static void WriteLogFile(String data) throws IOException {
        Date now = new Date();
        File file = new File("C:\\Users\\Anhkhoa\\Desktop\\AutoNews\\CrawlingWeb\\src\\main\\resources\\log\\" + now);

//Create the file
        if (file.createNewFile())
        {
            System.out.println("File is created!");
        } else {
            System.out.println("File already exists.");
        }

//Write Content
        FileWriter writer = new FileWriter(file);
        writer.write(data);
        writer.close();
    }

    public static String checkImageRequire(String attr) {
        String img= "";
        Pattern patternStart = Pattern.compile("https:");
        Matcher matcherStart = patternStart.matcher(attr);

        while (matcherStart.find()) {
            img = attr.substring(matcherStart.start());
        }
        return img;
    }

    public static List<?> convertStringJsonToObject(String json,Class<?> type) {
		  List<Object> listObject = new ArrayList<>();
	        try {
	        	
	            JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();
	            for (int i = 0; i < jsonArray.size(); i++) {
	                String jsonElement = jsonArray.get(i).toString();
	                Object object =  new Gson().fromJson(jsonElement, type);
	                listObject.add(object);
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return listObject;
	}
}
