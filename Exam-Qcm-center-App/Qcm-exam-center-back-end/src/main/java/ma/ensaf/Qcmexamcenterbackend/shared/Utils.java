package ma.ensaf.Qcmexamcenterbackend.shared;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class Utils {
    private final Random RANDOM = new SecureRandom();
    private final String alfa = "1230654789azertyuiopqsdfghjklmAZERTYUIOPQSDFGHJKLMQSDFGHJKLMWXCVBN";

    public String generateCustomId(int length)
    {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length ; i++) {
            returnValue.append(alfa.charAt(RANDOM.nextInt(alfa.length())));
        }

        return new String(returnValue);
    }
}
