package cz.wake.manager.utils;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class TextComponentMerger {

    private List<TextComponent> textComponentList;

    public TextComponentMerger() {
        textComponentList = new ArrayList<>();
    }

    public TextComponentMerger(BaseComponent... textComponents) {
        textComponentList = new ArrayList<>();
        for (BaseComponent textComponent : textComponents) {
            this.textComponentList.add(new TextComponent(textComponent));
        }
    }

    public TextComponent output(boolean withSpaces) {
        ListIterator<TextComponent> iterator = textComponentList.listIterator();
        TextComponent textComponent = iterator.next();
        while (iterator.hasNext()) {
            if (withSpaces) textComponent.addExtra(" ");
            textComponent.addExtra(iterator.next());
        }
        return textComponent;
    }
}
