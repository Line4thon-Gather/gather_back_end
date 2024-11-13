package org.example.gather_back_end.util.format;

import java.util.List;
import org.example.gather_back_end.promotion.dto.cost.PromotionCostRes;

public class Comma {

    public static int getWithoutComma(List<PromotionCostRes> costList, int index) {
        return costList.size() > index ? Integer.parseInt(costList.get(index).getCostWithoutComma()) : 0;
    }
}
