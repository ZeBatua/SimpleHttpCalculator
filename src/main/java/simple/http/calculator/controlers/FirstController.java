package simple.http.calculator.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/app")
public class FirstController {
    @GetMapping("/calc")
    public String makeCalc(@RequestParam(value = "first", required = false) Double first,
                           @RequestParam(value = "second", required = false) Double second,
                           @RequestParam(value = "sign", required = false) String sign,
                           Model model) {
        if (checkValues(first, second, sign)) {
            double result = calcValues(first, second, sign);
            if (sign.equals(" ")) sign = "+";
            model.addAttribute("message", first + " " + sign + " " + second + " = " + result);
            return "app/calc";
        } else {
            model.addAttribute("message", "Invalid values or sign");
            return  "app/error";
        }
    }

    private double calcValues(Double first, Double second, String sign) {
        return switch (sign) {
            case " ", "+" ->  first + second;
            case "-" -> first - second;
            case "*" -> first * second;
            case "/" -> first / second;
            default -> 0.0;
        };
    }

    private boolean checkValues(Double v1, Double v2, String sign) {
        return v1 != null && v2 != null && isSign(sign);
    }

    private boolean isSign(String sign) {
        return sign != null &&
                (sign.equals("+") || sign.equals(" ") || sign.equals("-") || sign.equals("*") || sign.equals("/"));
    }
}
