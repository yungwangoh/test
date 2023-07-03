package yun.test.test.domain.order.list.menu;

import lombok.Builder;
import yun.test.test.domain.user.Money;

public final class Beverage extends Menu {

    private final String title;
    private final Money price;
    private final String description;
    private final Nutrients nutrients;
    private final MenuSize menuSize;

    @Builder
    public Beverage(String title, String description, Money price, Nutrients nutrients, MenuSize menuSize) {
        super(title, description, price, nutrients);
        assert menuSize != null;
        this.title = title;
        this.price = price;
        this.description = description;
        this.nutrients = nutrients;
        this.menuSize = menuSize;
    }
}
