package br.com.suculent4s.resources.util;

import br.com.suculent4s.domain.enums.TipoPessoaEnum;
import br.com.suculent4s.domain.enums.TipoUsuarioEnum;
import br.com.suculent4s.domain.model.TipoUsuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VerifyIsExistTypeEnum {

    public static boolean isExistsString(String value, Object object) {
        List<Enum> list = typeEnum(object);
        for(Object type : list){
            if(type.toString().equalsIgnoreCase(value)){
                return true;
            }
        }
        return false;
    }

    public static boolean isExistsEnum(Object value, Object object) {
        List<Enum> list = typeEnum(object);
        for(Object type : list){
            if(type.toString().equalsIgnoreCase(value.toString())){
                return true;
            }
        }
        return false;
    }

    private static List<Enum> typeEnum(Object typeEnum){
        List<Enum> list = new ArrayList<>();
        if(typeEnum.toString().equalsIgnoreCase(TipoUsuarioEnum.class.toString())){
            list = Arrays.asList(TipoUsuarioEnum.values());
        } else if (typeEnum.toString().equalsIgnoreCase(TipoPessoaEnum.class.toString())) {
            list = Arrays.asList(TipoPessoaEnum.values());
        }
        return list;
    }
}
