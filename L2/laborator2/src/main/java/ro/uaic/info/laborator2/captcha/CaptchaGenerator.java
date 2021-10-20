package ro.uaic.info.laborator2.captcha;

import java.util.ArrayList;
import java.util.Random;
import org.javatuples.Pair;
import org.javatuples.Triplet;

/**
 *
 * @author stefa
 */
public class CaptchaGenerator {

    /**
     *
     */
    private final static ArrayList<Triplet<Integer, String, Integer>> captchas = new ArrayList<>();
    
    public static int getResult(int captchaId) {
        return captchas
                .stream()
                .filter((x) -> x.getValue0() == captchaId)
                .findFirst()
                .get()
                .getValue2();
    }
    
    public static Pair<Integer, String> getRandomCaptcha() {
        initCaptchaList();
        
        Random randomizer = new Random();
        Triplet<Integer, String, Integer> randomCaptcha = captchas.get(randomizer.nextInt(captchas.size()));
        
        return Pair.with(randomCaptcha.getValue0(), randomCaptcha.getValue1());
    }
    
    private static void initCaptchaList() {
        if (captchas.isEmpty()) {
            captchas.add(Triplet.with(1, "1 + 2", 3));
            captchas.add(Triplet.with(2, "7 - 3", 4));
            captchas.add(Triplet.with(3, "10 * 2", 20));
            captchas.add(Triplet.with(4, "12 / 6", 2));
        } 
    }
}
