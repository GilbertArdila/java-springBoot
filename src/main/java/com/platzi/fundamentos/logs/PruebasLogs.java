package com.platzi.fundamentos.logs;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog

public class PruebasLogs {

    public static void main(String[] args) {
        try {
            int value = 10/0;

        }catch (Exception e){
            log.error("Error en esta clase");
            log.debug("debug");
            log.info("Este método hace una división");
        }
    }
}
