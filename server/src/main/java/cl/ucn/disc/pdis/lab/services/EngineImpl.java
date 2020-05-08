/*
 * Copyright (c) 2020 Diego Urrutia-Astorga. http://durrutia.cl.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which accompanies
 * this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package cl.ucn.disc.pdis.lab.services;

import cl.ucn.disc.pdis.lab.zeroice.model.Engine;
import com.zeroc.Ice.Current;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The implementation of {@link cl.ucn.disc.pdis.lab.zeroice.model.Engine}.
 *
 * @author Diego Urrutia-Astorga.
 */
public final class EngineImpl implements Engine {

    /**
     * @see Engine#getDate(Current)
     */
    @Override
    public String getDate(Current current) {
        return ZonedDateTime.now().format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    public  boolean validateRut(String rut, Current current ) {

            Pattern pattern = Pattern.compile("^[0-9]+-[0-9kK]{1}$");
            Matcher matcher = pattern.matcher(rut);
            if (matcher.matches() == false) return false;
            String[] stringRut = rut.split("-");
            return true;
    }

    public boolean getDigitoVerificador(String rut,Current current){

        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));
            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {

                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }
}
