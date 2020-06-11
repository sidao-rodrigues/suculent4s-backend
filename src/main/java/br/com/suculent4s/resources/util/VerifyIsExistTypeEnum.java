package br.com.suculent4s.resources.util;

import java.util.List;

public class VerifyIsExistTypeEnum {

    public static boolean isExistsString(String value, List<Enum> enumType) {
        for(Object type : enumType){
            if(type.toString().equalsIgnoreCase(value)){
                return true;
            }
        }
        return false;
    }

    public static boolean isExistsEnum(Object value, List<Enum> enumType) {
        for(Object type : enumType){
            if(type.toString().equalsIgnoreCase(value.toString())){
                return true;
            }
        }
        return false;
    }
}
