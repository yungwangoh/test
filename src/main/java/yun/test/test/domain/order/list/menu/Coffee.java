package yun.test.test.domain.order.list.menu;

import lombok.Builder;
import yun.test.test.domain.user.Money;

public final class Coffee extends Menu {

    private final String title;
    private final Money price;
    private final String description;
    private final Nutrients nutrients;
    private final MenuSize menuSize;
    private final HotAndCold hotAndCold;
    private final Caffeine caffeine;

    @Builder
    public Coffee(String title, String description, Money price, Nutrients nutrients,
                  MenuSize menuSize, HotAndCold hotAndCold, Caffeine caffeine) {

        super(title, description, price, nutrients);
        assert menuSize != null && hotAndCold != null && caffeine != null;
        this.title = title;
        this.price = price;
        this.description = description;
        this.nutrients = nutrients;
        this.menuSize = menuSize;
        this.hotAndCold = hotAndCold;
        this.caffeine = caffeine;
    }
}
