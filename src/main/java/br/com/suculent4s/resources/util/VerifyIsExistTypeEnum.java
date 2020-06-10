package br.com.suculent4s.resources.util;

import java.util.List;

public class VerifyIsExistTypeEnum {

    public static boolean isExistsValueInEnum(String value, List<Enum> enumType) {
        for(Object type : enumType){
            if(type.toString().equalsIgnoreCase(value)){
                return true;
            }
        }
        return false;
    }
}
