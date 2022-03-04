package com.everton.cashflow.util;

import com.everton.cashflow.models.constantes.Constantes;
import javafx.scene.control.TextField;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class ExtracaoDeDados {

    public static String textFieldToString(TextField textField){
        return Objects.nonNull(textField) ? textField.getText() : null;
    }

    public static int textFieldToInt(TextField textField){
        return Objects.nonNull(textField) ? Integer.parseInt(textField.getText()) : 0;
    }

    public static double textFieldToDouble(TextField textField){
        return Objects.nonNull(textField) ? Double.parseDouble(textField.getText()) : 0.0;
    }

    public static long textFieldToLong(TextField textField){
        return Objects.nonNull(textField) ? Long.parseLong(textField.getText()) : 0;
    }

    public static Properties obterPropriedades(){
        try {
            Properties properties = new Properties();
            properties.load(
                    new FileInputStream(Constantes.CONFIG_PROPERTIES));
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String obterURLBase(){
            Properties properties = obterPropriedades();
            return properties.getProperty(Constantes.PROP_URL_BASE);
    }


}
