package org.example.gather_back_end.util.format;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import org.example.gather_back_end.promotion.dto.cost.PromotionCostRes;

public class Comma {

    public static int getWithoutComma(List<PromotionCostRes> costList, int index) {
        return costList.size() > index ? Integer.parseInt(costList.get(index).getCostWithoutComma()) : 0;
    }

    // 천 단위로 콤마를 붙인 문자열 반환
    public static String formatWithComma(int amount) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.KOREA);
        return numberFormat.format(amount);
    }
}
