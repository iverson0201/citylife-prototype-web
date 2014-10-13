package com.citylife.backend.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.SimpleTimeZone;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.validation.FieldError;

public class Utils {
    public static String encodeDate(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        format.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return format.format(date);
    }

    public static Date parseDate(String dateString) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        format.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return format.parse(dateString);
    }

    public static JSONObject parseJSON(Object obj) throws IllegalAccessException, JSONException {
        Field[] fields = obj.getClass().getDeclaredFields();
        JSONObject json = new JSONObject();
        for (Field field : fields) {
            field.setAccessible(true);
            String mod = Modifier.toString(field.getModifiers());

            // 跳过静态属性
            if (mod.indexOf("static") != -1) {
                continue;
            }
            json.put(field.getName(), field.get(obj));
        }
        return json;
    }

    public static String sha256Encode(String password) {
        return Encode("SHA-256", password);
    }

    private static String Encode(String code, String password) {
        MessageDigest md;
        String encode = null;
        try {
            md = MessageDigest.getInstance(code);

            md.update(password.getBytes());

            byte byteData[] = md.digest();

            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            encode = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encode;
    }

    public static String object2String(Object obj) {
        String objBody = null;
        ByteArrayOutputStream baops = null;
        ObjectOutputStream oos = null;

        try {
            baops = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baops);
            oos.writeObject(obj);
            byte[] bytes = baops.toByteArray();
            objBody = new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null)
                    oos.close();
                if (baops != null)
                    baops.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return objBody;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T string2Object
            (String objBody, Class<T> clazz)

    {
        byte[] bytes = objBody.getBytes();
        ObjectInputStream ois = null;
        T obj = null;
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            obj = (T) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            try {
                if (ois != null)
                    ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return obj;
    }

    public static String parseErrors(List<FieldError> errors) {
        StringBuffer sb = new StringBuffer();
        for (FieldError error : errors) {
            sb.append(error.getField());
            sb.append(error.getDefaultMessage());
        }
        return sb.toString();
    }
    /**
     * 生成6位随机数
     * @return
     */
    public static Integer randomNumber(){
    	Random random = new Random();    
		int x = random.nextInt(899999);    
		x += 100000; 
		return x;
    }
}
