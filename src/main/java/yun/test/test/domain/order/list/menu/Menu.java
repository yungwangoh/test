package yun.test.test.domain.order.list.menu;

import lombok.Builder;
import lombok.Getter;
import yun.test.test.domain.user.Money;

@Getter
public abstract class Menu {

    private final String title;
    private final String description;
    private final Money price;
    private final Nutrients nutrients;

    public Menu(String title, String description, Money price, Nutrients nutrients) {
        assert title != null && description != null && price != null && nutrients != null;
        this.title = title;
        this.description = description;
        this.price = price;
        this.nutrients = nutrients;
    }

}
