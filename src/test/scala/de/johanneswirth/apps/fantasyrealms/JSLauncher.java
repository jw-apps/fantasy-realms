package de.johanneswirth.apps.fantasyrealms;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import javax.script.ScriptException;
import java.io.IOException;
import java.util.List;

public class JSLauncher {

    private Context context;

    public void init() throws IOException {
        Context cx = Context.newBuilder("js").allowIO(true).build();
        cx.eval(Source.newBuilder("js", JSLauncher.class.getResource("/deck.js")).build());
        cx.eval(Source.newBuilder("js", JSLauncher.class.getResource("/discard.js")).build());
        cx.eval(Source.newBuilder("js", JSLauncher.class.getResource("/hand.js")).build());
        cx.eval(Source.newBuilder("js", JSLauncher.class.getResource("/score.js")).build());
        context = cx;
    }

    public void clearHand() {
        Value clear = context.eval("js", "(function() {clear()})");
        clear.executeVoid();
    }

    public void addCard(String id) {
        Value add = context.eval("js", "(function(card) {add(card)})");
        add.executeVoid(id);
    }

    public Integer getJSScore() {
        Value score = context.eval("js", "(function() {return getScore()})");
        return score.execute().asInt();
    }

    public void actionBookOfChanges(String card, String suit) {
        context.eval("js", "(function (card, suit) {getCardById('FR49').performCardAction([card,suit])})").executeVoid(card, suit);
    }

    public void actionIsland(String card) {
        context.eval("js", "(function (card) {getCardById('FR09').performCardAction([card])})").executeVoid();
    }

    public void actionShapeshifter(String card) {
        context.eval("js", "(function (card) {getCardById('FR51').performCardAction([card])})").executeVoid();
    }

    public void actionMirage(String card) {
        context.eval("js", "(function (card) {getCardById('FR52').performCardAction([card])})").executeVoid();
    }

    public void actionDoppelganger(String card) {
        context.eval("js", "(function (card) {getCardById('FR53').performCardAction([card])})").executeVoid();
    }
}
