package com.ubs.opsit.interviews.stub;

import com.ubs.opsit.interviews.TimeConverter;

public class TimeConverterImplStub implements TimeConverter {

    @Override
    public String convertTime(String aTime) {

        String nl = System.getProperty("line.separator");
        
        switch (aTime) {
            case "00:00:00":
                return "Y" + nl
                        + "OOOO" + nl
                        + "OOOO" + nl
                        + "OOOOOOOOOOO" + nl
                        + "OOOO";
            case "13:17:01":
                return "O" + nl
                        + "RROO" + nl
                        + "RRRO" + nl
                        + "YYROOOOOOOO" + nl
                        + "YYOO";
            case "23:59:59":
                return "O" + nl
                        + "RRRR" + nl
                        + "RRRO" + nl
                        + "YYRYYRYYRYY" + nl
                        + "YYYY";
            case "24:00:00":
                return "Y" + nl
                        + "RRRR" + nl
                        + "RRRR" + nl
                        + "OOOOOOOOOOO" + nl
                        + "OOOO";
            default:
                return "";
        }

    }

}
