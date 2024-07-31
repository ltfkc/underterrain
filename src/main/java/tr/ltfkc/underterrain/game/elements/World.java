package tr.ltfkc.underterrain.game.elements;

import tr.ltfkc.underterrain.engine.graphics.Renderer;

import java.util.HashSet;
import java.util.Set;

public class World extends Element {

    protected Set<Element> children;

    public World() {
        super();
        children = new HashSet<>();
    }

    public boolean add(Element element) {
        if (element.getParent() == null) {
            children.add(element);
            element.setParent(this);
            return true;
        }
        return false;
    }

    public boolean remove(Element element) {
        if (children.remove(element)) {
            element.setParent(null);
            return true;
        }
        return false;
    }

    @Override
    public void update(float delta, Element[] family) {
        for (Element element : children) {
            if (element.isEnabled()) {
                children.remove(element);
                element.update(delta, (Element[]) children.toArray());
                children.add(element);
            }
        }
    }

    @Override
    public void render(Renderer renderer) {
        for (Element element : children) {
            if (element.isEnabled())
                element.render(renderer);
        }
    }
}
