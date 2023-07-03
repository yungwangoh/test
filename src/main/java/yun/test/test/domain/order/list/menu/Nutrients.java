package yun.test.test.domain.order.list.menu;

import lombok.Builder;

public final class Nutrients {

    private final int kcal;
    private final int carbohydrates;
    private final int fats;
    private final int proteins;

    @Builder
    public Nutrients(int kcal, int carbohydrates, int fats, int proteins) {
        assert kcal >= 0 && carbohydrates >= 0 && fats >= 0 && proteins >= 0;
        this.kcal = kcal;
        this.carbohydrates = carbohydrates;
        this.fats = fats;
        this.proteins = proteins;
    }
}
