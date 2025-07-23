package me.laxuty.uselesstweaks.config.categories;

import com.moulberry.lattice.annotation.LatticeCategory;
import com.moulberry.lattice.annotation.LatticeOption;
import com.moulberry.lattice.annotation.widget.LatticeWidgetButton;
import com.moulberry.lattice.annotation.widget.LatticeWidgetDropdown;

public final class MiscellaneousCategory {

    @LatticeCategory(name = "uselesstweaks.config.miscellaneous.chat")
    public Chat chat = new Chat();
    public static class Chat {
        @LatticeOption(title = "uselesstweaks.config.miscellaneous.chat.compactInputBox", description = "!!.description")
        @LatticeWidgetButton
        public boolean compactInputBox;
    }

    @LatticeOption(title = "uselesstweaks.config.miscellaneous.hide_recipe_book", description = "!!.description")
    @LatticeWidgetButton
    public boolean hideRecipeBook;

    @LatticeOption(title = "uselesstweaks.config.miscellaneous.no_recipe_book_shifting", description = "!!.description")
    @LatticeWidgetButton
    public boolean noRecipeBookShift;

    @LatticeOption(title = "uselesstweaks.config.miscellaneous.hideOffHandSlot", description = "!!.description")
    @LatticeWidgetButton
    public boolean hideOffHandSlot;

    @LatticeOption(title = "uselesstweaks.config.miscellaneous.hide_status_effects", description = "!!.description")
    @LatticeWidgetDropdown
    public HideStatusEffects hideStatusEffects = HideStatusEffects.NONE;
    public enum HideStatusEffects {
        INVENTORY,
        HUD,
        BOTH,
        NONE;
    }
}